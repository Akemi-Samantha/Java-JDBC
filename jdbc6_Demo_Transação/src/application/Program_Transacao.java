package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DBException;

public class Program_Transacao {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		try {
			
			conn = DB.getConnection();
			conn.setAutoCommit(false); // Não confirma as operações automaticamente...Por padrão ficarão pendetes por uma confirmação explicita do programador 
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			/* 
			 //Simulando uma falha para testar a transação dos dados
			int x = 1;
			if(x < 2) {
				throw new SQLException("Fake Error");
			}
			 */

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit(); // Para confirmar que a transação terminou
			
			System.out.println("rows1 -> " + rows1 );
			System.out.println("rows2 -> " + rows2 );
			

		}
		
		catch(SQLException e) { // Criar uma lógica para que volte como era o banco se ouver um erro 
			try{
				conn.rollback();
				throw new DBException("Transaction rolled back! Cased by: " + e.getMessage());
			}
			catch(SQLException e1){
				throw new DBException("Error trying to rollback! Caused by: " + e1.getMessage());			
				
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		

	}
}
