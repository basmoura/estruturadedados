package diversos;

import diversos.*;

public class FilaEnc<E> extends Fila<E> {

	private ListaSimpEnc<E> fila = new ListaSimpEnc<E>();

	@Override
	public void clear() {
		fila.clear();
		numItens = 0;
	}

	@Override
	public MyIterator<E> iterator() {
		return fila.iterator();
	}

	@Override
	public E remova() {
		if (fila.isEmpty())
			return null;
		E retorno = fila.removeFromBegin();
		numItens--;
		return retorno;
	}

	@Override
	public boolean insira(E obj) {
		fila.insertAtEnd(obj);
		numItens++;
		return true;
	}
}
