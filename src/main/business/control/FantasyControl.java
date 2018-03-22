package main.business.control;

import main.business.exceptions.InsufficientStockException;
import main.data.database.IDatabase;
import main.data.entities.Cloth;
import main.data.entities.Customer;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

public class FantasyControl {
	private IDatabase<Fantasy> fd;

	public FantasyControl(IDatabase<Fantasy> fd) {
		this.fd = fd;
	}

	public void addFantasy(String name, String id, Object[] hat, Object[] top, Object[] bottom, Object[] arms,
			Object[] shoes, int quantity, double buyPrice, double sellPrice) throws DatabaseErrorException {
		Cloth cHat = null, cTop = null, cBottom = null, cArms = null, cShoes = null;

		if (hat != null)
			cHat = new Cloth((String) hat[0], (String) hat[1], (boolean) hat[2], (boolean) hat[3]);

		if (top != null)
			cTop = new Cloth((String) top[0], (String) top[1], (boolean) top[2], (boolean) top[3]);

		if (bottom != null)
			cBottom = new Cloth((String) bottom[0], (String) bottom[1], (boolean) bottom[2], (boolean) bottom[3]);

		if (arms != null)
			cArms = new Cloth((String) arms[0], (String) arms[1], (boolean) arms[2], (boolean) arms[3]);

		if (shoes != null)
			cShoes = new Cloth((String) shoes[0], (String) shoes[1], (boolean) shoes[2], (boolean) shoes[3]);

		this.fd.add(new Fantasy(name, id, cHat, cTop, cBottom, cArms, cShoes, quantity, buyPrice, sellPrice));

	}

	public void buyFantasy(String id, int quantity) throws DatabaseErrorException {
		if (quantity > 0) {
			Fantasy f = this.fd.search(id);
			f.setQuantity(f.getQuantity() + quantity);

			this.fd.edit(id, f);
		}
	}

	public void sellFantasy(IDatabase<Customer> cd, String cpf, String id, int quantity)
			throws DatabaseErrorException, InsufficientStockException {
		if (quantity > 0) {
			Customer c = cd.search(cpf);
			Fantasy f = this.fd.search(id);

			if (f.getQuantity() >= quantity) {
				c.setSpending(c.getSpending() + (quantity * f.getSellPrice()));
				f.setQuantity(f.getQuantity() - quantity);

				cd.edit(cpf, c);
				this.fd.edit(id, f);
			} else {
				throw new InsufficientStockException(quantity, f.getQuantity());
			}
		}
	}

	public void changePrice(String id, double buyPrice, double sellPrice) throws DatabaseErrorException {
		Fantasy f = this.fd.search(id);

		if (buyPrice > 0)
			f.setBuyPrice(buyPrice);

		if (sellPrice > 0)
			f.setSellPrice(sellPrice);

		if (f.getBuyPrice() < f.getSellPrice())
			this.fd.edit(id, f);
	}

	public Fantasy searchFantasy(String id) throws DatabaseErrorException {
		return this.fd.search(id);
	}
}