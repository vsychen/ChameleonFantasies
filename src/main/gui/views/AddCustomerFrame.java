package main.gui.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class AddCustomerFrame extends JInternalFrame {
	private static final long serialVersionUID = 348850455314760754L;

	public AddCustomerFrame() throws ParseException {
		super("Cadastrar Cliente", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane().setLayout(
				new MigLayout("", "[30px][40px][140px][80px][30px][30px]", "[20px][20px][20px][20px][20px][20px]"));

		JLabel lbl_name = new JLabel("Nome");
		getContentPane().add(lbl_name, "cell 1 1,alignx right,aligny center");
		JTextField tf_name = new JTextField();
		getContentPane().add(tf_name, "cell 2 1 3 1,growx,aligny center");
		tf_name.setColumns(10);

		JLabel lbl_cpf = new JLabel("CPF:");
		getContentPane().add(lbl_cpf, "cell 1 3,alignx right,aligny center");
		JFormattedTextField ftf_cpf = new JFormattedTextField(new MaskFormatter("###########"));
		getContentPane().add(ftf_cpf, "cell 2 3,growx,aligny center");

		JButton btn_addCustomer = new JButton("Cadastrar");
		getContentPane().add(btn_addCustomer, "cell 4 4,growx,aligny bottom");
		btn_addCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = tf_name.getText();
					String cpf = new StringBuilder(ftf_cpf.getText()).insert(9, "-").insert(6, ".").insert(3, ".")
							.toString();

					ChameleonFantasies.facade.addCustomer(name, cpf);

					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
					frame.dispose();
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "NumberFormatException: " + nfe.getMessage());
				} catch (DatabaseErrorException dee) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + dee.getMessage());
				} catch (InvalidFieldException ife) {
					JOptionPane.showMessageDialog(null, "InvalidFieldException: " + ife.getMessage());
				} catch (LoginErrorException lee) {
					JOptionPane.showMessageDialog(null, "InvalidFieldException: " + lee.getMessage());
				} catch (UnauthorizedActionException uae) {
					JOptionPane.showMessageDialog(null, "InvalidFieldException: " + uae.getMessage());
				}
			}
		});
	}
}