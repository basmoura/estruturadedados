package lista03;

import java.util.Comparator;

public class ComparaSaldo implements Comparator<Conta>{
	@Override
	public int compare(Conta o1, Conta o2) {
		if(o1.getSaldoConta() > o2.getSaldoConta())
			return -1;
		if(o1.getSaldoConta() < o2.getSaldoConta())
			return 1;
		return 0;
	}
}
