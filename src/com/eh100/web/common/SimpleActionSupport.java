//package com.eh100.web.common ;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts2.ServletActionContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.opensymphony.xwork2.ActionSupport;
//
///**
// * Struts2 Action基类.
// * 
// * 实现绕过jsp/freemaker,直接输出文本.
// * 
// * @author calvin
// */
//
//@SuppressWarnings( { "serial" })
//public class SimpleActionSupport extends ActionSupport {
//
//	protected final Logger logger = LoggerFactory.getLogger(getClass());
//
//	/**
//	 * 绕过Template,直接输出内容的简便函敄1�7. 
//	 */
//	protected String render(String text, String contentType) {
//		try {
//			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setContentType(contentType);
//			response.getWriter().write(text);
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//		return null;
//	}
//
//	/**
//	 * 直接输出字符丄1�7.
//	 */
//	protected String renderText(String text) {
//		return render(text, "text/plain;charset=UTF-8");
//	}
//
//	/**
//	 * 直接输出HTML.
//	 */
//	protected String renderHtml(String html) {
//		return render(html, "text/html;charset=UTF-8");
//	}
//
//	/**
//	 * 直接输出XML.
//	 */
//	protected String renderXML(String xml) {
//		return render(xml, "text/xml;charset=UTF-8");
//	}
//}
