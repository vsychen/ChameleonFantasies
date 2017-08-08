package br.com.chameleonfantasies.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	private String user;
	private String pass;
	private String pathToConnect;

	public SQLConnection() {
		setUser("");
		setPass("");
		setPathToConnect("");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getUser() {
		return user;
	}

	public SQLConnection setUser(String user) {
		this.user = user;
		return this;
	}

	public String getPass() {
		return pass;
	}

	public SQLConnection setPass(String pass) {
		this.pass = pass;
		return this;
	}

	public String getPathToConnect() {
		return pathToConnect;
	}

	public SQLConnection setPathToConnect(String pathToConnect) {
		this.pathToConnect = pathToConnect;
		return this;
	}

	protected Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/" + getPathToConnect();
		return DriverManager.getConnection(url, this.getUser(), this.getPass());
	}
}