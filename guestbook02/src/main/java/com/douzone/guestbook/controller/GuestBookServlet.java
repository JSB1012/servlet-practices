package com.douzone.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.guestbook.dao.GuestBookDao;
import com.douzone.guestbook.vo.GuestBookVo;

public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("a"); // 임의로 지정한거 a
		if ("list".equals(action)) {

		} else if ("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);

			new GuestBookDao().insert(vo);

			response.sendRedirect(request.getContextPath() + "/gb");

		} else if ("deleteform".equals(action)) {
			String no = request.getParameter("no");
			request.setAttribute("no", no);
			// action에서 객체를 request에 담아 보낼경우.request.setAttribute("객체명", 객체);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp"); // 포워딩
			rd.forward(request, response);

		} else if ("delete".equals(action)) {

			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setPassword(password);

			new GuestBookDao().delete(vo);

			response.sendRedirect(request.getContextPath() + "/gb");

		} else {
			List<GuestBookVo> list = new GuestBookDao().findAll();

			request.setAttribute("list", list); // 저장

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp"); // 포워딩
			rd.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
