package Biblioteca;

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
        int k, iMenor;

        for (int i = 0; i < a.length - 2; i++) {
            iMenor = i;

            for (k = i + 1; k <= a.length - 1; k++) {
                if (c.compare(a[k], a[iMenor]) < 0) {
                    iMenor = k;
                }
            }

            temp = a[i];
            a[i] = a[iMenor];
            a[iMenor] = temp;
        }
    }

    public static void insercao(Object[] a, Comparator c) {
        int k;
        Object temp;

        for (int i = 0; i <= a.length - 1; i++) {
            k = i;
            temp = a[i];

            while ((k > 0) && c.compare(temp, a[k - 1]) < 0) {
                a[k] = a[k - 1];
                k--;
            }
            a[k] = temp;
        }
    }

    public static void quickSort(Object[] a, Comparator c) {
        qSort(a, c, 0, a.length - 1);
    }

    private static void qSort(Object[] a, Comparator c, int primeiro, int ultimo) {
        int indicePivo;
        if (primeiro < ultimo) {
            indicePivo = particao(a, c, primeiro, ultimo);
            qSort(a, c, primeiro, ultimo - 1);
            qSort(a, c, indicePivo + 1, ultimo);
        }
    }

    private static int particao(Object[] a, Comparator c, int primeiro, int ultimo) {
        int i, j, indicePivo;
        Object temp, pivo, tempPos;
        
        int pos = primeiro + (int) (((ultimo - primeiro) + 1) * Math.random());

        tempPos = a[primeiro];
        a[primeiro] = a[pos];
        a[pos] = tempPos;

        pivo = a[primeiro];
        i = primeiro;
        j = ultimo;

        do {
            while (c.compare((Object) a[i], pivo) <= 0 && (i < ultimo)) {
                i++;
            }
            while ((c.compare((Object) a[j], pivo) >= 1) && (j > primeiro)) {
                j--;
            }
            if (i < j) {
                temp = (Object) a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        } while (i < j);

        indicePivo = j;
        temp = (Object) a[primeiro];
        a[primeiro] = a[indicePivo];
        a[indicePivo] = temp;
        return indicePivo;
    }
}