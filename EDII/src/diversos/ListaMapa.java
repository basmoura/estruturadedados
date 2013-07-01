package diversos;

public class ListaMapa<K extends Comparable<K>, V> extends MapaAbstrata<K, V> {

	private ListaEncOrd<ChaveValor<K, V>> mapa = new ListaEncOrd<ChaveValor<K, V>>();

	private class MapaIterator implements MyIteratorMapa<K, V> {

		private MyIterator<ChaveValor<K, V>> it = mapa.iterator();

		@Override
		public ChaveValor<K, V> getFirst() {
			return it.getFirst();
		}

		@Override
		public ChaveValor<K, V> getNext() {
			return it.getNext();
		}

		@Override
		public void remove() {
			it.remove();
			numItens--;
		}

	}

	@Override
	public V put(K chave, V valor) {

		ChaveValor<K, V> obj = new ChaveValor<K, V>(chave, valor);
		ChaveValor<K, V> objAnt = mapa.retrieve(obj);
		if (objAnt != null) {
			V valorAnt = objAnt.getValor();
			objAnt.setValor(valor);
			return valorAnt;
		}
		mapa.add(obj);
		numItens++;
		return valor;
	}

	@Override
	public V remove(K chave) {
		ChaveValor<K, V> chaveValor = new ChaveValor<K, V>(chave, null);
		chaveValor = mapa.retrieve(chaveValor);
		if (chaveValor == null)
			return null;
		mapa.remove(chaveValor);
		numItens--;
		return chaveValor.getValor();
	}

	@Override
	public V getValor(K chave) {
		ChaveValor<K, V> chaveValor = new ChaveValor<K, V>(chave, null);
		chaveValor = mapa.retrieve(chaveValor);
		if (chaveValor == null)
			return null;
		return chaveValor.getValor();
	}

	@Override
	public void clear() {
		mapa.clear();
		numItens = 0;
	}

	@Override
	public boolean contains(K chave) {
		ChaveValor<K, V> entrada = new ChaveValor<K, V>(chave, null);
		return mapa.contains(entrada);
	}

	@Override
	public ChaveValor<K, V> getChaveValor(K chave) {
		ChaveValor<K, V> entrada = new ChaveValor<K, V>(chave, null);
		return mapa.retrieve(entrada);
	}

	@Override
	public MyIteratorMapa<K, V> iterator() {
		return new MapaIterator();
	}

}
