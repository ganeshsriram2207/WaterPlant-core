package com.revature.wp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.revature.wp.connection.ConnectionUtil;
import com.revature.wp.connection.Logger;
import com.revature.wp.connection.MessageConstant;
import com.revature.wp.exception.DBException;
import com.revature.wp.model.UserDetail;
import com.revature.wp.services.CanAvailability;
import com.revature.wp.services.HomePage;

public class UserDAOImpl implements UserDAO {
	static UserDetail obj = new UserDetail();

	public UserDetail login(String name, String password) throws  DBException {

		UserDetail ud = new UserDetail();
		UserDAO test = new UserDAOImpl();
		try {
			ud = test.findByUsername(name, password);
			if (name.equals(name) && password.equals(password)) {
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to login");
			
		}

		
		return ud;
		} 
	

	@SuppressWarnings("static-access")
	

	public void setavailablecans(int noOfCans) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionUtil.getConnection();
			String sqlstmnt = "insert into set_available_cans (available_cans) values (?)";
			pstmt = con.prepareStatement(sqlstmnt);

			pstmt.setInt(1, noOfCans);
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				Logger.info(MessageConstant.NO_ROWS_INSERTED+" "+rows);
			} else {
				Logger.info(MessageConstant.NO_ROWS_INSERTED);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			pstmt.close();
		}
	}

	public void orderedcans(int order) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql_stmnt = "insert into ordered_cans(cans_ordered) values (?)";
			pstmt = con.prepareStatement(sql_stmnt);
			pstmt.setInt(1, order);
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				Logger.info(MessageConstant.NUMBER_OF_ROWS_INSERTED_AFTER_ORDERED+" "+rows);
			} else {
				Logger.info(MessageConstant.NO_ROWS_INSERTED_AFTER_ORDERED);
			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			con.close();
			pstmt.close();
		}
	}

	public void reservecans(int can)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionUtil.getConnection();
			String sqlstmnt = "update reserve_cans set cans_reserved=?";
			pstmt = con.prepareStatement(sqlstmnt);
			pstmt.setInt(1,can);
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				Logger.info(MessageConstant.NUMBER_OF_ROWS_INSERTED_AFTER_RESERVED+" "+rows);
			} else {
				Logger.info(MessageConstant.NO_ROWS_INSERTED_AFTER_RESERVED);
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	
	
	
	
	public void availablecansafterordered(int can) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionUtil.getConnection();
			String sqlstmnt = "insert into available_cans_after_ordered(available_cans_after_ordered) values (?)";
			pstmt = con.prepareStatement(sqlstmnt);
			pstmt.setInt(1, can);
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				Logger.info(MessageConstant.NUMBER_OF_ROWS_INSERTED_AFTER_ORDER_GOT_COMPLETED+" "+rows);
			} else {
				Logger.info(MessageConstant.NO_ROWS_INSERTED_AFTER_ORDER_GOT_COMPLETED);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			con.close();
			pstmt.close();
		}
	}

	public UserDetail findByUsername(String name, String password) throws SQLException {
		UserDetail ud = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql_stmnt = "select name,password from Login where name = ? and password = ?";
			pstmt = con.prepareStatement(sql_stmnt);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			

			if (rs.next()) {
				ud = new UserDetail();
				ud = toRow(rs);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
		return ud;

	}

	private UserDetail toRow(ResultSet rs) throws SQLException {

		String name = rs.getString("Name");
		String password = rs.getString("password");
		UserDetail user = new UserDetail();
		user.setName(name);
		user.setPassword(password);
		System.out.println("Login Success");
		return user;
	}


	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}


	public void setPhone_number(String phone_number) {
		// TODO Auto-generated method stub
		
	}


	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}


	public void setEmail_id(String email_id) {
		// TODO Auto-generated method stub
		
	}


	public void register(UserDetail use)
			throws SQLException, DBException {
		Connection con = ConnectionUtil.getConnection();
		System.out.println("Welcome To GS Water Treatment Plant");
		PreparedStatement pstmt = null;
		int rows;
			String sqlstmnt = "insert into login(name,mobile_number,Email_id,password) values (?,?,?,?)";
			try
			{
			pstmt = con.prepareStatement(sqlstmnt);
			pstmt.setString(1, use.getName());
			pstmt.setLong(2, use.getMobilenumber());
			pstmt.setString(3, use.getEmailid());
			pstmt.setString(4, use.getPassword());
		rows = pstmt.executeUpdate();
		//	System.out.println("Number Of Rows Inserted:" + " " + rows + "\n");
			System.out.println("Your Details Have Been Registered Successfully");
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			//LocalDateTime now = LocalDateTime.now();
			//System.out.println(dtf.format(now));
			//System.out.println("Redirecting To HomePage \n");
			//HomePage.homepage();
		}catch(SQLException e){
			throw new DBException("Email already Exists");
		}
		}
	public  List<UserDetail> findAll() throws SQLException {
	    Connection con = ConnectionUtil.getConnection();
	    String sql = "select available_cans,date from set_available_cans";
	    PreparedStatement pst = con.prepareStatement(sql);
	    ResultSet rs = pst.executeQuery();
	    List<UserDetail> list = new ArrayList<UserDetail>();
	    while (rs.next()) {
	        
	    	UserDetail user= new UserDetail();
	    	user.setAvailablecans(rs.getInt("available_cans"));
	    	user.setDate(rs.getString("date"));
	        list.add(user);
	    }
	    return list;
	}
	private static UserDetail toRow1(ResultSet rs) throws SQLException {
	    
	    Integer available_cans= rs.getInt("available_cans");
	    String date=rs.getString("date");
	    UserDetail user = new UserDetail();
	    user.getAvailablecans();
	    
	    
	    return user;
	}
	
	}

