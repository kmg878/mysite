package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.mysite.vo.GuestBookVo;



public class GuestBookDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public int delete(GuestBookVo vo){
		Connection conn =null;
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			conn = getConnection();
			
			//3.SQL 준비
			String sql = "delete from guestbook where no=? and password=?";
			pstmt = conn.prepareStatement(sql);
			
			//4.바인딩
			pstmt.setLong(1,vo.getNo());
			pstmt.setString(2,vo.getPassWord());
			
			//5.SQL 실행
			count = pstmt.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("SQL 에러 :"+e);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			
			}catch(SQLException e){
				System.out.println("SQL 에러 :"+e);
			}
		}
		return count;
	}
	


	public boolean insert(GuestBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			conn = getConnection();

			String sql = "insert into guestbook values(seq_guestbook.nextval,?,?,?,sysdate)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassWord());
			pstmt.setString(3, vo.getContent());
			

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (count == 1);
	}

	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "select no ,name ,password,content,to_char(reg_date,'yyyy-mm-dd') from guestbook order by reg_date desc";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name =rs.getString(2);
				String passWord =rs.getString(3);
				String content = rs.getString(4);
				String regDate =rs.getString(5);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassWord(passWord);
				vo.setContent(content);
				vo.setRegDate(regDate);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
