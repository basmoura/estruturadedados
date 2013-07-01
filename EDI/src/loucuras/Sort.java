package loucuras;

import java.util.Comparator;

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
		int menor;
		Object temp;
		for (int i = 0; i < a.length - 1; i++) {
			menor = i;
			for (int j = i + 1; j < a.length; j++) {
				if (c.compare(a[menor], a[j]) > 0) {
					menor = j;
				}
			}
			temp = a[i];
			a[i] = a[menor];
			a[menor] = temp;
		}
	}

	public static void insercao(Object[] a, Comparator c) {
		Object temp;
		int k;
		for (int i = 0; i < a.length; i++) {
			k = i;
			temp = a[i];

			while ((k > 0) && ((c.compare(a[k - 1], temp)) > 0)) {
				a[k] = a[k - 1];
				k--;
			}
			a[k] = temp;
		}
	}

	private static void particao(Object[] a, Comparator c, int primeiro,
			int ultimo) {
		int i = primeiro;
		int j = ultimo;
		int x = (int) (Math.random() * (ultimo - primeiro + 1)) + primeiro;
		Object pivo = a[x];
		Object temp;
		do {
			while ((c.compare(a[i], pivo) <= 0) && (i < ultimo))
				i++;
			while ((c.compare(a[j], pivo) > 0) && (j > primeiro))
				j--;
			if (i < j) {
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		} while (i < j);
		if (primeiro < j)
			particao(a, c, primeiro, j);
		if (i < ultimo)
			particao(a, c, i, ultimo);
	}

	public static void quickSort(Object[] a, Comparator c) {
		particao(a, c, 0, a.length - 1);
	}
}


