package cn.feiruishu.controller;

import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.Product;
import cn.feiruishu.pojo.ProductForm;
import cn.feiruishu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private ProductService productService;

	/**
	 * 显示首页
	 * @return
	 */
	@GetMapping("")
	public String index() {
		return "index";
	}

	@GetMapping("about")
	public String about() {
		return "about";
	}


	@RequestMapping("topproducts")
	@ResponseBody
	public LayUiResult<Product> findTopProducts(int page, int limit,String topshow){

		return productService.findTopDatas(page, limit,topshow);
	}

	@RequestMapping("countTopproducts")
	@ResponseBody
	public int countTopProducts(String topshow){

		return productService.countTopDatas(topshow);
	}

}
