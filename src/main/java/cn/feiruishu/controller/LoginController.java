package cn.feiruishu.controller;

import cn.feiruishu.pojo.User;
import cn.feiruishu.service.ProductService;
import cn.feiruishu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * 登录
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 登录
	 * @return
	 */
	@GetMapping("register")
	@ResponseBody
	public String register(String loginname,String pwd) {
		User user = userService.findUserByUsername(loginname);
		if(user!=null){
			if( pwd.equals(user.getPassword())){
				return "succes";
			}else{
				return "failed";
			}
		}else{
			return "failed";
		}
	}

	/**
	 * 显示登录页面
	 * @return
	 */
	@GetMapping("showRegister")
	public String showRegister() {
		return "register";
	}


	public User getUser() { //为了session从获取用户信息,可以配置如下
		User user = new User();
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth.getPrincipal() instanceof UserDetails) user = (User) auth.getPrincipal();
		return user;
	}

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

}
