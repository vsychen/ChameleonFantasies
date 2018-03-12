package negocios.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dados.base.excecoes.PessoaInexistenteException;
import dados.base.pessoas.Cliente;
import dados.base.pessoas.Endereco;
import negocios.excecoes.CpfInvalidoException;
import negocios.excecoes.EnderecoInvalidoException;
import negocios.excecoes.NomeInvalidoException;
import negocios.fachada.Fachada;

public class ClienteAtualizar extends JFrame {
	private static final long serialVersionUID = -8622426049237075203L;
	private JPanel contentPane;
	private JFormattedTextField ftfCpf, ftfNumero;
	private JTextField txtNome, txtRua, txtComplemento, txtBairro, txtCidade, txtEstado;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteAtualizar frame = new ClienteAtualizar(fachada);
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
	public ClienteAtualizar(final Fachada fachada) throws ParseException {
		setTitle("Chamaleon Fantasies - Atualização de Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInformacoesPessoais = new JLabel("Informa\u00E7\u00F5es Pessoais");
		lblInformacoesPessoais.setForeground(new Color(204, 0, 0));
		lblInformacoesPessoais.setBounds(10, 11, 150, 14);
		contentPane.add(lblInformacoesPessoais);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.RED);
		lblNome.setBounds(10, 36, 46, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(66, 33, 358, 20);
		contentPane.add(txtNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(Color.RED);
		lblCpf.setBounds(10, 61, 46, 14);
		contentPane.add(lblCpf);

		ftfCpf = new JFormattedTextField();
		ftfCpf.setBounds(66, 58, 150, 20);
		MaskFormatter mf1 = new MaskFormatter("###.###.###-##");
		mf1.install(ftfCpf);
		contentPane.add(ftfCpf);

		JLabel lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setForeground(new Color(204, 0, 0));
		lblEndereco.setBounds(10, 96, 93, 14);
		contentPane.add(lblEndereco);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setForeground(Color.RED);
		lblRua.setBounds(10, 121, 46, 14);
		contentPane.add(lblRua);

		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(66, 118, 358, 20);
		contentPane.add(txtRua);

		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setForeground(Color.RED);
		lblNumero.setBounds(10, 146, 55, 14);
		contentPane.add(lblNumero);

		ftfNumero = new JFormattedTextField();
		ftfNumero.setBounds(66, 143, 94, 20);
		MaskFormatter mf2 = new MaskFormatter("#####");
		mf2.install(ftfNumero);
		contentPane.add(ftfNumero);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setForeground(Color.RED);
		lblComplemento.setBounds(174, 146, 93, 14);
		contentPane.add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(277, 143, 99, 20);
		contentPane.add(txtComplemento);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setForeground(Color.RED);
		lblBairro.setBounds(10, 171, 46, 14);
		contentPane.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(66, 168, 94, 20);
		contentPane.add(txtBairro);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setForeground(Color.RED);
		lblCidade.setBounds(174, 171, 46, 14);
		contentPane.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(277, 168, 99, 20);
		contentPane.add(txtCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(10, 196, 46, 14);
		contentPane.add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(66, 193, 94, 20);
		contentPane.add(txtEstado);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(131, 228, 99, 23);
		contentPane.add(btnAtualizar);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String nome = txtNome.getText().toUpperCase();
					String cpf = ftfCpf.getText().replace(" ", "");
					String rua = txtRua.getText().toUpperCase();
					String string = ftfNumero.getText().replace(" ", "");
					int numero = 0;

					for (int i = 0; i <= 9; i++) {
						if (string.contains(i + "")) {
							numero = Integer.parseInt(string);
							i = 10;
						}
					}

					String complemento = txtComplemento.getText().toUpperCase();
					String bairro = txtBairro.getText().toUpperCase();
					String cidade = txtCidade.getText().toUpperCase();
					String estado = txtEstado.getText().toUpperCase();
					Endereco endereco = null;

					if (numero == 0) {
						if (complemento == null) {
							endereco = new Endereco(rua, bairro, cidade, estado);
							Cliente cliente = new Cliente(nome, cpf, endereco);
							fachada.atualizarCliente(cliente);
							JOptionPane.showMessageDialog(null, "Atualização concluída.");
						} else {
							endereco = new Endereco(rua, complemento, bairro, cidade, estado);
							Cliente cliente = new Cliente(nome, cpf, endereco);
							fachada.atualizarCliente(cliente);
							JOptionPane.showMessageDialog(null, "Atualização concluída.");
						}
					} else {
						if (complemento == null) {
							endereco = new Endereco(rua, numero, bairro, cidade, estado);
							Cliente cliente = new Cliente(nome, cpf, endereco);
							fachada.atualizarCliente(cliente);
							JOptionPane.showMessageDialog(null, "Atualização concluída.");
						} else {
							endereco = new Endereco(rua, numero, complemento, bairro, cidade, estado);
							Cliente cliente = new Cliente(nome, cpf, endereco);
							fachada.atualizarCliente(cliente);
							JOptionPane.showMessageDialog(null, "Atualização concluída.");
						}
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NomeInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (CpfInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (EnderecoInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (PessoaInexistenteException e) {
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
		lblBackground.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Heads_Vance_Kovacs.jpg"));
		lblBackground.setBounds(0, 0, 434, 262);
		contentPane.add(lblBackground);
	}
}