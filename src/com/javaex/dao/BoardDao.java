package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao {
	public int write(String title, String content, int user_no) {
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
			String query = "insert into board(no, title, content,"
							+ " hit, reg_date, user_no)"
					+ " values (seq_board_no.NEXTVAL," + " ?, ?, 1, sysdate, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, user_no);

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
	
	public int plushit(int hit, int no) {
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
			String query = "update board set hit=? where no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, hit);
			pstmt.setInt(2, no);

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
	
	public int modify(BoardVo bvo) {
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
			String query = "update board set title=?, content=? where no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContent());
			pstmt.setInt(3, bvo.getNo());

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

	public int delete(int num) {
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
			String query = "Delete from board where no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			
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

	public BoardVo read(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;

		try {
			// 1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3.sql문 준비 / 바인딩 /실행
			String query = "select no, title, content, hit, reg_date, user_no " + "from board " + "where no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			// 4. 결과처리
			while (rs.next()) {
				no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("hit");
				int user_no = rs.getInt("user_no");
				vo = new BoardVo();

				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setReg_date(reg_date);
				vo.setHit(hit);
				vo.setUser_no(user_no);
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

	public List<BoardVo> getlist() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		try {
			//1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.sql문 준비 / 바인딩 /실행
			String query = "select no, title, content, hit, reg_date, user_no "
					+"from board";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			//4. 결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				String content = rs.getString("content");
				int user_no = rs.getInt("user_no");
				//String reg_date = rs.getString("b.reg_date");
				
				BoardVo vo = new BoardVo(no,title,content, hit,reg_date,user_no);
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
	
	public List<BoardVo> getlist2() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		try {
			//1. jdbc 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.sql문 준비 / 바인딩 /실행
			String query = "select b.no, b.title, b.content,"
					+ " b.hit, b.reg_date, b.user_no, u.name "
					+"from users u, board b "
					+"where u.no = b.user_no "
					+"order by b.no desc";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			//4. 결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");
				String name = rs.getString("name");
				
				BoardVo bvo = new BoardVo(no, title, content, hit, reg_date, user_no, name);
				list.add(bvo);
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
}
