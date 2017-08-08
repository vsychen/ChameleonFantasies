package main.gui.views;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class RemoveFantasyFrame extends JInternalFrame {
	private static final long serialVersionUID = 2026381813691075981L;

	public RemoveFantasyFrame() throws ParseException {
		super("Remover Fantasia", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane()
				.setLayout(new MigLayout("", "[30px][30px][20px][140px][20px][80px][30px]", "[20px][20px][20px]"));

		JLabel lbl_id = new JLabel("ID");
		getContentPane().add(lbl_id, "cell 1 1,alignx right,aligny center");
		JTextField tf_id = new JTextField();
		getContentPane().add(tf_id, "cell 3 1,growx,aligny center");

		JButton btn_removeFantasy = new JButton("Remover");
		getContentPane().add(btn_removeFantasy, "cell 5 1,growx,aligny bottom");
		btn_removeFantasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = tf_id.getText();

					ChameleonFantasies.facade.removeWrongAdditions(id, 2);

					JOptionPane.showMessageDialog(null, "Fantasia removido com sucesso.");
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