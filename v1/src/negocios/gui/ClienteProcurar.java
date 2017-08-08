package negocios.gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import dados.base.excecoes.PessoaInexistenteException;

public class ClienteProcurar extends JFrame {
	private static final long serialVersionUID = -51622078672839951L;
	private JPanel contentPane;
	private JFormattedTextField ftfCpf;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteProcurar frame = new ClienteProcurar(fachada);
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
	public ClienteProcurar(final Fachada fachada) throws ParseException {
		setTitle("Chameleon Fantasies - Informação de Cliente");
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

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(151, 139, 111, 36);
		contentPane.add(btnProcurar);
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					JOptionPane.showMessageDialog(null, fachada.procurarCliente(ftfCpf.getText().replace(" ", "")));
				} catch (PessoaInexistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (HeadlessException e) {
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

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Deadpool_Melissa_Smith.jpg"));
		lblBackground.setBounds(0, 0, 434, 262);
		contentPane.add(lblBackground);
	}
}