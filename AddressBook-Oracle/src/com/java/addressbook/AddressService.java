package com.java.addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressService implements AddressDao {

	//	공통
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			//	드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "C##KDY", "1234");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패");
		}
		return conn;
	}

	//	리스트
	@Override
	public List<AddressVo> getList() {
		List<AddressVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT id, name, hp, tel FROM phone_book ORDER BY id";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("tel");
				
				AddressVo vo = new AddressVo(id, name, hp, tel);
				list.add(vo);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
					stmt.close();
					rs.close();
				} catch (Exception e) {
					
				}
			}
		return list;
	}

	//	검색
	@Override
	public List<AddressVo> search(String keyword) {
		List<AddressVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel FROM phone_book WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AddressVo vo = new AddressVo();
				vo.setId(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setHp(rs.getString(3));
				vo.setTel(rs.getString(4));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 등록
	@Override
	public boolean insert(AddressVo vo) {
		Connection conn = null;
		String sql = "INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL, ?, ?, ?)";
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			//	파라미터 연결
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return insertCount == 1;
	}

	//	삭제
	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			deleteCount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deleteCount == 1;
	}
	
	
}
