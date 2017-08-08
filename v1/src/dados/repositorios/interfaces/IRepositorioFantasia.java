package dados.repositorios.interfaces;

import java.io.IOException;

import dados.base.roupas.Fantasia;
import dados.base.excecoes.*;

public interface IRepositorioFantasia {
	void inserir(Fantasia fantasia) throws ConjuntoCadastradoException, IOException;

	Fantasia procurar(String nomeConjunto) throws ConjuntoInexistenteException;

	void remover(String nomeConjunto) throws ConjuntoInexistenteException, IOException;

	void atualizar(Fantasia fantasia) throws ConjuntoInexistenteException, IOException;

	String gerarRelatorio();
}