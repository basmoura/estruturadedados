package lista03_ArvoreBin;

import utilitarios.*;

public class DemoArvoreBusca {

	static ArvoreBin<Integer> arvore = new ArvoreBin<>();
	
	private static boolean insira(int valor) {
		NoArvoreBin<Integer> p = arvore.getRaiz();
		NoArvoreBin<Integer> pai = null;
		int direcao = 1;
		if (p != null) {
			while(true) {
				if (valor == p.getObj()) {
					return false;
				}
				if (valor > p.getObj()) {
					if (p.getDir() == null) {
						pai = p;
						direcao = 1;
						break;
					}
					p = p.getDir();
				}
				else {
					if (p.getEsq() == null) {
						pai = p;
						direcao = -1;
						break;
					}
					p = p.getEsq();
				}
			}
		}
		arvore.insert(valor, pai, direcao);
		return true;
	}
	
	private static boolean remova(int valor) {
		NoArvoreBin<Integer> p = arvore.getRaiz();
		if (p != null) {
			while(true) {
				if (valor == p.getObj()) {
					arvore.delete(p);
					return true;
				}
				if (valor > p.getObj()) {
					if (p.getDir() == null) {
						break;
					}
					p = p.getDir();
				}
				else {
					if (p.getEsq() == null) {
						break;
					}
					p = p.getEsq();
				}
			}
		}
		return false;
	}
	
	private static void inserir() {
		char resp;
		do {
			Keyboard.clrscr();
			int valor = Keyboard.readInt("Entrar com o valor: ");
			if(insira(valor))
				System.out.println("Valor inserido com sucesso!");
			else
				System.out.println("Valor já existente.");
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');
	}

	private static void remover() {
		Keyboard.clrscr();
		int  valor = Keyboard.readInt("Entrar com o valor: ");
		if(remova(valor))
			System.out.println("Valor removido com sucesso!");
		else
			System.out.println("Valor não encontrado.");
		Keyboard.waitEnter();
	}
	

	private static void listar() {
		Keyboard.clrscr();
		System.out.println("Valor");
		System.out.println("------");

		MyIterator<Integer> it = arvore.iterator();
		Integer valor = it.getFirst();
		while (valor != null) {
			System.out.printf("%6d\n", valor);
			valor = it.getNext();
		}
		Keyboard.waitEnter();
	}
	
	public static void main(String[] args) {
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Inserir/Remover/Listar/Desenhar/Terminar");
			switch (opcao) {
				case 1:
					inserir();
					break;
				case 2:
					remover();
					break;
				case 3:
					listar();
					break;
				case 4:
					Keyboard.clrscr();
					arvore.desenhe(80);
					Keyboard.waitEnter();
					break;

			}
		} while (opcao < 5);
		System.out.println("\nFim do programa");
	}	
}
