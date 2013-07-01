package diversos;

import java.io.Serializable;

public interface MyIterator<E> extends Serializable {
	// Retorna o primeiro elemento da cole��o.
	// Se n�o existir o primeiro elemento, retorna null
	E getFirst();

	// Retorna o pr�ximo elemento da cole��o.
	// Se n�o existir o pr�ximo elemento, retorna null
	E getNext();

	// Remove da cole��o o �ltimo elemento retornado
	void remove();
}
