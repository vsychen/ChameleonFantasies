package main.gui.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.exceptions.DatabaseErrorException;
import net.miginfocom.swing.MigLayout;

public class AddFantasyFrame extends JInternalFrame {
	private static final long serialVersionUID = -5015355366757661978L;

	public AddFantasyFrame() throws ParseException {
		super("Cadastrar Fantasia", true, true, true, true);
		initialize();
		pack();
	}

	private void initialize() throws ParseException {
		JInternalFrame frame = this;
		getContentPane().setLayout(new MigLayout("",
				"[30px][40px][140px,grow][40px][140px,grow][50px][40px][140px,grow][40px][140px,grow][30px]",
				"[20px][20px][10px][20px][10px][10px][20px][20px][10px][20px][20px][20px][10px][20px][20px][20px][10px]"));

		JLabel lbl_name = new JLabel("Nome");
		getContentPane().add(lbl_name, "cell 1 1,alignx right,aligny center");
		JTextField tf_name = new JTextField();
		getContentPane().add(tf_name, "cell 2 1,growx,aligny center");
		tf_name.setColumns(10);

		JLabel lbl_id = new JLabel("ID");
		getContentPane().add(lbl_id, "cell 3 1,alignx right,aligny center");
		JTextField tf_id = new JTextField();
		getContentPane().add(tf_id, "cell 4 1,growx,aligny center");

		JLabel lbl_buyPrice = new JLabel("Preço de Compra");
		getContentPane().add(lbl_buyPrice, "cell 6 1,alignx right,aligny center");
		JTextField tf_buyPrice = new JTextField();
		getContentPane().add(tf_buyPrice, "cell 7 1,growx,aligny center");
		tf_buyPrice.setColumns(10);

		JLabel lbl_sellPrice = new JLabel("Preço de Venda");
		getContentPane().add(lbl_sellPrice, "cell 8 1,alignx right,aligny center");
		JTextField tf_sellPrice = new JTextField();
		getContentPane().add(tf_sellPrice, "cell 9 1,growx,aligny center");
		tf_sellPrice.setColumns(10);

		JLabel lbl_quantity = new JLabel("Quantidade");
		getContentPane().add(lbl_quantity, "cell 1 3,alignx trailing");
		JTextField tf_quantity = new JTextField();
		getContentPane().add(tf_quantity, "cell 2 3,growx");
		tf_quantity.setColumns(10);

		// Hat
		JLabel lbl_hat = new JLabel("Chapeu");
		getContentPane().add(lbl_hat, "cell 2 5");
		JRadioButton rdbtn_hat = new JRadioButton();
		getContentPane().add(rdbtn_hat, "cell 1 5,alignx right,aligny center");

		JLabel lbl_hat_type = new JLabel("Tipo");
		getContentPane().add(lbl_hat_type, "cell 1 6,alignx trailing");
		lbl_hat_type.setEnabled(false);
		JTextField tf_hat_type = new JTextField();
		getContentPane().add(tf_hat_type, "cell 2 6,growx,aligny center");
		tf_hat_type.setColumns(10);
		tf_hat_type.setEnabled(false);

		JRadioButton rdbtn_hat_ornament = new JRadioButton("Tem ornamento?");
		getContentPane().add(rdbtn_hat_ornament, "cell 4 6,alignx left,aligny center");
		rdbtn_hat_ornament.setEnabled(false);

		JLabel lbl_hat_color = new JLabel("Cor");
		getContentPane().add(lbl_hat_color, "cell 1 7,alignx trailing");
		lbl_hat_color.setEnabled(false);
		JTextField tf_hat_color = new JTextField();
		getContentPane().add(tf_hat_color, "cell 2 7,growx,aligny center");
		tf_hat_color.setColumns(10);
		tf_hat_color.setEnabled(false);

		JRadioButton rdbtn_hat_stamp = new JRadioButton("Tem estampa?");
		getContentPane().add(rdbtn_hat_stamp, "cell 4 7,alignx left,aligny center");
		rdbtn_hat_stamp.setEnabled(false);

		// Top
		JLabel lbl_top = new JLabel("Parte de Cima");
		getContentPane().add(lbl_top, "cell 7 5");
		JRadioButton rdbtn_top = new JRadioButton();
		getContentPane().add(rdbtn_top, "cell 6 5,alignx right,aligny center");

		JLabel lbl_top_type = new JLabel("Tipo");
		getContentPane().add(lbl_top_type, "cell 6 6,alignx right");
		lbl_top_type.setEnabled(false);
		JTextField tf_top_type = new JTextField();
		getContentPane().add(tf_top_type, "cell 7 6,growx,aligny center");
		tf_top_type.setColumns(10);
		tf_top_type.setEnabled(false);

		JRadioButton rdbtn_top_ornament = new JRadioButton("Tem ornamento?");
		getContentPane().add(rdbtn_top_ornament, "cell 9 6,alignx left,aligny center");
		rdbtn_top_ornament.setEnabled(false);

		JLabel lbl_top_color = new JLabel("Cor");
		getContentPane().add(lbl_top_color, "cell 6 7,alignx trailing");
		lbl_top_color.setEnabled(false);
		JTextField tf_top_color = new JTextField();
		getContentPane().add(tf_top_color, "cell 7 7,growx,aligny center");
		tf_top_color.setColumns(10);
		tf_top_color.setEnabled(false);

		JRadioButton rdbtn_top_stamp = new JRadioButton("Tem estampa?");
		getContentPane().add(rdbtn_top_stamp, "cell 9 7,alignx left,aligny center");
		rdbtn_top_stamp.setEnabled(false);

		// Bottom
		JLabel lbl_bottom = new JLabel("Parte de Baixo");
		getContentPane().add(lbl_bottom, "cell 2 9");

		JLabel lbl_bottom_type = new JLabel("Tipo");
		getContentPane().add(lbl_bottom_type, "cell 1 10,alignx right");
		JTextField tf_bottom_type = new JTextField();
		getContentPane().add(tf_bottom_type, "cell 2 10,growx,aligny center");
		tf_bottom_type.setColumns(10);

		JRadioButton rdbtn_bottom_ornament = new JRadioButton("Tem ornamento?");
		getContentPane().add(rdbtn_bottom_ornament, "cell 4 10,alignx left,aligny center");

		JLabel lbl_bottom_color = new JLabel("Cor");
		getContentPane().add(lbl_bottom_color, "cell 1 11,alignx right");
		JTextField tf_bottom_color = new JTextField();
		getContentPane().add(tf_bottom_color, "cell 2 11,growx,aligny center");
		tf_bottom_color.setColumns(10);

		JRadioButton rdbtn_bottom_stamp = new JRadioButton("Tem estampa?");
		getContentPane().add(rdbtn_bottom_stamp, "cell 4 11,alignx left,aligny center");

		// Arms
		JLabel lbl_arms = new JLabel("Braços");
		getContentPane().add(lbl_arms, "cell 7 9");
		JRadioButton rdbtn_arms = new JRadioButton();
		getContentPane().add(rdbtn_arms, "cell 6 9,alignx right,aligny center");

		JLabel lbl_arms_type = new JLabel("Tipo");
		getContentPane().add(lbl_arms_type, "cell 6 10,alignx right");
		lbl_arms_type.setEnabled(false);
		JTextField tf_arms_type = new JTextField();
		getContentPane().add(tf_arms_type, "cell 7 10,growx,aligny center");
		tf_arms_type.setColumns(10);
		tf_arms_type.setEnabled(false);

		JRadioButton rdbtn_arms_ornament = new JRadioButton("Tem ornamento?");
		getContentPane().add(rdbtn_arms_ornament, "cell 9 10,alignx left,aligny center");
		rdbtn_arms_ornament.setEnabled(false);

		JLabel lbl_arms_color = new JLabel("Cor");
		getContentPane().add(lbl_arms_color, "cell 6 11,alignx right");
		lbl_arms_color.setEnabled(false);
		JTextField tf_arms_color = new JTextField();
		getContentPane().add(tf_arms_color, "cell 7 11,growx,aligny center");
		tf_arms_color.setColumns(10);
		tf_arms_color.setEnabled(false);

		JRadioButton rdbtn_arms_stamp = new JRadioButton("Tem estampa?");
		getContentPane().add(rdbtn_arms_stamp, "cell 9 11,alignx left,aligny center");
		rdbtn_arms_stamp.setEnabled(false);
		JRadioButton rdbtn_shoes = new JRadioButton();
		getContentPane().add(rdbtn_shoes, "cell 1 13,alignx right,aligny center");

		// Shoes
		JLabel lbl_shoes = new JLabel("Sapatos");
		getContentPane().add(lbl_shoes, "cell 2 13");

		JLabel lbl_shoes_type = new JLabel("Tipo");
		getContentPane().add(lbl_shoes_type, "cell 1 14,alignx trailing");
		lbl_shoes_type.setEnabled(false);
		JTextField tf_shoes_type = new JTextField();
		getContentPane().add(tf_shoes_type, "cell 2 14,growx");
		tf_shoes_type.setColumns(10);
		tf_shoes_type.setEnabled(false);

		JRadioButton rdbtn_shoes_ornament = new JRadioButton("Tem ornamento?");
		getContentPane().add(rdbtn_shoes_ornament, "cell 4 14");
		rdbtn_shoes_ornament.setEnabled(false);

		JLabel lbl_shoes_color = new JLabel("Cor");
		getContentPane().add(lbl_shoes_color, "cell 1 15,alignx trailing");
		lbl_shoes_color.setEnabled(false);
		JTextField tf_shoes_color = new JTextField();
		getContentPane().add(tf_shoes_color, "cell 2 15,growx");
		tf_shoes_color.setColumns(10);
		tf_shoes_color.setEnabled(false);

		JRadioButton rdbtn_shoes_stamp = new JRadioButton("Tem estampa?");
		getContentPane().add(rdbtn_shoes_stamp, "cell 4 15");
		rdbtn_shoes_stamp.setEnabled(false);

		JButton btn_addFantasy = new JButton("Cadastrar");
		getContentPane().add(btn_addFantasy, "cell 7 15,growx,aligny center");

		// Actions
		rdbtn_hat.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					lbl_hat_type.setEnabled(true);
					tf_hat_type.setEnabled(true);
					rdbtn_hat_ornament.setEnabled(true);
					lbl_hat_color.setEnabled(true);
					tf_hat_color.setEnabled(true);
					rdbtn_hat_stamp.setEnabled(true);
				} else {
					lbl_hat_type.setEnabled(false);
					tf_hat_type.setEnabled(false);
					rdbtn_hat_ornament.setEnabled(false);
					lbl_hat_color.setEnabled(false);
					tf_hat_color.setEnabled(false);
					rdbtn_hat_stamp.setEnabled(false);
				}
			}
		});

		rdbtn_top.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					lbl_top_type.setEnabled(true);
					tf_top_type.setEnabled(true);
					rdbtn_top_ornament.setEnabled(true);
					lbl_top_color.setEnabled(true);
					tf_top_color.setEnabled(true);
					rdbtn_top_stamp.setEnabled(true);
				} else {
					lbl_top_type.setEnabled(false);
					tf_top_type.setEnabled(false);
					rdbtn_top_ornament.setEnabled(false);
					lbl_top_color.setEnabled(false);
					tf_top_color.setEnabled(false);
					rdbtn_top_stamp.setEnabled(false);
				}
			}
		});

		rdbtn_arms.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					lbl_arms_type.setEnabled(true);
					tf_arms_type.setEnabled(true);
					rdbtn_arms_ornament.setEnabled(true);
					lbl_arms_color.setEnabled(true);
					tf_arms_color.setEnabled(true);
					rdbtn_arms_stamp.setEnabled(true);
				} else {
					lbl_arms_type.setEnabled(false);
					tf_arms_type.setEnabled(false);
					rdbtn_arms_ornament.setEnabled(false);
					lbl_arms_color.setEnabled(false);
					tf_arms_color.setEnabled(false);
					rdbtn_arms_stamp.setEnabled(false);
				}
			}
		});

		rdbtn_shoes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					lbl_shoes_type.setEnabled(true);
					tf_shoes_type.setEnabled(true);
					rdbtn_shoes_ornament.setEnabled(true);
					lbl_shoes_color.setEnabled(true);
					tf_shoes_color.setEnabled(true);
					rdbtn_shoes_stamp.setEnabled(true);
				} else {
					lbl_shoes_type.setEnabled(false);
					tf_shoes_type.setEnabled(false);
					rdbtn_shoes_ornament.setEnabled(false);
					lbl_shoes_color.setEnabled(false);
					tf_shoes_color.setEnabled(false);
					rdbtn_shoes_stamp.setEnabled(false);
				}
			}
		});

		btn_addFantasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = tf_name.getText();
					String id = tf_id.getText();
					Object[] hat = { tf_hat_type.getText(), tf_hat_color.getText(), rdbtn_hat_ornament.isSelected(),
							rdbtn_hat_stamp.isSelected() };
					Object[] top = { tf_top_type.getText(), tf_top_color.getText(), rdbtn_top_ornament.isSelected(),
							rdbtn_top_stamp.isSelected() };
					Object[] bottom = { tf_bottom_type.getText(), tf_bottom_color.getText(),
							rdbtn_bottom_ornament.isSelected(), rdbtn_bottom_stamp.isSelected() };
					Object[] arms = { tf_arms_type.getText(), tf_arms_color.getText(), rdbtn_arms_ornament.isSelected(),
							rdbtn_arms_stamp.isSelected() };
					Object[] shoes = { tf_shoes_type.getText(), tf_shoes_color.getText(),
							rdbtn_shoes_ornament.isSelected(), rdbtn_shoes_stamp.isSelected() };
					int quantity = Integer.parseInt(tf_quantity.getText());
					double buyPrice = Double.parseDouble(tf_buyPrice.getText());
					double sellPrice = Double.parseDouble(tf_sellPrice.getText());

					ChameleonFantasies.facade.addFantasy(name, id, hat, top, bottom, arms, shoes, quantity, buyPrice,
							sellPrice);

					JOptionPane.showMessageDialog(null, "Fantasia cadastrada com sucesso.");
					frame.dispose();
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "NumberFormatException: " + nfe.getMessage());
				} catch (DatabaseErrorException dee) {
					JOptionPane.showMessageDialog(null, "DatabaseErrorException: " + dee.getMessage());
				} catch (InvalidFieldException ife) {
					JOptionPane.showMessageDialog(null, "InvalidFieldException: " + ife.getMessage());
				} catch (LoginErrorException lee) {
					JOptionPane.showMessageDialog(null, "InvalidFieldException: " + lee.getMessage());
				} catch (UnauthorizedActionException uae) {
					JOptionPane.showMessageDialog(null, "InvalidFieldException: " + uae.getMessage());
				}
			}
		});
	}
}