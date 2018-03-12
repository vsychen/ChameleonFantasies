package negocios.gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import dados.base.excecoes.ConjuntoInexistenteException;
import negocios.excecoes.NomeConjuntoInvalidoException;
import negocios.fachada.Fachada;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FantasiaProcurar extends JFrame {
	private static final long serialVersionUID = 8688439794418775729L;
	private JPanel contentPane;
	private JTextField txtNomeConjunto;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FantasiaProcurar frame = new FantasiaProcurar(fachada);
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
	public FantasiaProcurar(final Fachada fachada) {
		setTitle("Chameleon Fantasies - Informação de Fantasia");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeConjunto = new JLabel("Nome do Conjunto:");
		lblNomeConjunto.setForeground(new Color(255, 255, 0));
		lblNomeConjunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeConjunto.setBounds(40, 75, 111, 36);
		contentPane.add(lblNomeConjunto);

		txtNomeConjunto = new JTextField();
		txtNomeConjunto.setBounds(163, 83, 162, 20);
		contentPane.add(txtNomeConjunto);

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(151, 139, 111, 36);
		contentPane.add(btnProcurar);
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					JOptionPane.showMessageDialog(null, fachada.procurarFantasia(txtNomeConjunto.getText()).toString());
				} catch (ConjuntoInexistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (HeadlessException e) {
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
		lblBackground.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Hulk_Melissa_Smith.jpg"));
		lblBackground.setBounds(0, 0, 434, 262);
		contentPane.add(lblBackground);
	}
}