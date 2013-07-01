package Lista09;

import Biblioteca.Keyboard;
import Biblioteca.Pilha;
import Biblioteca.PilhaArray;
import Biblioteca.PilhaEnc;



/**
 * Programa que avalia uma expressão na notação pós-fixa. Os operandos só
 * poderão ser números inteiros ou reais e entre dois operandos deverá existir
 * pelo menos um espaço em branco. Os operadores aceitos são: +, -, *, / e
 * ^(exponenciação).
 *
 * @author Raimundo Machado Costa (15/09/2008)
 *
 */
public class DemoAvaliaExpressao {

    static boolean erro;
    static int inic;

    static double calcule(char operador, double opnd1, double opnd2) {
        switch (operador) {
            case '+':
                return opnd1 + opnd2;
            case '-':
                return opnd1 - opnd2;
            case '*':
                return opnd1 * opnd2;
            case '/':
                return opnd1 / opnd2;
            case '^':
                return Math.pow(opnd1, opnd2);
        }
        return 0;
    }

    static double obtemValor(String expressao) {
        // Método que extrai o número real que se encontra na expressão
        // começando a partir de inic
        String valorString = "";
        char ch;
        while (inic < expressao.length()) {
            ch = expressao.charAt(inic);
            if ("0123456789.".indexOf(ch) >= 0) {
                valorString = valorString + ch;
                inic++;
            } else {
                break;
            }
        }
        return Double.valueOf(valorString);
    }

    static double avaliePosFixa(String expPosfixa) {
        Pilha<Double> p = new PilhaArray<>(10);
        char ch;
        double opnd1, opnd2, valor;

        inic = 0;
        while (inic < expPosfixa.length()) {
            ch = expPosfixa.charAt(inic);
            if (ch == ' ') {
                inic++;
                continue;
            }
            if ("^*/+-".indexOf(ch) >= 0) {
                if (p.isEmpty()) {
                    erro = true;
                    return 0;
                }
                opnd2 = p.desempilhe();
                if (p.isEmpty()) {
                    erro = true;
                    return 0;
                }
                opnd1 = p.desempilhe();
                valor = calcule(ch, opnd1, opnd2);
                p.empilhe(valor);
                inic++;
            } else {
                if ("0123456789".indexOf(ch) >= 0) {
                    valor = obtemValor(expPosfixa);
                    p.empilhe(valor);
                } else {
                    erro = true;
                    return 0;
                }
            }
        } // while

        valor = p.desempilhe();

        if (!p.isEmpty()) {
            erro = true;
            return 0;
        } else {
            erro = false;
            return valor;
        }
    }

    public static void main(String[] args) {
        String expPosfixa;
        char resp;
        double resultado;

        do {
            expPosfixa = Keyboard.readString("Entrar com a expressão posfixa: ");
            resultado = avaliePosFixa(expPosfixa);
            if (erro) {
                System.out.println("Expressão inválida");
            } else {
                System.out.println("Resultado = " + resultado);
            }
            resp = Keyboard.readChar("Outra expressão(s/n): ");
        } while (resp == 's');

    }
}
