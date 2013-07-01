package Lista09;

import Biblioteca.Keyboard;
import Biblioteca.Pilha;
import Biblioteca.PilhaArray;
import Biblioteca.PilhaEnc;

public class DecParaBin {

    public static String decParaBin(int n) {
        Pilha<Integer> p = new PilhaArray<>(10);
        String resultado = "";

        while (n != 0) {
            p.empilhe(n % 2);
            n = n / 2;
        }

        while (!p.isEmpty()) {
            int d = p.desempilhe();
            resultado = resultado + d;
        }
        return resultado;
    }

    public static void main(String[] args) {
        char resp;
        do {
            int n = Keyboard.readInt("Entrar com um número inteiro: ");
            System.out.println("Valor em binário: " + decParaBin(n));
            resp = Keyboard.readChar("Outro número(s/n): ");
        } while (resp == 's');

        System.out.println("\nFim do programa");
    }
}
