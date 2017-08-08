import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConfiguration extends SQLConnection {

	public void createDatabase(String db_name) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();

		// Statement string
		StringBuffer sb = new StringBuffer("CREATE DATABASE IF NOT EXISTS ");
		sb.append(db_name);
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

	public void addUser(String db_name, String user, String host, String pass) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();

		// Statement string
		StringBuffer sb = new StringBuffer("GRANT ALL PRIVILEGES ON ");
		sb.append(db_name);
		sb.append(".* TO '");
		sb.append(user);
		sb.append("'@'");
		sb.append(host);
		sb.append("' IDENTIFIED BY '");
		sb.append(pass);
		sb.append("';");

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
}