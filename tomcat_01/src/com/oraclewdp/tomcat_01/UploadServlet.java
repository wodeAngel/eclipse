package com.oraclewdp.tomcat_01;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig // 告诉服务器要上传文件
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// post请求乱码解决，不能解决get请求参数乱码。因为它只修改请求体中的编码，而get参数在请求行
		request.setCharacterEncoding("utf-8");
		// 获取多个part
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			// 如果Header里有"; filename="
			if (part.getHeader("Content-Disposition").contains("; filename=")) {
				// ?解决的是没有文件就提交所报的空指针异常，因此完善代码
				// 反过来是短路行为：如果第一个条件为false，后面就不再执行了，因而不会出现空指针异常
				if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {
					// ?解决重复上传被覆盖问题
					// 1.先找到文件的后缀名，因为要先知道后缀名，才能知道是什么类型的文件，文件名也可以有".", 所有需要截取最后一个.
					String es = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf(".") + 1);
					// 2.文件名不能重复，随机生成文件名然后再加上后缀名
					String newFile = UUID.randomUUID() + "." + es;
					// 把获取到的文件写到指定的文件路径下
					part.write(request.getServletContext().getRealPath("/upload/" + newFile));
				}
			} else {// 否则
				// 就是一般的值，先请求到参数.在获取name
				System.out.println(request.getParameter(part.getName()));
			}
		}
	}
}
