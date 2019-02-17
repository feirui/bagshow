package cn.feiruishu.service;

import cn.feiruishu.dao.FileDao;
import cn.feiruishu.dao.ProductDao;
import cn.feiruishu.pojo.FileForm;
import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImp implements FileService {


	/**
	 *
	 */
	@Autowired
	private FileDao fileDAO;
	
	@Override
	public LayUiResult findDatas(int page, int limit) {
		LayUiResult<FileForm> result = new LayUiResult<>();
		result.setData(fileDAO.findAllFile((page-1)*limit, page*limit));
		result.setCount(fileDAO.countFileByPage());
		result.setMsg("查询成功。");
		return result;
	}

	@Override
	public LayUiResult findData(Integer id) {
		LayUiResult<FileForm> result = new LayUiResult<>();
		result.setT(fileDAO.findFileById(id));
		result.setCount(1);
		return result;
	}

	@Override
	public LayUiResult findData(int page, int limit, int object_id) {
		LayUiResult<FileForm> result = new LayUiResult<>();
		result.setData(fileDAO.findFilesByProID(object_id));
		//result.setCount(productDAO.countProductByPage(status));
		return result;
	}

	@Override
	public LayUiResult addData(Object o) {
		FileForm f = (FileForm) o;
		fileDAO.addFile(f);
		LayUiResult result = new LayUiResult();
		result.setT(f);
		result.setMsg("添加成功。");
		return result;
	}

	@Override
	public LayUiResult modifyData(Object o) {
		FileForm f = (FileForm) o;
		fileDAO.addFile(f);
		LayUiResult result = new LayUiResult();
		result.setCode(0);
		result.setMsg("修改成功");
		result.setT(f);
		result.setCount(1);
		return result;
	}

	@Override
	public int deleteData(int imgId) {
		fileDAO.deleteFile(imgId);
		return imgId;
	}


}
