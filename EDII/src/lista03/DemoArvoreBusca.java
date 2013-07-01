package lista03;

import diversos.Keyboard;

public class DemoArvoreBusca {

	static ArvoreBin<Integer> arvore = new ArvoreBin<>();

	static boolean insira(int valor) {
		NoArvoreBin<Integer> p = arvore.getRaiz();
		NoArvoreBin<Integer> pai = null;
		int direcao = -1;
		if (p != null) {
			while (true) {
				if (p.getObj() == valor)
					return false;
				if (valor < p.getObj()) {
					if (p.getEsq() == null) {
						pai = p;
						direcao = -1;
						break;
					}
					p = p.getEsq();
				} else {
					if (p.getDir() == null) {
						pai = p;
						direcao = 1;
						break;
					}
					p = p.getDir();
				}
			}
		}
		arvore.insert(valor, pai, direcao);
		return true;
	}

	static void inserir() {
		char resp;
		Keyboard.clrscr();
		do {
			int valor = Keyboard.readInt("Entrar com o valor:");
			if (!insira(valor))
				System.out.println("Valor ja existente.");
			resp = Keyboard.readChar("Outra inclusao(s/n)?");
		} while (resp == 's');
	}

	static boolean delete(int valor) {
		NoArvoreBin<Integer> p = arvore.getRaiz();
		NoArvoreBin<Integer> pai = null;
		if (valor == p.getObj()) {
			return true;
		} else if (valor < p.getObj()) {
			if (p.getEsq() != null) {
				if (p.getEsq().getObj() == valor) {
					arvore.delete(new NoArvoreBin<Integer>(valor, p.getEsq()
							.getPai()));
					return true;
				}
				p = p.getEsq();
			}
		} else if (valor > p.getObj()) {
			if (p.getDir() != null) {
				if (p.getDir().getObj() == valor) {
					arvore.delete(new NoArvoreBin<Integer>(valor, p.getDir()
							.getPai()));
					return true;
				}
				p = p.getDir();
			}
		}
		return false;
	}

	static void remover() {
		char resp;
		Keyboard.clrscr();
		int valor = Keyboard.readInt("Entrar com o valor:");
		delete(valor);
	}

	static void listar() {

	}

	public static void main(String[] args) {
		int opcao;
		do {
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
	}
}
