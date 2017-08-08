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

public class BuyFantasyFrame extends JInternalFrame {
	private static final long serialVersionUID = 3713483931589121479L;

	public BuyFantasyFrame() throws ParseException {
		super("Comprar Fantasias", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane().setLayout(new MigLayout("", "[30px][30px][20px][50px][70px][20px][30px][60px][30px]",
				"[20px][20px][10px][10px][20px]"));

		JLabel lbl_id = new JLabel("ID");
		getContentPane().add(lbl_id, "cell 1 1,alignx right,aligny center");
		JTextField tf_id = new JTextField();
		getContentPane().add(tf_id, "cell 3 1 2 1,growx,aligny center");

		JLabel lbl_quantity = new JLabel("Quantidade");
		getContentPane().add(lbl_quantity, "cell 6 1,alignx trailing");
		JTextField tf_quantity = new JTextField();
		getContentPane().add(tf_quantity, "cell 7 1,growx");
		tf_quantity.setColumns(10);

		JButton btn_buyFantasy = new JButton("Comprar Fantasias");
		getContentPane().add(btn_buyFantasy, "cell 4 4,growx,aligny bottom");
		btn_buyFantasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = tf_id.getText();
					int quantity = Integer.parseInt(tf_quantity.getText());

					ChameleonFantasies.facade.buyFantasy(id, quantity);

					JOptionPane.showMessageDialog(null, "Quantidade de fantasias atualizado.");
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