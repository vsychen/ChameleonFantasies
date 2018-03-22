package br.com.chameleonfantasies.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.chameleonfantasies.model.entities.Address;
import br.com.chameleonfantasies.model.entities.Employee;

public class EmployeeSQL extends SQLConnection implements IDatabase<Employee> {

	public void insert(Employee e) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			if (e.getId() == null)
				stmt = conn.prepareStatement(
						"INSERT INTO EMPLOYEE (name,cpf,password,email,street,city,state,country,job,salary) VALUES (?,?,?,?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			else
				throw new SQLException("Não foi possível inserir o item.");

			stmt.setString(1, e.getName());
			stmt.setString(2, e.getCpf());
			stmt.setString(3, e.getPassword());
			stmt.setString(4, e.getEmail());
			stmt.setString(5, e.getAddress().getStreet());
			stmt.setString(6, e.getAddress().getCity());
			stmt.setString(7, e.getAddress().getState());
			stmt.setString(8, e.getAddress().getCountry());
			stmt.setString(9, e.getJob());
			stmt.setString(10, String.valueOf(e.getSalary()));

			int count = stmt.executeUpdate();
			e.setId(getGeneratedId(stmt));

			if (count == 0)
				throw new SQLException("Não foi possível inserir o item.");
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}
	}

	public void update(Employee e) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			if (e.getId() == null)
				throw new SQLException("Não foi possível atualizar o item.");
			else
				stmt = conn.prepareStatement(
						"UPDATE EMPLOYEE SET name=?,cpf=?,password=?,email=?,street=?,city=?,state=?,country=?,job=?,salary=? WHERE id=?");

			stmt.setString(1, e.getName());
			stmt.setString(2, e.getCpf());
			stmt.setString(3, e.getPassword());
			stmt.setString(4, e.getEmail());
			stmt.setString(5, e.getAddress().getStreet());
			stmt.setString(6, e.getAddress().getCity());
			stmt.setString(7, e.getAddress().getState());
			stmt.setString(8, e.getAddress().getCountry());
			stmt.setString(9, e.getJob());
			stmt.setString(10, String.valueOf(e.getSalary()));
			stmt.setLong(11, e.getId());

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

	public List<Employee> searchByName(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE lower(name) like ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				employees.add(createEmployee(rs));
			}

			rs.close();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return employees;
	}

	public Employee searchByCpf(String cpf) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Employee e = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE cpf like ?");
			stmt.setString(1, "%" + cpf + "%");
			rs = stmt.executeQuery();

			if (rs.next()) {
				e = createEmployee(rs);
				rs.close();
			}
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return e;
	}

	public Employee searchById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Employee e = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE id=?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				e = createEmployee(rs);
				rs.close();
			}
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return e;
	}

	public boolean remove(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int count = 0;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM EMPLOYEE WHERE id=?");
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
			stmt = conn.prepareStatement("DELETE FROM EMPLOYEE");
			count = stmt.executeUpdate();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return count;
	}

	public List<Employee> list() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE");
			rs = stmt.executeQuery();

			if (rs.next()) {
				employees.add(createEmployee(rs));
			}

			rs.close();
		} finally {
			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}

		return employees;
	}

	private Employee createEmployee(ResultSet rs) throws NumberFormatException, SQLException {
		Employee e = new Employee(rs.getString("name"),
				rs.getString("cpf"), rs.getString("email"), new Address(rs.getString("street"), rs.getString("city"),
						rs.getString("state"), rs.getString("country")),
				rs.getString("job"), Double.parseDouble(rs.getString("salary")));
		e.setId(rs.getLong("id"));
		return e;
	}

	private static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		Long id = 0L;

		if (rs.next())
			id = rs.getLong(1);

		return id;
	}
}