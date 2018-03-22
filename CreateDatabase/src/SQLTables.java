import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTables extends SQLConnection {

	private void createTable(String name, String[] param_name, String[] param_type) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();

		// Statement string
		StringBuffer sb = new StringBuffer("CREATE TABLE ");
		sb.append(name);
		sb.append(" (ID INTEGER NOT NULL, ");

		for (int i = 0; i < param_name.length; i++) {
			sb.append(param_name[i]);
			sb.append(" ");
			sb.append(param_type[i]);
			sb.append(", ");
		}

		sb.append("PRIMARY KEY(ID));");

		try {
			stmt.execute(sb.toString());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}
	}

	private void dropTable(String name) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();

		// Statement string
		StringBuffer sb = new StringBuffer("DROP TABLE ");
		sb.append(name);
		sb.append(";");

		try {
			stmt.execute(sb.toString());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}
	}

	public void createCFDatabases() {
		// CUSTOMER
		String customer_table_name = "CUSTOMER";
		String[] customer_param_name = { "NAME", "CPF", "SPENDING" };
		String[] customer_param_type = { "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)" };

		try {
			createTable(customer_table_name, customer_param_name, customer_param_type);
			System.out.println("Tabela CUSTOMER criada com sucesso.");
		} catch (SQLException sqle) {
			System.out.println("Não foi possível criar a tabela CUSTOMER.");
		}

		// EMPLOYEE
		String employee_table_name = "EMPLOYEE";
		String[] employee_param_name = { "NAME", "CPF", "STREET", "CITY", "STATE", "COUNTRY", "ZIPCODE", "EMAIL",
				"PHONE", "JOB", "SALARY" };
		String[] employee_param_type = { "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)",
				"VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)" };

		try {
			createTable(employee_table_name, employee_param_name, employee_param_type);
			System.out.println("Tabela EMPLOYEE criada com sucesso.");
		} catch (SQLException sqle) {
			System.out.println("Não foi possível criar a tabela EMPLOYEE.");
		}

		// FANTASY
		String fantasy_table_name = "FANTASY";
		String[] fantasy_param_name = { "NAME", "CODE", "BUY_PRICE", "SELL_PRICE", "QUANTITY",
				// Hat
				"HAT_TYPE", "HAT_COLOR", "HAT_ORNAMENT", "HAT_STAMP",
				// Top
				"TOP_TYPE", "TOP_COLOR", "TOP_ORNAMENT", "TOP_STAMP",
				// Bottom
				"BOTTOM_TYPE", "BOTTOM_COLOR", "BOTTOM_ORNAMENT", "BOTTOM_STAMP",
				// Arms
				"ARMS_TYPE", "ARMS_COLOR", "ARMS_ORNAMENT", "ARMS_STAMP",
				// Shoes
				"SHOES_TYPE", "SHOES_COLOR", "SHOES_ORNAMENT", "SHOES_STAMP" };
		String[] fantasy_param_type = { "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)",
				// Hat
				"VARCHAR(255)", "VARCHAR(255)", "BOOLEAN", "BOOLEAN",
				// Top
				"VARCHAR(255)", "VARCHAR(255)", "BOOLEAN", "BOOLEAN",
				// Bottom
				"VARCHAR(255)", "VARCHAR(255)", "BOOLEAN", "BOOLEAN",
				// Arms
				"VARCHAR(255)", "VARCHAR(255)", "BOOLEAN", "BOOLEAN",
				// Shoes
				"VARCHAR(255)", "VARCHAR(255)", "BOOLEAN", "BOOLEAN" };

		try {
			createTable(fantasy_table_name, fantasy_param_name, fantasy_param_type);
			System.out.println("Tabela FANTASY criada com sucesso.");
		} catch (SQLException sqle) {
			System.out.println("Não foi possível criar a tabela FANTASY.");
		}

		// ACCESS
		String access_table_name = "ACCESS";
		String[] access_param_name = { "CPF", "PASSWORD", "JOB" };
		String[] access_param_type = { "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)" };

		try {
			createTable(access_table_name, access_param_name, access_param_type);
			System.out.println("Tabela ACCESS criada com sucesso.");
		} catch (SQLException sqle) {
			System.out.println("Não foi possível criar a tabela ACCESS.");
		}
	}

	public void dropCFDatabases() {
		try { // CUSTOMER
			dropTable("CUSTOMER");
		} catch (SQLException sqle) {
			System.out.println("Tabela CUSTOMER não existe.");
		}

		try { // EMPLOYEE
			dropTable("EMPLOYEE");
		} catch (SQLException sqle) {
			System.out.println("Tabela EMPLOYEE não existe.");
		}

		try { // FANTASY
			dropTable("FANTASY");
		} catch (SQLException sqle) {
			System.out.println("Tabela FANTASY não existe.");
		}

		try { // ACCESS
			dropTable("ACCESS");
		} catch (SQLException sqle) {
			System.out.println("Tabela FANTASY não existe.");
		}
	}
}