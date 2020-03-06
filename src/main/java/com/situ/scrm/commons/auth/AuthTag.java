package com.situ.scrm.commons.auth;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.situ.scrm.utils.AppConfig;



/**
 * @ClassName:AuthTag
 * @Description:(权限管理的自定义标签)
 */
public class AuthTag extends SimpleTagSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SEPARATOR = "/";
	// 判断传入的值(实际为URL)是否在用户的权限列表中存在,如果存在则显示标签内的内容,否则不显示(一般为链接或是动作按钮)
	private String url;// 需要检测的URL
	private String method;// 需要检测的method方法

	/**
	 * @Title: doTag
	 * @Description:(标签渲染时调用此方法)
	 * @throws JspException
	 * @throws IOException
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// 获取HttpSession对象
		HttpSession session = ((PageContext) this.getJspContext()).getSession();
		// 在SimpleTagSupport类中存在一个getJspBody()方法,此方法返回的就是一个Fragment对象,利用此对象的invoke()方法即可完成标签体内容的输出
		JspFragment jspFragment = getJspBody();
		// 判断是否满足显示标签内的内容,如果true,则显示自定义标签中的内容。否则不显示。
		if (checkInvoke(session)) {
			jspFragment.invoke(null);
		}
	}

	/**
	 * @Title: checkInvoke
	 * @Description:(判断是否满足显示标签内的内容)
	 * @param session HttpSession对象
	 * @return
	 */
	private Boolean checkInvoke(HttpSession session) {
		Object objectResourceMap = session.getAttribute(AppConfig.SESSION_RESOURCE_MAP_ROLE);
	    if(objectResourceMap!=null) {
	    	Map<String,Set<String>> actionInfoMap = (Map<String,Set<String>>)objectResourceMap;
	    
	       if(actionInfoMap!=null) {
	    	   if(url!=null) {
	    		   String checkUrl = url.trim();
	    		   if (url.startsWith(SEPARATOR)) {
	    			   checkUrl = url.replaceFirst (SEPARATOR,"");
	    		   }
	    		   Set<String> actionUrlSet = actionInfoMap.get (method.toUpperCase());
	    		   if (actionUrlSet != null && !actionUrlSet.isEmpty()) {
	    			   for (String regex : actionUrlSet) {
	    				   if (checkUrl.matches (regex)) {
	    					   return true;
	    				   }
	    			   }
	    		   }
	    	   }
	       }
	    
	    
	    }
		return false;
	
	}

	/**
	 * @Title: checkIsSuper
	 * @Description:(首先检测是否为超级角色,超级角色不做权限检测)
	 * @param session
	 * @return
	 */
	private boolean checkIsSuper(HttpSession session) {
		// 首先检测是否为超级角色,超级角色不做权限检测
		Boolean isSuper = false;// 是否为超级角色#true:是;false:不是;
		Object objectSupper = session.getAttribute(AppConfig.SESSION_IS_SUPPER);
		if (objectSupper != null) {
			if (Boolean.parseBoolean(objectSupper.toString())) {
				isSuper = true;
			}
		}
		return isSuper;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}

