
import Biblioteca.Sort;
import java.util.Comparator;
import java.util.Random;

public class DemoTesteSort {

    static class CompareInteger implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        Integer[] dado = new Integer[10];
        for (int i = 0; i < dado.length; i++) {
            dado[i] = r.nextInt(1000);
        }
        System.out.println("Dados desordenados");
        for (int i = 0; i < dado.length; i++) {
            System.out.print(dado[i] + " ");
        }
        System.out.println();
        Sort.quickSort(dado, new CompareInteger());
        System.out.println("Dados ordenados");
        for (int i = 0; i < dado.length; i++) {
            System.out.print(dado[i] + " ");
        }
    }
}
