package dados.repositorios.vetor;

import java.io.Serializable;
import java.util.Vector;

import dados.base.roupas.Fantasia;
import dados.base.excecoes.ConjuntoCadastradoException;
import dados.base.excecoes.ConjuntoInexistenteException;
import dados.repositorios.interfaces.IRepositorioFantasia;
import dados.repositorios.iterator.Iterator;

public class RepositorioVetorFantasia implements IRepositorioFantasia, Serializable {
	private static final long serialVersionUID = -4331404362758219604L;
	private Vector<Fantasia> fantasias;

	public RepositorioVetorFantasia() {
		fantasias = new Vector<Fantasia>();
	}

	public void inserir(Fantasia fantasia) throws ConjuntoCadastradoException {
		Fantasia temp;

		for (int i = 0; i < fantasias.size(); i++) {
			temp = fantasias.get(i);

			if (temp.getParteBaixo().getNomeConjunto().equals(fantasia.getParteBaixo().getNomeConjunto())) {
				throw new ConjuntoCadastradoException();
			}
		}

		fantasias.add(fantasia);
	}

	public Fantasia procurar(String nomeConjunto) throws ConjuntoInexistenteException {
		Fantasia fantasia = null;
		boolean achou = false;

		for (int i = 0; i < fantasias.size(); i++) {
			fantasia = fantasias.get(i);

			if (fantasia.getParteBaixo().getNomeConjunto().equals(nomeConjunto)) {
				i = fantasias.size();
				achou = true;
			}
		}

		if (!achou) {
			throw new ConjuntoInexistenteException();
		}

		return fantasia;
	}

	public void remover(String nomeConjunto) throws ConjuntoInexistenteException {
		boolean achou = false;

		for (int i = 0; i < fantasias.size(); i++) {
			Fantasia fantasia = fantasias.get(i);

			if (fantasia.getParteBaixo().getNomeConjunto().equals(nomeConjunto)) {
				fantasias.remove(i);
				achou = true;
			}
		}

		if (!achou) {
			throw new ConjuntoInexistenteException();
		}
	}

	public void atualizar(Fantasia fantasia) throws ConjuntoInexistenteException {
		Fantasia temp = null;
		boolean achou = false;

		for (int i = 0; i < fantasias.size(); i++) {
			temp = fantasias.get(i);

			if (temp.getParteBaixo().getNomeConjunto().equals(fantasia.getParteBaixo().getNomeConjunto())) {
				fantasias.set(i, fantasia);
				achou = true;
			}
		}

		if (!achou) {
			throw new ConjuntoInexistenteException();
		}
	}

	public String gerarRelatorio() {
		Iterator<Fantasia> iterator = getIterator();
		String retorno = "";

		while (iterator.hasNext()) {
			retorno = retorno + iterator.next();
		}

		return retorno;
	}

	public Iterator<Fantasia> getIterator() {
		Iterator<Fantasia> iterator = new Iterator<Fantasia>(fantasias);
		return iterator;
	}
}