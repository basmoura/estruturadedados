package diversos;

import diversos.*;

public class FilaArray<E> extends Fila<E> {
	private E[] fila;
	private int inicio, fim;

	private class FilaArrayIterator implements MyIterator<E> {
		private int indiceCorrente;

		@Override
		public E getFirst() {
			if (numItens == 0)
				return null;
			indiceCorrente = inicio;
			return fila[indiceCorrente];
		}

		@Override
		public E getNext() {
			if (indiceCorrente < fila.length - 1) {
				indiceCorrente++;
			} else if (indiceCorrente == fila.length - 1){
				indiceCorrente = 0;
			}
			if ((indiceCorrente > fim) || (fim == fila.length)) {
				return null;
			}
			return fila[indiceCorrente];
		}

		@Override
		public void remove() {
			if (indiceCorrente == inicio) {
				FilaArray.this.remova();
			} else if (indiceCorrente == fila.length - 1) {
				fim = indiceCorrente;
				fila[indiceCorrente] = null;
			} else if (indiceCorrente < fim) {
				for (int i = indiceCorrente; i < fim; i++) {
					fila[i] = fila[i + 1];
				}
				fim--;
			} else {
				for (int i = indiceCorrente; i > inicio; i--) {
					fila[i] = fila[i - 1];
				}
				inicio++;
			}
			numItens--;
		}
	}

	public FilaArray(int tamFila) {
		fila = (E[]) new Object[tamFila];
	}

	@Override
	public void clear() {
		inicio = fim = 0;
		numItens = 0;
	}

	@Override
	public MyIterator<E> iterator() {
		return new FilaArrayIterator();
	}

	@Override
	public E remova() {
		E retorno = fila[inicio];
		inicio = inicio + 1;
		if (inicio == fila.length) {
			inicio = 0;
		}
		numItens--;
		return retorno;
	}

	@Override
	public boolean insira(E obj) {
		fila[fim] = obj;
		fim = fim + 1;
		if (fim == fila.length) {
			fim = 0;
		}
		numItens++;
		return true;
	}

}
