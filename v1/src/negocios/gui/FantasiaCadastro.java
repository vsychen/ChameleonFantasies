package negocios.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

import dados.base.roupas.Chapeu;
import dados.base.roupas.Fantasia;
import dados.base.roupas.ParteBaixo;
import dados.base.roupas.ParteCima;
import dados.base.excecoes.ConjuntoCadastradoException;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

import negocios.excecoes.CorInvalidaException;
import negocios.excecoes.NomeConjuntoDiferentesException;
import negocios.excecoes.NomeConjuntoInvalidoException;
import negocios.excecoes.PrecoInvalidoException;
import negocios.excecoes.QuantidadeInvalidaException;
import negocios.excecoes.TipoRoupaInvalidoException;
import negocios.fachada.Fachada;

public class FantasiaCadastro extends JFrame {
	private static final long serialVersionUID = -6187855267239595491L;
	private JPanel contentPane;
	private JLabel lblChapeuPrecoCompra, lblChapeuPrecoVenda, lblParteCimaPrecoCompra, lblParteCimaPrecoVenda,
			lblParteCimaTipo, lblParteBaixoPrecoCompra, lblParteBaixoPrecoVenda, lblParteBaixoTipo;
	private JRadioButton rdbtnChapeu, rdbtnChapeuEnfeite, rdbtnChapeuEstampa, rdbtnParteCima, rdbtnParteCimaEnfeite,
			rdbtnParteCimaEstampa, rdbtnParteBaixoEnfeite, rdbtnParteBaixoEstampa;
	private JFormattedTextField ftfChapeuPrecoCompra, ftfChapeuPrecoVenda, ftfParteCimaPrecoCompra,
			ftfParteCimaPrecoVenda, ftfParteBaixoPrecoCompra, ftfParteBaixoPrecoVenda;
	private JTextField txtNomeConjunto, txtChapeuCor, txtParteCimaCor, txtParteCimaTipo, txtParteBaixoCor,
			txtParteBaixoTipo;
	private JFormattedTextField ftfQuantidade;
	private static Fachada fachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FantasiaCadastro frame = new FantasiaCadastro(fachada);
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
	public FantasiaCadastro(final Fachada fachada) throws ParseException {
		setTitle("Chameleon Fantasies - Cadastro de Fantasia");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Informações Gerais
		JLabel lblNomeDoConjunto = new JLabel("Nome do Conjunto: ");
		lblNomeDoConjunto.setForeground(Color.GREEN);
		lblNomeDoConjunto.setBounds(10, 11, 115, 14);
		contentPane.add(lblNomeDoConjunto);

		txtNomeConjunto = new JTextField();
		txtNomeConjunto.setToolTipText("Nome do conjunto");
		txtNomeConjunto.setColumns(10);
		txtNomeConjunto.setBounds(135, 8, 201, 20);
		contentPane.add(txtNomeConjunto);

		ftfQuantidade = new JFormattedTextField();
		ftfQuantidade.setToolTipText("Quantidade");
		ftfQuantidade.setBounds(346, 8, 78, 20);
		MaskFormatter mfQuantidade = new MaskFormatter("###");
		mfQuantidade.install(ftfQuantidade);
		contentPane.add(ftfQuantidade);

		// Parte de Baixo
		JLabel lblParteBaixo = new JLabel("Parte de Baixo");
		lblParteBaixo.setForeground(Color.BLACK);
		lblParteBaixo.setBounds(20, 39, 86, 14);
		contentPane.add(lblParteBaixo);

		lblParteBaixoPrecoCompra = new JLabel("Compra:");
		lblParteBaixoPrecoCompra.setForeground(Color.GREEN);
		lblParteBaixoPrecoCompra.setBounds(127, 39, 51, 14);
		contentPane.add(lblParteBaixoPrecoCompra);

		ftfParteBaixoPrecoCompra = new JFormattedTextField();
		ftfParteBaixoPrecoCompra.setToolTipText("Pre\u00E7o de compra da parte de baixo");
		ftfParteBaixoPrecoCompra.setColumns(10);
		ftfParteBaixoPrecoCompra.setBounds(188, 36, 51, 20);
		MaskFormatter mf1 = new MaskFormatter("###,##");
		mf1.install(ftfParteBaixoPrecoCompra);
		contentPane.add(ftfParteBaixoPrecoCompra);

		lblParteBaixoPrecoVenda = new JLabel("Venda:");
		lblParteBaixoPrecoVenda.setForeground(Color.GREEN);
		lblParteBaixoPrecoVenda.setBounds(128, 63, 49, 14);
		contentPane.add(lblParteBaixoPrecoVenda);

		ftfParteBaixoPrecoVenda = new JFormattedTextField();
		ftfParteBaixoPrecoVenda.setToolTipText("Pre\u00E7o de venda da parte de baixo");
		ftfParteBaixoPrecoVenda.setColumns(10);
		ftfParteBaixoPrecoVenda.setBounds(189, 60, 51, 20);
		MaskFormatter mf2 = new MaskFormatter("###,##");
		mf2.install(ftfParteBaixoPrecoVenda);
		contentPane.add(ftfParteBaixoPrecoVenda);

		JLabel lblParteBaixoCor = new JLabel("Cor: ");
		lblParteBaixoCor.setForeground(Color.GREEN);
		lblParteBaixoCor.setBounds(257, 39, 39, 14);
		contentPane.add(lblParteBaixoCor);

		txtParteBaixoCor = new JTextField();
		txtParteBaixoCor.setToolTipText("Cor da parte de baixo");
		txtParteBaixoCor.setColumns(10);
		txtParteBaixoCor.setBounds(306, 36, 118, 20);
		contentPane.add(txtParteBaixoCor);

		rdbtnParteBaixoEnfeite = new JRadioButton("Enfeite");
		rdbtnParteBaixoEnfeite.setBounds(250, 59, 86, 23);
		contentPane.add(rdbtnParteBaixoEnfeite);

		rdbtnParteBaixoEstampa = new JRadioButton("Estampa");
		rdbtnParteBaixoEstampa.setBounds(338, 59, 86, 23);
		contentPane.add(rdbtnParteBaixoEstampa);

		lblParteBaixoTipo = new JLabel("Tipo: ");
		lblParteBaixoTipo.setForeground(Color.GREEN);
		lblParteBaixoTipo.setBounds(10, 86, 39, 14);
		contentPane.add(lblParteBaixoTipo);

		txtParteBaixoTipo = new JTextField();
		txtParteBaixoTipo.setToolTipText("Tipo da parte de baixo");
		txtParteBaixoTipo.setColumns(10);
		txtParteBaixoTipo.setBounds(57, 83, 367, 20);
		contentPane.add(txtParteBaixoTipo);

		// Parte de Cima
		rdbtnParteCima = new JRadioButton("Parte de Cima");
		rdbtnParteCima.setForeground(Color.BLACK);
		rdbtnParteCima.setBounds(0, 110, 107, 23);
		contentPane.add(rdbtnParteCima);

		lblParteCimaPrecoCompra = new JLabel("Compra:");
		lblParteCimaPrecoCompra.setForeground(Color.GREEN);
		lblParteCimaPrecoCompra.setBounds(128, 114, 51, 14);
		contentPane.add(lblParteCimaPrecoCompra);

		ftfParteCimaPrecoCompra = new JFormattedTextField();
		ftfParteCimaPrecoCompra.setToolTipText("Pre\u00E7o de compra da parte de cima");
		ftfParteCimaPrecoCompra.setEditable(false);
		ftfParteCimaPrecoCompra.setColumns(10);
		ftfParteCimaPrecoCompra.setBounds(189, 108, 51, 20);
		MaskFormatter mf3 = new MaskFormatter("###,##");
		mf3.install(ftfParteCimaPrecoCompra);
		contentPane.add(ftfParteCimaPrecoCompra);

		lblParteCimaPrecoVenda = new JLabel("Venda:");
		lblParteCimaPrecoVenda.setForeground(Color.GREEN);
		lblParteCimaPrecoVenda.setBounds(128, 134, 49, 14);
		contentPane.add(lblParteCimaPrecoVenda);

		ftfParteCimaPrecoVenda = new JFormattedTextField();
		ftfParteCimaPrecoVenda.setEditable(false);
		ftfParteCimaPrecoVenda.setToolTipText("Pre\u00E7o de venda da parte de cima");
		ftfParteCimaPrecoVenda.setColumns(10);
		ftfParteCimaPrecoVenda.setBounds(189, 131, 51, 20);
		MaskFormatter mf4 = new MaskFormatter("###,##");
		mf4.install(ftfParteCimaPrecoVenda);
		contentPane.add(ftfParteCimaPrecoVenda);

		JLabel lblParteCimaCor = new JLabel("Cor: ");
		lblParteCimaCor.setForeground(Color.GREEN);
		lblParteCimaCor.setBounds(257, 114, 46, 14);
		contentPane.add(lblParteCimaCor);

		txtParteCimaCor = new JTextField();
		txtParteCimaCor.setEditable(false);
		txtParteCimaCor.setToolTipText("Cor da parte de cima");
		txtParteCimaCor.setColumns(10);
		txtParteCimaCor.setBounds(313, 108, 111, 20);
		contentPane.add(txtParteCimaCor);

		rdbtnParteCimaEnfeite = new JRadioButton("Enfeite");
		rdbtnParteCimaEnfeite.setEnabled(false);
		rdbtnParteCimaEnfeite.setBounds(250, 130, 86, 23);
		contentPane.add(rdbtnParteCimaEnfeite);

		rdbtnParteCimaEstampa = new JRadioButton("Estampa");
		rdbtnParteCimaEstampa.setEnabled(false);
		rdbtnParteCimaEstampa.setBounds(338, 130, 86, 23);
		contentPane.add(rdbtnParteCimaEstampa);

		lblParteCimaTipo = new JLabel("Tipo: ");
		lblParteCimaTipo.setForeground(Color.GREEN);
		lblParteCimaTipo.setBounds(10, 158, 39, 14);
		contentPane.add(lblParteCimaTipo);

		txtParteCimaTipo = new JTextField();
		txtParteCimaTipo.setEditable(false);
		txtParteCimaTipo.setToolTipText("Tipo da parte de cima");
		txtParteCimaTipo.setColumns(10);
		txtParteCimaTipo.setBounds(59, 155, 365, 20);
		contentPane.add(txtParteCimaTipo);

		rdbtnParteCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (rdbtnParteCima.isSelected()) {
					ftfParteCimaPrecoCompra.setEditable(true);
					ftfParteCimaPrecoVenda.setEditable(true);
					txtParteCimaCor.setEditable(true);
					rdbtnParteCimaEnfeite.setEnabled(true);
					rdbtnParteCimaEstampa.setEnabled(true);
					txtParteCimaTipo.setEditable(true);
				} else {
					ftfParteCimaPrecoCompra.setEditable(false);
					ftfParteCimaPrecoVenda.setEditable(false);
					txtParteCimaCor.setEditable(false);
					rdbtnParteCimaEnfeite.setEnabled(false);
					rdbtnParteCimaEstampa.setEnabled(false);
					txtParteCimaTipo.setEditable(false);
				}
			}
		});

		// Chapéu
		rdbtnChapeu = new JRadioButton("Chap\u00E9u");
		rdbtnChapeu.setForeground(Color.BLACK);
		rdbtnChapeu.setBounds(0, 182, 109, 23);
		contentPane.add(rdbtnChapeu);

		lblChapeuPrecoCompra = new JLabel("Compra:");
		lblChapeuPrecoCompra.setForeground(Color.GREEN);
		lblChapeuPrecoCompra.setBounds(128, 186, 51, 14);
		contentPane.add(lblChapeuPrecoCompra);

		ftfChapeuPrecoCompra = new JFormattedTextField();
		ftfChapeuPrecoCompra.setToolTipText("Pre\u00E7o de compra do chap\u00E9u");
		ftfChapeuPrecoCompra.setEditable(false);
		ftfChapeuPrecoCompra.setColumns(10);
		ftfChapeuPrecoCompra.setBounds(189, 183, 51, 20);
		MaskFormatter mf5 = new MaskFormatter("###,##");
		mf5.install(ftfChapeuPrecoCompra);
		contentPane.add(ftfChapeuPrecoCompra);

		lblChapeuPrecoVenda = new JLabel("Venda:");
		lblChapeuPrecoVenda.setForeground(Color.GREEN);
		lblChapeuPrecoVenda.setBounds(128, 208, 49, 14);
		contentPane.add(lblChapeuPrecoVenda);

		ftfChapeuPrecoVenda = new JFormattedTextField();
		ftfChapeuPrecoVenda.setEditable(false);
		ftfChapeuPrecoVenda.setToolTipText("Pre\u00E7o de venda do chap\u00E9u");
		ftfChapeuPrecoVenda.setColumns(10);
		ftfChapeuPrecoVenda.setBounds(189, 205, 51, 20);
		MaskFormatter mf6 = new MaskFormatter("###,##");
		mf6.install(ftfChapeuPrecoVenda);
		contentPane.add(ftfChapeuPrecoVenda);

		JLabel lblChapeuCor = new JLabel("Cor: ");
		lblChapeuCor.setForeground(Color.GREEN);
		lblChapeuCor.setBounds(257, 186, 39, 14);
		contentPane.add(lblChapeuCor);

		txtChapeuCor = new JTextField();
		txtChapeuCor.setEditable(false);
		txtChapeuCor.setToolTipText("Cor do chap\u00E9u");
		txtChapeuCor.setColumns(10);
		txtChapeuCor.setBounds(306, 183, 118, 20);
		contentPane.add(txtChapeuCor);

		rdbtnChapeuEnfeite = new JRadioButton("Enfeite");
		rdbtnChapeuEnfeite.setEnabled(false);
		rdbtnChapeuEnfeite.setBounds(250, 204, 86, 23);
		contentPane.add(rdbtnChapeuEnfeite);

		rdbtnChapeuEstampa = new JRadioButton("Estampa");
		rdbtnChapeuEstampa.setEnabled(false);
		rdbtnChapeuEstampa.setBounds(338, 204, 86, 23);
		contentPane.add(rdbtnChapeuEstampa);

		rdbtnChapeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (rdbtnChapeu.isSelected()) {
					ftfChapeuPrecoCompra.setEditable(true);
					ftfChapeuPrecoVenda.setEditable(true);
					txtChapeuCor.setEditable(true);
					rdbtnChapeuEnfeite.setEnabled(true);
					rdbtnChapeuEstampa.setEnabled(true);
				} else {
					ftfChapeuPrecoCompra.setEditable(false);
					ftfChapeuPrecoVenda.setEditable(false);
					txtChapeuCor.setEditable(false);
					rdbtnChapeuEnfeite.setEnabled(false);
					rdbtnChapeuEstampa.setEnabled(false);
				}
			}
		});

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(57, 228, 107, 23);
		contentPane.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Chapeu chapeu = null;
					ParteCima parteCima = null;
					ParteBaixo parteBaixo = null;
					Fantasia fantasia = null;

					// Informações Gerais
					String nomeConjunto = txtNomeConjunto.getText();
					int quantidade = 0;

					for (int i = 0; i <= 9; i++) {
						if (ftfQuantidade.getText().contains(i + "")) {
							quantidade = Integer.parseInt(ftfQuantidade.getText().replace(" ", ""));
							i = 10;
						}
					}

					// Parte de Baixo
					double parteBaixoPrecoCompra = Double
							.parseDouble(ftfParteBaixoPrecoCompra.getText().replace(" ", "0").replace(",", "."));

					double parteBaixoPrecoVenda = 0;
					String string2 = ftfParteBaixoPrecoVenda.getText().replace(" ", "").replace(",", ".");

					for (int i = 0; i <= 9; i++) {
						if (string2.contains(i + "")) {
							parteBaixoPrecoVenda = Double.parseDouble(string2);
							i = 10;
						}
					}

					String parteBaixoCor = txtParteBaixoCor.getText();
					boolean parteBaixoEnfeite = false, parteBaixoEstampa = false;

					if (rdbtnParteBaixoEnfeite.isSelected()) {
						parteBaixoEnfeite = true;
					}

					if (rdbtnParteBaixoEstampa.isSelected()) {
						parteBaixoEstampa = true;
					}

					String parteBaixoTipo = txtParteBaixoTipo.getText();

					parteBaixo = new ParteBaixo(nomeConjunto, parteBaixoPrecoVenda, parteBaixoPrecoCompra,
							parteBaixoCor, parteBaixoEnfeite, parteBaixoEstampa, parteBaixoTipo);

					// Parte de Cima
					if (rdbtnParteCima.isSelected()) {
						double parteCimaPrecoCompra = 0;
						String string3 = ftfParteCimaPrecoCompra.getText().replace(" ", "").replace(",", ".");

						for (int i = 0; i <= 9; i++) {
							if (string3.contains(i + "")) {
								parteCimaPrecoCompra = Double.parseDouble(string3);
								i = 10;
							}
						}

						double parteCimaPrecoVenda = 0;
						String string4 = ftfParteCimaPrecoVenda.getText().replace(" ", "").replace(",", ".");

						for (int i = 0; i <= 9; i++) {
							if (string4.contains(i + "")) {
								parteCimaPrecoVenda = Double.parseDouble(string4);
								i = 10;
							}
						}

						String parteCimaCor = txtParteCimaCor.getText();
						boolean parteCimaEnfeite = false, parteCimaEstampa = false;

						if (rdbtnParteCimaEnfeite.isSelected()) {
							parteCimaEnfeite = true;
						}

						if (rdbtnParteCimaEstampa.isSelected()) {
							parteCimaEstampa = true;
						}

						String parteCimaTipo = txtParteCimaTipo.getText();

						parteCima = new ParteCima(nomeConjunto, parteCimaPrecoVenda, parteCimaPrecoCompra, parteCimaCor,
								parteCimaEnfeite, parteCimaEstampa, parteCimaTipo);
					}

					// Chapéu
					if (rdbtnChapeu.isSelected()) {
						double chapeuPrecoCompra = 0;
						String string5 = ftfChapeuPrecoCompra.getText().replace(" ", "").replace(",", ".");

						for (int i = 0; i <= 9; i++) {
							if (string5.contains(i + "")) {
								chapeuPrecoCompra = Double.parseDouble(string5);
								i = 10;
							}
						}

						double chapeuPrecoVenda = 0;
						String string6 = ftfChapeuPrecoVenda.getText().replace(" ", "").replace(",", ".");

						for (int i = 0; i <= 9; i++) {
							if (string6.contains(i + "")) {
								chapeuPrecoVenda = Double.parseDouble(string6);
								i = 10;
							}
						}

						String chapeuCor = txtChapeuCor.getText();
						boolean chapeuEnfeite = false, chapeuEstampa = false;

						if (rdbtnChapeuEnfeite.isSelected()) {
							chapeuEnfeite = true;
						}

						if (rdbtnChapeuEstampa.isSelected()) {
							chapeuEstampa = true;
						}

						chapeu = new Chapeu(nomeConjunto, chapeuPrecoVenda, chapeuPrecoCompra, chapeuCor, chapeuEnfeite,
								chapeuEstampa);
					}

					if (chapeu == null && parteCima == null) {
						fantasia = new Fantasia(parteBaixo, quantidade);
						fachada.cadastrarFantasia(fantasia);
						JOptionPane.showMessageDialog(null, "Cadastro concluído.");
					} else if (chapeu == null && parteCima != null) {
						fantasia = new Fantasia(parteCima, parteBaixo, quantidade);
						fachada.cadastrarFantasia(fantasia);
						JOptionPane.showMessageDialog(null, "Cadastro concluído.");
					} else if (chapeu != null && parteCima == null) {
						fantasia = new Fantasia(chapeu, parteBaixo, quantidade);
						fachada.cadastrarFantasia(fantasia);
						JOptionPane.showMessageDialog(null, "Cadastro concluído.");
					} else if (chapeu != null && parteCima != null) {
						fantasia = new Fantasia(chapeu, parteCima, parteBaixo, quantidade);
						fachada.cadastrarFantasia(fantasia);
						JOptionPane.showMessageDialog(null, "Cadastro concluído.");
					} else {
						JOptionPane.showMessageDialog(null, "Não existe fantasia desse tipo.");
					}
				} catch (QuantidadeInvalidaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (CorInvalidaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (PrecoInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (TipoRoupaInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NomeConjuntoInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (NomeConjuntoDiferentesException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (ConjuntoCadastradoException e) {
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
		lblBackground.setIcon(new ImageIcon(fachada.getCaminhoImagem() + "Loki_Melissa_Smith.jpg"));
		lblBackground.setBounds(0, 0, 434, 262);
		contentPane.add(lblBackground);
	}
}