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

public class ChangePricesFrame extends JInternalFrame {
	private static final long serialVersionUID = -8323503998875423357L;

	public ChangePricesFrame() throws ParseException {
		super("Atualizar Preços", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane().setLayout(new MigLayout("", "[30px][50px][10px][100px,grow][50px][50px][10px][100px][30px]",
				"[20px][20px][10px][20px][10px][20px][20px]"));

		JLabel lbl_id = new JLabel("ID");
		getContentPane().add(lbl_id, "cell 1 1,alignx right,aligny center");
		JTextField tf_id = new JTextField();
		getContentPane().add(tf_id, "cell 3 1 5 1,growx,aligny center");

		JLabel lbl_buyPrice = new JLabel("Preço de Compra");
		getContentPane().add(lbl_buyPrice, "cell 1 3");
		JTextField tf_buyPrice = new JTextField();
		getContentPane().add(tf_buyPrice, "cell 3 3,growx,aligny center");
		tf_buyPrice.setColumns(10);

		JLabel lbl_sellPrice = new JLabel("Preço de Venda");
		getContentPane().add(lbl_sellPrice, "cell 5 3,alignx trailing");
		JTextField tf_sellPrice = new JTextField();
		getContentPane().add(tf_sellPrice, "cell 7 3,growx");
		tf_sellPrice.setColumns(10);

		JButton btn_changePrices = new JButton("Atualizar Preços");
		getContentPane().add(btn_changePrices, "cell 4 5,growx,aligny bottom");
		btn_changePrices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = tf_id.getText();
					double buyPrice = Double.parseDouble(tf_buyPrice.getText());
					double sellPrice = Double.parseDouble(tf_sellPrice.getText());

					ChameleonFantasies.facade.changePrices(id, buyPrice, sellPrice);

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