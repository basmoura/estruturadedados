package utilitarios;

@SuppressWarnings("serial")
public class HashMapa<K extends Comparable<K>, V> extends MapaAbstrata<K,V> {

	private HashDup<ChaveValor<K, V>> mapa;
	
	private class MapaIterator implements MyIteratorMapa<K, V> {
		MyIterator<ChaveValor<K, V>> it = mapa.iterator();
		
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
	
	public HashMapa(int tamTabela) {
		mapa = new HashDup<ChaveValor<K,V>>(tamTabela);
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
	public boolean contains(K chave) {
		ChaveValor<K, V> entrada = new ChaveValor<K, V>(chave, null);
		return mapa.contains(entrada);
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
	public ChaveValor<K, V> getChaveValor(K chave) {
		ChaveValor<K, V> entrada = new ChaveValor<K, V>(chave, null);
		return mapa.retrieve(entrada);
	}

	@Override
	public void clear() {
		mapa.clear();
		numItens = 0;
	}

	@Override
	public MyIteratorMapa<K, V> iterator() {
		return new MapaIterator();
	}

}
