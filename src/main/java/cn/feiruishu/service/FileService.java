package cn.feiruishu.service;

import cn.feiruishu.pojo.LayUiResult;

/**
 * 数据通用接口
 */
public interface FileService<D> {

	/**
	 * 通过分页查询数据
	 * @param page
	 * @param limit
	 * @return
	 */
	public LayUiResult findDatas(int page, int limit);

	/**
	 * 查询单个产品
	 * @return
	 */
	public LayUiResult findData(Integer id);
	/**
	 * 通过分页查询数据
	 * @param page
	 * @param limit
	 * @return
	 */
	public LayUiResult findData(int page, int limit, int pro_id);

	/**
	 * 添加数据
	 * @return
	 */
	public LayUiResult addData(D d);

	/**
	 * 修改数据
	 * @return
	 */
	public LayUiResult modifyData(D d);

	/**
	 * 删除数据
	 * @return
	 */
	public int deleteData(int d);
	
}
