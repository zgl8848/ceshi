package com.campus.grid.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.entity.SysDict;
import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.admin.api.feign.RemoteDictService;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.AlarmDTO;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.Person;
import com.campus.grid.api.entity.emotion.mongo.MongoEmotionPerson;
import com.campus.grid.api.vo.AlarmVO;
import com.campus.grid.api.vo.SchoolVO;
import com.campus.grid.config.SchoolAndPortConfig;
import com.campus.grid.mapper.AlarmMapper;
import com.campus.grid.mapper.SaftyManagementMapper;
import com.campus.grid.mapper.SchoolMapper;
import com.campus.grid.service.AlarmService;
import com.campus.grid.util.InspectHiddenUtils;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, Alarm> implements AlarmService {

//	@Autowired
//	private SchoolServiceImpl schoolService;

	@Autowired
	private MongoTemplate mongoTemplate;

	private final RemoteDeptService remoteDeptService;

	private final RemoteDictService remoteDictService;

	private AlarmMapper alarmMapper;

	private SaftyManagementMapper saftyManagementMapper;
	
	private SchoolMapper schoolMapper;
	
	private  SchoolAndPortConfig maps;

	@Override
	public PageBean queryPage(Integer currentPage, Integer size, String schoolId, String date) {
		LocalDateTime startTime = InspectHiddenUtils.getFirstDayOfMonth(date);
		LocalDateTime endTime = InspectHiddenUtils.getLastDayOfMonth(date);

		String roleType = SecurityUtils.getUser().getRoleType();
		//String roleType = "2";
		Map<String, List<String>> map = getUserRole(roleType, schoolId);
		List<String> childDepts = map.get("childDepts");

		List<String> schoolIds = map.get("schoolIds");
		if (schoolIds!=null && schoolIds.size()>0){
			schoolId = schoolIds.get(0);
		}

//		Map<String, String> dict = getDict();//字典信息

		Integer startIndex = (currentPage - 1) * size;
		int total = alarmMapper.getTotal(schoolId, childDepts, roleType, startTime, endTime);
		List<AlarmVO> alarmList = alarmMapper.queryPage(startIndex, size, schoolId, childDepts, roleType, startTime, endTime);
		List<AlarmDTO> alarmDTOList = new ArrayList<>();
		if (alarmList != null && alarmList.size() > 0) {
			for (AlarmVO a : alarmList) {
				AlarmDTO alarmDTO = new AlarmDTO(a);
//				// 告警类型为黑名单告警时，查询对应黑名单信息
//				Integer dataType = Integer.valueOf(a.getDataType());
//				Person p = new Person();
//				if (dataType == 3) {
//					p = alarmMapper.findBlackPersonById(a.getAlarmMsg());
//				}
//				p.setAlarmMsg(a.getAlarmMsg());
//				alarmDTO.setPerson(p);
				alarmDTO.setSchoolName(a.getSchoolName());
//				alarmDTO.setAlarmType(dict.get(a.getAlarmType()));
				alarmDTO.setAlarmType(findLabelByType(a.getAlarmType(), "alarm_type"));
				alarmDTOList.add(alarmDTO);
			}
		}
		PageBean pageBean = new PageBean();
		pageBean.setCurrent(currentPage);
		double ceil = Math.ceil((total * 1.0) / size);
		int pages = (int) ceil;
		pageBean.setSize(size);
		pageBean.setPages(pages);
		pageBean.setTotal(total);
		pageBean.setRecords(alarmDTOList);
		return pageBean;
	}

	@Override
	public String findLabelByType(String value, String type) {
		return alarmMapper.findLabelByType(value, type);
	}

	@Override
	public boolean saveAlarmInfo(Alarm alarm) {
		try {
//			存储告警前根据告警的编号来获取对应的告警标题
			String alarmTitle = alarmMapper.getAlarmByValueAndType(alarm.getAlarmTitle(), alarm.getDataType());
			//alarm.setAlarmTitle(alarmTitle);
			return alarmMapper.insertAlarmInfo(alarm);
		} catch (Exception e) {
			log.error("存储警告信息出现异常", e);
			return false;
		}
	}

	@Override
	public String isHaveAlarm(Alarm alarm) {
		return alarmMapper.isHaveAlarm(alarm);
	}

	@Override
	public boolean saveBlackPerson(Person person) {
		return alarmMapper.saveBlackPerson(person);
	}


	/**
	 * 分页查询告警信息
	 *
	 * @param page 分页对象
	 * @param schoolId 学校ID
	 * @param title 告警标题
	 * @return
	 */
	@Override
	public IPage getAlarmPage(Page page, String schoolId, String title) {
		return  baseMapper.getAlarmPage(page, schoolId, title);
	}

	/**
	 * 根据告警模块 统计
	 * @param schoolId
	 * @return
	 */
	@Override
	public Map<Object,Object> statByModule(String schoolId, String date){
		LocalDateTime startTime = InspectHiddenUtils.getFirstDayOfMonth(date);
		LocalDateTime endTime = InspectHiddenUtils.getLastDayOfMonth(date);

        Map<Object,Object> map1 = null;
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<String, List<String>> map = getUserRole(roleType, schoolId);
		List<String> childDepts = map.get("childDepts");
		List<String> schoolIds = map.get("schoolIds");
		if (schoolIds!=null && schoolIds.size()>0){
			schoolId = schoolIds.get(0);
		}

		List<AlarmVO> list = baseMapper.statByModule(schoolId, childDepts, roleType, startTime, endTime);
		if (list!=null && list.size()>0){
            map1 = new HashMap<>();
			for (AlarmVO alarmVO : list){
				String module = alarmVO.getAlarmModule();
				//治安监控、校车安全、消防安全、厨房安全、危化品安全、学生行为
				if ("1".equals(module)){
					alarmVO.setModuleName("治安监控");
				}
				if ("2".equals(module)){
					alarmVO.setModuleName("校车安全");
				}
				if ("3".equals(module)){
					alarmVO.setModuleName("消防安全");
				}
				if ("4".equals(module)){
					alarmVO.setModuleName("厨房安全");
				}
				if ("5".equals(module)){
					alarmVO.setModuleName("危化品安全");
				}
				if ("6".equals(module)){
					alarmVO.setModuleName("学生行为");
				}
			}
            map1.put("records", list);
		}
		return map1;
	}

	/**
	 * 根据区域 统计
	 * @param date
	 * @return
	 */
	@Override
	public Map<Object, Object> statByAreaCode(String date){
		LocalDateTime startTime = InspectHiddenUtils.getFirstDayOfMonth(date);
		LocalDateTime endTime = InspectHiddenUtils.getLastDayOfMonth(date);

		Map<Object,Object> mp5=new HashMap<>();

		String schoolId = "";
        String roleType = SecurityUtils.getUser().getRoleType();
//		String roleType = "2";
		Map<String, List<String>> map = getUserRole(roleType, schoolId);
		List<String> childDepts = map.get("childDepts");
		List<String> schoolIds = map.get("schoolIds");
		if (schoolIds != null && schoolIds.size() > 0){
			schoolId = schoolIds.get(0);
		}

		List<AlarmVO> list = baseMapper.statByAreaCode(schoolId, childDepts, roleType, startTime, endTime);
		String cityCodeOfAreaCode=null;
		String provinceCodeOfAreaCode=null;
		if("2".equals(roleType)) {
			String schoolAreCode = saftyManagementMapper.findSchoolAreCode(schoolId);
			cityCodeOfAreaCode = saftyManagementMapper.findCityCodeOfAreaCode(schoolAreCode);
			provinceCodeOfAreaCode = saftyManagementMapper.findProvinceCodeOfAreaCode(schoolAreCode);
		}else if("1".equals(roleType)) {
			List<String> strings = saftyManagementMapper.selectCounty(childDepts);
			for (String string : strings) {
				String cityCodeOfAreaCodeNew = saftyManagementMapper.findCityCodeOfAreaCode(string);
				if(cityCodeOfAreaCodeNew!=null&&!cityCodeOfAreaCodeNew.equals("")) {
					cityCodeOfAreaCode=cityCodeOfAreaCodeNew;
				}
				String provinceCodeOfAreaCodeNew = saftyManagementMapper.findProvinceCodeOfAreaCode(string);
				if(provinceCodeOfAreaCodeNew!=null&&!provinceCodeOfAreaCodeNew.equals("")) {
					provinceCodeOfAreaCode=provinceCodeOfAreaCodeNew;
				}
			}
		}else if("0".equals(roleType)) {
			List<String> strings = saftyManagementMapper.selecAlltCounty();
			for (String string : strings) {
				String cityCodeOfAreaCodeNew = saftyManagementMapper.findCityCodeOfAreaCode(string);
				if(cityCodeOfAreaCodeNew!=null&&!cityCodeOfAreaCodeNew.equals("")) {
					cityCodeOfAreaCode=cityCodeOfAreaCodeNew;
				}
				String provinceCodeOfAreaCodeNew = saftyManagementMapper.findProvinceCodeOfAreaCode(string);
				if(provinceCodeOfAreaCodeNew!=null&&!provinceCodeOfAreaCodeNew.equals("")) {
					provinceCodeOfAreaCode=provinceCodeOfAreaCodeNew;
				}
			}
		}
		mp5.put("province_code",provinceCodeOfAreaCode);
		mp5.put("city_code",cityCodeOfAreaCode);
		if (list!=null && list.size()>0){
			mp5.put("records",list);
			if(cityCodeOfAreaCode!=null&&!cityCodeOfAreaCode.equals("")) {
				mp5.put("city_code", list.get(0).getCity());
			}
			if(provinceCodeOfAreaCode!=null&&!provinceCodeOfAreaCode.equals("")) {
				mp5.put("province_code", list.get(0).getProvince());
			}
		}
		return mp5;
	}

	/**
	 * 根据月份 统计
	 * @param date
	 * @return
	 */
	@Override
	public Map<Object, Object> statByMonth(String date){
		Random random = new Random();

		if (StringUtils.isNotBlank(date)){
			date = date.substring(0,4);
		}

		Map<Object, Object> map = null;

		String schoolId = "";
		String roleType = SecurityUtils.getUser().getRoleType();
//		String roleType = "0";
		Map<String, List<String>> idsMap = getUserRole(roleType, schoolId);
		List<String> childDepts = idsMap.get("childDepts");
		List<String> schoolIds = idsMap.get("schoolIds");
		if (schoolIds!=null && schoolIds.size()>0){
			schoolId = schoolIds.get(0);
		}

		Map<String, Integer> mmap = baseMapper.statByMonth(schoolId, childDepts, roleType, date);

		map = new HashMap<>();
		List<Map<String, Integer>> list1 = null;
		if(mmap!=null && mmap.size()>0){
			//排序
			Map<String, Integer> result = mmap.entrySet().stream()
					.sorted(Map.Entry.comparingByKey())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
							(oldValue, newValue) -> oldValue, LinkedHashMap::new));

			Map<String, Integer> map1;
			list1 = new ArrayList<>();
			for(Map.Entry<String, Integer> entry : result.entrySet()){
				map1 = new HashMap<>();
				Integer month = Integer.valueOf(String.valueOf(entry.getKey()));
				Integer count = Integer.valueOf(String.valueOf(entry.getValue()));
				map1.put("month", month);
				map1.put("count", count);
				list1.add(map1);
			}
		}

		List<Map<String, Object>> list = new ArrayList<>();

		Map<String, Object> mapp1 = new HashMap<>();
		mapp1.put("type","1");
		mapp1.put("name", "治安监控");
		mapp1.put("values", list1);
		list.add(mapp1);

		Map<String, Object> mapp2 = new HashMap<>();
		List<Map<String, Integer>> list2 = setValues(random);
		mapp2.put("type","2");
		mapp2.put("name", "校车安全");
		mapp2.put("values", list2);
		list.add(mapp2);

		Map<String, Object> mapp3 = new HashMap<>();
		List<Map<String, Integer>> list3 = setValues(random);
		mapp3.put("type","3");
		mapp3.put("name", "消防安全");
		mapp3.put("values", list3);
		list.add(mapp3);

		Map<String, Object> mapp4 = new HashMap<>();
		List<Map<String, Integer>> list4 = setValues(random);
		mapp4.put("type","4");
		mapp4.put("name", "食品安全");
		mapp4.put("values", list4);
		list.add(mapp4);

		map.put("records", list);

		return map;
	}


	private List<Map<String, Integer>> setValues(Random random){

		Map<String, Integer> map;
		List<Map<String, Integer>> list = new ArrayList<>();
		for(int i=1; i<13; i++){
			map = new HashMap<>();
			map.put("month", i);
			map.put("count", random.nextInt(30000));
			list.add(map);
		}
		return list;
	}


	/**
	 * 获取登录用户所属的数据权限
	 * @param roleType
	 * @param schoolId
	 * @return
	 */
    private Map<String, List<String>> getUserRole(String roleType, String schoolId){
		Map<String, List<String>> map = new HashMap<>();
		List<String> schoolIds;
		List<String> childDepts;

		if("2".equals(roleType)){//学校用户
			schoolIds = new ArrayList<>();
			if (StringUtils.isBlank(schoolId)){
				schoolId = SecurityUtils.getUser().getSchoolId();
			}
			schoolIds.add(schoolId);
			map.put("schoolIds", schoolIds);
		}else if ("1".equals(roleType)){//教育局用户
			childDepts = remoteDeptService.listChildDepts().getData();
			map.put("childDepts", childDepts);
		}
		return map;
	}

    /**
     * 存储表情识别出来的人脸信息
     * @param emotionPerson
     * @return
     */

    @Override
    public boolean saveFaceInfo(MongoEmotionPerson emotionPerson) {
		mongoTemplate.save(emotionPerson);
        return true;
    }

	/**
	 * 获取告警类型的字典信息
	 * @return
	 */
	private Map<String, String> getDict(){
		Map<String, String> map = null;
		List<SysDict> dictList = remoteDictService.list("alarm_type").getData();
		if (dictList!=null && dictList.size()>0){
			map = new HashMap<>();
			for (SysDict dict : dictList){
				map.put(dict.getValue(), dict.getLabel());
			}
		}
		return  map;
	}

	@Override
	public String getBase64Image(String schoolId, String url)throws Exception {
		SchoolVO school = schoolMapper.findSchoolById(schoolId);
		String schoolCode = school.getSchoolCode();
		String[] ipAndKey = getIpAmdPort(schoolCode);
		ArtemisConfig.host = ipAndKey[0]; // artemis网关服务器ip端口
		ArtemisConfig.appKey = ipAndKey[1];  // 秘钥appkey
		ArtemisConfig.appSecret = ipAndKey[2];// 秘钥appSecret
		//STEP2：设置OpenAPI接口的上下文
		final String ARTEMIS_PATH = "/artemis";
		//STEP3：设置接口的URI地址
		final String previewURLsApi = ARTEMIS_PATH + "/api/frs/v1/application/picture";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
			}
		};
		//STEP4：设置参数提交方式
		String contentType = "application/json";
		// STEP5：组装请求参数
		JSONObject body = new JSONObject();
		body.put("url", url);
		//STEP6：调用接口
		String result = ArtemisHttpUtil.doPostStringArtemis(path, body.toJSONString(), null, null, contentType, null);// post请求application/json类型参数
		JSONObject jo = JSONObject.parseObject(result);
		return jo.getString("data");
	}
	
	/**获取学校订阅的Isc平台地址
	 * 
	 * @param schoolCodeId
	 * @return
	 */
	public String[] getIpAmdPort(String schoolCodeId) {
		Map<String, String> schoolCodeMap = maps.getSchoolCode();
		String[] split = schoolCodeMap.get(schoolCodeId).split(";");
		return split;
	}
}