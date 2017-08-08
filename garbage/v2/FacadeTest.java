package main.business.facade;

import main.business.exceptions.LoginErrorException;
import main.business.exceptions.NotFirstAccountException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;

public class FacadeTest {
	public static void main(String[] args) {
		Facade fachada = Facade.getInstance();

		try {
			fachada.firstAccess("José Augusto", "021.163.167-16", "funcionario1@gmail.com", "Rua Dom Macaco 31 A",
					"Cidade", "Estado", "País", 3000);
		} catch (DatabaseErrorException | NotFirstAccountException e) {
			e.printStackTrace();
		}

		Object[] chapeu1 = { "Máscara", "Preto", false, false };
		Object[] parteCima1 = { "Camisa Longa", "Preto", false, true };
		Object[] parteBaixo1 = { "Calça Jeans", "Marrom", false, false };
		Object[] parteBaixo2 = { "Calça Comprida", "Cinza", false, false };
		Object[] parteBaixo3 = { "Barbatana", "Verde", false, true };

		// admin actions
		try {
			fachada.login("021.163.167-16", "s05c");
			fachada.addEmployee("Todo Mundo", "631.466.567-36", "funcionario2@hotmail.com", "Estrada dos Tomatos",
					"Malfica", "Chuva Toda Hora", "Braçobuco", "cashier", 1500);
			fachada.addEmployee("Todo Mundo2", "156.746.875-64", "funcionario3@hotmail.com", "Estrada dos Tomatos",
					"Malfica", "Chuva Toda Hora", "Braçobuco", "stock", 1200);

			System.out.println(fachada.seeEmployee("631.466.567-36"));

			fachada.editEmployee("631.466.567-36", "functionario2@lalalala.com.br", "Estrada dos Tomatos", "Malfica",
					"Chuva Toda Hora", "Braçobuco", "cashier", 1800);
			System.out.println(fachada.seeEmployee("631.466.567-36"));

			fachada.addFantasy("batman", "121125", chapeu1, parteCima1, parteBaixo2, null, null, 10, 500, 1500);
			System.out.println(fachada.seeFantasy("121125"));
			fachada.changePrices("121125", 0, 3000);
			System.out.println(fachada.seeFantasy("121125"));

			fachada.logout();
		} catch (LoginErrorException | UnauthorizedActionException | DatabaseErrorException e) {
			e.printStackTrace();
		}

		// cashier actions
		try {
			fachada.login("631.466.567-36", "eaii");

			fachada.addCustomer("Gama Tester", "631.524.482-61"); // cliente1
			fachada.addCustomer("Ninguém", "234.616.646-86"); // cliente2

			fachada.sellFantasy("631.524.482-61", "121125", 2);
			System.out.println(fachada.seeCustomer("631.524.482-61"));
			fachada.logout();
		} catch (LoginErrorException | UnauthorizedActionException | DatabaseErrorException e) {
			e.printStackTrace();
		}

		// stock employee actions
		try {
			fachada.login("156.746.875-64", "ls7r");

			fachada.addFantasy("hulk", "217457", null, null, parteBaixo1, null, null, 6, 100, 300);
			fachada.addFantasy("aquaman", "3548465", null, null, parteBaixo3, null, null, 50, 100, 200);
			fachada.buyFantasy("121125", 10);
			System.out.println(fachada.seeFantasy("121125"));
			System.out.println(fachada.seeFantasy("217457"));
			System.out.println(fachada.seeFantasy("3548465"));

			fachada.logout();
		} catch (LoginErrorException | UnauthorizedActionException | DatabaseErrorException e) {
			e.printStackTrace();
		}

		// admin actions 2
		try {
			fachada.login("021.163.167-16", "r5em");

			System.out.println(fachada.getReport(0));
			System.out.println(fachada.getReport(1));
			System.out.println(fachada.getReport(2));

			fachada.removeWrongAdditions("631.524.482-61", 0);
			fachada.removeWrongAdditions("021.163.167-16", 1);
			fachada.removeWrongAdditions("121125", 2);

			System.out.println(fachada.getReport(0));
			System.out.println(fachada.getReport(1));
			System.out.println(fachada.getReport(2));
		} catch (LoginErrorException | UnauthorizedActionException | DatabaseErrorException e) {
			e.printStackTrace();
		}
	}
}