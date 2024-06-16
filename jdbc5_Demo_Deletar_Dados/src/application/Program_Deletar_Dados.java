package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DBIntegrityException;

public class Program_Deletar_Dados {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"DELETE from department "
					+ "WHERE "
					+ "Id = ? "
					);
			//Atribuir valores para as ?;
			
			st.setInt(1, 6);
	
			
			int rowAffected = st.executeUpdate(); // Informa quantas linhas foram afetadas
			
			System.out.println("Done!! Row affected: " + rowAffected);
		}
		
		catch(SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		

	}
}
