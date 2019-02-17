package cn.feiruishu.service;

import cn.feiruishu.dao.UserDao;
import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.User;
import cn.feiruishu.util.WebSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private UserDao userDAO;
	
	@Override
	public LayUiResult findDataByPage(int page, int limit) {
		LayUiResult<User> result = new LayUiResult<>();
		result.setData(userDAO.findUserByPage((page-1)*limit, page*limit));
		result.setCount(userDAO.countUserByPage());
		return result;
	}

	@Override
	public LayUiResult addData(Object o) {
		return null;
	}

	@Override
	public LayUiResult modifyData(Object o) {
		return null;
	}

	@Override
	public LayUiResult deleteData(Object o) {
		return null;
	}

	@Override
	public User findUserByUsername(String loginname) {
		return userDAO.findUserByUsername(loginname);
	}


}
