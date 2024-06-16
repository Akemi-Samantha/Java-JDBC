package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		//Conectar o banco e preparar uma consulta para buscar todos os departamentos;
		
		try {
			conn = DB.getConnection(); // Conectar ao banco de dados
			st = conn.createStatement();
			rs = st.executeQuery("select * from department");
			//Percorrendo um ResultSet
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + " -> " + rs.getString("Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
