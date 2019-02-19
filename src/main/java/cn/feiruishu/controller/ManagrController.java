package cn.feiruishu.controller;

import cn.feiruishu.pojo.FileForm;
import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.Product;
import cn.feiruishu.pojo.ProductForm;
import cn.feiruishu.service.FileService;
import cn.feiruishu.service.ProductService;
import cn.feiruishu.util.Datetime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ManagrController {

	@Autowired
	private ProductService<Product> productService;

	@Autowired
	private FileService<FileForm> fileService;

	/**
	 * 显示产品管理页面
	 * @return
	 */
	@GetMapping("")
	public String index() {
		return "managr";
	}

	/**
	 * 显示产品管理添加页面
	 * @return
	 */
	@GetMapping("showProduct")
	public String showProduct(int id, Model m) {
		if(id <= 0){
			m.addAttribute("product",new Product());
		}else{
			LayUiResult<ProductForm> lur = productService.findData(id);
			m.addAttribute("product",lur.getT());
		}

		return "product";
	}

	/**
	 * 显示产品管理添加页面
	 * @return
	 */
	@GetMapping("product")
	@ResponseBody
	public LayUiResult<Product> getProduct(Integer id) {

		return productService.findData(id);
	}

	/**
	 * 查询图片产品
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("products")
	@ResponseBody
	public LayUiResult<Product> findProducts(int page, int limit){

		return productService.findDatas(page, limit);
	}

	/**
	 * 添加产品
	 * @return
	 */
	@PostMapping("addProduct")
	@ResponseBody
	public LayUiResult<Product> addProduct(@RequestParam  Map<String,Object> reqMap) {
		Product p = new Product();
		if(reqMap.get("id")!=null && reqMap.get("id").toString().length()>0){
			int id= Integer.parseInt(reqMap.get("id").toString());
			p = (Product) productService.findData(id).getT();
			parseRequest(reqMap, p);
			return productService.modifyData(p);
		}else {
			parseRequest(reqMap, p);
			return productService.addData(p);
		}
	}

	/**
	 * 添加产品
	 * @return
	 */
	@PostMapping("showImgs")
	@ResponseBody
	public LayUiResult<Product> getFilesByProductId(@RequestParam  Map<String,Object> reqMap) {
		int pro_id = Integer.parseInt(reqMap.get("prop_id").toString());
		return fileService.findData(1,100,pro_id);
	}

	private void parseRequest(@RequestParam Map<String, Object> reqMap, Product p) {
		p.setName(reqMap.get("name").toString());
		p.setDes(reqMap.get("des").toString());
		p.setPic("");
		p.setTopshow(reqMap.get("topshow").toString());
		p.setStatus(1);
		p.setKey_word("");
		p.setAtime(Datetime.getCurrentDatetimeByString());
		p.setMtime(Datetime.getCurrentDatetimeByString());
	}



	/**
	 * 删除产品的图片
	 * @return
	 */
	@RequestMapping("deleteProduct")
	@ResponseBody
	public String deleteProduct(int id){

		productService.deleteData(id);
		return "删除成功。";
	}

	/**
	 * 删除产品的图片
	 * @return
	 */
	@RequestMapping("deleteImg")
	@ResponseBody
	public String deleteImg(int imgId){

		fileService.deleteData(imgId);
		return "删除成功。";
	}
}
