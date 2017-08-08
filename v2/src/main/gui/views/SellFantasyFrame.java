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
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import main.business.exceptions.InsufficientStockException;
import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class SellFantasyFrame extends JInternalFrame {
	private static final long serialVersionUID = -8323503998875423357L;

	public SellFantasyFrame() throws ParseException {
		super("Vender Fantasias", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane().setLayout(new MigLayout("", "[30px][30px][20px][50px,grow][70px][20px][30px][60px][30px]",
				"[20px][20px][10px][20px][10px][20px]"));

		JLabel lbl_cpf = new JLabel("Cpf");
		getContentPane().add(lbl_cpf, "cell 1 1,alignx right,aligny center");
		JFormattedTextField ftf_cpf = new JFormattedTextField(new MaskFormatter("###########"));
		getContentPane().add(ftf_cpf, "cell 3 1 5 1,growx,aligny center");

		JLabel lbl_id = new JLabel("ID");
		getContentPane().add(lbl_id, "cell 1 3,alignx right,aligny center");
		JTextField tf_id = new JTextField();
		getContentPane().add(tf_id, "cell 3 3 2 1,growx,aligny center");

		JLabel lbl_quantity = new JLabel("Quantidade");
		getContentPane().add(lbl_quantity, "cell 6 3,alignx trailing");
		JTextField tf_quantity = new JTextField();
		getContentPane().add(tf_quantity, "cell 7 3,growx");
		tf_quantity.setColumns(10);

		JButton btn_sellFantasy = new JButton("Vender Fantasias");
		getContentPane().add(btn_sellFantasy, "cell 4 5,growx,aligny bottom");
		btn_sellFantasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cpf = new StringBuilder(ftf_cpf.getText()).insert(9, "-").insert(6, ".").insert(3, ".")
							.toString();
					String id = tf_id.getText();
					int quantity = Integer.parseInt(tf_quantity.getText());

					ChameleonFantasies.facade.sellFantasy(cpf, id, quantity);

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
				} catch (InsufficientStockException ise) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + ise.getMessage());
				}
			}
		});
	}
}