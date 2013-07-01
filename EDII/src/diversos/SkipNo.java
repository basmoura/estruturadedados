package diversos;

public class SkipNo<E extends Comparable<E>> {

	public E obj;
	public SkipNo<E>[] prox;

	@SuppressWarnings("unchecked")
	public SkipNo(int nivel, E obj) {
		prox = new SkipNo[nivel + 1];
		this.obj = obj;
	}

}
