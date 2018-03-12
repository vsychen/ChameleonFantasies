package main.gui.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.text.MaskFormatter;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import net.miginfocom.swing.MigLayout;

public class LoginFrame extends JInternalFrame {
	private static final long serialVersionUID = -1148883477391024929L;

	public LoginFrame() throws ParseException {
		super("Login", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		getContentPane().setLayout(new MigLayout("", "[20px][30px][20px][20px][50px][20px][50px][20px]",
				"[10px][20px][10px][20px][10px][20px][10px]"));
		JInternalFrame frame = this;

		JLabel lbl_cpf = new JLabel("Cpf");
		getContentPane().add(lbl_cpf, "cell 1 1,alignx right,aligny center");
		JFormattedTextField ftf_cpf = new JFormattedTextField(new MaskFormatter("###########"));
		getContentPane().add(ftf_cpf, "cell 3 1 4 1,growx,aligny center");

		JLabel lbl_password = new JLabel("Password");
		getContentPane().add(lbl_password, "cell 1 3,alignx right,aligny center");
		JPasswordField pf_password = new JPasswordField();
		getContentPane().add(pf_password, "cell 3 3 3 1,growx,aligny center");

		JButton btn_login = new JButton("Login");
		getContentPane().add(btn_login, "cell 4 5,growx,aligny center");
		btn_login.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				try {
					String cpf = new StringBuilder(ftf_cpf.getText()).insert(9, "-").insert(6, ".").insert(3, ".")
							.toString();
					String password = String.valueOf(pf_password.getPassword());

					ChameleonFantasies.facade.login(cpf, password);

					JOptionPane.showMessageDialog(null, "Você está logado.");
					frame.dispose();
				} catch (LoginErrorException lee) {
					JOptionPane.showMessageDialog(null, "LoginErrorException: " + lee.getMessage());
				} catch (InvalidFieldException ife) {
					JOptionPane.showMessageDialog(null, "InvalidFieldException: " + ife.getMessage());
				}
			}
		});
	}
}