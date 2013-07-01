package diversos;

import java.util.Comparator;
import java.util.Random;

public class Sort {
	private static final Random rnd = new Random();
	
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
	
	private static int particao(Object a[], Comparator c, int primeiro, int ultimo)
    {
		  if((ultimo - primeiro) <= 0)
			  return 0;
          int i = primeiro, j = ultimo;
          Object tmp;
          int indexPivo = primeiro + rnd.nextInt((ultimo - primeiro));
          Object pivo = a[indexPivo];

          while (i <= j) {
                while (c.compare(a[i], pivo) < 0)
                      i++;
                while (c.compare(a[j], pivo) > 0)
                      j--;
                if (i <= j) {
                      tmp = a[i];
                      a[i] = a[j];
                      a[j] = tmp;
                      i++;
                      j--;
                }
          }

          return i;
    }

    private static void qSort(Object a[], Comparator c, int primeiro, int ultimo) {
          int index = particao(a, c, primeiro, ultimo);
          if (primeiro < index - 1)
                qSort(a, c, primeiro, index - 1);
          if (index < ultimo)
                qSort(a, c, index, ultimo);
    }
	
	public static void quickSort(Object[] a, Comparator c) {
		qSort(a, c, 0, a.length - 1);
	}
}
