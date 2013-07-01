package lista06;

import java.util.Comparator;

public class ComparaNomePessoa implements Comparator<Pessoa> {
	@Override
	public int compare(Pessoa o1, Pessoa o2) {
		return o1.getNome().compareToIgnoreCase(o2.getNome());
	}
}