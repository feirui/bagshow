package cn.feiruishu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.Demo;
import cn.feiruishu.service.DemoService;

@Controller
@RequestMapping("user")
public class DemoThymeleaf {

	@Autowired
	private DemoService demoService;
	
	@RequestMapping("list")
	public String list() {
		return "user";
	}
	
	@RequestMapping("users")
	@ResponseBody
	public LayUiResult<Demo> users(int page, int limit){
		return demoService.findUserByPage(page, limit);
	}
}
