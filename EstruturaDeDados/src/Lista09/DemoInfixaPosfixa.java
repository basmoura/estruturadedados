package Lista09;

import Biblioteca.Keyboard;
import Biblioteca.Pilha;
import Biblioteca.PilhaEnc;

public class DemoInfixaPosfixa {

    static int hierarquia(char operador) {
        switch (operador) {
            case '(':
                return 1;
            case '+':
                return 2;
            case '-':
                return 2;
            case '*':
                return 3;
            case '/':
                return 3;
            case '^':
                return 4;
            default:
                return 0;
        }
    }

    static boolean precede(char op1, char op2) {
        if (op1 == op2) {
            if (op1 == '^') {
                return false;
            } else {
                return true;
            }
        }

        return (hierarquia(op1) >= hierarquia(op2));
    }

    static String infixaToPosfixa(String expInfixa) {
        char op, opAnt;
        String expPosFixa;
        Pilha<Character> p = new PilhaEnc<>();
        expPosFixa = "";
        opAnt = ' ';

        for (int i = 0; i < expInfixa.length(); i++) {
            op = expInfixa.charAt(i);
            if (op == ' ') {
                continue;
            }
            if ("()^+-*/".indexOf(op) == -1) {
                expPosFixa = expPosFixa + op;
            } else {
                switch (op) {
                    case '(':
                        // Processa abre par�nteses
                        if (opAnt == ')') {
                            return null;
                        }
                        p.empilhe('(');
                        break;
                    case ')':
                        // Processa fecha par�nteses
                        if (p.isEmpty()) {
                            return null;
                        } else {
                            char opTopo = p.desempilhe();
                            while (opTopo != '(') {
                                expPosFixa = expPosFixa + " " + opTopo;
                                if (p.isEmpty()) {
                                    return null;
                                }
                                opTopo = p.desempilhe();
                            }
                        }
                        break;
                    default:
                        // Processa operador
                        if ("+-*/^".indexOf(opAnt) >= 0) {
                            return null;
                        }
                        char opTopo;
                        if (!p.isEmpty()) {
                            opTopo = p.getTopo();
                            while (!p.isEmpty() && precede(opTopo, op)) {
                                opTopo = p.desempilhe();
                                expPosFixa = expPosFixa + opTopo;
                                if (!p.isEmpty()) {
                                    opTopo = p.getTopo();
                                }
                            }
                        }
                        p.empilhe(op);
                        expPosFixa = expPosFixa + " ";
                }
            }
            opAnt = op;
        } // for

        while (!p.isEmpty()) {
            op = p.desempilhe();
            if (op == '(') {
                return null;
            }
            expPosFixa = expPosFixa + " " + op;
        }
        return expPosFixa;
    }

    public static void main(String[] args) {
        String expInfixa, expPosFixa;
        char resp;
        do {
            expInfixa = Keyboard.readString("Entrar com a expressão infixa: ");
            expPosFixa = infixaToPosfixa(expInfixa);
            if (expPosFixa == null) {
                System.out.println("Expressão inválida");
            } else {
                System.out.println("Expressão posfixa\n" + expPosFixa);
            }
            resp = Keyboard.readChar("Outra expressão(s/n): ");
        } while (resp == 's');
    }
}
