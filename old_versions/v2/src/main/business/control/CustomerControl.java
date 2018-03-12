package main.business.control;

import main.data.database.IDatabase;
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

public class CustomerControl {
	private IDatabase<Customer> cd;

	public CustomerControl(IDatabase<Customer> cd) {
		this.cd = cd;
	}

	public void addCustomer(String name, String cpf) throws DatabaseErrorException {
		this.cd.add(new Customer(name, cpf));
	}

	public Customer searchCustomer(String cpf) throws DatabaseErrorException {
		return this.cd.search(cpf);
	}
}