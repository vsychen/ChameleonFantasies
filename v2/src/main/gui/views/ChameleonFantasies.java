package main.gui.views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.business.exceptions.LoginErrorException;
import main.business.facade.Facade;

public class ChameleonFantasies extends JFrame implements ActionListener {
	private static final long serialVersionUID = 4548558908993249719L;
	private JDesktopPane desktop;

	public final static int xOffset = 30, yOffset = 30;
	private final String IMG_PATH = "../v2/img/";
	private final String[] imgs = { "Black_Widow_Melissa_Smith.jpg", "Captain_America_Melissa_Smith.jpg",
			"Deadpool_Melissa_Smith.jpg", "Hawkeye_Melissa_Smith.jpg", "Heads_Vance_Kovacs.jpg",
			"Hulk_Melissa_Smith.jpg", "Iron_Man_Melissa_Smith.jpg", "Loki_Melissa_Smith.jpg",
			"Pikachu_Melissa_Smith.jpg", "Scrat_Peter_De_Seve.jpg", "Spider_Man_Melissa_Smith.jpg",
			"Thor_Melissa_Smith.jpg" };
	private final int index = new Random().nextInt(11);

	public static Facade facade;

	JInternalFrame lf, faf, acf, scf, rcf1, rcf2, aef, eef, sef, ref1, ref2, aff, bff, sff1, cpf, sff2, rff1, rff2;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				facade = Facade.getInstance();
				initialize();
			}
		});
	}

	private static void initialize() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		ChameleonFantasies frame = new ChameleonFantasies();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public ChameleonFantasies() {
		super("Chameleon Fantasies v2");

		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

		desktop = new JDesktopPane() {
			private static final long serialVersionUID = 5978573631038126065L;

			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				Image img = new ImageIcon(IMG_PATH + imgs[index]).getImage();
				grphcs.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		setContentPane(desktop);
		setJMenuBar(createMenuBar());

		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}

	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		// ACCESS MENU
		menu = new JMenu("Acesso");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		menuItem = new JMenuItem("Primeiro Acesso");
		menuItem.setActionCommand("firstAccess");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Login");
		menuItem.setActionCommand("login");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Logout");
		menuItem.setActionCommand("logout");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// CUSTOMER MENU
		menu = new JMenu("Cliente");
		menu.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menu);

		menuItem = new JMenuItem("Adicionar Cliente");
		menuItem.setActionCommand("addCustomer");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Procurar Cliente");
		menuItem.setActionCommand("seeCustomer");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Listar Clientes");
		menuItem.setActionCommand("reportCustomers");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Remover Cliente");
		menuItem.setActionCommand("removeCustomer");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// EMPLOYEE MENU
		menu = new JMenu("Funcionário");
		menu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menu);

		menuItem = new JMenuItem("Adicionar Funcionário");
		menuItem.setActionCommand("addEmployee");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Editar Funcionário");
		menuItem.setActionCommand("editEmployee");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Procurar Funcionário");
		menuItem.setActionCommand("seeEmployee");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Listar Funcionários");
		menuItem.setActionCommand("reportEmployees");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Remover Funcionário");
		menuItem.setActionCommand("removeEmployee");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// FANTASY MENU
		menu = new JMenu("Fantasia");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		menuItem = new JMenuItem("Adicionar Fantasia");
		menuItem.setActionCommand("addFantasy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Comprar Fantasia");
		menuItem.setActionCommand("buyFantasy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Vender Fantasia");
		menuItem.setActionCommand("sellFantasy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Atualizar Preços");
		menuItem.setActionCommand("changePrices");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Procurar Fantasia");
		menuItem.setActionCommand("seeFantasy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Listar Fantasia");
		menuItem.setActionCommand("reportFantasies");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Remover Fantasia");
		menuItem.setActionCommand("removeFantasy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("logout")) {
			try {
				facade.logout();
				JOptionPane.showMessageDialog(null, "Você foi deslogado.");
			} catch (LoginErrorException lee) {
				JOptionPane.showMessageDialog(null, "LoginErrorException: " + lee.getMessage());
			}
		} else {
			try {
				createFrame(e.getActionCommand());
			} catch (ParseException pe) {
				JOptionPane.showMessageDialog(null, "ParseException: " + pe.getMessage());
			} catch (PropertyVetoException pve) {
				JOptionPane.showMessageDialog(null, "PropertyVetoException: " + pve.getMessage());
			}
		}
	}

	protected void createFrame(String frameName) throws ParseException, PropertyVetoException {
		Random r = new Random();

		switch (frameName) {
		case "firstAccess":
			if (this.faf == null || !this.faf.isValid()) {
				this.faf = new FirstAccessFrame();
				this.faf.setLocation(r.nextInt(10) * xOffset, r.nextInt(7) * yOffset);
				this.faf.setVisible(true);
				this.desktop.add(this.faf);
			}

			this.faf.setSelected(true);
			break;

		case "login":
			if (this.lf == null || !this.lf.isValid()) {
				this.lf = new LoginFrame();
				this.lf.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.lf.setVisible(true);
				this.desktop.add(this.lf);
			}

			this.lf.setSelected(true);
			break;
		case "addCustomer":
			if (this.acf == null || !this.acf.isValid()) {
				this.acf = new AddCustomerFrame();
				this.acf.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.acf.setVisible(true);
				this.desktop.add(this.acf);
			}

			this.acf.setSelected(true);
			break;
		case "seeCustomer":
			if (this.scf == null || !this.scf.isValid()) {
				this.scf = new SeeCustomerFrame();
				this.scf.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.scf.setVisible(true);
				this.desktop.add(this.scf);
			}

			this.scf.setSelected(true);
			break;
		case "reportCustomers":
			if (this.rcf1 == null || !this.rcf1.isValid()) {
				this.rcf1 = new ReportCustomerFrame();
				this.rcf1.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.rcf1.setVisible(true);
				this.desktop.add(this.rcf1);
			}

			this.rcf1.setSelected(true);
			break;
		case "removeCustomer":
			if (this.rcf2 == null || !this.rcf2.isValid()) {
				this.rcf2 = new RemoveCustomerFrame();
				this.rcf2.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.rcf2.setVisible(true);
				this.desktop.add(this.rcf2);
			}

			this.rcf2.setSelected(true);
			break;
		case "addEmployee":
			if (this.aef == null || !this.aef.isValid()) {
				this.aef = new AddEmployeeFrame();
				this.aef.setLocation(r.nextInt(10) * xOffset, r.nextInt(7) * yOffset);
				this.aef.setVisible(true);
				this.desktop.add(this.aef);
			}

			this.aef.setSelected(true);
			break;
		case "editEmployee":
			if (this.eef == null || !this.eef.isValid()) {
				this.eef = new EditEmployeeFrame();
				this.eef.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.eef.setVisible(true);
				this.desktop.add(this.eef);
			}

			this.eef.setSelected(true);
			break;
		case "seeEmployee":
			if (this.sef == null || !this.sef.isValid()) {
				this.sef = new SeeEmployeeFrame();
				this.sef.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.sef.setVisible(true);
				this.desktop.add(this.sef);
			}

			this.sef.setSelected(true);
			break;
		case "reportEmployees":
			if (this.ref1 == null || !this.ref1.isValid()) {
				this.ref1 = new ReportEmployeeFrame();
				this.ref1.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.ref1.setVisible(true);
				this.desktop.add(this.ref1);
			}

			this.ref1.setSelected(true);
			break;
		case "removeEmployee":
			if (this.ref2 == null || !this.ref2.isValid()) {
				this.ref2 = new RemoveEmployeeFrame();
				this.ref2.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.ref2.setVisible(true);
				this.desktop.add(this.ref2);
			}

			this.ref2.setSelected(true);
			break;
		case "addFantasy":
			if (this.aff == null || !this.aff.isValid()) {
				this.aff = new AddFantasyFrame();
				this.aff.setLocation(r.nextInt(5) * xOffset, 30);
				this.aff.setVisible(true);
				this.desktop.add(this.aff);
			}

			this.aff.setSelected(true);
			break;
		case "buyFantasy":
			if (this.bff == null || !this.bff.isValid()) {
				this.bff = new BuyFantasyFrame();
				this.bff.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.bff.setVisible(true);
				this.desktop.add(this.bff);
			}

			this.bff.setSelected(true);
			break;
		case "sellFantasy":
			if (this.sff1 == null || !this.sff1.isValid()) {
				this.sff1 = new SellFantasyFrame();
				this.sff1.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.sff1.setVisible(true);
				this.desktop.add(this.sff1);
			}

			this.sff1.setSelected(true);
			break;
		case "changePrices":
			if (this.cpf == null || !this.cpf.isValid()) {
				this.cpf = new ChangePricesFrame();
				this.cpf.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.cpf.setVisible(true);
				this.desktop.add(this.cpf);
			}

			this.cpf.setSelected(true);
			break;
		case "seeFantasy":
			if (this.sff2 == null || !this.sff2.isValid()) {
				this.sff2 = new SeeFantasyFrame();
				this.sff2.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.sff2.setVisible(true);
				this.desktop.add(this.sff2);
			}

			this.sff2.setSelected(true);
			break;
		case "reportFantasies":
			if (this.rff1 == null || !this.rff1.isValid()) {
				this.rff1 = new ReportFantasiesFrame();
				this.rff1.setLocation(r.nextInt(10) * xOffset, r.nextInt(7) * yOffset);
				this.rff1.setVisible(true);
				this.desktop.add(this.rff1);
			}

			this.rff1.setSelected(true);
			break;
		case "removeFantasy":
			if (this.rff2 == null || !this.rff2.isValid()) {
				this.rff2 = new RemoveFantasyFrame();
				this.rff2.setLocation(r.nextInt(15) * xOffset, r.nextInt(10) * yOffset);
				this.rff2.setVisible(true);
				this.desktop.add(this.rff2);
			}

			this.rff2.setSelected(true);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Ação selecionada não foi reconhecida pelo sistema.");
			break;
		}
	}
}