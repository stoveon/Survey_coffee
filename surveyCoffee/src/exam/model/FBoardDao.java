package exam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FBoardDao {
	private static FBoardDao instance;
	
	private FBoardDao() {}
	
	public static FBoardDao getInstance() {
		synchronized(FBoardDao.class) {
			if(instance == null) {
				instance = new FBoardDao();
			}
		}
		return instance;
	}
	
	//FILE 게시판 DAO(Table : BOARDFILE)
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		try {
			conn = ConnUtil.getConnection();
			sql.append("select count(*) from BOARDFILE");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}else {
				result = -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<BoardDto> getArticles(int startNum, int endNum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<BoardDto> articleList = new ArrayList<BoardDto>();
		
		try {
			conn = ConnUtil.getConnection();
			sql.append("select * from (select rownum RNUM, \"NUM\", \"WRITER\", \"PASS\", \"TITLE\", \"CONTENT\", \"READCOUNT\", \"REF\", \"STEP\", \"DEPTH\", \"REGDATE\", \"FILE\" ");
			sql.append("from (select * from BOARDFILE order by BOARDFILE.\"REF\" desc, BOARDFILE.\"STEP\", BOARDFILE.\"REGDATE\" desc)) where ? <= rnum and rnum < ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto article= new BoardDto(
						rs.getInt("num"), 
						rs.getString("writer"), 
						rs.getString("pass"),
						rs.getString("title"), 
						rs.getString("content"), 
						rs.getInt("readcount"), 
						rs.getInt("ref"), 
						rs.getInt("step"), 
						rs.getInt("depth"), 
						rs.getTimestamp("regdate"),
						rs.getString("file")
						);
				article.setRnum(rs.getInt("RNUM"));
				articleList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return articleList;
	}
	
	public BoardDto getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		BoardDto article = null;
		
		try {
			conn = ConnUtil.getConnection();
			sql.append("update BOARDFILE set READCOUNT = READCOUNT +1 where NUM =?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate() == 1) {
				sql.delete(0, sql.length());
				pstmt.close();
				sql.append("select rownum rnum, \"NUM\", \"WRITER\", \"PASS\", \"TITLE\", \"CONTENT\", \"READCOUNT\", ");
				sql.append("\"REF\", \"STEP\", \"DEPTH\", \"REGDATE\", \"FILE\" from BOARDFILE where \"NUM\" = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					article = new BoardDto(
							rs.getInt("num"), 
							rs.getString("writer"), 
							rs.getString("pass"),
							rs.getString("title"), 
							rs.getString("content"), 
							rs.getInt("readcount"), 
							rs.getInt("ref"), 
							rs.getInt("step"), 
							rs.getInt("depth"), 
							rs.getTimestamp("regdate"),
							rs.getString("file")
							);
					article.setRnum(rs.getInt("RNUM"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return article;
	}
	
	public BoardDto getArticleDetail(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		BoardDto article = null;
		
		try {
			conn = ConnUtil.getConnection();
			sql.append("select rownum RNUM, \"NUM\", \"WRITER\", \"PASS\", \"TITLE\", \"CONTENT\", \"READCOUNT\", ");
			sql.append("\"REF\", \"STEP\", \"DEPTH\", \"REGDATE\", \"FILE\" FROM BOARDFILE where NUM = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardDto(
						rs.getInt("num"), 
						rs.getString("writer"), 
						rs.getString("pass"),
						rs.getString("title"), 
						rs.getString("content"), 
						rs.getInt("readcount"), 
						rs.getInt("ref"), 
						rs.getInt("step"), 
						rs.getInt("depth"), 
						rs.getTimestamp("regdate"),
						rs.getString("file")
						);
				article.setRnum(rs.getInt("RNUM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return article;
	}
	
	public BoardDto getNextArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		BoardDto article = null;
		int rnum = 0;
		
		try {
			conn = ConnUtil.getConnection();		
			sql.append("select min(NUM) from BOARDFILE where \"NUM\" > ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rnum = rs.getInt(1);
			}else {
				rnum = -1;
			}
			if(rnum > 0) {
				sql.delete(0, sql.length());
				pstmt.close();
				sql.append("update BOARDFILE set \"READCOUNT\" = \"READCOUNT\" +1 where \"NUM\" = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, rnum);
				if(pstmt.executeUpdate() == 1) {
					sql.delete(0, sql.length());
					pstmt.close();
					rs.close();
					sql.append("select * from (select rownum RNUM, \"NUM\", \"WRITER\", \"PASS\", \"TITLE\", \"CONTENT\", \"READCOUNT\", ");
					sql.append("\"REF\", \"STEP\", \"DEPTH\", \"REGDATE\", \"FILE\" from BOARDFILE order by ");
					sql.append("BOARDFILE.\"REGDATE\", BOARDFILE.\"REF\" desc, BOARDFILE.\"STEP\") where \"NUM\" = ?");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setInt(1, rnum);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						article = new BoardDto(
								rs.getInt("num"), 
								rs.getString("writer"), 
								rs.getString("pass"),
								rs.getString("title"), 
								rs.getString("content"), 
								rs.getInt("readcount"), 
								rs.getInt("ref"), 
								rs.getInt("step"), 
								rs.getInt("depth"), 
								rs.getTimestamp("regdate"),
								rs.getString("file")
								);
						article.setRnum(rs.getInt("RNUM"));
					}
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) {rs.close();}
					if(pstmt != null) {pstmt.close();}
					if(conn != null) {conn.close();}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		return article;
	}
	
	public BoardDto getAgoArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		BoardDto article = null;
		int rnum = 0;
		
		try {
			conn = ConnUtil.getConnection();		
			sql.append("select max(NUM) from BOARDFILE where \"NUM\" < ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rnum = rs.getInt(1);
			}else {
				rnum = -1;
			}
			if(rnum > 0) {
				sql.delete(0, sql.length());
				pstmt.close();
				sql.append("update BOARDFILE set \"READCOUNT\" = \"READCOUNT\" +1 where \"NUM\" = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, rnum);
				if(pstmt.executeUpdate() == 1) {
					sql.delete(0, sql.length());
					pstmt.close();
					rs.close();
					sql.append("select * from (select rownum RNUM, \"NUM\", \"WRITER\", \"PASS\", \"TITLE\", \"CONTENT\", \"READCOUNT\", ");
					sql.append("\"REF\", \"STEP\", \"DEPTH\", \"REGDATE\", \"FILE\" from BOARDFILE order by ");
					sql.append("BOARDFILE.\"REGDATE\", BOARDFILE.\"REF\" desc, BOARDFILE.\"STEP\") WHERE \"NUM\" = ?");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setInt(1, rnum);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						article = new BoardDto(
								rs.getInt("num"), 
								rs.getString("writer"), 
								rs.getString("pass"),
								rs.getString("title"), 
								rs.getString("content"), 
								rs.getInt("readcount"), 
								rs.getInt("ref"), 
								rs.getInt("step"), 
								rs.getInt("depth"), 
								rs.getTimestamp("regdate"),
								rs.getString("file")
								);
						article.setRnum(rs.getInt("RNUM"));
					}
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) {rs.close();}
					if(pstmt != null) {pstmt.close();}
					if(conn != null) {conn.close();}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		return article;
	}
	
	public int getArticleInsert(BoardDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int num = article.getNum();
		int ref = article.getRef();
		System.out.println(ref);
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		int result = 0;	
		try {
			conn = ConnUtil.getConnection();
			sql.append("select max(NUM) mnum from BOARDFILE");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				number = rs.getInt("mnum") + 1;
			}else {
				number = 1;
			}
			if(num != 0) {		//답글인 경우
				sql.delete(0, sql.length());
				pstmt.close();
				sql.append("update BOARDFILE set STEP = STEP +1 where REF = ? and STEP > ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				step = step + 1;
				depth = depth + 1;
			}else {				//새글인 경우
				ref = number;
				step = 0;
				depth = 0;
			}
			sql.delete(0, sql.length());
			pstmt.close();
			sql.append("insert into BOARDFILE(\"NUM\", \"WRITER\", \"PASS\", \"TITLE\", \"CONTENT\", \"READCOUNT\", \"REF\", \"STEP\", \"DEPTH\", ");
			sql.append("\"REGDATE\", \"FILE\") VALUES(BOARDFILE.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getPass());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, step);
			pstmt.setInt(7, depth);
			pstmt.setTimestamp(8, article.getRegdate());
			pstmt.setString(9, article.getFile());
			result = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(conn != null) {conn.close();}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
		return result;
	}
	
	public int getArticleUpdate(BoardDto article, String inputPass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int result = 0;
		
		try {
			conn = ConnUtil.getConnection();
			sql.append("select rownum RNUM, \"NUM\", \"WRITER\", \"PASS\", \"TITLE\", \"CONTENT\", \"READCOUNT\", ");
			sql.append("\"REF\", \"STEP\", \"DEPTH\", \"REGDATE\", \"FILE\" from BOARDFILE where \"NUM\" = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("PASS").equals(inputPass)) {
				sql.delete(0, sql.length());
				pstmt.close();
				sql.append("update BOARDFILE set TITLE = ?, CONTENT = ?, FILE = ?, REGDATE = sysdate where \"NUM\" = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, article.getTitle());
				pstmt.setString(2, article.getContent());
				pstmt.setString(3, article.getFile());
				pstmt.setInt(4, article.getNum());
				result = pstmt.executeUpdate();
				}else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int getArticleDelete(int num, String inputPass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		int result = 0;
		
		try {
			conn = ConnUtil.getConnection();
			sql.append("select rownum rnum, \"NUM\", \"WRITER\", \"PASS\", \"TITLE\", \"CONTENT\", \"READCOUNT\", ");
			sql.append("\"REF\", \"STEP\", \"DEPTH\", \"REGDATE\", \"FILE\" from BOARDFILE where \"NUM\" = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("PASS").equals(inputPass)) {
				sql.delete(0, sql.length());
				pstmt.close();
				sql.append("delete BOARDFILE where \"NUM\" = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, num);
				result = pstmt.executeUpdate();
				}else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
