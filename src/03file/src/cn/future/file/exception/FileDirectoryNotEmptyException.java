package cn.future.file.exception;

import cn.future.common.exception.BaseException;


public class FileDirectoryNotEmptyException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5896455717028905440L;

	public FileDirectoryNotEmptyException(String e){
		super(e);
	}
}
