package com.kitahost.naivebayes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database{
	private Connection connect=null;
	private Statement statement=null;
	
	public Database(String host, String user, String pass, String db){
		try {
			this.connect = DriverManager.getConnection("jdbc:mysql://"+host+"/"+db+"?"
					+ "user="+user+"&password="+pass);
			this.statement = connect.createStatement();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Connection failed!\nPlease configure database in source (Model.java).","Error",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void createTable(){
		try {
			this.statement.execute("DROP TABLE IF EXISTS `data`,`header`");
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
