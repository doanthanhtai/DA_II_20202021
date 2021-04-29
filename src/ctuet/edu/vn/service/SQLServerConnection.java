package ctuet.edu.vn.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnection {

	protected Connection conn = null;
	
	public SQLServerConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connecttionURL = "jdbc:sqlserver://" + "DESKTOP-138V8NI" + ":1433;databaseName = dbQLShopTT;user= sa;password = 123456 ;integratedSercurity = true;";
			conn = DriverManager.getConnection(connecttionURL);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
