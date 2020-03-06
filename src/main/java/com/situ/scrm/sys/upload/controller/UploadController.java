package com.situ.scrm.sys.upload.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.upload.domain.UploadParam;
import com.situ.scrm.sys.upload.service.UploadService;
@RestController
@RequestMapping("/upload")
public class UploadController implements Serializable {

	private static final long serialVersionUID = 1L;
    @Autowired 
	private UploadService uploadService ;
    @PostMapping
    public LayResult doUploadFile(UploadParam uploadParam) {
    	
    	return uploadService.doUploadFile(uploadParam);
    	
    }
    
}
