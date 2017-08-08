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
import javax.swing.JTextField;

import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class SeeFantasyFrame extends JInternalFrame {
	private static final long serialVersionUID = 1828330769925950204L;

	public SeeFantasyFrame() throws ParseException {
		super("Procurar Fantasia", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		getContentPane().setLayout(new MigLayout("", "[20px][30px][20px][140px][20px][80px][20px]",
				"[20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px]"));

		JLabel lbl_id = new JLabel("ID");
		getContentPane().add(lbl_id, "cell 1 1,alignx right,aligny center");
		JTextField tf_id = new JTextField();
		getContentPane().add(tf_id, "cell 3 1,growx,aligny center");

		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 1 3 5 7,alignx left,growy");

		JButton btn_seeFantasy = new JButton("Procurar");
		getContentPane().add(btn_seeFantasy, "cell 5 1,growx,aligny bottom");
		btn_seeFantasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panel.removeAll();

					String id = tf_id.getText();

					String str = ChameleonFantasies.facade.seeFantasy(id).toString();
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