package com.javaex.controller;

import java.io.IOException;
import java.util.List;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;


/**
 * Servlet implementation class EmaillistServlet
 */
@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String actionName = request.getParameter("a");
		
		if("add".equals(actionName)) {//추가
			System.out.println("저장 관련"); 
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookVo vo = new GuestBookVo(name,password,content);
			
			GuestBookDao dao = new GuestBookDao();
			dao.add(vo);

			//response.sendRedirect("/mysite/gb");
			WebUtil.redirect(response, "http://localhost:8088/mysite/gb");
		} else if("deleteform".equals(actionName)) {// 딜리트폼
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			//rd.forward(request, response);	
		} else if("delete".equals(actionName)) {//삭제
			GuestBookDao dao = new GuestBookDao();
			String pass = request.getParameter("password");
			int no = Integer.parseInt(request.getParameter("no").trim());

			dao.delete(no, pass);
			
			//response.sendRedirect("http://localhost:8088/mysite/gb");
			WebUtil.redirect(response, "http://localhost:8088/mysite/gb");
		}  else {//디폴트 리스트
			System.out.println("기본값 리스트"); 
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getlist();
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
			
			request.setAttribute("list", list);
			
			///rd.forward(request, response);
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		}
		
		System.out.println(actionName); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
