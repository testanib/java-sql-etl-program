package main;
import java.io.*;
import java.sql.*;
import java.util.*;

public class main{
	public static void main(String args[]) {
		String line = "";  
		String splitBy = ",";  
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\oracle\\instantclient_19_12\\players.csv"));  
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{  
				String[] col = line.split(splitBy);    // use comma as separator  
				System.out.println(col[0] + ", " + col[1] + ", " + col[2] + ", " + col[3]);
				
				try{
					String query1 =  "INSERT INTO PLAYERS (PLAYER_NUM, PLAYER_LN, PLAYER_FN, PLAYER_POS)"
							 + "VALUES ('" + col[0] + "', '" + col[1] + "', '"+ col[2] + "', '" + col[3] + "')";
			 
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@3.86.149.170:1521:WILMA", "UD_TESTANIB", "UD_TESTANIB");
					Statement stmt = con.createStatement();
					int count = stmt.executeUpdate(query1);
					System.out.println("Number of rows updated in database =  " + count);
				} catch(Exception e){
					System.out.println(e);
				}
			} 
		} catch(Exception e) {
			System.out.println(e);
		}
		try{  
		  FileWriter fw = new FileWriter("C:\\oracle\\instantclient_19_12\\players_output.csv");
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.86.149.170:1521:WILMA", "UD_TESTANIB", "UD_TESTANIB");
		  String query  = "select * from PLAYERS";
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(query);
		  while(rs.next()){
			  fw.append(rs.getString(1));
			  fw.append(',');
			  fw.append(rs.getString(2));
			  fw.append(',');
			  fw.append(rs.getString(3));
			  fw.append(',');
			  fw.append(rs.getString(4));
			  fw.append('\n');
		  }
		  fw.flush();
		  fw.close();
		  conn.close();
		  System.out.println("CSV File is created successfully.");
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
}