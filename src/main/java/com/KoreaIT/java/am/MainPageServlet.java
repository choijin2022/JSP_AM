package com.KoreaIT.java.am;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home/main")
public class MainPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		boolean isLogined = false;
		int loginedMemberId = -1;
		
		if(session.getAttribute("loginedMemberLoginId") !=null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			isLogined = true;
		}
		
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
		
	}

}