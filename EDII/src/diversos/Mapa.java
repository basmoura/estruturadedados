package diversos;

public interface Mapa<K extends Comparable<K>, V> {
	// Armazena no mapa o par (chave, valor). Se a chave j� existir no mapa
	// o valor associado � chave dever� ser substitu�do pelo novo valor.
	// O m�todo o valor antigo associado � chave.
	V put(K chave, V valor);

	// Informa se o mapa cont�m ou n�o a chave especificada.
	boolean contains(K chave);

	// Remove do mapa a entrada contendo a chave especificada, retornado o
	// valor associado a ela.
	V remove(K chave);

	// Retorna o valor associado � chave especificada.
	V getValor(K chave);

	// Retornar o par (chave, valor), para a chave especificada
	ChaveValor<K, V> getChaveValor(K chave);

	// Informa o n�mero de entradas armazenadas no mapa.
	int size();

	// Informa se o mapa est� vazio ou n�o.
	boolean isEmpty();

	// Esvazia o mapa.
	void clear();

	// Retorna um iterator do tipo MyIteratorMapa.
	MyIteratorMapa<K, V> iterator();

	// Retorna um array contendo todas as entradas do mapa. O tipo dos
	// elementos do array dever� ser ChaveValor.
	Object[] toArray();

}
