package main.data.database;

import main.data.exceptions.DatabaseErrorException;

public interface IDatabase<O> {

	public boolean isEmpty() throws DatabaseErrorException;

	public void add(O o) throws DatabaseErrorException;

	public void edit(String code, O o) throws DatabaseErrorException;

	public O search(String code) throws DatabaseErrorException;

	public O remove(String code) throws DatabaseErrorException;

	public void removeAll() throws DatabaseErrorException;

	public String list() throws DatabaseErrorException;
}