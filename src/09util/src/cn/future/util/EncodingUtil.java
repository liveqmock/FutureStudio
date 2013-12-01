package cn.future.util;

import javax.servlet.http.HttpServletRequest;

public class EncodingUtil {
	/**
	 * 获取编码字符集
	 * @param request
	 * @param response
	 * @return String
	 */
	public static String getCharacterEncoding(HttpServletRequest request) {
		
		if(null == request ) {
			return "UTF-8";
		}
		
		String enc = request.getCharacterEncoding();

		if(null == enc || "".equals(enc)) {
			enc = "UTF-8";
		}
		
		return enc;
	}
}
