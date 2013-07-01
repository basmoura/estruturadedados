package lista09;

import diversos.*;

/**
 * Programa que usa a classe Pilha para converter um n�mero decimal para bin�rio
 * Programado por Raimundo Machado Costa em 15/09/2008
 */
public class DecParaBin {

	/**
	 * Converte decimal para bin�rio O m�todo retorna uma string contendo os
	 * d�gitos bin�rios resultantes da convers�o
	 */
	static String decParaBin(int n) {
		Pilha<Integer> p = new PilhaEnc<Integer>();
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
			int n = Keyboard.readInt("Entrar com um n�mero inteiro: ");
			System.out.println("Valor em bin�rio: " + decParaBin(n));
			resp = Keyboard.readChar("Outro n�mero(s/n): ");
		} while (resp == 's');

		System.out.println("\nFim do programa");
	}

}
