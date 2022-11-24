package com.KoreaIT.java.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.KoreaIT.java.am.config.Config;
import com.KoreaIT.java.am.exception.SQLErrorException;
import com.KoreaIT.java.am.util.DBUtil;
import com.KoreaIT.java.am.util.SecSql;


@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=UTF-8");

		Connection conn = null;

		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}

		String url = Config.getDBUrl();

		try {
			conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassword());
			
			
			String loginId = request.getParameter("loginId"); 
			String loginPw = request.getParameter("loginPw"); 
			String userName = request.getParameter("userName"); 
			
			SecSql sql = SecSql.from("INSERT INTO `member`");
			sql.append("SET regDate = NOW()");
			sql.append(",loginId =  ?",loginId);
			sql.append(",loginPw =  ?",loginPw);
			sql.append(", userName = ?",userName);
		

			
			int id = DBUtil.insert(conn, sql);

			response.getWriter().append(String.format("<script>alert('%d번 회원가입되었습니다'); location.replace('../home/main');</script>", id));


		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} catch(SQLErrorException e) {
			e.getOrigin().printStackTrace();
		}finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
