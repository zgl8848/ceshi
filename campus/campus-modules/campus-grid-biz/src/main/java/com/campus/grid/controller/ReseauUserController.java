package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.grid.api.entity.ReseauUser;
import com.campus.grid.service.ReseauUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网格分配
 * </p>
 *
 * @author hu
 * @date 2019-01-02 16:17:17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/reseauuser")
public class ReseauUserController {

	private final ReseauUserService reseauUserService;


	/**
	 * 通过id查询
	 *
	 * @param reseauId id
	 * @return R
	 */
	@GetMapping("/{reseauId}")
	public R getById(@PathVariable("reseauId") String reseauId) {
		return new R<>(reseauUserService.getById(reseauId));
	}

	/**
	 * 新增
	 *
	 * @param reseauUser
	 * @return R
	 */
	@SysLog("新增")
	@PostMapping
	public R save(@RequestBody ReseauUser reseauUser) {
		return new R<>(reseauUserService.save(reseauUser));
	}

	/**
	 * 修改
	 *
	 * @param reseauUser
	 * @return R
	 */
	@SysLog("修改")
	@PutMapping
	public R updateById(@RequestBody ReseauUser reseauUser) {
		return new R<>(reseauUserService.updateById(reseauUser));
	}

	/**
	 * 通过id删除
	 *
	 * @param reseauId id
	 * @return R
	 */
	@SysLog("删除")
	@DeleteMapping("/{reseauId}")
	public R removeById(@PathVariable String reseauId) {
		return new R<>(reseauUserService.removeById(reseauId));
	}

	/**
	 * 通过id查询
	 *
	 * @param reseauId 网格ID
	 * @return R
	 */
	@GetMapping("/info/{reseauId}/{type}")
	public R getInfo(@PathVariable("reseauId") String reseauId, @PathVariable("type") Integer type) {
		return new R<>(reseauUserService.getInfo(reseauId, type));
	}


}
