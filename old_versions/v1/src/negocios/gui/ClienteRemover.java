package negocios.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import negocios.excecoes.CpfInvalidoException;
import negocios.fachada.Fachada;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import dados.base.excecoes.PessoaInexistenteException;

public class ClienteRemover extends JFrame {
	private static final long serialVersionUID = -4774835663905763867L;
	private JPanel contentPane;
	private JFormattedTextField ftfCpf;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteRemover frame = new ClienteRemover(fachada);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @param fachada
	 * @throws ParseException
	 */
	public ClienteRemover(final Fachada fachada) throws ParseException {
		setTitle("Chameleon Fantasies - Remoção de Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpf.setBounds(88, 75, 63, 36);
		contentPane.add(lblCpf);

		ftfCpf = new JFormattedTextField();
		ftfCpf.setBounds(163, 83, 162, 20);
		MaskFormatter mf = new MaskFormatter("###.###.###-##");
		mf.install(ftfCpf);
		contentPane.add(ftfCpf);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(151, 139, 111, 36);
		contentPane.add(btnRemover);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					fachada.removerCliente(ftfCpf.getText().replace(" ", ""));
					JOptionPane.showMessageDialog(null, "Remoção Concluída.");
				} catch (PessoaInexistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (CpfInvalidoException e) {
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

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Pikachu_Melissa_Smith.jpg"));
		label.setBounds(0, 0, 434, 262);
		contentPane.add(label);
	}
}