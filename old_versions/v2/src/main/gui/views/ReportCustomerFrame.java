package main.gui.views;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class ReportCustomerFrame extends JInternalFrame {
	private static final long serialVersionUID = -3310618719117882409L;

	public ReportCustomerFrame() throws ParseException {
		super("Listar Clientes", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		getContentPane().setLayout(
				new MigLayout("", "[20px][20px][20px][20px][20px][20px][50px][20px][20px][20px][20px][20px][20px]",
						"[10px][20px][20px][20px][20px][20px][20px][20px][20px][10px][20px]"));

		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 1 1 11 8,grow");

		JButton btn_reportCustomers = new JButton("Listar");
		getContentPane().add(btn_reportCustomers, "cell 6 10,growx,aligny bottom");
		btn_reportCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panel.removeAll();

					String str = ChameleonFantasies.facade.getReport(0);
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
				}
			}
		});
	}
}