package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection con = null;

	public static Connection getConnection() { // metodo que conecta ao banco de dados

		if (con == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl"); // pegando url
				con = DriverManager.getConnection(url, props); // instanciando a conexao
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
		return con;

	}

	public static void closeConnection() { // metodo para fechar conexao.
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() { // metodo que carrega as propriedadas do arquivo db.properties
		try (FileInputStream fs = new FileInputStream("db.properties")) {

			Properties props = new Properties();
			props.load(fs);
			return props;

		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}

	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			}
		}

	}
	public static void closeResultSet(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
