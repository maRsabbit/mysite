package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVo;;

public class GuestBookDao {
	public int add(GuestBookVo vo ) {
		int count =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.sql문 준비 / 바인딩 /실행
			String query = "insert into guestbook values ("
					+ "seq_gb_no.NEXTVAL, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPwd());	
			pstmt.setString(3, vo.getMessege());
			
			count = pstmt.executeUpdate();
			//4. 결과처리
			System.out.println(count+"건 등록");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:드라이버 로딩 실패 - "+e);
		} catch (SQLException e) {
			System.out.println("error : "+e);
		} finally {
			//5. 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}
		}
		return count;
	}
	
	public int delete(int num, String pwd) {
		int count =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.sql문 준비 / 바인딩 /실행
			String query = "Delete from guestbook where no=? and pwd=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.setString(2, pwd);
			
			count = pstmt.executeUpdate();
			//4. 결과처리
			System.out.println(count+"건 등록");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:드라이버 로딩 실패 - "+e);
		} catch (SQLException e) {
			System.out.println("error : "+e);
		} finally {
			//5. 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}
		}
		return count;
	}
	
	public List<GuestBookVo> getlist() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		try {
			//1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.sql문 준비 / 바인딩 /실행
			String query = "select no, name, pwd,"
					+ " messege, visit_date "
					+"from guestbook "
					+ "order by visit_date desc";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			//4. 결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String messege = rs.getString("messege");
				String date = rs.getString("visit_date");
				
				GuestBookVo vo = new GuestBookVo(no, name, pwd, date, messege);
				list.add(vo);
				/*
				for(int i=0; i<list.size(); i++) {
					vo.toString();
				}*/
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:드라이버 로딩 실패 - "+e);
		} catch (SQLException e) {
			System.out.println("error : "+e);
		} finally {
			//5. 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}
		}
		return list;
	}

	public GuestBookVo getlist(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GuestBookVo gbvo = null;
		
		try {
			//1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.sql문 준비 / 바인딩 /실행
			String query = "select no, name, pwd,"
					+ " messege, visit_date "
					+"from guestbook "
					+ "where no=?";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			pstmt.setInt(1, no);
			//4. 결과처리
			while(rs.next()) {
				no = rs.getInt("no");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String messege = rs.getString("messege");
				String date = rs.getString("visit_date");
				
				gbvo = new GuestBookVo(no, name, pwd, messege, date);
				/*
				for(int i=0; i<list.size(); i++) {
					vo.toString();
				}*/
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:드라이버 로딩 실패 - "+e);
		} catch (SQLException e) {
			System.out.println("error : "+e);
		} finally {
			//5. 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}
		}
		return gbvo;
	}
}
