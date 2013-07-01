package lista09;
import diversos.*;

public abstract class Pilha<E> extends ColecaoNaoComparavel<E>{
	public abstract void empilhe(E obj);
	public abstract E desempilhe();
	public abstract E getTopo();
}
