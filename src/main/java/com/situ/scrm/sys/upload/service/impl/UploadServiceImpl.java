package com.situ.scrm.sys.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.upload.domain.UploadParam;
import com.situ.scrm.sys.upload.service.UploadService;
import com.situ.scrm.sys.user.dao.UserDao;
@Service
public class UploadServiceImpl implements UploadService {
	@Value("${spring.servlet.multipart.location}")
	private String fileLocation;// 上传文件的路径#配置的路径
	@Autowired
	private UserDao userDao;
	private static Map<Integer, String> FOLDER_MAP = new HashMap<Integer,String>();
	private static String SEPARETER = "/";
	static {
	FOLDER_MAP.put(UploadParam.UPLOAD_TYPE_CUSTOMER_FILE,"filescrm/customer/");

	}
	
	
	@Override
	public LayResult doUploadFile(UploadParam uploadParam) {
		//需要保存到数据库中的文件的路径
		String filePath = null;
		MultipartFile uploadFile = uploadParam. getUploadFile();
		if (!uploadFile.isEmpty()) {
			//上传来的文件的类别(用户头像客户附件等等)
			Integer uploadType = uploadParam.getUploadType();
			//得到写出文件的文件夹名称(不同类别的文件写出到不同的文件夹下面)
			String folder = FOLDER_MAP.get(uploadType);
			//上传文件的名称
			String fileName = uploadFile.getOriginalFilename();
			//拿到文件的后缀
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			//开始构建写出文件的相对路径
			StringBuffer filePathBuffer = new StringBuffer(folder) ;
			filePathBuffer.append(Calendar.getInstance().getTimeInMillis()).append(suffix);
			//得到写出文件的的相对路径，此路径需要保存到数据库中
			filePath = filePathBuffer.toString();
			//开始构建写出文件的绝对路径( 是需要带有盘符)
			String fullFilePath = fileLocation + filePath;
			File dest = new File(fullFilePath);
			//此处判断了一下如果需要写出文件的文件夹不存在，则生成相关的文件夹
			File parentFile = dest.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			try {
				//通过MultipartFile的trainsferTo方法将文件写出。|
				uploadFile.transferTo(dest);
			} catch (IllegalStateException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			switch (uploadType) {
			case UploadParam.UPLOAD_TYPE_CUSTOMER_FILE:
				
				break;
			default:
				break;
			}
			
		}
		return new LayResult(0, "", filePath);
	}






}
