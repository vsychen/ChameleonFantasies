package negocios.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import negocios.fachada.Fachada;

public class FantasiaFrame extends JFrame {
	private static final long serialVersionUID = -5965040190044543386L;
	private JPanel contentPane;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FantasiaFrame frame = new FantasiaFrame(fachada);
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
	public FantasiaFrame(final Fachada fachada) {
		setTitle("Chameleon Fantasies - Fantasia");
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
		btnCadastrar.setBounds(10, 161, 104, 23);
		contentPane.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					FantasiaCadastro newWindow = new FantasiaCadastro(fachada);
					newWindow.setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(157, 161, 104, 23);
		contentPane.add(btnAtualizar);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					FantasiaAtualizar newWindow = new FantasiaAtualizar(fachada);
					newWindow.setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(10, 195, 104, 23);
		contentPane.add(btnProcurar);
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FantasiaProcurar newWindow = new FantasiaProcurar(fachada);
				newWindow.setVisible(true);
			}
		});

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(157, 195, 104, 23);
		contentPane.add(btnRemover);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FantasiaRemover newWindow = new FantasiaRemover(fachada);
				newWindow.setVisible(true);
			}
		});

		JButton btnVenda = new JButton("Venda/Compra");
		btnVenda.setBounds(302, 161, 122, 23);
		contentPane.add(btnVenda);
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoque newWindow = new Estoque(fachada);
				newWindow.setVisible(true);
			}
		});

		JButton btnReceita = new JButton("Receita");
		btnReceita.setBounds(302, 195, 122, 23);
		contentPane.add(btnReceita);
		btnReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Receita newWindow = new Receita(fachada);
				newWindow.setVisible(true);
			}
		});

		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(302, 229, 122, 23);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					JOptionPane.showMessageDialog(null, ("Relatório: \n" + fachada.gerarRelatorioFantasia()));
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