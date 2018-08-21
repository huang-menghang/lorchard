package com.ysdevelop.lorchard.shiro.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;
/**
 * 
 * @author OldHuang
 *
 * @Package com.ysdevelop.oa.shiro.core
 *
 * @Description: 修改ShiroHttpServletResponse,删除Response url中jessionId
 *
 * @date 2017年11月22日
 * 
 * @version 
 *
 */
public class ApplicationShiroHttpServletResponse extends ShiroHttpServletResponse  {

	public ApplicationShiroHttpServletResponse(HttpServletResponse wrapped, ServletContext context, ShiroHttpServletRequest request) {
		super(wrapped, context, request);
	}
	
	 @Override
	protected String toEncoded(String url, String sessionId) {
		 if ((url == null) || (sessionId == null))
	            return (url);

	        String path = url;
	        String query = "";
	        String anchor = "";
	        int question = url.indexOf('?');
	        if (question >= 0) {
	            path = url.substring(0, question);
	            query = url.substring(question);
	        }
	        int pound = path.indexOf('#');
	        if (pound >= 0) {
	            anchor = path.substring(pound);
	            path = path.substring(0, pound);
	        }
	        StringBuilder sb = new StringBuilder(path);
	        sb.append(anchor);
	        sb.append(query);
	        return (sb.toString());
	}

}
