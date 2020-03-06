package com.situ.scrm.sys.upload.service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.upload.domain.UploadParam;

public interface UploadService {
	LayResult doUploadFile(UploadParam uploadParam);
}
