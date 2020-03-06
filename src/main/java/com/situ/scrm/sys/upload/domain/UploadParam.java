package com.situ.scrm.sys.upload.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadParam implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int UPLOAD_TYPE_USER_AVATAR = 1;//用户头像
	public static final int UPLOAD_TYPE_CUSTOMER_FILE = 2;//客户附件
	private MultipartFile uploadFile;
	private Integer uploadType;//上传的文件类型
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public Integer getUploadType() {
		return uploadType;
	}
	public void setUploadType(Integer uploadType) {
		this.uploadType = uploadType;
	}
	
	
}
