package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program_Atualizar_Dados {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller  "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ?)"
					);
			//Atribuir valores para as ?;
			
			st.setDouble(1, 200.0);
			st.setInt(2, 2);
			
			int rowAffected = st.executeUpdate(); // Informa quantas linhas foram afetadas
			
			System.out.println("Done!! Row affected: " + rowAffected);
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
