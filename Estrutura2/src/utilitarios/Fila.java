package utilitarios;

public abstract class Fila<E> extends ColecaoNaoComparavel<E>{
	public abstract E remova();
	public abstract boolean insira(E obj);
}
