package main.gui.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.NotFirstAccountException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class FirstAccessFrame extends JInternalFrame {
	private static final long serialVersionUID = 4853067679348108327L;

	public FirstAccessFrame() throws ParseException {
		super("Primeiro Acesso", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane().setLayout(new MigLayout("", "[30px][40px][20px][140px][20px][80px][20px][30px][20px][30px]",
				"[20px][20px][20px][20px][20px][20px][20px][20px][20px][20px]"));

		JLabel lbl_name = new JLabel("Nome");
		getContentPane().add(lbl_name, "cell 1 1,alignx right,aligny center");
		JTextField tf_name = new JTextField();
		getContentPane().add(tf_name, "cell 3 1 6 1,growx,aligny center");
		tf_name.setColumns(10);

		JLabel lbl_cpf = new JLabel("CPF:");
		getContentPane().add(lbl_cpf, "cell 1 2,alignx right,aligny center");
		JFormattedTextField ftf_cpf = new JFormattedTextField(new MaskFormatter("###########"));
		getContentPane().add(ftf_cpf, "cell 3 2,growx,aligny center");

		JLabel lbl_email = new JLabel("E-mail");
		getContentPane().add(lbl_email, "cell 1 3,alignx right,aligny center");
		JTextField tf_email = new JTextField();
		getContentPane().add(tf_email, "cell 3 3 3 1,growx");
		tf_email.setColumns(10);

		JLabel lbl_street = new JLabel("Logradouro");
		getContentPane().add(lbl_street, "cell 1 4,alignx right,aligny center");
		JTextField tf_street = new JTextField();
		getContentPane().add(tf_street, "cell 3 4 6 1,growx,aligny center");
		tf_street.setColumns(10);

		JLabel lbl_city = new JLabel("Cidade");
		getContentPane().add(lbl_city, "cell 1 5,alignx right,aligny center");
		JTextField tf_city = new JTextField();
		getContentPane().add(tf_city, "cell 3 5,growx,aligny center");
		tf_city.setColumns(10);

		JLabel lbl_state = new JLabel("Estado");
		getContentPane().add(lbl_state, "cell 5 5,alignx right,aligny center");
		JTextField tf_state = new JTextField();
		getContentPane().add(tf_state, "cell 7 5 2 1,growx,aligny center");
		tf_state.setColumns(10);

		JLabel lbl_country = new JLabel("País");
		getContentPane().add(lbl_country, "cell 1 6,alignx right,aligny center");
		JTextField tf_country = new JTextField();
		getContentPane().add(tf_country, "cell 3 6,growx,aligny center");
		tf_country.setColumns(10);

		JLabel lbl_salary = new JLabel("Salário");
		getContentPane().add(lbl_salary, "cell 1 8,alignx right,aligny center");
		JFormattedTextField ftf_salary = new JFormattedTextField(NumberFormat.getNumberInstance(Locale.US));
		getContentPane().add(ftf_salary, "cell 3 8,growx,aligny center");

		JButton btn_firstAccess = new JButton("Cadastrar");
		getContentPane().add(btn_firstAccess, "cell 8 8,growx,aligny bottom");
		btn_firstAccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = tf_name.getText();
					String cpf = new StringBuilder(ftf_cpf.getText()).insert(9, "-").insert(6, ".").insert(3, ".")
							.toString();
					String email = tf_email.getText();
					String street = tf_street.getText();
					String city = tf_city.getText();
					String state = tf_state.getText();
					String country = tf_country.getText();
					double salary = Double.parseDouble(ftf_salary.getText().replace(",", ""));

					ChameleonFantasies.facade.firstAccess(name, cpf, email, street, city, state, country, salary);

					JOptionPane.showMessageDialog(null, "Primeiro cadastro concluído com sucesso.");
					frame.dispose();
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "NumberFormatException: " + nfe.getMessage());
				} catch (DatabaseErrorException dee) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + dee.getMessage());
				} catch (NotFirstAccountException nfae) {
					JOptionPane.showMessageDialog(null, "NotFirstAccountException: " + nfae.getMessage());
				} catch (InvalidFieldException ife) {
					JOptionPane.showMessageDialog(null, "InvalidFieldException: " + ife.getMessage());
				}
			}
		});
	}
}