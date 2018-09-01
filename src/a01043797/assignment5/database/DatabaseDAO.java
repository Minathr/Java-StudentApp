package a01043797.assignment5.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDAO {

	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://Beangrinder.bcit.ca";
	private String username = "javastudent";
	private String password = "compjava";
	
	private Connection connection;
	
	public void loadDriver() {
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean createTable (String createQuery) {
		boolean success = false;
		try {
			Statement statement = connection.createStatement();
			success = statement.execute(createQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public ResultSet findTable (String tableName) {
		ResultSet foundTable = null;
		try {
			DatabaseMetaData meta = connection.getMetaData();
			foundTable = meta.getTables(null, null, tableName, new String[] { "TABLE" });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foundTable;
	}
	
	public boolean deleteTable (String deleteQuery) {
		boolean success = false;
		try {
			Statement statement = connection.createStatement();
			success = statement.execute(deleteQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	public int updateData (String insertQuery) {
		int affectedRecords = 0;
		try {
			Statement statement = connection.createStatement();
			affectedRecords = statement.executeUpdate(insertQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRecords;
	}
	
	public ResultSet selectData (String selectQuery) {
		ResultSet results = null;
		try {
			Statement statement = connection.createStatement();
			results = statement.executeQuery(selectQuery); //ResultSet indexes starts at 1!!!!
			while(results.next()) {
				System.out.print(results.getString(1) + ", ");
				System.out.print(results.getString(2) + ", ");
				System.out.print(results.getString(3) + ", ");
				System.out.print(results.getString(4) + ", ");
				System.out.print(results.getString(5) + ", ");
				System.out.println(results.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void shutDown() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
