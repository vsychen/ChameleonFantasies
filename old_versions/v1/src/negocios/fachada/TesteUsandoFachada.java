package negocios.fachada;

import java.io.IOException;

import dados.base.excecoes.ConjuntoCadastradoException;
import dados.base.excecoes.ConjuntoInexistenteException;
import dados.base.excecoes.PessoaCadastradaException;
import dados.base.excecoes.PessoaInexistenteException;
import dados.base.pessoas.Cliente;
import dados.base.pessoas.Endereco;
import dados.base.pessoas.Funcionario;
import dados.base.roupas.Chapeu;
import dados.base.roupas.Fantasia;
import dados.base.roupas.ParteBaixo;
import dados.base.roupas.ParteCima;
import negocios.excecoes.CorInvalidaException;
import negocios.excecoes.CpfInvalidoException;
import negocios.excecoes.EnderecoInvalidoException;
import negocios.excecoes.NomeConjuntoDiferentesException;
import negocios.excecoes.NomeConjuntoInvalidoException;
import negocios.excecoes.NomeInvalidoException;
import negocios.excecoes.PrecoInvalidoException;
import negocios.excecoes.QuantidadeInvalidaException;
import negocios.excecoes.TempoTrabalhoInvalidoException;
import negocios.excecoes.TipoRoupaInvalidoException;
import negocios.excecoes.TxtInvalidoException;

public class TesteUsandoFachada {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(
				"When the project v1 was created, we used Jakarta POI to save the information when saving in files (.xls). This library is very bad, full of deprecated functions and to make Jakarta work, the code would need to be modified so, we just removed all things related to Jakarta from the project.");
		System.out.println("But, hey, what does this mean?");
		System.out.println(
				"This mean that when you close the application, all information will be lost. Do not worry. In v2 the only repository used was JSON files, meaning that information can be saved");

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Fachada fachada = new Fachada();
			Endereco endereco1 = new Endereco("Rua Dom Macaco", 31, "A", "Bairro", "Cidade", "Estado");
			Cliente cliente1 = new Cliente("Gama Tester", "631.524.482-61", endereco1);
			Cliente cliente2 = new Cliente("Ninguém", "234.616.646-86", endereco1);
			Endereco endereco2 = new Endereco("Estrada dos Tomatos", "Malfica", "Chuva Toda Hora", "Braçobuco");
			Funcionario funcionario1 = new Funcionario("José Augusto", "021.163.167-16", endereco2, 3);
			Funcionario funcionario2 = new Funcionario("Todo Mundo", "631.466.567-36", endereco2, 4);
			Chapeu chapeu1 = new Chapeu("Batman", 100, 80, "Preto", false, false);
			ParteCima parteCima1 = new ParteCima("Batman", 150, 125, "Cinza", false, true, "Camisa Longa");
			ParteBaixo parteBaixo1 = new ParteBaixo("Hulk", 130, 120, "Marrom", false, false, "Calça Jeans");
			ParteBaixo parteBaixo2 = new ParteBaixo("Batman", 130, 115, "Cinza", false, false, "Calça Comprida");
			Fantasia fantasia1 = new Fantasia(chapeu1, parteCima1, parteBaixo2, 10);
			ParteBaixo parteBaixo3 = new ParteBaixo("Aquaman", 200, 150, "Verde", false, true, "Barbatana");
			Fantasia fantasia2 = new Fantasia(parteBaixo1, 6);
			Fantasia fantasia3 = new Fantasia(parteBaixo3, 50);

			fachada.cadastrarCliente(cliente1);
			fachada.cadastrarCliente(cliente2);
			fachada.cadastrarFuncionario(funcionario1);
			fachada.cadastrarFuncionario(funcionario2);
			fachada.cadastrarFantasia(fantasia1);
			fachada.cadastrarFantasia(fantasia2);
			fachada.cadastrarFantasia(fantasia3);

			System.out.println(fachada.procurarCliente("631.524.482-61"));
			System.out.println(fachada.procurarFuncionario("631.466.567-36"));
			System.out.println(fachada.procurarFantasia("Batman"));

			cliente1 = new Cliente("Beta Tester", "631.524.482-61", endereco2);
			fachada.atualizarCliente(cliente1);

			funcionario1 = new Funcionario("José Augusto da Silva", "021.163.167-16", endereco1, 5);
			fachada.atualizarFuncionario(funcionario1);

			fantasia1 = new Fantasia(chapeu1, parteCima1, parteBaixo2, 4);
			fachada.atualizarFantasia(fantasia1);

			System.out.println(fachada.gerarRelatorioCliente());
			System.out.println(fachada.gerarRelatorioFuncionario());
			System.out.println(fachada.gerarRelatorioFantasia());

			fachada.removerCliente("631.524.482-61");
			fachada.removerFuncionario("021.163.167-16");
			fachada.removerFantasia("Batman");

			fachada.cadastrarCliente(cliente1);
			fachada.cadastrarFuncionario(funcionario1);
			fachada.cadastrarFantasia(fantasia1);

			fachada.vender(fantasia2.getParteBaixo().getNomeConjunto(), 2);

			fachada.comprar(fantasia3.getParteBaixo().getNomeConjunto(), 4);

			System.out.println(fachada.gerarReceita());

			System.out.println(fachada.gerarRelatorioCliente());
			System.out.println(fachada.gerarRelatorioFuncionario());
			System.out.println(fachada.gerarRelatorioFantasia());

		} catch (PessoaCadastradaException e) {
			e.printStackTrace();
		} catch (PessoaInexistenteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TxtInvalidoException e) {
			e.printStackTrace();
		} catch (NomeInvalidoException e) {
			e.printStackTrace();
		} catch (CpfInvalidoException e) {
			e.printStackTrace();
		} catch (EnderecoInvalidoException e) {
			e.printStackTrace();
		} catch (TempoTrabalhoInvalidoException e) {
			e.printStackTrace();
		} catch (QuantidadeInvalidaException e) {
			e.printStackTrace();
		} catch (CorInvalidaException e) {
			e.printStackTrace();
		} catch (PrecoInvalidoException e) {
			e.printStackTrace();
		} catch (TipoRoupaInvalidoException e) {
			e.printStackTrace();
		} catch (NomeConjuntoInvalidoException e) {
			e.printStackTrace();
		} catch (NomeConjuntoDiferentesException e) {
			e.printStackTrace();
		} catch (ConjuntoCadastradoException e) {
			e.printStackTrace();
		} catch (ConjuntoInexistenteException e) {
			e.printStackTrace();
		}
	}
}