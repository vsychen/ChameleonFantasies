import java.io.Console;
import java.sql.SQLException;

public class SQLIntegration {

	public void runSetup(String root_pass, String user, String host, String pass, String db_name) throws SQLException {
		String root_user = "root";

		SQLConfiguration sqlc = new SQLConfiguration();
		sqlc.initialize(root_user, root_pass, "");
		sqlc.createDatabase(db_name);
		sqlc.addUser(db_name, user, host, pass);

		SQLTables sqlt = new SQLTables();
		sqlt.initialize(user, pass, db_name);
		sqlt.dropCFDatabases();
		sqlt.createCFDatabases();
	}

	public static void main(String[] args) {
		SQLIntegration sqli = new SQLIntegration();
		Console console = System.console();

		if (console == null)
			System.exit(0);

		char[] root_pass = console.readPassword("Root password: ");
		String new_user = "cf";
		String host = "localhost";
		String new_pass = "1234";
		String db_name = "chameleonfantasies";
		String change = "";

		change = console.readLine("new_user default value: 'cf'. Change new_user value? (Y/N) ");
		if (change.toUpperCase().equals("Y"))
			new_user = console.readLine("new_user new value: ");

		change = console.readLine("host default value: 'localhost'. Change host value? (Y/N) ");
		if (change.toUpperCase().equals("Y"))
			host = console.readLine("host new value: ");

		change = console.readLine("new_pass default value: '1234'. Change new_pass value? (Y/N) ");
		if (change.toUpperCase().equals("Y"))
			new_pass = console.readLine("new_pass new value: ");

		change = console
				.readLine("database_name default value: 'chameleonfantasies'. Change database_name value? (Y/N) ");
		if (change.toUpperCase().equals("Y"))
			db_name = console.readLine("database_name new value: ");

		try {
			sqli.runSetup(String.valueOf(root_pass), new_user, host, new_pass, db_name);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}