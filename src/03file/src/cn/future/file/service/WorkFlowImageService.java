package cn.future.file.service;

import java.io.InputStream;

public abstract interface WorkFlowImageService {
	/**
	 * 通过KEY的名称获取流程定义的图片
	 * @param key
	 * @return
	 */
	public InputStream findWorkFlowImageByKey(String key);
}
