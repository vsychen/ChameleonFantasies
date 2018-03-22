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

import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

public class CustomerDatabase implements IDatabase<Customer>, Serializable {
	private static final long serialVersionUID = -7357618576259425348L;
	private final String DIRECTORY_PATH = "../v2/external_files/";
	private final String FILE_PATH = "../v2/external_files/customer_database.json/";

	public CustomerDatabase() {
		checkPath();
	}

	public synchronized boolean isEmpty() throws DatabaseErrorException {
		return loadDatabase().isEmpty();
	}

	public synchronized void add(Customer c) throws DatabaseErrorException {
		List<Customer> customers = loadDatabase();
		String cpf = c.getCpf();
		int compare;

		if (customers.isEmpty()) {
			customers.add(c);
			saveDatabase(customers);
			return;
		}

		for (int i = 0; i < customers.size(); i++) {
			if ((compare = compare(cpf, customers.get(i).getCpf())) == 0) {
				throw new DatabaseErrorException("Item já existe na base de dados.");
			} else if (compare < 0) {
				customers.add(i, c);
				saveDatabase(customers);
				return;
			} else if (compare > 0 && (i == customers.size() - 1)) {
				customers.add(c);
				saveDatabase(customers);
				return;
			}
		}

		saveDatabase(customers);
	}

	public synchronized void edit(String cpf, Customer c) throws DatabaseErrorException {
		List<Customer> customers = loadDatabase();

		for (int i = 0; i < customers.size(); i++) {
			if (compare(cpf, customers.get(i).getCpf()) == 0) {
				customers.set(i, c);
				saveDatabase(customers);
				return;
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized Customer search(String cpf) throws DatabaseErrorException {
		List<Customer> customers = loadDatabase();

		for (int i = 0; i < customers.size(); i++) {
			if (compare(cpf, customers.get(i).getCpf()) == 0) {
				return customers.get(i);
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized Customer remove(String cpf) throws DatabaseErrorException {
		List<Customer> customers = loadDatabase();
		Customer c;

		for (int i = 0; i < customers.size(); i++) {
			if (compare(cpf, customers.get(i).getCpf()) == 0) {
				c = customers.remove(i);
				saveDatabase(customers);
				return c;
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized void removeAll() throws DatabaseErrorException {
		List<Customer> customers = loadDatabase();
		int i = customers.size() - 1;

		for (; i >= 0; i--) {
			customers.remove(i);
		}

		saveDatabase(customers);
	}

	public synchronized String list() throws DatabaseErrorException {
		List<Customer> customers = loadDatabase();
		StringBuffer sb = new StringBuffer("");
		Customer c;

		for (int i = 0; i < customers.size(); i++) {
			c = customers.get(i);
			sb.append(c.toString()).append("\n");
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

	private synchronized List<Customer> loadDatabase() throws DatabaseErrorException {
		List<Customer> customers = new ArrayList<Customer>();
		File f = new File(FILE_PATH);

		if (f.exists()) {
			try {
				JSONTokener jToken = new JSONTokener(new FileInputStream(f));
				JSONObject jObj = (JSONObject) jToken.nextValue();
				JSONArray jArr = jObj.getJSONArray("customers");

				for (int i = 0; i < jArr.length(); i++) {
					jObj = jArr.getJSONObject(i);
					Customer c = new Customer(jObj.getString("name"), jObj.getString("cpf"));
					c.setSpending(jObj.getDouble("spending"));
					customers.add(c);
				}
			} catch (FileNotFoundException | JSONException e) {
				throw new DatabaseErrorException(e.getMessage());
			}
		}

		return customers;
	}

	private synchronized void saveDatabase(List<Customer> customers) throws DatabaseErrorException {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), "UTF-8"));
			JSONArray jArr = new JSONArray(customers);
			JSONObject jObj = new JSONObject();
			jObj.put("customers", jArr);

			out.write(jObj.toString());
			out.close();
		} catch (JSONException | IOException e) {
			throw new DatabaseErrorException(e.getMessage());
		}
	}
}