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
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class EditEmployeeFrame extends JInternalFrame {
	private static final long serialVersionUID = -2021359794964852027L;

	public EditEmployeeFrame() throws ParseException {
		super("Editar Funcionário", true, true, true, true);
		setSize(550, 275);
		initialize();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane().setLayout(new MigLayout("", "[30px][40px][20px][140px][20px][80px][20px][30px][20px][30px]",
				"[20px][20px][20px][20px][20px][20px][20px][20px][20px][20px]"));

		JLabel lbl_cpf = new JLabel("CPF:");
		getContentPane().add(lbl_cpf, "cell 1 1,alignx right,aligny center");

		JFormattedTextField ftf_cpf = new JFormattedTextField(new MaskFormatter("###########"));
		getContentPane().add(ftf_cpf, "cell 3 1,growx,aligny center");

		JLabel lbl_email = new JLabel("E-mail");
		getContentPane().add(lbl_email, "cell 1 2,alignx right,aligny center");

		JTextField tf_email = new JTextField();
		getContentPane().add(tf_email, "cell 3 2 3 1,growx");
		tf_email.setColumns(10);

		JLabel lbl_street = new JLabel("Logradouro");
		getContentPane().add(lbl_street, "cell 1 3,alignx right,aligny center");

		JTextField tf_street = new JTextField();
		getContentPane().add(tf_street, "cell 3 3 6 1,growx,aligny center");
		tf_street.setColumns(10);

		JLabel lbl_city = new JLabel("Cidade");
		getContentPane().add(lbl_city, "cell 1 4,alignx right,aligny center");

		JTextField tf_city = new JTextField();
		getContentPane().add(tf_city, "cell 3 4,growx,aligny center");
		tf_city.setColumns(10);

		JLabel lbl_state = new JLabel("Estado");
		getContentPane().add(lbl_state, "cell 5 4,alignx right,aligny center");

		JTextField tf_state = new JTextField();
		getContentPane().add(tf_state, "cell 7 4 2 1,growx,aligny center");
		tf_state.setColumns(10);

		JLabel lbl_country = new JLabel("País");
		getContentPane().add(lbl_country, "cell 1 5,alignx right,aligny center");

		JTextField tf_country = new JTextField();
		tf_country.setColumns(10);
		getContentPane().add(tf_country, "cell 3 5,growx,aligny center");

		JLabel lbl_job = new JLabel("Cargo");
		getContentPane().add(lbl_job, "cell 1 6,alignx right,aligny center");

		JTextField tf_job = new JTextField();
		getContentPane().add(tf_job, "cell 3 6,growx,aligny center");
		tf_job.setColumns(10);

		JLabel lbl_salary = new JLabel("Salário");
		getContentPane().add(lbl_salary, "cell 1 7,alignx right,aligny center");

		JFormattedTextField ftf_salary = new JFormattedTextField(NumberFormat.getNumberInstance(Locale.US));
		getContentPane().add(ftf_salary, "cell 3 7,growx,aligny center");

		JButton btn_editEmployee = new JButton("Editar");
		getContentPane().add(btn_editEmployee, "cell 8 8,growx,aligny bottom");
		btn_editEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cpf = new StringBuilder(ftf_cpf.getText()).insert(9, "-").insert(6, ".").insert(3, ".")
							.toString();
					String email = tf_email.getText();
					String street = tf_street.getText();
					String city = tf_city.getText();
					String state = tf_state.getText();
					String country = tf_country.getText();
					String job = tf_job.getText();
					double salary = Double.parseDouble(ftf_salary.getText().replace(",", ""));

					ChameleonFantasies.facade.editEmployee(cpf, email, street, city, state, country, job, salary);

					JOptionPane.showMessageDialog(null, "Funcionário editado com sucesso.");
					frame.dispose();
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "NumberFormatException: " + nfe.getMessage());
				} catch (LoginErrorException lee) {
					JOptionPane.showMessageDialog(null, "NumberFormatException: " + lee.getMessage());
				} catch (UnauthorizedActionException uae) {
					JOptionPane.showMessageDialog(null, "NumberFormatException: " + uae.getMessage());
				} catch (DatabaseErrorException dee) {
					JOptionPane.showMessageDialog(null, "NumberFormatException: " + dee.getMessage());
				} catch (InvalidFieldException ife) {
					JOptionPane.showMessageDialog(null, "NumberFormatException: " + ife.getMessage());
				}
			}
		});
	}
}