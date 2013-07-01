package Lista09;

import Biblioteca.Keyboard;
import Biblioteca.Pilha;
import Biblioteca.PilhaEnc;

public class ValidaExpressao {

    static boolean casa(char c1, char c2) {
        switch (c1) {
            case '{':
                return (c2 == '}');
            case '[':
                return (c2 == ']');
            case '(':
                return (c2 == ')');
        }
        return false;
    }

    static boolean expressaoOk(String expressao) {
        char c1, c2;
        Pilha<Character> p = new PilhaEnc<>();

        for (int i = 0; i < expressao.length(); i++) {
            c2 = expressao.charAt(i);
            if ("{[(".indexOf(c2) >= 0) {
                p.empilhe(c2);
            } else if ("}])".indexOf(c2) >= 0) {
                if (p.isEmpty()) {
                    return false;
                }

                c1 = p.desempilhe();
                if (!casa(c1, c2)) {
                    return false;
                }
            }
        }
        if (!p.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        String exp;
        char resp;

        do {
            exp = Keyboard.readString("Entrar com uma expressao: ");
            if (expressaoOk(exp)) {
                System.out.println("Expressao correta");
            } else {
                System.out.println("Expressao incorreta");
            }
            resp = Keyboard.readChar("Outra expressao(s/n)? ");
        } while (resp == 's');

        System.out.println("\nFim do programa");
    }
}
