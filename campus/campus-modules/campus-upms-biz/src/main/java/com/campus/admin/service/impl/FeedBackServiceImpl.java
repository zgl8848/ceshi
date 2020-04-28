package com.campus.admin.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.entity.FeedBack;
import com.campus.admin.mapper.SysFeedBackMapper;
import com.campus.admin.service.FeedBackService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class FeedBackServiceImpl extends ServiceImpl<SysFeedBackMapper, FeedBack> implements FeedBackService {

	private SysFeedBackMapper FeedBackMapper;

	@Override
	public void createFeedBack(FeedBack feedBack) {
		FeedBackMapper.createFeedBack(feedBack);
	}

	@Override
	public FeedBack selectOne(String feedback_id) {
		return FeedBackMapper.selectOne(feedback_id);
	}

	@Override
	public boolean save(FeedBack entity) {
		return false;
	}

	@Override
	public boolean saveBatch(Collection<FeedBack> entityList, int batchSize) {
		return false;
	}

	@Override
	public boolean saveOrUpdateBatch(Collection<FeedBack> entityList, int batchSize) {
		return false;
	}


	@Override
	public boolean removeByMap(Map<String, Object> columnMap) {
		return false;
	}

	@Override
	public boolean remove(Wrapper<FeedBack> queryWrapper) {
		return false;
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return false;
	}


	@Override
	public boolean update(FeedBack entity, Wrapper<FeedBack> updateWrapper) {
		return false;
	}

	@Override
	public boolean updateBatchById(Collection<FeedBack> entityList, int batchSize) {
		return false;
	}

	@Override
	public boolean saveOrUpdate(FeedBack entity) {
		return false;
	}

	@Override
	public FeedBack getById(Serializable id) {
		return null;
	}

	@Override
	public Collection<FeedBack> listByIds(Collection<? extends Serializable> idList) {
		return null;
	}

	@Override
	public Collection<FeedBack> listByMap(Map<String, Object> columnMap) {
		return null;
	}

	@Override
	public FeedBack getOne(Wrapper<FeedBack> queryWrapper, boolean throwEx) {
		return null;
	}

	@Override
	public Map<String, Object> getMap(Wrapper<FeedBack> queryWrapper) {
		return null;
	}

	@Override
	public int count(Wrapper<FeedBack> queryWrapper) {
		return 0;
	}

	@Override
	public List<FeedBack> list(Wrapper<FeedBack> queryWrapper) {
		return null;
	}

	/*@Override
	public IPage<FeedBack> page(IPage<FeedBack> page, Wrapper<FeedBack> queryWrapper) {
		return baseMapper.selectPage(page, queryWrapper);
	}*/

	@Override
	public List<Map<String, Object>> listMaps(Wrapper<FeedBack> queryWrapper) {
		return null;
	}

	@Override
	public List<Object> listObjs(Wrapper<FeedBack> queryWrapper) {
		return null;
	}

	@Override
	public IPage<Map<String, Object>> pageMaps(IPage<FeedBack> page, Wrapper<FeedBack> queryWrapper) {
		return null;
	}
}
