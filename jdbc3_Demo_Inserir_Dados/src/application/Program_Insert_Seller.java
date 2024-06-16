package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program_Insert_Seller {

	public static void main(String[] args) {

		//Inserir dados Demo
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			//primeiro conectar com o banco
			 conn = DB.getConnection();
			//Espera uma string para anotar o comando sql
			 st = conn.prepareStatement( 
					 "INSERT INTO seller" 
							 + "(Name, Email, BirthDate, BaseSalary, DepartmentId)" 
							 +	 "VALUES" 
							 + 	 "(?, ? , ? , ? ,?)",
							 Statement.RETURN_GENERATED_KEYS); 
			 
			st.setString(1, "Call Purple");
			st.setString(2, "call@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			int rowAffected = st.executeUpdate();
			
			if(rowAffected > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				//percorrer resulSet
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done!! Id = " + id);
				}
			}
			else {
				
				System.out.println("No row affected!! ");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace(); 	
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
