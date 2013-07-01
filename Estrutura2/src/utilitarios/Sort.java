package utilitarios;

import java.util.Comparator;
import java.util.Random;

public class Sort {

	public static void bolha(Object[] a, Comparator c) {
		int fim;
		boolean troquei;
		Object temp;

		fim = a.length - 1;
		do {
			troquei = false;
			for (int i = 0; i <= fim - 1; i++) {
				if (c.compare(a[i], a[i + 1]) > 0) {
					temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;
					troquei = true;
				}
			}
			fim--;
		} while (troquei);
	}

	public static void selecao(Object[] a, Comparator c) {
		Object temp;
		for (int i = 0; i <= a.length - 2; i++) {
			for (int j = i + 1; j <= a.length - 1; j++) {
				if (c.compare(a[j], a[i]) > 0) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
	}

	public static void insercao(Object[] a, Comparator c) {
		Object temp;
		int j;
		for (int i = 0; i <= a.length - 1; i++) {
			temp = a[i];
			j = i;
			while (j > 0 && c.compare(temp, a[j - 1]) > 0) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = temp;
		}
	}
	
	static final Random rnd = new Random();
	
	public static void quickSort(Object[] a, Comparator c) {  
        qSort(a, 0, a.length - 1, c);  
    }
	
	private static void qSort(Object[] a, int primeiro, int ultimo, Comparator c) {
		if (ultimo > primeiro) {
			int indicePivo = particao(a, primeiro, ultimo, c);
			qSort(a, primeiro, indicePivo - 1, c);
			qSort(a, indicePivo + 1, ultimo, c);
		}
	}
	
	private static int particao(Object[] a, int primeiro, int ultimo,
			Comparator c) {
		int i, j, indicePivo;
		Object pivo;	
		indicePivo = primeiro + rnd.nextInt(ultimo - primeiro + 1);
		pivo = a[indicePivo];
		
		trocar(a, primeiro, indicePivo);
		
		i = primeiro;
		j = ultimo;
		do {
			while ((c.compare(a[i], pivo) <= 0) && (i < ultimo))
				i++;
			while ((c.compare(a[j], pivo) > 0) && (j > primeiro))
				j--;
			if (i < j) {
				trocar(a, i, j);
			}
		} while (i < j);
		
		indicePivo = j;
		trocar(a, primeiro, indicePivo);
		return indicePivo;
	}
	
	private static void trocar(Object[] a, int i, int j ) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
    }
	
}
