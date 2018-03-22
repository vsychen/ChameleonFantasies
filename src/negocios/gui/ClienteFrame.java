package negocios.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import negocios.fachada.Fachada;

public class ClienteFrame extends JFrame {
	private static final long serialVersionUID = 5143958148611966588L;
	private JPanel contentPane;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteFrame frame = new ClienteFrame(fachada);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @param fachada
	 */
	public ClienteFrame(final Fachada fachada) {
		setTitle("Chameleon Fantasies - Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChameleonFantasies = new JLabel("Chameleon Fantasies");
		lblChameleonFantasies.setHorizontalAlignment(SwingConstants.CENTER);
		lblChameleonFantasies.setForeground(Color.RED);
		lblChameleonFantasies.setFont(new Font("Viner Hand ITC", Font.PLAIN, 30));
		lblChameleonFantasies.setBounds(46, 11, 337, 74);
		contentPane.add(lblChameleonFantasies);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(10, 195, 104, 23);
		contentPane.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					ClienteCadastro newWindow = new ClienteCadastro(fachada);
					newWindow.setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(101, 229, 89, 23);
		contentPane.add(btnProcurar);
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					ClienteProcurar newWindow = new ClienteProcurar(fachada);
					newWindow.setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(171, 195, 104, 23);
		contentPane.add(btnAtualizar);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					ClienteAtualizar newWindow = new ClienteAtualizar(fachada);
					newWindow.setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(251, 229, 89, 23);
		contentPane.add(btnRemover);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					ClienteRemover newWindow = new ClienteRemover(fachada);
					newWindow.setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(320, 195, 104, 23);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					JOptionPane.showMessageDialog(null, "Relatório: \n" + fachada.gerarRelatorioCliente());
				} catch (HeadlessException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

				dispose();
			}
		});

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Scrat_Peter_De_Seve.jpg"));
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.setBounds(0, 0, 434, 262);
		contentPane.add(lblBackground);
	}
}