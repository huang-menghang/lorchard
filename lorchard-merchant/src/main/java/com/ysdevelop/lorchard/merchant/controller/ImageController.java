package com.ysdevelop.lorchard.merchant.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.QiniuUtil;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Result<String> upload(@RequestParam("imageFile") MultipartFile files, HttpServletRequest request) {

		String imagePath = null;
		// 文件保存路径
		String filePath = request.getSession().getServletContext().getRealPath("/upload") + File.separator
				+ QiniuUtil.renamePic(files.getOriginalFilename());
		// 转存文件
		try {
			// 保存至服务器
			File file = new File((filePath));
			if (!file.exists()) {
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			files.transferTo(file);
			// 上传七牛云服务器
			imagePath = QiniuUtil.qiniuUpload(filePath);
			if (imagePath.contains("error")) {
				throw new WebServiceException(CodeMsg.UPLOAD_FAILED);
			}
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.successData(imagePath);

	}

}
