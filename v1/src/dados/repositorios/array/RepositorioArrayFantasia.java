package dados.repositorios.array;

import java.io.Serializable;

import dados.base.roupas.Fantasia;
import dados.base.excecoes.ConjuntoCadastradoException;
import dados.base.excecoes.ConjuntoInexistenteException;
import dados.repositorios.interfaces.IRepositorioFantasia;
import dados.repositorios.iterator.Iterator;

public class RepositorioArrayFantasia implements IRepositorioFantasia, Serializable {
	private static final long serialVersionUID = -2074162496276096784L;
	private Fantasia[] fantasias;

	public RepositorioArrayFantasia() {
		fantasias = new Fantasia[1];
	}

	public void inserir(Fantasia fantasia) throws ConjuntoCadastradoException {
		int indice = getIndice();

		for (int i = 0; i <= indice; i++) {
			if (fantasias[i] != null && fantasia.getParteBaixo().getNomeConjunto()
					.equals(fantasias[i].getParteBaixo().getNomeConjunto())) {
				throw new ConjuntoCadastradoException();
			}
		}

		if (indice != fantasias.length - 1) {
			fantasias[indice + 1] = fantasia;
		} else {
			Fantasia[] temp = new Fantasia[fantasias.length + 1];

			for (int i = 0; i < fantasias.length; i++) {
				temp[i] = fantasias[i];
			}

			fantasias = temp;
			fantasias[fantasias.length - 1] = fantasia;
		}
	}

	public Fantasia procurar(String nomeConjunto) throws ConjuntoInexistenteException {
		Fantasia fantasia = null;
		int indice = getIndice();

		for (int i = 0; i <= indice; i++) {
			if (fantasias[i] != null && fantasias[i].getParteBaixo().getNomeConjunto().equals(nomeConjunto)) {
				fantasia = fantasias[i];
			}
		}

		if (fantasia == null) {
			throw new ConjuntoInexistenteException();
		}

		return fantasia;
	}

	public void remover(String nomeConjunto) throws ConjuntoInexistenteException {
		int indice = getIndice();
		boolean existe = false;

		for (int i = 0; i <= indice; i++) {
			if (fantasias[i] != null && fantasias[i].getParteBaixo().getNomeConjunto().equals(nomeConjunto)) {
				fantasias[i] = null;
				existe = true;
			}
		}

		if (!existe) {
			throw new ConjuntoInexistenteException();
		}

		Fantasia[] temp = new Fantasia[fantasias.length - 1];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < fantasias.length; j++) {
				if (temp[i] == null && fantasias[j] != null) {
					temp[i] = fantasias[j];
					fantasias[j] = null;
					j = fantasias.length;
				}
			}
		}

		fantasias = temp;
	}

	public void atualizar(Fantasia fantasia) throws ConjuntoInexistenteException {
		int indice = getIndice();
		boolean existe = false;

		for (int i = 0; i <= indice; i++) {
			if (fantasias[i] != null && fantasias[i].getParteBaixo().getNomeConjunto()
					.equals(fantasia.getParteBaixo().getNomeConjunto())) {
				fantasias[i] = fantasia;
				existe = true;
				i = indice;
			}
		}

		if (!existe) {
			throw new ConjuntoInexistenteException();
		}
	}

	public int getIndice() {
		int indice = -1;

		for (int i = 0; i < fantasias.length; i++) {
			if (fantasias[i] != null) {
				indice = i;
			}
		}

		return indice;
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