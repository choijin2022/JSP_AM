package com.KoreaIT.java.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.am.config.Config;
import com.KoreaIT.java.am.exception.SQLErrorException;
import com.KoreaIT.java.am.util.DBUtil;
import com.KoreaIT.java.am.util.SecSql;

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		//
		int loginedMemberId = (int) session.getAttribute("loginedMemberId");
		
		if(session.getAttribute("loginedMemberId")==null) {
			response.getWriter().append(String.format("<script>alert('로그인 후 이용해주세요'); location.replace('../member/login');</script>"));

		}
		
		Connection conn = null;

		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}

		String url = "jdbc:mysql://127.0.0.1:3306/JSPTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

		try {
			conn = DriverManager.getConnection(url, "root", "");

			String title = request.getParameter("title"); 
			String body = request.getParameter("body"); 
			
			SecSql sql = SecSql.from("INSERT INTO article");
			sql.append("SET regDate = NOW()");
			sql.append(",memberId = ?",loginedMemberId);
			sql.append(",title = ?",title);
			sql.append(",`body` = ?",body);
		
			int id = DBUtil.insert(conn, sql);

			response.getWriter().append(String.format("<script>alert('%d번 글이 생성 되었습니다.'); location.replace('list');</script>", id));


		} catch (SQLException e) {
			System.out.println("에러: " + e);
		}catch(SQLErrorException e) {
			e.getOrigin().printStackTrace();
		}
		finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
