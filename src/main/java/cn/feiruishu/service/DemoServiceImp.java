package cn.feiruishu.service;

import cn.feiruishu.dao.DemoDao;
import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImp implements DemoService {

	@Autowired
	private DemoDao demoDao;
	
	@Override
	public LayUiResult findUserByPage(int page, int limit) {
		LayUiResult<Demo> result = new LayUiResult<>();
		result.setData(demoDao.findUserByPage((page-1)*limit, page*limit));
		result.setCount(demoDao.countUserByPage());
		return result;
	}

}
