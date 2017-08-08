package dados.repositorios.iterator;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Vector;

public class Iterator<O> implements IIterator, Serializable {
	private static final long serialVersionUID = 3466483976255149586L;
	private ArrayList<O> arrayList;
	private int indice = 0;

	/**
	 * Iterator para array
	 * 
	 * @param array
	 */
	public Iterator(O[] array) {
		indice = 0;
		arrayList = new ArrayList<O>();

		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				arrayList.add(array[i]);
			}
		}
	}

	/**
	 * Iterator para vetor
	 * 
	 * @param vetor
	 */
	public Iterator(Vector<O> vetor) {
		indice = 0;
		arrayList = new ArrayList<O>();

		for (int i = 0; i < vetor.size(); i++) {
			arrayList.add(vetor.get(i));
		}
	}

	/**
	 * Iterator para arquivo
	 * 
	 * @param arrayList
	 */
	public Iterator(ArrayList<O> arrayList) {
		indice = 0;
		this.arrayList = arrayList;
	}

	public boolean hasNext() {
		return (indice < arrayList.size());
	}

	public O next() {
		O objeto = arrayList.get(indice);
		indice++;
		return objeto;
	}

	public ArrayList<O> getArrayList() {
		return arrayList;
	}
}