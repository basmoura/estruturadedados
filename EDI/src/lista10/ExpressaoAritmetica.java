package lista10;

import diversos.*;

public class ExpressaoAritmetica {

	static boolean erro;
	static int inic;
	
	public static double calcule(String expInfixa) {
		String expPosfixa = infixaToPosfixa(expInfixa);
		return avaliePosFixa(expPosfixa);
	}

	private static double calculeOpnd(char operador, double opnd1, double opnd2) {
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

	private static double obtemValor(String expressao) {
		// Método que extrai o número real que se encontra na expressão
		// começando a partir de inic
		String valorString = "";
		char ch;
		while (inic < expressao.length()) {
			ch = expressao.charAt(inic);
			if ("0123456789.".indexOf(ch) >= 0) {
				valorString = valorString + ch;
				inic++;
			}
			else
				break;
		}
		return Double.valueOf(valorString);
	}

	private static double avaliePosFixa(String expPosfixa) {
		Pilha<Double> p = new PilhaEnc<Double>();
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
				valor = calculeOpnd(ch, opnd1, opnd2);
				p.empilhe(valor);
				inic++;
			}
			else {
				if ("0123456789".indexOf(ch) >= 0) {
					valor = obtemValor(expPosfixa);
					p.empilhe(valor);
				}
				else {
					erro = true;
					return 0;
				}
			}
		} // while

		valor = p.desempilhe();

		if (!p.isEmpty()) {
			erro = true;
			return 0;
		}
		else {
			erro = false;
			return valor;
		}
	}
	
	private static String infixaToPosfixa(String expInfixa) {
		char op, opAnt;
		String expPosFixa;
		Pilha<Character> p = new PilhaEnc<Character>();
		expPosFixa = "";
		opAnt = ' ';

		for (int i = 0; i < expInfixa.length(); i++) {
			op = expInfixa.charAt(i);
			if (op == ' ')
				continue;
			if ("()^+-*/".indexOf(op) == -1)
				expPosFixa = expPosFixa + op;
			else {
				switch (op) {
				case '(':
					// Processa abre parênteses
					if (opAnt == ')') {
						return null;
					}
					p.empilhe('(');
					break;
				case ')':
					// Processa fecha parênteses
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
					if ("+-*/^".indexOf(opAnt) >= 0)
						return null;
					char opTopo;
					if (!p.isEmpty()) {
						opTopo = p.getTopo();
						while (!p.isEmpty() && precede(opTopo, op)) {
							opTopo = p.desempilhe();
							expPosFixa = expPosFixa + opTopo;
							if (!p.isEmpty())
								opTopo = p.getTopo();
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

	private static boolean precede(char op1, char op2) {
		if (op1 == op2)
			if (op1 == '^')
				return false;
			else
				return true;

		return (hierarquia(op1) >= hierarquia(op2));
	}

	private static int hierarquia(char operador) {
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
}
