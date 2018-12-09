package com.oraclewdp.tomcat_01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HelloWorldServlet() {
		System.out.println("我来了");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<b>世界安好!</b>");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		out.print(sdf.format(new Date()));
		out.flush();
	}
}
