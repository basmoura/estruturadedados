package lista04;

import java.util.Comparator;

public class ComparaNomeCliente implements Comparator<Conta> {
	@Override
	public int compare(Conta o1, Conta o2) {
		return o1.getNomeCliente().compareToIgnoreCase(o2.getNomeCliente());
	}
}
