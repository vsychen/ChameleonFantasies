package br.com.chameleonfantasies.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.chameleonfantasies.model.entities.Cloth;
import br.com.chameleonfantasies.model.entities.Fantasy;

public class FantasySQL extends SQLConnection implements IDatabase<Fantasy> {

	public void insert(Fantasy f) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			if (f.getId() == null)
				stmt = conn.prepareStatement(
						"INSERT INTO FANTASY (name,buyPrice,sellPrice,quantity,hatType,hatColor,hatOrnament,hatStamp,topType,topColor,topOrnament,topStamp,bottomType,bottomColor,bottomOrnament,bottomStamp,armsType,armsColor,armsOrnament,armsStamp,shoesType,shoesColor,shoesOrnament,shoesStamp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			else
				throw new SQLException("Não foi possível inserir o item.");

			stmt.setString(1, f.getName());
			stmt.setString(2, String.valueOf(f.getBuyPrice()));
			stmt.setString(3, String.valueOf(f.getSellPrice()));
			stmt.setString(4, String.valueOf(f.getQuantity()));
			// Hat
			stmt.setString(5, f.getHat().getType());
			stmt.setString(6, f.getHat().getColor());
			stmt.setBoolean(7, f.getHat().isOrnate());
			stmt.setBoolean(8, f.getHat().isStamped());
			// Top
			stmt.setString(9, f.getTop().getType());
			stmt.setString(10, f.getTop().getColor());
			stmt.setBoolean(11, f.getTop().isOrnate());
			stmt.setBoolean(12, f.getTop().isStamped());
			// Bottom
			stmt.setString(13, f.getBottom().getType());
			stmt.setString(14, f.getBottom().getColor());
			stmt.setBoolean(15, f.getBottom().isOrnate());
			stmt.setBoolean(16, f.getBottom().isStamped());
			// Arms
			stmt.setString(17, f.getArms().getType());
			stmt.setString(18, f.getArms().getColor());
			stmt.setBoolean(19, f.getArms().isOrnate());
			stmt.setBoolean(20, f.getArms().isStamped());
			// Shoes
			stmt.setString(21, f.getShoes().getType());
			stmt.setString(22, f.getShoes().getColor());
			stmt.setBoolean(23, f.getShoes().isOrnate());
			stmt.setBoolean(24, f.getShoes().isStamped());

			int count = stmt.executeUpdate();
			f.setId(getGeneratedId(stmt));

			if (count == 0)
				throw new SQLException("Não foi possível inserir o item.");
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}
	}

	public void update(Fantasy f) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			if (f.getId() == null)
				throw new SQLException("Não foi possível atualizar o item.");
			else
				stmt = conn.prepareStatement(
						"UPDATE FANTASY SET name=?,buyPrice=?,sellPrice=?,quantity=?,hatType=?,hatColor=?,hatOrnament=?,hatStamp=?,topType=?,topColor=?,topOrnament=?,topStamp=?,bottomType=?,bottomColor=?,bottomOrnament=?,bottomStamp=?,armsType=?,armsColor=?,armsOrnament=?,armsStamp=?,shoesType=?,shoesColor=?,shoesOrnament=?,shoesStamp=? WHERE id=?");

			stmt.setString(1, f.getName());
			stmt.setString(2, String.valueOf(f.getBuyPrice()));
			stmt.setString(3, String.valueOf(f.getSellPrice()));
			stmt.setString(4, String.valueOf(f.getQuantity()));
			// Hat
			stmt.setString(5, f.getHat().getType());
			stmt.setString(6, f.getHat().getColor());
			stmt.setBoolean(7, f.getHat().isOrnate());
			stmt.setBoolean(8, f.getHat().isStamped());
			// Top
			stmt.setString(9, f.getTop().getType());
			stmt.setString(10, f.getTop().getColor());
			stmt.setBoolean(11, f.getTop().isOrnate());
			stmt.setBoolean(12, f.getTop().isStamped());
			// Bottom
			stmt.setString(13, f.getBottom().getType());
			stmt.setString(14, f.getBottom().getColor());
			stmt.setBoolean(15, f.getBottom().isOrnate());
			stmt.setBoolean(16, f.getBottom().isStamped());
			// Arms
			stmt.setString(17, f.getArms().getType());
			stmt.setString(18, f.getArms().getColor());
			stmt.setBoolean(19, f.getArms().isOrnate());
			stmt.setBoolean(20, f.getArms().isStamped());
			// Shoes
			stmt.setString(21, f.getShoes().getType());
			stmt.setString(22, f.getShoes().getColor());
			stmt.setBoolean(23, f.getShoes().isOrnate());
			stmt.setBoolean(24, f.getShoes().isStamped());
			stmt.setLong(25, f.getId());

			int count = stmt.executeUpdate();

			if (count == 0)
				throw new SQLException("Não foi possível atualizar o item.");
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}
	}

	public Fantasy searchById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Fantasy f = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM FANTASY WHERE id=?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				f = createFantasy(rs);
				rs.close();
			}
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return f;
	}

	public List<Fantasy> searchByName(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Fantasy> fantasies = new ArrayList<Fantasy>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM FANTASY WHERE lower(name) like ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				fantasies.add(createFantasy(rs));
			}

			rs.close();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return fantasies;
	}

	public boolean remove(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int count = 0;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM FANTASY WHERE id=?");
			stmt.setLong(1, id);
			count = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return count > 0;
	}

	public int removeAll() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int count = 0;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM FANTASY");
			count = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return count;
	}

	public List<Fantasy> list() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Fantasy> fantasies = new ArrayList<Fantasy>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM FANTASY");
			rs = stmt.executeQuery();

			if (rs.next()) {
				fantasies.add(createFantasy(rs));
			}

			rs.close();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return fantasies;
	}

	private Fantasy createFantasy(ResultSet rs) throws NumberFormatException, SQLException {
		Cloth hat = new Cloth(rs.getString("hatType"), rs.getString("hatColor"), rs.getBoolean("hatOrnament"),
				rs.getBoolean("hatStamp"));
		Cloth top = new Cloth(rs.getString("topType"), rs.getString("topColor"), rs.getBoolean("topOrnament"),
				rs.getBoolean("topStamp"));
		Cloth bottom = new Cloth(rs.getString("bottomType"), rs.getString("bottomColor"),
				rs.getBoolean("bottomOrnament"), rs.getBoolean("bottomStamp"));
		Cloth arms = new Cloth(rs.getString("armsType"), rs.getString("armsColor"), rs.getBoolean("armsOrnament"),
				rs.getBoolean("armsStamp"));
		Cloth shoes = new Cloth(rs.getString("shoesType"), rs.getString("shoesColor"), rs.getBoolean("shoesOrnament"),
				rs.getBoolean("shoesStamp"));
		Fantasy f = new Fantasy(rs.getString("name"), hat, top, bottom, arms, shoes,
				Integer.parseInt(rs.getString("quantity")), Double.parseDouble(rs.getString("buyPrice")),
				Double.parseDouble(rs.getString("sellPrice")));
		f.setId(rs.getLong("id"));
		return f;
	}

	private static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		Long id = 0L;

		if (rs.next())
			id = rs.getLong(1);

		return id;
	}
}