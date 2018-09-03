package com.ysdevelop.lorchard.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiGoodsCategoryDao {
	/**
	 * 获取所有分类
	 * @param queryMap
	 * @return
	 */
	List<GoodsCategoryVo> list(@Param(value = "queryMap") Map<String, String> queryMap);
	
	/**
	 * 获取所有当前分类下的分类
	 * @param parentId
	 * @return
	 */
	List<GoodsCategoryVo> listByParentId(@Param(value = "parentId")Long parentId);
	
	/**
	 * 数据库函数调用
	 * @param rootId
	 */
	void callTreeProcedure(Integer rootId);
	
	/**
	 * 获取所有顶级分类
	 * @return
	 */
	List<GoodsCategoryVo> listParent();
	
	/**
	 * 添加分类
	 * @param category
	 * @return
	 */
	Integer add(GoodsCategoryVo category);
	
	/**
	 * 获取分类
	 * @param id
	 * @return
	 */
	GoodsCategoryVo getById(Long id);
	
	/**
	 * 更新分类
	 * @param category
	 * @return
	 */
	Integer update(GoodsCategoryVo category);
	
	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	Integer deleteById(Long id);
}
