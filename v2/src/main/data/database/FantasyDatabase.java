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

import main.data.entities.Cloth;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

public class FantasyDatabase implements IDatabase<Fantasy>, Serializable {
	private static final long serialVersionUID = -1972533590691305142L;
	private final String DIRECTORY_PATH = "../v2/external_files/";
	private final String FILE_PATH = "../v2/external_files/fantasy_database.json/";

	public FantasyDatabase() {
		checkPath();
	}

	public synchronized boolean isEmpty() throws DatabaseErrorException {
		return loadDatabase().isEmpty();
	}

	public synchronized void add(Fantasy f) throws DatabaseErrorException {
		List<Fantasy> fantasies = loadDatabase();
		String id = f.getId();
		int compare;

		if (fantasies.isEmpty()) {
			fantasies.add(f);
			saveDatabase(fantasies);
			return;
		}

		for (int i = 0; i < fantasies.size(); i++) {
			if ((compare = compare(id, fantasies.get(i).getId())) == 0) {
				throw new DatabaseErrorException("Item já existe na base de dados.");
			} else if (compare < 0) {
				fantasies.add(i, f);
				saveDatabase(fantasies);
				return;
			} else if (compare > 0 && (i == fantasies.size() - 1)) {
				fantasies.add(f);
				saveDatabase(fantasies);
				return;
			}
		}

		saveDatabase(fantasies);
	}

	public synchronized void edit(String id, Fantasy f) throws DatabaseErrorException {
		List<Fantasy> fantasies = loadDatabase();

		for (int i = 0; i < fantasies.size(); i++) {
			if (compare(id, fantasies.get(i).getId()) == 0) {
				fantasies.set(i, f);
				saveDatabase(fantasies);
				return;
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized Fantasy search(String id) throws DatabaseErrorException {
		List<Fantasy> fantasies = loadDatabase();

		for (int i = 0; i < fantasies.size(); i++) {
			if (compare(id, fantasies.get(i).getId()) == 0) {
				return fantasies.get(i);
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized Fantasy remove(String id) throws DatabaseErrorException {
		List<Fantasy> fantasies = loadDatabase();
		Fantasy f;

		for (int i = 0; i < fantasies.size(); i++) {
			if (compare(id, fantasies.get(i).getId()) == 0) {
				f = fantasies.remove(i);
				saveDatabase(fantasies);
				return f;
			}
		}

		throw new DatabaseErrorException("Item inexistente na base de dados.");
	}

	public synchronized void removeAll() throws DatabaseErrorException {
		List<Fantasy> fantasies = loadDatabase();
		int i = fantasies.size() - 1;

		for (; i >= 0; i--) {
			fantasies.remove(i);
		}

		saveDatabase(fantasies);
	}

	public synchronized String list() throws DatabaseErrorException {
		List<Fantasy> fantasies = loadDatabase();
		StringBuffer sb = new StringBuffer("");
		Fantasy f;

		for (int i = 0; i < fantasies.size(); i++) {
			f = fantasies.get(i);
			sb.append(f.toString()).append("\n");
		}

		return sb.toString();
	}

	private synchronized void checkPath() {
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

	private synchronized List<Fantasy> loadDatabase() throws DatabaseErrorException {
		List<Fantasy> fantasies = new ArrayList<Fantasy>();
		File f = new File(FILE_PATH);

		if (f.exists()) {
			try {
				JSONTokener jToken = new JSONTokener(new FileInputStream(f));
				JSONObject jObj = (JSONObject) jToken.nextValue();
				JSONArray jArr = jObj.getJSONArray("fantasies");

				for (int i = 0; i < jArr.length(); i++) {
					jObj = jArr.getJSONObject(i);
					Cloth hat = null, top = null, bottom = null, arms = null, shoes = null;

					if (jObj.has("hat")) {
						JSONObject jObjHat = jObj.getJSONObject("hat");
						hat = new Cloth(jObjHat.getString("type"), jObjHat.getString("color"),
								jObjHat.getBoolean("ornate"), jObjHat.getBoolean("stamped"));
					}

					if (jObj.has("top")) {
						JSONObject jObjTop = jObj.getJSONObject("top");
						top = new Cloth(jObjTop.getString("type"), jObjTop.getString("color"),
								jObjTop.getBoolean("ornate"), jObjTop.getBoolean("stamped"));
					}

					if (jObj.has("bottom")) {
						JSONObject jObjBottom = jObj.getJSONObject("bottom");
						bottom = new Cloth(jObjBottom.getString("type"), jObjBottom.getString("color"),
								jObjBottom.getBoolean("ornate"), jObjBottom.getBoolean("stamped"));
					}

					if (jObj.has("arms")) {
						JSONObject jObjArms = jObj.getJSONObject("arms");
						arms = new Cloth(jObjArms.getString("type"), jObjArms.getString("color"),
								jObjArms.getBoolean("ornate"), jObjArms.getBoolean("stamped"));
					}

					if (jObj.has("shoes")) {
						JSONObject jObjShoes = jObj.getJSONObject("shoes");
						shoes = new Cloth(jObjShoes.getString("type"), jObjShoes.getString("color"),
								jObjShoes.getBoolean("ornate"), jObjShoes.getBoolean("stamped"));
					}

					Fantasy fant = new Fantasy(jObj.getString("name"), jObj.getString("id"), hat, top, bottom, arms,
							shoes, jObj.getInt("quantity"), jObj.getDouble("buyPrice"), jObj.getDouble("sellPrice"));
					fantasies.add(fant);
				}
			} catch (FileNotFoundException | JSONException e) {
				throw new DatabaseErrorException(e.getMessage());
			}
		}

		return fantasies;
	}

	private synchronized void saveDatabase(List<Fantasy> fantasies) throws DatabaseErrorException {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), "UTF-8"));
			JSONArray jArr = new JSONArray(fantasies);
			JSONObject jObj = new JSONObject();
			jObj.put("fantasies", jArr);

			out.write(jObj.toString());
			out.close();
		} catch (JSONException | IOException e) {
			throw new DatabaseErrorException(e.getMessage());
		}
	}
}