package com.app.tests;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.app.utilities.ConfigurationReader;

public class JDBCConnection {
	
	String oracleDbUrl = ConfigurationReader.getProperty("oracledb.url");
    
    String oracleDbUsername = "hr";
    
    String oracleDbPassword = "hr";
	
    @Test(enabled = false)
    public void oracleJDBC() throws SQLException {
    	Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDbUsername, oracleDbPassword);
    	//Statement statement = connection.createStatement();
    	Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    	ResultSet resultSet = statement.executeQuery("SELECT* FROM countries");
    	
    	// resultSet.next() -> move to next row. It returns boolean: true -> it has next row, false -> it does't have next
    	// getObject(colname/index) -> read data from column
    	
//    	while(resultSet.next()) {
//    	System.out.println(resultSet.getString(1)+" "+resultSet.getString("country_name")+" "+resultSet.getInt("region_id"));
//    	}
    	
    	// find out how many records in the resultset
    	resultSet.last();
    	int rowsCount = resultSet.getRow();
    	System.out.println("Number of rows: "+ rowsCount);
    	
    	resultSet.beforeFirst();
    	while(resultSet.next()) {
    	System.out.println(resultSet.getString(1)+" "+resultSet.getString("country_name")+" "+resultSet.getInt("region_id"));
    	}
    	
    	
    	resultSet.close();
    	statement.close();
    	connection.close();
    }
    
    @Test
    public void jdbcMetadata() throws SQLException {
    	Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDbUsername, oracleDbPassword);
    	//Statement statement = connection.createStatement();
    	Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    	
    	String sql = "select employee_id, last_name, job_id, salary from employees";
    	//String sql = "select* from employees";
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	// Database metadata
    	DatabaseMetaData dbMetadata = connection.getMetaData();
    	System.out.println("User: "+dbMetadata.getUserName());
    	System.out.println("Database type: "+dbMetadata.getDatabaseProductName());
    	
    	// resultSet metadata
    	ResultSetMetaData rsMedata = resultSet.getMetaData();
//    	System.out.println("Columns count: "+rsMedata.getColumnCount());
//    	System.out.println(rsMedata.getColumnName(1));
    	
    	for(int i = 1; i <= rsMedata.getColumnCount(); i++) {
    		System.out.println(i+" -> "+rsMedata.getColumnName(i));
    	}	
    	
    	// Throw resultset into a List of Map
    	// Create a List ob Maps
    	List<Map<String, Object>> list = new ArrayList<>();
    	ResultSetMetaData rsMdata = resultSet.getMetaData();
    	int colCount = rsMdata.getColumnCount();
    	
    	while(resultSet.next()) {
    		Map<String,Object> rowMap = new HashMap<>();
    		for (int i = 1; i < colCount; i++) {
				rowMap.put(rsMedata.getColumnName(i), resultSet.getObject(i));
			}
    		list.add(rowMap);
    	}
    	
    	// print all emp Id for list 
    	
    	for(Map<String, Object> map: list) {
    		System.out.println(map.get("EMPLOYEE_ID"));
    	}
    	
    	
    	
    	
     	resultSet.close();
    	statement.close();
    	connection.close();
    }
}
