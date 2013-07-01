package diversos;

import diversos.*;

//Versao usando a classe ListaEncNaoOrd
public class HashEnc<E extends Comparable<E>> extends ColecaoComparavel<E> {

	private class HashEncIterator implements MyIterator<E> {
		int indCorrente = 0;
		MyIterator<E> it;
		E obj;
		E objAux;

		@Override
		public E getFirst() {
			for (int i = indCorrente; i < tabela.length; i++) {
				if (!tabela[i].isEmpty()) {
					indCorrente = i;
					it = tabela[i].iterator();
					obj = it.getFirst();
					objAux = obj;
					obj = it.getNext();
					return objAux;
				}
			}
			return null;
		}

		@Override
		public E getNext() {
			if (obj == null) {
				indCorrente++;
				return getFirst();
			} else {
				objAux = obj;
				obj = it.getNext();
				return objAux;
			}
		}

		@Override
		public void remove() {
			tabela[indCorrente].remove(objAux);
			numItens--;
		}

	}

	private ListaEncNaoOrd<E>[] tabela;

	protected int funcHash(Object obj) {
		return Math.abs(obj.hashCode()) % tabela.length;
	}

	public HashEnc(int tamTabela) {
		if (!isPrime(tamTabela))
			tamTabela = nextPrime(tamTabela);
		tabela = new ListaEncNaoOrd[tamTabela];
		for (int i = 0; i < tabela.length; i++) {
			tabela[i] = new ListaEncNaoOrd<E>();
		}
	}

	public static boolean isPrime(int x) {
		if (x % 2 == 0)
			return false;
		for (int i = 3; i < x; i += 2) {
			if (x % i == 0)
				return false;
		}
		return true;
	}

	public static int nextPrime(int x) {
		if (x < 2)
			return 2;
		if (x % 2 == 0)
			x++;
		else
			x = x + 2;
		while (!isPrime(x))
			x = x + 2;
		return x;
	}

	private void redimensione() {
		int novoTamanho = tabela.length * 2;
		if (!isPrime(novoTamanho))
			novoTamanho = nextPrime(novoTamanho);
		ListaEncNaoOrd<E>[] novaTabela = tabela;
		int numItensAux = numItens;
		tabela = new ListaEncNaoOrd[novoTamanho];
		for (int i = 0; i < tabela.length; i++) {
			tabela[i] = new ListaEncNaoOrd<E>();
		}
		for (int i = 0; i < novaTabela.length; i++) {
			if ((!novaTabela[i].isEmpty())) {
				MyIterator<E> it = novaTabela[i].iterator();
				E busca = it.getFirst();
				while (busca != null) {
					add(busca);
					busca = it.getNext();
				}
			}
		}
		numItens = numItensAux;
	}

	@Override
	public boolean add(E obj) {
		if (numItens >= 1.75 * tabela.length)
			redimensione();
		int k = funcHash(obj);
		if (!tabela[k].isEmpty()) {
			E objeto = tabela[k].retrieve(obj);
			if (objeto == null) {
				tabela[k].add(obj);
				numItens++;
				return true;
			} else {
				return false;
			}
		} else {
			tabela[k].add(obj);
			numItens++;
			return true;
		}
	}

	@Override
	public E retrieve(E obj) {
		int k = funcHash(obj);
		if (!tabela[k].isEmpty()) {
			return tabela[k].retrieve(obj);
		} else {
			return null;
		}
	}

	@Override
	public boolean remove(E obj) {
		int k = funcHash(obj);
		if (!tabela[k].isEmpty()) {
			E objeto = tabela[k].retrieve(obj);
			if (objeto == null) {
				return false;
			} else {
				tabela[k].remove(obj);
				numItens--;
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i < tabela.length; i++) {
			tabela[i] = new ListaEncNaoOrd<E>();
		}
		numItens = 0;
	}

	@Override
	public MyIterator<E> iterator() {
		return new HashEncIterator();
	}

}
