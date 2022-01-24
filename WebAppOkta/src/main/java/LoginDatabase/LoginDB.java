package LoginDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import LoginBean.loginBean;

public class LoginDB {
	private String dbUrl = "jdbc:mysql://localhost:3306/logindb";
	private String dbUsername = "root";
	private String dbPassword = "root";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public Boolean validate(loginBean loginbean) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		Boolean status = false;
		
		String sql = "select * from login where username = ? and password = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,loginbean.getUsername());
			ps.setString(2,loginbean.getPassword());
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return status;
		
	}


	public boolean register(loginBean loginbean) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		Boolean status = false;
		
		String sql = "insert into login (username, password ,tfa) values(?,?,?)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,loginbean.getUsername());
			ps.setString(2,loginbean.getPassword());
			ps.setString(3,"false");
			int rs = ps.executeUpdate();
			if(rs!=0) {
				status = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return status;
		
	}
	
	

	public Boolean insertkey(String secretKey,String user) {
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		Boolean status = false;
		
		
		
		String sql = "UPDATE `login` SET `skey` = ?, `tfa` = ? WHERE (`username` = ?);";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,secretKey);
			ps.setString(2,"true");
			ps.setString(3, user);
			int rs = ps.executeUpdate();
			
			if(rs!=0) {
				
				status = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return status;
		
	}
	
	public String getsecretKey(String user) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		String status = "blank";
		
		String sql = "select skey from login where username = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,user);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			status = rs.getString("skey");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
public Boolean insertTfa(String user) {
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		Boolean status = false;
		
		
		
		String sql = "update login set tfa = ? where username = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,"true");
			ps.setString(2, user);
			int rs = ps.executeUpdate();
			if(rs!=0) {
				status = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return status;
		
	}


	public boolean tfaEnabled(loginBean loginbean) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		Boolean status = false;
		
		String sql = "select * from login where username = ? and tfa = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,loginbean.getUsername());
			ps.setString(2,"true");
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return status;
	}
}