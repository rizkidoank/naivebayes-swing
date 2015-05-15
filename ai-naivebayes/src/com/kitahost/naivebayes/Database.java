package com.kitahost.naivebayes;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database{
	Connection connect=null;
	Statement statement=null;
	
	public Database(String host, String user, String pass, String db){
		try {
			connect = DriverManager.getConnection("jdbc:mysql://"+host+"/"+db+"?"
					+ "user="+user+"&password="+pass);
			statement = connect.createStatement();
			this.createTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createTable(){
		try {
			String sqlAttributes = "CREATE TABLE IF NOT EXISTS header " +
                   "(header varchar(255),"
                   + "value varchar(255))";
			String sqlData = "CREATE TABLE IF NOT EXISTS data ("
					+ "`buying` varchar(255),"
					+ "`maint` varchar(255),"
					+ "`doors` varchar(255),"
					+ "`persons` varchar(255),"
					+ "`lug_boot` varchar(255),"
					+ "`safety` varchar(255),"
					+ "`acceptability` varchar(255)"
					+ ")";
			statement.execute(sqlAttributes);
			System.out.println(sqlData);
			statement.execute(sqlData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnect() {
		return connect;
	}

	public Statement getStatement() {
		return statement;
	}
	
	
}
