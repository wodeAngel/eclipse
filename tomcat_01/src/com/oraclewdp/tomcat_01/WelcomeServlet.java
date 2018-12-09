package com.oraclewdp.tomcat_01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WelcomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter pw =response.getWriter();
		// 正文
		// 标题
		pw.print("<title>你好！我是标题</title>");
		// 更改字体颜色与加粗
		pw.print("<p style=color:purple><b>世界安好!</b></p>");
		// 强制发出
		pw.flush();
	}
}
