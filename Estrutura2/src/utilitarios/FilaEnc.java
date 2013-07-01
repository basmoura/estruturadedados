package utilitarios;

public class FilaEnc<E> extends Fila<E>{

	private ListaSimpEnc<E> fila;
	
	public FilaEnc() {
		this.fila = new ListaSimpEnc<E>();
	}

	@Override
   public void clear() {
	   this.fila.clear();  
   }

	@Override
   public MyIterator<E> iterator() {
	   return this.fila.iterator();
   }

	@Override
   public E remova() {
	   return this.fila.removeFromBegin();
   }

	@Override
   public boolean insira(E obj) {
		try {
			this.fila.insertAtEnd(obj);
			return true;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
   }
	
	@Override
	public int size() {
		return this.fila.size();
	}
}
