package br.com.chameleonfantasies.model.database;

import java.sql.SQLException;
import java.util.List;

public interface IDatabase<O> {

	public void insert(O o) throws SQLException;

	public void update(O o) throws SQLException;

	public O searchById(Long id) throws SQLException;

	public List<O> searchByName(String name) throws SQLException;

	public boolean remove(Long id) throws SQLException;

	public List<O> list() throws SQLException;

}