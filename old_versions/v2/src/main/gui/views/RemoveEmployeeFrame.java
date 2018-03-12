package main.gui.views;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class RemoveEmployeeFrame extends JInternalFrame {
	private static final long serialVersionUID = -6171614962766022786L;

	public RemoveEmployeeFrame() throws ParseException {
		super("Remover Funcionário", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane()
				.setLayout(new MigLayout("", "[30px][30px][20px][140px][20px][80px][30px]", "[20px][20px][20px]"));

		JLabel lbl_cpf = new JLabel("CPF:");
		getContentPane().add(lbl_cpf, "cell 1 1,alignx right,aligny center");
		JFormattedTextField ftf_cpf = new JFormattedTextField(new MaskFormatter("###########"));
		getContentPane().add(ftf_cpf, "cell 3 1,growx,aligny center");

		JButton btn_removeEmployee = new JButton("Remover");
		getContentPane().add(btn_removeEmployee, "cell 5 1,growx,aligny bottom");
		btn_removeEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cpf = new StringBuilder(ftf_cpf.getText()).insert(9, "-").insert(6, ".").insert(3, ".")
							.toString();

					ChameleonFantasies.facade.removeWrongAdditions(cpf, 1);

					JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso.");
					frame.dispose();
				} catch (DatabaseErrorException dee) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + dee.getMessage());
				} catch (HeadlessException he) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + he.getMessage());
				} catch (LoginErrorException lee) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + lee.getMessage());
				} catch (UnauthorizedActionException uae) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + uae.getMessage());
				} catch (InvalidFieldException ife) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + ife.getMessage());
				}
			}
		});
	}
}