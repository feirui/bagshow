package cn.feiruishu.service;

import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 数据通用接口
 */
public interface UserService<D>  {

	/**
     * 通过分页查询数据
	 * @param page
     * @param limit
     * @return
     */
	public LayUiResult findDataByPage(int page, int limit);

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
	public LayUiResult deleteData(D d);

	public User findUserByUsername(String loginname);
	
}
