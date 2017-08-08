package br.com.chameleonfantasies.model.control;

import java.sql.SQLException;
import java.util.List;

import br.com.chameleonfantasies.model.database.CustomerSQL;
import br.com.chameleonfantasies.model.database.FantasySQL;
import br.com.chameleonfantasies.model.entities.Cloth;
import br.com.chameleonfantasies.model.entities.Customer;
import br.com.chameleonfantasies.model.entities.Fantasy;
import br.com.chameleonfantasies.model.exceptions.InsufficientStockException;

public class FantasyControl {
	private CustomerSQL csql;
	private FantasySQL fsql;

	public FantasyControl(String user, String pass, String path) {
		this.csql = new CustomerSQL();
		this.fsql = new FantasySQL();

		this.csql.setUser(user).setPass(pass).setPathToConnect(path);
		this.fsql.setUser(user).setPass(pass).setPathToConnect(path);
	}

	public void add(String name, Object[] hat, Object[] top, Object[] bottom, Object[] arms, Object[] shoes,
			int quantity, double buyPrice, double sellPrice) throws SQLException {
		Cloth cHat = new Cloth((String) hat[0], (String) hat[1], (boolean) hat[2], (boolean) hat[3]);
		Cloth cTop = new Cloth((String) top[0], (String) top[1], (boolean) top[2], (boolean) top[3]);
		Cloth cBottom = new Cloth((String) bottom[0], (String) bottom[1], (boolean) bottom[2], (boolean) bottom[3]);
		Cloth cArms = new Cloth((String) arms[0], (String) arms[1], (boolean) arms[2], (boolean) arms[3]);
		Cloth cShoes = new Cloth((String) shoes[0], (String) shoes[1], (boolean) shoes[2], (boolean) shoes[3]);
		Fantasy f = new Fantasy(name, cHat, cTop, cBottom, cArms, cShoes, quantity, buyPrice, sellPrice);
		this.fsql.insert(f);
	}

	public void buyFantasy(Long id, int quantity) throws SQLException {
		Fantasy f = this.fsql.searchById(id);
		f.setQuantity(f.getQuantity() + quantity);
		this.fsql.update(f);
	}

	public void sellFantasy(Long id, String cpf, int quantity) throws SQLException, InsufficientStockException {
		Customer c = this.csql.searchByCpf(cpf);
		Fantasy f = this.fsql.searchById(id);

		if (f.getQuantity() < quantity)
			throw new InsufficientStockException(quantity, f.getQuantity());

		c.setSpending(c.getSpending() + (quantity * f.getSellPrice()));
		f.setQuantity(f.getQuantity() - quantity);

		this.csql.update(c);
		this.fsql.update(f);
	}

	public void changePrices(Long id, double buyPrice, double sellPrice) throws SQLException {
		Fantasy f = this.fsql.searchById(id);
		f.setBuyPrice(buyPrice);
		f.setSellPrice(sellPrice);
		this.fsql.update(f);
	}

	public Fantasy searchById(Long id) throws SQLException {
		return this.fsql.searchById(id);
	}

	public List<Fantasy> searchByName(String name) throws SQLException {
		return this.fsql.searchByName(name);
	}

	public boolean remove(Long id) throws SQLException {
		return this.fsql.remove(id);
	}

	public List<Fantasy> list() throws SQLException {
		return this.fsql.list();
	}
}