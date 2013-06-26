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
	 * ������get�����ҳ��
	 * 
	 * @param url
	 */
	public static void jspToHtmlForGet(String url,String htmlPath) {
		try {
			//�õ�jsp��document����
			Document doc = Jsoup
					.connect(url)
					.userAgent(
							"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)") // ����User-Agent 
            .timeout(5000).get();
			//ȡ��jsp��htmlҳ��
			String html = doc.select("html").outerHtml();
			//��htmlҳ��д��ȥ
			writeFromString(html,htmlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * ������post�����ҳ��
	 * @param url
	 * @param htmlPath
	 * @param parp �������
	 * @param cookie
	 */
	public static void jspToHtmlForPost(String url,String htmlPath,Map<String, String>parp,Cookie cookie) {
		try {
			//�õ�jsp��document����
			Document doc = Jsoup.connect(url).data(parp).cookie(cookie.getName(), cookie.getValue()).timeout(300)
			.userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)") // ����User-Agent 
            .post();
			//ȡ��jsp��htmlҳ��
			String html = doc.select("html").outerHtml();
			//��htmlҳ��д��ȥ
			writeFromString(html,htmlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *�������ѱ���htmlҳ��ת������html
	 * @param fileurl
	 * @param htmlPath
	 */
	public static void jspToHtmlForFile(String fileurl,String htmlPath) {
		try {
			File input = new File(fileurl);
			//�õ�jsp��document����
			Document doc = Jsoup.parse(input, "UTF-8");
			//ȡ��jsp��htmlҳ��
			String html = doc.select("html").outerHtml();
			//��htmlҳ��д��ȥ
			writeFromString(html,htmlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * ���������ַ���д��������
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
