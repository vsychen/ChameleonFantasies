import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	private String user;
	private String pass;
	private String path;

	public SQLConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	public void initialize(String user, String pass, String path) {
		setUser(user);
		setPass(pass);
		setPath(path);
	}

	public String getUser() {
		return user;
	}

	private void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	private void setPass(String pass) {
		this.pass = pass;
	}

	public String getPath() {
		return path;
	}

	private void setPath(String path) {
		this.path = path;
	}

	protected Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/" + getPath();
		return DriverManager.getConnection(url, getUser(), getPass());
	}
}