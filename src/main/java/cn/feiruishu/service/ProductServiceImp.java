package cn.feiruishu.service;

import cn.feiruishu.dao.FileDao;
import cn.feiruishu.dao.ProductDao;
import cn.feiruishu.pojo.FileForm;
import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.Product;
import cn.feiruishu.pojo.ProductForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp  implements ProductService {


	/**
	 *
	 */
	@Autowired
	private ProductDao productDAO;

	@Autowired
	private FileDao fileDAO;
	
	@Override
	public LayUiResult findDatas(int page, int limit) {
		LayUiResult<ProductForm> result = new LayUiResult<>();

		result.setCount(productDAO.countProductByPage());
		List<Product> productList = productDAO.findAllProduct((page-1)*limit, page*limit);
		List<ProductForm> productFormList = new ArrayList<>(productList.size());
		for(Product p : productList	){
			ProductForm pf = new ProductForm();
			BeanUtils.copyProperties(p,pf);
			List<FileForm> fileList = fileDAO.findFilesByProID(p.getId());
			if(fileList.size()>0) {
				pf.setFileList(fileList);
				pf.setMainPic(fileList.get(0).getFileurl());
			}
			productFormList.add(pf);
		}
		result.setData(productFormList);
		result.setMsg("查询成功。");
		return result;
	}

	@Override
	public LayUiResult findData(Integer id) {
		LayUiResult<ProductForm> result = new LayUiResult<>();
		Product p = productDAO.findProductById(id);
		if(p!=null) {
			ProductForm pf = new ProductForm();
			BeanUtils.copyProperties(p, pf);
			pf.setFileList(fileDAO.findFilesByProID(p.getId()));
			result.setT(pf);
			result.setMsg("查询成功");
		}else{
			result.setMsg("没有查询到数据");
		}

		result.setCount(1);
		return result;
	}

	@Override
	public LayUiResult findTopDatas(int page, int limit, String topshow) {
		LayUiResult<ProductForm> result = new LayUiResult<>();
		result.setCount(productDAO.countTopProductByPage(topshow));
		List<Product> productList = productDAO.findProduct((page-1)*limit, limit,topshow);
		List<ProductForm> productFormList = new ArrayList<>(productList.size());
		for(Product p : productList	){
			ProductForm pf = new ProductForm();
			BeanUtils.copyProperties(p,pf);
			List<FileForm> fileList = fileDAO.findFilesByProID(p.getId());
			if(fileList.size()>0) {
				pf.setFileList(fileList);
				pf.setMainPic(fileList.get(0).getFileurl());
			}
			productFormList.add(pf);
		}
		result.setData(productFormList);
		return result;
	}

	@Override
	public int countTopDatas(String topshow) {
		return productDAO.countTopProductByPage(topshow);
	}

	@Override
	public LayUiResult addData(Object o) {
		Product p = (Product) o;
		productDAO.addProduct(p);
		LayUiResult result = new LayUiResult();
		result.setT(p);
		result.setMsg("添加成功。");
		return result;
	}

	@Override
	public LayUiResult modifyData(Object o) {
		Product p = (Product) o;
		productDAO.addProduct(p);
		LayUiResult result = new LayUiResult();
		result.setCode(0);
		result.setMsg("修改成功");
		result.setT(p);
		result.setCount(1);
		return result;
	}

	@Override
	public int deleteData(Integer id) {
		productDAO.deleteProduct(id);
		return id;
	}


}
