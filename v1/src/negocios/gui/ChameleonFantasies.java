package negocios.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;

import negocios.excecoes.TxtInvalidoException;
import negocios.fachada.Fachada;

public class ChameleonFantasies extends JFrame {
	private static final long serialVersionUID = -8719781147377825863L;
	private JPanel contentPane;
	private Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOptionPane.showMessageDialog(null,
							"When the project v1 was created, we used Jakarta POI to save the information when saving in files (.xls).\nThis library is very bad, full of deprecated functions and to make Jakarta work, the code would need to be modified so, we just removed all things related to Jakarta from the project.\n\nBut, hey, what does this mean?\n\nThis mean that when you close the application, all information will be lost.\nDo not worry. In v2 the only repository used was JSON files, meaning that information can be saved");

					ChameleonFantasies frame = new ChameleonFantasies();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChameleonFantasies() {
		setTitle("Chameleon Fantasies");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		try {
			fachada = new Fachada();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (TxtInvalidoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChameleonFantasies = new JLabel("Chameleon Fantasies");
		lblChameleonFantasies.setFont(new Font("Viner Hand ITC", Font.PLAIN, 30));
		lblChameleonFantasies.setForeground(Color.RED);
		lblChameleonFantasies.setHorizontalAlignment(SwingConstants.CENTER);
		lblChameleonFantasies.setBounds(49, 11, 337, 74);
		contentPane.add(lblChameleonFantasies);

		JButton btnFantasias = new JButton("Fantasias");
		btnFantasias.setBounds(10, 228, 114, 23);
		contentPane.add(btnFantasias);
		btnFantasias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FantasiaFrame newWindow = new FantasiaFrame(fachada);
				newWindow.setVisible(true);
			}
		});

		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBounds(159, 228, 114, 23);
		contentPane.add(btnClientes);
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ClienteFrame newWindow = new ClienteFrame(fachada);
				newWindow.setVisible(true);
			}
		});

		JButton btnFuncionarios = new JButton("Funcion\u00E1rios");
		btnFuncionarios.setBounds(310, 228, 114, 23);
		contentPane.add(btnFuncionarios);
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FuncionarioFrame newWindow = new FuncionarioFrame(fachada);
				newWindow.setVisible(true);
			}
		});

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Scrat_Peter_De_Seve.jpg"));
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.setBounds(0, 0, 434, 262);
		contentPane.add(lblBackground);
	}
}