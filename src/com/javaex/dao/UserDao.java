package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {
	public int insert(UserVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3.sql문 준비 / 바인딩 /실행
			String query = "insert into users values (" + "seq_users_no.NEXTVAL, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			count = pstmt.executeUpdate();
			// 4. 결과처리
			System.out.println(count + "건 등록");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
		return count;
	}

	public int update(UserVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			if (vo.getPassword() == "") {
				// 3.sql문 준비 / 바인딩 /실행
				String query = "update users set name=?, gender=? where no=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setInt(3, vo.getNo());

				count = pstmt.executeUpdate();
			} else {
				String query = "update users set name=?, password=?,gender=? where no=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setInt(4, vo.getNo());
				
				count = pstmt.executeUpdate();
			}
			// 4. 결과처리
			System.out.println(count + "건 수정");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
		return count;
	}

	public UserVo getUser(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo vo = null;

		try {
			// 1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3.sql문 준비 / 바인딩 /실행
			String query = "select no, name " + "from users " + "where email=? and password=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			// 4. 결과처리
			while (rs.next()) {
				int num = rs.getInt("no");
				String name = rs.getString("name");
				vo = new UserVo();

				vo.setNo(num);
				vo.setName(name);
			}
			return vo;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

		return vo;
	}

	public UserVo getUser(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo vo = null;

		try {
			// 1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3.sql문 준비 / 바인딩 /실행
			String query = "select no, name,password, email, gender " + "from users " + "where no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			// 4. 결과처리
			while (rs.next()) {
				no = rs.getInt("no");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				vo = new UserVo();

				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setPassword(password);
				vo.setGender(gender);
			}
			return vo;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error:드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

		return vo;
	}
}
