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
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class SeeEmployeeFrame extends JInternalFrame {
	private static final long serialVersionUID = 2142489109527680397L;

	public SeeEmployeeFrame() throws ParseException {
		super("Procurar Funcionário", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		getContentPane().setLayout(new MigLayout("", "[20px][30px][20px][140px][20px][80px][20px]",
				"[20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px]"));

		JLabel lbl_cpf = new JLabel("CPF:");
		getContentPane().add(lbl_cpf, "cell 1 1,alignx right,aligny center");
		JFormattedTextField ftf_cpf = new JFormattedTextField(new MaskFormatter("###########"));
		getContentPane().add(ftf_cpf, "cell 3 1,growx,aligny center");

		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 1 3 5 7,alignx left,growy");

		JButton btn_seeEmployee = new JButton("Procurar");
		getContentPane().add(btn_seeEmployee, "cell 5 1,growx,aligny bottom");
		btn_seeEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panel.removeAll();

					String cpf = new StringBuilder(ftf_cpf.getText()).insert(9, "-").insert(6, ".").insert(3, ".")
							.toString();

					String str = ChameleonFantasies.facade.seeEmployee(cpf).toString();
					panel.add(new JLabel("<html>" + str.replace("\n", "<br>") + "</html>"));

					panel.revalidate();
					panel.repaint();
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