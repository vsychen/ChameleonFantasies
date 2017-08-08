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

import negocios.excecoes.CpfInvalidoException;
import negocios.excecoes.EnderecoInvalidoException;
import negocios.excecoes.NomeInvalidoException;
import negocios.excecoes.TempoTrabalhoInvalidoException;
import negocios.fachada.Fachada;
import dados.base.pessoas.Endereco;
import dados.base.pessoas.Funcionario;
import dados.base.excecoes.PessoaCadastradaException;

public class FuncionarioCadastro extends JFrame {
	private static final long serialVersionUID = 4867201366057476234L;
	private JPanel contentPane;
	private JTextField txtNome, txtRua, txtComplemento, txtBairro, txtCidade, txtEstado;
	private JFormattedTextField ftfCpf, ftfTempoTrabalho, ftfNumero;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionarioCadastro frame = new FuncionarioCadastro(fachada);
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
	public FuncionarioCadastro(final Fachada fachada) throws ParseException {
		setTitle("Chameleon Fantasies - Cadastro de Funcionário");
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
		lblNome.setForeground(new Color(255, 0, 0));
		lblNome.setBounds(10, 36, 46, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(66, 33, 358, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(new Color(255, 0, 0));
		lblCpf.setBounds(10, 61, 46, 14);
		contentPane.add(lblCpf);

		ftfCpf = new JFormattedTextField();
		ftfCpf.setBounds(66, 58, 127, 20);
		MaskFormatter mf1 = new MaskFormatter("###.###.###-##");
		mf1.install(ftfCpf);
		contentPane.add(ftfCpf);

		JLabel lblTempoTrabalho = new JLabel("Tempo de Trabalho (anos): ");
		lblTempoTrabalho.setForeground(Color.RED);
		lblTempoTrabalho.setBounds(203, 61, 156, 14);
		contentPane.add(lblTempoTrabalho);

		ftfTempoTrabalho = new JFormattedTextField();
		ftfTempoTrabalho.setBounds(369, 58, 55, 20);
		MaskFormatter mf2 = new MaskFormatter("##");
		mf2.install(ftfTempoTrabalho);
		contentPane.add(ftfTempoTrabalho);

		JLabel lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setForeground(new Color(204, 0, 0));
		lblEndereco.setBounds(10, 96, 93, 14);
		contentPane.add(lblEndereco);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setForeground(new Color(255, 0, 0));
		lblRua.setBounds(10, 121, 46, 14);
		contentPane.add(lblRua);

		txtRua = new JTextField();
		txtRua.setBounds(66, 118, 358, 20);
		contentPane.add(txtRua);
		txtRua.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setForeground(new Color(255, 0, 0));
		lblNumero.setBounds(10, 146, 55, 14);
		contentPane.add(lblNumero);

		ftfNumero = new JFormattedTextField();
		ftfNumero.setBounds(66, 143, 94, 20);
		MaskFormatter mf3 = new MaskFormatter("#####");
		mf3.install(ftfNumero);
		contentPane.add(ftfNumero);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setForeground(new Color(255, 0, 0));
		lblComplemento.setBounds(174, 146, 93, 14);
		contentPane.add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(277, 143, 99, 20);
		contentPane.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setForeground(new Color(255, 0, 0));
		lblBairro.setBounds(10, 171, 46, 14);
		contentPane.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(66, 168, 94, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setForeground(new Color(255, 0, 0));
		lblCidade.setBounds(174, 171, 46, 14);
		contentPane.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(277, 168, 99, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(new Color(255, 0, 0));
		lblEstado.setBounds(10, 196, 46, 14);
		contentPane.add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBounds(66, 193, 94, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(131, 228, 99, 23);
		contentPane.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String nome = txtNome.getText().toUpperCase();
					String cpf = ftfCpf.getText().replace(" ", "");
					int tempoTrabalho = 0;

					if (!ftfTempoTrabalho.getText().equals("  ")) {
						tempoTrabalho = Integer.parseInt(ftfTempoTrabalho.getText().replace(" ", ""));
					}

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
							Funcionario funcionario = new Funcionario(nome, cpf, endereco, tempoTrabalho);
							fachada.cadastrarFuncionario(funcionario);
							JOptionPane.showMessageDialog(null, "Cadastro concluído.");
						} else {
							endereco = new Endereco(rua, complemento, bairro, cidade, estado);
							Funcionario funcionario = new Funcionario(nome, cpf, endereco, tempoTrabalho);
							fachada.cadastrarFuncionario(funcionario);
							JOptionPane.showMessageDialog(null, "Cadastro concluído.");
						}
					} else {
						if (complemento == null) {
							endereco = new Endereco(rua, numero, bairro, cidade, estado);
							Funcionario funcionario = new Funcionario(nome, cpf, endereco, tempoTrabalho);
							fachada.cadastrarFuncionario(funcionario);
							JOptionPane.showMessageDialog(null, "Cadastro concluído.");
						} else {
							endereco = new Endereco(rua, numero, complemento, bairro, cidade, estado);
							Funcionario funcionario = new Funcionario(nome, cpf, endereco, tempoTrabalho);
							fachada.cadastrarFuncionario(funcionario);
							JOptionPane.showMessageDialog(null, "Cadastro concluído.");
						}
					}
				} catch (PessoaCadastradaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NomeInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (CpfInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (EnderecoInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (TempoTrabalhoInvalidoException e) {
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