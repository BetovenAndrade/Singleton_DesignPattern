/*
 * Example of using the SINGLETON Design Pattern in the database connection (JDBC). 
 * It allows only one connection to be made.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciarConexao{
	public static Connection c = null;
	
	private static String driverAddress= ""; //Example: "com.mysql.cj.jdbc.Driver";
	private static String db_URL= "";        //Example: "jdbc:mysql://localhost:3306/teste?useTimezone=true&serverTimezone=UTC";
	private static String db_user= "";       //Example: "root";
	private static String db_password= "";   //Example: "abc123"; 
	
	//PRIVATE! No externally accessible
	private GerenciarConexao(){
		
		//Returns the object of the corresponding class to the JDBC driver
		try{
			Class.forName(driverAddress);
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		
		//Makes the connection to the database  
		try{
			c = DriverManager.getConnection(db_URL, db_user, db_password);
			System.out.println("Conectado!");
		}
		catch(SQLException s){
			System.err.println(s);
		}
	}
	
	//Accessible method
	public static Connection getConexao(String dA, String URL, String user, String password){
		if(c == null){
			driverAddress= dA;
			db_URL= URL;
			db_user= user;
			db_password= password;
			
			new GerenciarConexao();
		}
		return c;
	}
}