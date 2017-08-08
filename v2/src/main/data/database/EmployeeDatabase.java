package main.data.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import main.data.entities.Address;
import main.data.entities.Employee;
import main.data.exceptions.DatabaseErrorException;

public class EmployeeDatabase implements IDatabase<Employee>, Serializable {
	private static final long serialVersionUID = -8400508193995897058L;
	private final String DIRECTORY_PATH = "../v2/external_files/";
	private final String FILE_PATH = "../v2/external_files/employee_database.json/";

	public EmployeeDatabase() {
		checkPath();
	}

	public synchronized boolean isEmpty() throws DatabaseErrorException {
		return loadDatabase().isEmpty();
	}

	public synchronized void add(Employee e) throws DatabaseErrorException {
		List<Employee> employees = loadDatabase();
		String cpf = e.getCpf();
		int compare;

		if (employees.isEmpty()) {
			employees.add(e);
			saveDatabase(employees);
			return;
		}

		for (int i = 0; i < employees.size(); i++) {
			if ((compare = compare(cpf, employees.get(i).getCpf())) == 0) {
				throw new DatabaseErrorException("Item já existe na base de dados.");
			} else if (compare < 0) {
				employees.add(i, e);
				saveDatabase(employees);
				return;
			} else if (compare > 0 && (i == employees.size() - 1)) {
				employees.add(e);
				saveDatabase(employees);
				return;
			}
		}

		saveDatabase(employees);
	}

	public synchronized void edit(String cpf, Employee e) throws DatabaseErrorException {
		List<Employee> employees = loadDatabase();

		for (int i = 0; i < employees.size(); i++) {
			if (compare(cpf, employees.get(i).getCpf()) == 0) {
				employees.set(i, e);
				saveDatabase(employees);
				return;
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized Employee search(String cpf) throws DatabaseErrorException {
		List<Employee> employees = loadDatabase();

		for (int i = 0; i < employees.size(); i++) {
			if (compare(cpf, employees.get(i).getCpf()) == 0) {
				return employees.get(i);
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized Employee remove(String cpf) throws DatabaseErrorException {
		List<Employee> employees = loadDatabase();
		Employee e;

		for (int i = 0; i < employees.size(); i++) {
			if (compare(cpf, employees.get(i).getCpf()) == 0) {
				e = employees.remove(i);
				saveDatabase(employees);
				return e;
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized void removeAll() throws DatabaseErrorException {
		List<Employee> employees = loadDatabase();
		int i = employees.size() - 1;

		for (; i >= 0; i--) {
			employees.remove(i);
		}

		saveDatabase(employees);
	}

	public synchronized String list() throws DatabaseErrorException {
		List<Employee> employees = loadDatabase();
		StringBuffer sb = new StringBuffer("");
		Employee e;

		for (int i = 0; i < employees.size(); i++) {
			e = employees.get(i);
			sb.append(e.toString()).append("\n");
		}

		return sb.toString();
	}

	private void checkPath() {
		File d = new File(DIRECTORY_PATH);

		if (!d.exists())
			d.mkdirs();
	}

	private int compare(String s1, String s2) {
		if (s1 == null)
			return -1;
		else if (s2 == null)
			return 1;
		else if (s1.equals(s2))
			return 0;
		else
			return s1.compareTo(s2);
	}

	private synchronized List<Employee> loadDatabase() throws DatabaseErrorException {
		List<Employee> employees = new ArrayList<Employee>();
		File f = new File(FILE_PATH);

		if (f.exists()) {
			try {
				JSONTokener jToken = new JSONTokener(new FileInputStream(f));
				JSONObject jObj = (JSONObject) jToken.nextValue();
				JSONArray jArr = jObj.getJSONArray("employees");

				for (int i = 0; i < jArr.length(); i++) {
					jObj = jArr.getJSONObject(i);

					JSONObject jObjAddress = jObj.getJSONObject("address");
					Address a = new Address(jObjAddress.getString("street"), jObjAddress.getString("city"),
							jObjAddress.getString("state"), jObjAddress.getString("country"));

					Employee e = new Employee(jObj.getString("name"), jObj.getString("cpf"), jObj.getString("password"),
							jObj.getString("email"), a, jObj.getString("job"), jObj.getDouble("salary"));
					employees.add(e);
				}
			} catch (FileNotFoundException | JSONException exc) {
				throw new DatabaseErrorException(exc.getMessage());
			}
		}

		return employees;
	}

	private synchronized void saveDatabase(List<Employee> employees) throws DatabaseErrorException {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), "UTF-8"));
			JSONArray jArr = new JSONArray(employees);
			JSONObject jObj = new JSONObject();
			jObj.put("employees", jArr);

			out.write(jObj.toString());
			out.close();
		} catch (JSONException | IOException e) {
			throw new DatabaseErrorException(e.getMessage());
		}
	}
}