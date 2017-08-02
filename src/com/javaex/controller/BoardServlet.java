package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");

		if ("writeform".equals(actionName)) {
			HttpSession session = request.getSession();
			if(session == null) {
				WebUtil.redirect(response, "/mysite/board");
			} else {
			WebUtil.forward(request, response, "/WEB-INF/views/board/writeform.jsp");
			}
		} else if("write".equals(actionName)){
			HttpSession session = request.getSession();
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			int user_no = authUser.getNo();
			
			String title = request.getParameter("title");
			String content = request.getParameter("content").replace("\r\n","<br>");
			
			BoardDao bdao = new BoardDao();
			BoardVo bvo = new BoardVo();
			bvo.getNo();
			bdao.write(title, content, user_no);
			
			WebUtil.redirect(response, "http://localhost:8088/mysite/board");
		} else if("modifyform".equals(actionName)){
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int user_no = authUser.getNo();
			
			int cw_no = Integer.parseInt(request.getParameter("user_no"));
			int no = Integer.parseInt(request.getParameter("no").trim());
			
			if(user_no==cw_no) {
				BoardDao bdao = new BoardDao();
				BoardVo bvo = new BoardVo();
				bvo = bdao.read(no);
				System.out.println(bvo.toString());
				request.setAttribute("bcontent", bvo);
				
				WebUtil.forward(request, response, "/WEB-INF/views/board/modifyform.jsp");
			} else {
				WebUtil.redirect(response, "http://localhost:8088/mysite/board");
			}
		} else if("modify".equals(actionName)){
			String title = request.getParameter("title");
			String content = request.getParameter("content").replace("\r\n","<br>");
			int no = Integer.parseInt(request.getParameter("no").trim());
			BoardDao bdao = new BoardDao();
			BoardVo bvo = new BoardVo();
			bvo.setTitle(title);
			bvo.setContent(content);
			bvo.setNo(no);
			
			bdao.modify(bvo);
			
			request.setAttribute("bcontent", bvo);
			String url = "http://localhost:8088/mysite/board?a=read&no="+no;

			WebUtil.redirect(response, url);
			
		} else if("delete".equals(actionName)){
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int user_no = authUser.getNo();
			
			int no = Integer.parseInt(request.getParameter("no").trim());
			BoardDao bdao = new BoardDao();
			BoardVo bvo = new BoardVo();
			bvo = bdao.read(no);
			int wrieter_no = bvo.getNo();
			
			if(user_no == wrieter_no) {
				bdao.delete(no);
			} else {
				System.out.println("권한이 없습니다.");    
			}

			WebUtil.redirect(response, "http://localhost:8088/mysite/board");
		} else if("read".equals(actionName)){
			int no = Integer.parseInt(request.getParameter("no").trim());
			BoardDao bdao = new BoardDao();
			BoardVo bvo = new BoardVo();
			bvo = bdao.read(no);
			int hit = bvo.getHit()+1;
			bdao.plushit(hit, no);
			bvo = bdao.read(no);
			
			request.setAttribute("bcontent", bvo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
		} else if("search".equals(actionName)){
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		} else {
			System.out.println("기본값 리스트"); 
			BoardDao dao = new BoardDao();
			List<BoardVo> Blist = dao.getlist2();
			
			request.setAttribute("Blist", Blist);
			System.out.println(Blist.toString());

			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
