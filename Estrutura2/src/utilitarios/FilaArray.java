package utilitarios;

public class FilaArray<E> extends Fila<E>{
	private E[] fila;
	private int inicio, fim = - 1;
	
	private class ArrayIterator implements MyIterator<E> {

		private int indiceCorrente;

		@Override
		public E getFirst() {
			indiceCorrente = inicio;
			return fila[inicio];
		}

		@Override
		public E getNext() {
			if (indiceCorrente == fim) {
				return null;
			} else {
				if (indiceCorrente < fila.length) {
					indiceCorrente++;
					if (indiceCorrente != fila.length)
						return fila[indiceCorrente];
					else {
						indiceCorrente = 0;
						return fila[indiceCorrente];
					}
				} else
					return null;
			}

		}

		public void remove() {
			remova();
			indiceCorrente--;
		}

	}
	
	@SuppressWarnings("unchecked")
	public FilaArray(int tamFila) {
		fila = (E[]) new Object[tamFila];
		inicio = 0;
		fim = -1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
   public void clear() {
	   fila = (E[]) new Object[fila.length];
	   numItens = 0;
   }
	@Override
   public MyIterator<E> iterator() {
	   return new ArrayIterator();
   }
	@Override
   public E remova() {
	   E info;
	   info = fila[inicio];
	   if(inicio == fila.length - 1)
		   inicio = 0;
	   else {
		   inicio++;
	   }
	   numItens--;
	   return info;
   }
	@Override
   public boolean insira(E obj) {
		if(numItens == fila.length)
			return false;
		if(fim == fila.length - 1)
			fim = 0;
		else {
			fim++;
		}
		fila[fim] = obj;
		numItens++;
		return true;
   }

}
