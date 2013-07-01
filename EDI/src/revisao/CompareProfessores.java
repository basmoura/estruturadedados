package revisao;

import java.util.Comparator;

public class CompareProfessores implements Comparator<Professor> {

	public int compare(Professor obj1, Professor obj2) {
		if (obj1.getNumMat() < obj2.getNumMat())
			return -1;
		if (obj1.getNumMat() == obj2.getNumMat())
			return 0;
		return 1;
	}
}
