package negocios.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import dados.base.excecoes.ConjuntoInexistenteException;
import negocios.excecoes.NomeConjuntoInvalidoException;
import negocios.fachada.Fachada;

public class FantasiaRemover extends JFrame {
	private static final long serialVersionUID = -2149905527085638349L;
	private JPanel contentPane;
	private JTextField txtNomeConjunto;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FantasiaRemover frame = new FantasiaRemover(fachada);
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
	public FantasiaRemover(final Fachada fachada) {
		setTitle("Chameleon Fantasies - Remoção de Fantasia");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeConjunto = new JLabel("Nome do Conjunto:");
		lblNomeConjunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeConjunto.setForeground(Color.BLACK);
		lblNomeConjunto.setBounds(40, 75, 111, 36);
		contentPane.add(lblNomeConjunto);

		txtNomeConjunto = new JTextField();
		txtNomeConjunto.setBounds(163, 83, 162, 20);
		contentPane.add(txtNomeConjunto);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(151, 139, 111, 36);
		contentPane.add(btnRemover);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					fachada.removerFantasia(txtNomeConjunto.getText());
					JOptionPane.showMessageDialog(null, "Remoção Concluída.");
				} catch (ConjuntoInexistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NomeConjuntoInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(335, 228, 89, 23);
		contentPane.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Thor_Melissa_Smith.jpg"));
		lblBackground.setBounds(0, 0, 434, 262);
		contentPane.add(lblBackground);
	}
}