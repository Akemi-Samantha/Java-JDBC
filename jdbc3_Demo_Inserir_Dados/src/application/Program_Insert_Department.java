package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program_Insert_Department {

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
					"INSERT INTO department (Name)"
					+ "values ('D1'), ('D2')",
					Statement.RETURN_GENERATED_KEYS);
			
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
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
