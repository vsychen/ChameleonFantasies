package negocios.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import negocios.fachada.Fachada;

public class Receita extends JFrame {
	private static final long serialVersionUID = -4949462164437504902L;
	private JPanel contentPane;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receita frame = new Receita(fachada);
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
	public Receita(final Fachada fachada) {
		setTitle("Chameleon Fantasies - Receita do Estoque de Fantasias");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnGerarReceita = new JButton("Gerar Receita");
		btnGerarReceita.setBounds(146, 103, 138, 55);
		contentPane.add(btnGerarReceita);
		btnGerarReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, fachada.gerarReceita());
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

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Black_Widow_Melissa_Smith.jpg"));
		label.setBounds(0, 0, 434, 262);
		contentPane.add(label);
	}
}