package com.eh100.genHtml.common;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookVo;

import freemarker.template.*;

/*
 * Created on 2005-4-7
 *
 */

/**
 * 测试FreeMarker.
 * 
 * @author scud
 * 
 */
public class FreeMarker {

	private final Log logger = LogFactory.getLog(getClass());

	private Configuration freemarker_cfg = null;

	public static void main(String[] args) {
		// @todo 自己的一个类
		BookVo vo = new BookVo();
		vo.setAuthor("author1");
		vo.setChapterName("chapterName");

		vo.setBookName("七章食物");
		vo.setCreateDate(new Date());
		vo.setChapterUrl("www.sina.cn");
		vo.setTargetSiteName("targetname1");
		BookVo vo1 = new BookVo();
		vo1.setAuthor("author1");
		vo1.setChapterName("chapterName");
		vo1.setChapterUrl("www.sina.cn");
		vo1.setBookName("七章食物2");
		vo1.setCreateDate(new Date());
		vo1.setTargetSiteName("targetname1");
		List<BookVo> bookVos = new ArrayList<BookVo>();
		
		bookVos.add(vo);
		bookVos.add(vo1);
		// @todo 装入新闻
		// NewsItem = loadNewsItem(1);

		FreeMarker test = new FreeMarker();

		Map root = new HashMap();
		root.put("bookVos", bookVos);

		String sGeneFilePath = "/latestUpdate/";

		String sFileName = "test.html";

		boolean bOK = test.geneHtmlFile("oneBookupdate.ftl", root, sGeneFilePath,
				sFileName);

	}

	/**
	 * 获取freemarker的配置. freemarker本身支持classpath,目录和从ServletContext获取.
	 */
	protected Configuration getFreeMarkerCFG() {
		if (null == freemarker_cfg) {
			// Initialize the FreeMarker configuration;
			// - Create a configuration instance
			freemarker_cfg = new Configuration();

			// - FreeMarker支持多种模板装载方式,可以查看API文档,都很简单:路径,根据Servlet上下文,classpath等等

			// htmlskin是放在classpath下的一个目录
			freemarker_cfg.setClassForTemplateLoading(this.getClass(),
					"/htmlskin");

			freemarker_cfg.setEncoding(Locale.CHINA, "UTF-8");
			freemarker_cfg.setDefaultEncoding("UTF-8");
		}

		return freemarker_cfg;
	}

	/**
	 * 生成静态文件.
	 * 
	 * @param templateFileName
	 *            模板文件名,相对htmlskin路径,例如"/tpxw/view.ftl"
	 * @param propMap
	 *            用于处理模板的属性Object映射
	 * @param htmlFilePath
	 *            要生成的静态文件的路径,相对设置中的根路径,例如 "/tpxw/1/2005/4/"
	 * @param htmlFileName
	 *            要生成的文件名,例如 "1.htm"
	 */
	public boolean geneHtmlFile(String templateFileName, Map propMap,
			String htmlFilePath, String htmlFileName) {
		// @todo 从配置中取得要静态文件存放的根路径:需要改为自己的属性类调用
		String sRootDir = "d:/eh100/htmlfile2";

		try {
			Template t = getFreeMarkerCFG().getTemplate(templateFileName);

			t.setEncoding("UTF-8");
			// 如果根路径存在,则递归创建子目录
			creatDirs(sRootDir, htmlFilePath);

			File afile = new File(sRootDir + "/" + htmlFilePath + "/"
					+ htmlFileName);

			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(afile), "UTF-8"));

			t.process(propMap, out);
		} catch (TemplateException e) {
			logger.error("Error while processing FreeMarker template "
					+ templateFileName, e);
			return false;
		} catch (IOException e) {
			logger.error("Error while generate Static Html File "
					+ htmlFileName, e);
			return false;
		}

		return true;
	}

	/**
	 * 创建多级目录
	 * 
	 * @param aParentDir
	 *            String
	 * @param aSubDir
	 *            以 / 开头
	 * @return boolean 是否成功
	 */
	public static boolean creatDirs(String aParentDir, String aSubDir) {
		File aFile = new File(aParentDir);
		if (aFile.exists()) {
			File aSubFile = new File(aParentDir + aSubDir);
			if (!aSubFile.exists()) {
				return aSubFile.mkdirs();
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

}