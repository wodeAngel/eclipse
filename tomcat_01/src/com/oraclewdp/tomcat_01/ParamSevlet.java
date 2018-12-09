package com.oraclewdp.tomcat_01;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/param")
public class ParamSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ParamSevlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取单个值:如果有多个值，按照表单中出现的顺序返回第一个，若没有返回null
		String str=request.getParameter("name");
		System.out.println(str);
		System.out.println("==============111===============");
		//2.获取多个值:选中几个就返回几个值，返回所有值，没有参数就返回null
		String[] hobbies=request.getParameterValues("name");
		System.out.println(Arrays.toString(hobbies));
		System.out.println("==============222===============");
		//3.获取所有参数名,值重复只获得第一个
		Enumeration<String> en=request.getParameterNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			System.out.println(request.getParameter(name));
		}
		System.out.println("==============333===============");
		//4.获取键值对
		Map<String, String[]> map=request.getParameterMap();
		Set<String> set=map.keySet();
		for (String name : set) {
			System.out.println(Arrays.toString(map.get(name)));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
