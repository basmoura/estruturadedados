package utilitarios;

public class ExpressaoAritmetica {
	static boolean erro;
	static int inic;
	
	public static double calcule(String expInfixa) {
		String expPosFixa;
		double resultado = 0;
		expPosFixa = infixaToPosfixa(expInfixa);
		if (expPosFixa == null)
			System.out.println("Express�o inv�lida");
		else {
			resultado = avaliePosFixa(expPosFixa);
			if (erro)
			System.out.println("Erro ao calcular");
		}
		return resultado;
	}
	
	// Converte infixa para pos-fixa. Se ocorrer algum erro,
	// o m�todo retorna null
	static String infixaToPosfixa(String expInfixa) {
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
			if (op1 == op2)
				if (op1 == '^')
					return false;
				else
					return true;

			return (hierarquia(op1) >= hierarquia(op2));
		}

		

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
		// M�todo que extrai o n�mero real que se encontra na express�o
		// come�ando a partir de inic
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

	static double avaliePosFixa(String expPosfixa) {
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
				valor = calcule(ch, opnd1, opnd2);
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
}
