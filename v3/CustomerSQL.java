package br.com.chameleonfantasies.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.chameleonfantasies.model.entities.Customer;

public class CustomerSQL extends SQLConnection implements IDatabase<Customer> {

	public void insert(Customer c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			if (c.getId() == null)
				stmt = conn.prepareStatement("INSERT INTO CUSTOMER (name,cpf,spending) VALUES (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			else
				throw new SQLException("Não foi possível inserir o item.");

			stmt.setString(1, c.getName());
			stmt.setString(2, c.getCpf());
			stmt.setString(3, String.valueOf(c.getSpending()));

			int count = stmt.executeUpdate();
			c.setId(getGeneratedId(stmt));

			if (count == 0)
				throw new SQLException("Não foi possível inserir o item.");
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}
	}

	public void update(Customer c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			if (c.getId() == null)
				throw new SQLException("Não foi possível atualizar o item.");
			else
				stmt = conn.prepareStatement("UPDATE CUSTOMER SET name=?,cpf=?,spending=? WHERE id=?");

			stmt.setString(1, c.getName());
			stmt.setString(2, c.getCpf());
			stmt.setString(3, String.valueOf(c.getSpending()));
			stmt.setLong(4, c.getId());

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

	public List<Customer> searchByName(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Customer> customers = new ArrayList<Customer>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE lower(name) like ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				customers.add(createCustomer(rs));
			}

			rs.close();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return customers;
	}

	public Customer searchByCpf(String cpf) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer c = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE cpf like ?");
			stmt.setString(1, "%" + cpf + "%");
			rs = stmt.executeQuery();

			if (rs.next()) {
				c = createCustomer(rs);
				rs.close();
			}
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return c;
	}

	public Customer searchById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer c = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE id=?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				c = createCustomer(rs);
				rs.close();
			}
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return c;
	}

	public boolean remove(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int count = 0;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM CUSTOMER WHERE id=?");
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
			stmt = conn.prepareStatement("DELETE FROM CUSTOMER");
			count = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return count;
	}

	public List<Customer> list() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Customer> customers = new ArrayList<Customer>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM CUSTOMER");
			rs = stmt.executeQuery();

			if (rs.next()) {
				customers.add(createCustomer(rs));
			}

			rs.close();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return customers;
	}

	private Customer createCustomer(ResultSet rs) throws NumberFormatException, SQLException {
		Customer c = new Customer(rs.getString("name"), rs.getString("cpf"),
				Double.parseDouble(rs.getString("spending")));
		c.setId(rs.getLong("id"));
		return c;
	}

	private static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		Long id = 0L;

		if (rs.next())
			id = rs.getLong(1);

		return id;
	}
}