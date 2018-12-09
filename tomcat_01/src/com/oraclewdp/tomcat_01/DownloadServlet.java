package com.oraclewdp.tomcat_01;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

@WebServlet(value={"/download"},loadOnStartup=2)
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 一 创建实例
	 * 同一个Servlet只有一个实例，默认为第一次请求时，自动生成，只生成一次
	 * 在请求之前生成实例:
	 * 1.可以在XML里面<liad-on-startup>0</liad-on-startup>非负整数代表实例化，负整数实在第一次请求时生成
	 * 2.在注解里面@WebServlet(value={"/download"},loadOnStartup=2)
	 * Servelt里面有线程不安全，多个线程使用同一个Servlet实例不安全
	 * 
	 * 二 销毁实例
	 * 在本次请求结束，关闭服务器时，实例自动销毁
	 * 
	 */
	public DownloadServlet() {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 读取文件所在的路径
		// String queryString =
		// request.getRequestURI().substring(request.getRequestURI().indexOf("?") + 1);
		//找到文件
		String fileName =request.getParameter("file") ;
		System.out.println(fileName);
		//PrintWriter pw = response.getWriter();
		/*if (fileName.endsWith(".docx")) {
			response.setHeader("Content-Type",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document; charset=utf-8");
		}
		if (fileName.endsWith(".jpg")) {
			response.setHeader("Content-Type", "image/jpeg;charset=utf-8");
		}
		if (fileName.endsWith(".pdf")) {
			response.setHeader("Content-Type", "application/pdf;charset=utf-8");
		}*/
		//得到文件后缀名的类型
		response.setHeader("Content-Type", request.getServletContext().getMimeType(fileName));
		//得到文件的名字
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + fileName + "\";filename*=utf-8''" + URLEncoder.encode(fileName, "utf-8"));
		//获取服务器所在目录
		/*InputStream is = new FileInputStream(request.getServletContext().getRealPath("/download/" + fileName));
		byte[] buf = new byte[1024];
		int len;
		while ((len = is.read(buf)) != -1) {
			response.getOutputStream().write(buf, 0, len);
		}
		is.close();*/
		//输入，输出文件的内容
		IOUtils.copy(new FileInputStream(request.getServletContext().getRealPath("/download/" + fileName)), response.getOutputStream());
	}
}
