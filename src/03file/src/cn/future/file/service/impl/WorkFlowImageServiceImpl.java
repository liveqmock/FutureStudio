package cn.future.file.service.impl;

import java.io.InputStream;

import cn.future.file.service.WorkFlowImageService;
import cn.future.common.service.impl.ConfigHelperImpl;

public class WorkFlowImageServiceImpl implements WorkFlowImageService {

	@Override
	public InputStream findWorkFlowImageByKey(String key) {
		// TODO Auto-generated method stub
		String jpdlPath=ConfigHelperImpl.getProperty("jpdlPath", "");
		return this.getClass().getResourceAsStream(jpdlPath+key+".png");
	}

}
