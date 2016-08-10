package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.GuestBookVo;
import kr.ac.sungkyul.mysite.vo.UserVo;




public class BoardDao {
	private Connection getConnection()throws SQLException{
		Connection conn = null;
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return conn;
	}
	public int delete(BoardVo vo){
		Connection conn =null;
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			conn = getConnection();
			
			//3.SQL 준비
			String sql = "delete from board where no=? and user_no=?";
			pstmt = conn.prepareStatement(sql);
			
			//4.바인딩
			pstmt.setLong(1,vo.getNo());
			pstmt.setLong(2,vo.getUserNo());
			
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
	
	
	
	public void update(BoardVo vo) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;

	      try {
	         conn = getConnection();

	         Long no = vo.getNo();
	         String title = vo.getTitle();
	         String content = vo.getContent();
	         
	         String sql = "update board set title=?, content=?where no=?";
	         pstmt = conn.prepareStatement(sql);
	      
	            pstmt.setString(1, title);
	            pstmt.setString(2, content);
	            pstmt.setLong(3, no);
	         
	         pstmt.executeUpdate();

	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null) {
	               pstmt.cancel();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	   }
	public void updateViewCount(Long no) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;

	      try {
	         conn = getConnection();

//	         Long no = vo.getNo();
//	         int viewCount = vo.getViewCount();
	       
	         
	         String sql = "update board set view_count =view_count+1 where no=?";
	         pstmt = conn.prepareStatement(sql);
	      
	           
	            pstmt.setLong(1, no);
	            
	           
	         
	         pstmt.executeUpdate();

	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null) {
	               pstmt.cancel();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	   }
	
	
	
	
	public int delete(GuestBookVo vo){
		Connection conn =null;
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			conn = getConnection();
			
			//3.SQL 준비
			String sql = "delete from guestbook where no=?";
			pstmt = conn.prepareStatement(sql);
			
			//4.바인딩
			pstmt.setLong(1,vo.getNo());
			
			
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
	
	
	
	public BoardVo get(Long No ){
		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try{
			conn=getConnection();
			
			String sql ="select no,title,content,user_no from board where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,No );
			
			rs =pstmt.executeQuery();
			if(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content =rs.getString(3);
				Long userNo=rs.getLong(4);
				
				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUserNo(userNo);
				
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	public boolean insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			conn = getConnection();

			String sql = "insert into board values(seq_board.nextval,?,?,?,nvl((select max(group_no) from board),0)+1,?,?,?,sysdate)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getViewCount());
			pstmt.setLong(4, vo.getOrderNo());
			pstmt.setInt(5,vo.getDepth());
			pstmt.setLong(6,vo.getUserNo());
			
			
			

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
	
	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "select a.no,a.title,a.content,a.view_count,a.group_no,a.order_no,a.depth,a.user_no,a.reg_date,b.name  from board a,users b where b.no= a.user_no ";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title =rs.getString(2);
				String content =rs.getString(3);
				Integer viewCount = rs.getInt(4);
				Long groupNo =rs.getLong(5);
				Long orderNo =rs.getLong(6);
				Integer depth =rs.getInt(7);
				Long userNo =rs.getLong(8);
				String regDate =rs.getString(9);
				String name =rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setTitle(title);
				vo.setViewCount(viewCount);
				vo.setContent(content);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
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
