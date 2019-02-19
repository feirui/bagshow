package cn.feiruishu.controller;

import cn.feiruishu.pojo.FileForm;
import cn.feiruishu.pojo.LayUiResult;
import cn.feiruishu.pojo.Product;
import cn.feiruishu.service.FileService;
import cn.feiruishu.service.ProductService;
import cn.feiruishu.util.Datetime;
import cn.feiruishu.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.server.InactiveGroupException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class FileController {

	@Value("${storagepath}")
	private String storagepath;

	@Autowired
	private FileService<FileForm> fileService;


	@RequestMapping(value="/uploadimg", method = RequestMethod.POST)
	@ResponseBody
	public LayUiResult<FileForm> uploadImg(@RequestParam("file") MultipartFile file,
										  HttpServletRequest request) {
		String objectID = request.getParameter("object_id");
		int object_id  = -1;
		if(objectID!=null && objectID.length()>0) {
			object_id = Integer.parseInt(request.getParameter("object_id"));
		}
		if(object_id <0){
			LayUiResult lur = new LayUiResult();
			lur.setMsg("请先保存信息，再上传图片");
			lur.setCode(1);
			return lur;
		}
		String contentType = file.getContentType();   //图片文件类型
		String fileName = file.getOriginalFilename();  //图片名字
		Datetime now = new Datetime();
		//文件存储相对路径
		String filePath = storagepath +"fileData"+File.separator+now.getYear()+"-"+now.getMonth() +File.separator;
		try {
			FileUtil.uploadFile(file.getBytes(), filePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}


		FileForm f = new FileForm();
		f.setName(fileName);
		f.setObject_id(object_id);
		f.setType(1);
		f.setFileurl(filePath+fileName);
		f.setTime(Datetime.getCurrentDatetimeByString());
		return fileService.addData(f);
	}


	@RequestMapping("/download")
	public String downloadFile(HttpServletRequest request,
							   HttpServletResponse response) throws UnsupportedEncodingException {

		String fileName = request.getParameter("fileName"); //下载的文件名

		// 如果文件名不为空，则进行下载
		if (fileName != null) {
			//设置文件路径
			File file = new File(URLDecoder.decode(fileName,"UTF-8"));

			// 如果文件名存在，则进行下载
			if (file.exists()) {
				response.setHeader("content-type", "application/octet-stream");
				response.setContentType("images/"+ fileName.substring(fileName.lastIndexOf(".")+1));
				// 下载文件能正常显示中文
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
				// 实现文件下载
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					System.out.println("Download the song successfully!");
				}
				catch (Exception e) {
					System.out.println("Download the song failed!");
				}
				finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null;
	}

	@RequestMapping("/downloadById")
	public String downloadFileById(HttpServletRequest request,
							   HttpServletResponse response) throws UnsupportedEncodingException {

		String id = request.getParameter("id");

		if (id != null && id.length()>0) {

			LayUiResult<FileForm> fileForms = fileService.findData(Integer.parseInt(id));
			String fileName = fileForms.getT().getFileurl();
			File file = new File(fileName);

			if (file.exists()) {
				response.setHeader("content-type", "application/octet-stream");
				response.setContentType("images/"+ fileName.substring(fileName.lastIndexOf(".")+1));
				// 下载文件能正常显示中文
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
				// 实现文件下载
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					System.out.println("Download the song successfully!");
				}
				catch (Exception e) {
					System.out.println("Download the song failed!");
				}
				finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null;
	}

}
