package com.app.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;

import org.testng.annotations.Test;

import com.app.utilities.DBType;
import com.app.utilities.DBUtility;

public class EmployeesDbTest  {
  @Test
  public void countTest() throws SQLException {
	  // Connect to oracle database 
	  // run following sql query
	  // select * from employees where job_id = 'IT_PROG' 
	  // more than 0 records should be return 
	  
	  DBUtility.establishConnection(DBType.ORACLE);
	  int rowsCount = DBUtility.getRowsCount("SELECT * FROM employees WHERE job_id = 'IT_PROG'");
	  System.out.println(rowsCount);
	  assertTrue(rowsCount > 0);
	  DBUtility.closeConnections();
  }
  
  @Test
  public void nameTestByID() throws SQLException {
	  // Connected to oracle database
	  // employees full name record with Employee id 105
	  // should be David Austin
	  DBUtility.establishConnection(DBType.ORACLE);
	  String name = DBUtility.runSQLQuery(
			  "SELECT first_name||' '||last_name as NAME FROM employees WHERE employee_id=105"
			  ).get(0).get("NAME").toString();
	  System.out.println(DBUtility.getRowsCount("SELECT first_name||' '||last_name as NAME FROM employees WHERE employee_id=105"));
	  System.out.println(name);
	  assertEquals(name,"David Austin");
  }
  
  /*
   *    
   */
  
  
  
  
  
  
  
}
