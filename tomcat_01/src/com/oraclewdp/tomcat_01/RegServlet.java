package com.oraclewdp.tomcat_01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		// 正文
		pw.print("<b>注册成功</b>");
		pw.flush();
	}

}
