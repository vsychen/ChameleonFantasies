package negocios.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import dados.base.excecoes.ConjuntoInexistenteException;
import negocios.excecoes.QuantidadeInvalidaException;
import negocios.fachada.Fachada;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Estoque extends JFrame {
	private static final long serialVersionUID = 1472519991545510584L;
	private JPanel contentPane;
	private JTextField txtNomeFantasia;
	private JFormattedTextField ftfQuantidade;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoque frame = new Estoque(fachada);
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
	public Estoque(final Fachada fachada) {
		setTitle("Chameleon Fantasies - Estoque das Fantasias");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeDaFantasia = new JLabel("Nome da Fantasia: ");
		lblNomeDaFantasia.setForeground(new Color(0, 153, 0));
		lblNomeDaFantasia.setBounds(72, 99, 108, 30);
		contentPane.add(lblNomeDaFantasia);

		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setBounds(174, 104, 196, 20);
		contentPane.add(txtNomeFantasia);
		txtNomeFantasia.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade: ");
		lblQuantidade.setForeground(new Color(0, 153, 0));
		lblQuantidade.setBounds(141, 140, 77, 30);
		contentPane.add(lblQuantidade);

		ftfQuantidade = new JFormattedTextField();
		ftfQuantidade.setBounds(228, 145, 46, 20);
		contentPane.add(ftfQuantidade);

		JButton btnComprar = new JButton("Comprar");
		btnComprar.setBounds(10, 228, 89, 23);
		contentPane.add(btnComprar);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					fachada.comprar(txtNomeFantasia.getText(),
							Integer.parseInt(ftfQuantidade.getText().replace(" ", "0")));
					JOptionPane.showMessageDialog(null, "Compra efetuada.");
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (QuantidadeInvalidaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (ConjuntoInexistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnVender = new JButton("Vender");
		btnVender.setBounds(174, 228, 89, 23);
		contentPane.add(btnVender);
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					fachada.vender(txtNomeFantasia.getText(),
							Integer.parseInt(ftfQuantidade.getText().replace(" ", "0")));
					JOptionPane.showMessageDialog(null, "Venda efetuada.");
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (QuantidadeInvalidaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (ConjuntoInexistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
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
		lblBackground.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Captain_America_Melissa_Smith.jpg"));
		lblBackground.setBounds(0, 0, 434, 262);
		contentPane.add(lblBackground);
	}
}