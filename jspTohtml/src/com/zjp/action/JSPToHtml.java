package com.zjp.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSPToHtml {

	/**
	 * 描述：get请求的页面
	 * 
	 * @param url
	 */
	public static void jspToHtmlForGet(String url,String htmlPath) {
		try {
			//得到jsp的document对象
			Document doc = Jsoup
					.connect(url)
					.userAgent(
							"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)") // 设置User-Agent 
            .timeout(5000).get();
			//取得jsp的html页面
			String html = doc.select("html").outerHtml();
			//把html页面写出去
			writeFromString(html,htmlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 描述：post请求的页面
	 * @param url
	 * @param htmlPath
	 * @param parp 请求参数
	 * @param cookie
	 */
	public static void jspToHtmlForPost(String url,String htmlPath,Map<String, String>parp,Cookie cookie) {
		try {
			//得到jsp的document对象
			Document doc = Jsoup.connect(url).data(parp).cookie(cookie.getName(), cookie.getValue()).timeout(300)
			.userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)") // 设置User-Agent 
            .post();
			//取得jsp的html页面
			String html = doc.select("html").outerHtml();
			//把html页面写出去
			writeFromString(html,htmlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *描述：把本地html页面转换换成html
	 * @param fileurl
	 * @param htmlPath
	 */
	public static void jspToHtmlForFile(String fileurl,String htmlPath) {
		try {
			File input = new File(fileurl);
			//得到jsp的document对象
			Document doc = Jsoup.parse(input, "UTF-8");
			//取得jsp的html页面
			String html = doc.select("html").outerHtml();
			//把html页面写出去
			writeFromString(html,htmlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 描述：把字符串写到磁盘上
	 * @param html
	 * @param filePath
	 */
	public static void writeFromString(String html, String filePath) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(filePath));
			PrintStream ps = new PrintStream(os);
			ps.print(html);
			os.flush();
			os.close();
			ps.flush();
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		jspToHtmlForGet("http://www.baidu.com", "d:/baidu.html");

	}

}
