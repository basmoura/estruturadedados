package lista02_1;

import diversos.*;

public class DemoSkipList {

	static SkipNo<Pessoa> noCabeca;
	static int nivelAtual;
	static int maxNivel;

	static int nivelDoNo() {
		int newLevel = 0;
		while (newLevel < maxNivel && Math.random() < 0.5
				&& newLevel <= nivelAtual) {
			newLevel++;
		}
		return newLevel;
	}

	static boolean incluir(Pessoa pessoa) {
		SkipNo<Pessoa> p = noCabeca;
		SkipNo<Pessoa>[] pAnt = new SkipNo[maxNivel + 1];
		for (int i = nivelAtual; i >= 0; i--) {
			while (p.prox[i] != null
					&& p.prox[i].obj.getCodPessoa() < pessoa.getCodPessoa()) {
				p = p.prox[i];
			}
			pAnt[i] = p;
		}

		p = p.prox[0];
		if (p == null || !(p.obj.getCodPessoa() == pessoa.getCodPessoa())) {
			int nivel = nivelDoNo();
			if (nivel > nivelAtual) {
				pAnt[nivel] = noCabeca;
				nivelAtual = nivel;
			}
			p = new SkipNo<Pessoa>(nivel, pessoa);
			for (int i = 0; i <= nivel; i++) {
				p.prox[i] = pAnt[i].prox[i];
				pAnt[i].prox[i] = p;
			}
			return true;
		} else
			return false;
	}

	static boolean excluir(int codPessoa) {
		SkipNo<Pessoa> p = noCabeca;
		SkipNo<Pessoa>[] pAnt = new SkipNo[maxNivel + 1];
		for (int i = nivelAtual; i >= 0; i--) {
			while (p.prox[i] != null
					&& p.prox[i].obj.getCodPessoa() < codPessoa) {
				p = p.prox[i];
			}
			pAnt[i] = p;
		}

		p = p.prox[0];
		if (p != null && (p.obj.getCodPessoa() == codPessoa)) {
			p.obj = null;
			for (int i = 0; i <= nivelAtual; i++) {
				if (pAnt[i].prox[i] != p)
					break;
				pAnt[i].prox[i] = p.prox[i];
			}

			while (nivelAtual > 0 && noCabeca.prox[nivelAtual] == null) {
				nivelAtual--;
			}
			return true;
		} else
			return false;
	}

	static void incluirPessoa() {
		char resp;
		Keyboard.clrscr();
		do {
			int codPessoa = Keyboard.readInt("Entrar com o codigo: ");
			String nomePessoa = Keyboard.readString("Entrar com o nome: ");
			if (incluir(new Pessoa(codPessoa, nomePessoa)))
				System.out.println("Pessoa incluida");
			else
				System.out.println("Codigo ja existente");
			resp = Keyboard.readChar("Outra inclusao(s/n)? ");
		} while (resp == 's');
	}

	static void excluirPessoa() {
		Keyboard.clrscr();
		int codPessoa = Keyboard.readInt("Entrar com o codigo: ");
		if (excluir(codPessoa))
			System.out.println("Pessoa removida");
		else
			System.out.println("Codigo inexistente");
		Keyboard.waitEnter();
	}

	static void listar() {
		Keyboard.clrscr();
		System.out.println("Codigo  Nome da Pessoa");
		System.out.println("------  -----------------------------");
		SkipNo<Pessoa> p = noCabeca.prox[0];
		while (p != null) {
			System.out.printf("%6s  %29s\n", p.obj.getCodPessoa(),
					p.obj.getNomePessoa());
			p = p.prox[0];
		}
		Keyboard.waitEnter();
	}

	static void imprimirListas() {
		Keyboard.clrscr();
		SkipNo<Pessoa> p = noCabeca;
		for (int i = nivelAtual; i >= 0; i--) {
			System.out.print(i + " - ");
			while (p.prox[i] != null) {
				System.out.print(p.prox[i].obj.getCodPessoa() + " ");
				p = p.prox[i];
			}
			p = noCabeca;
			System.out.println("\n");
		}
		Keyboard.waitEnter();
	}

	static Pessoa procurar(int codPessoa) {
		SkipNo<Pessoa> p = noCabeca;
		for (int i = nivelAtual; i >= 0; i--) {
			while (p.prox[i] != null
					&& p.prox[i].obj.getCodPessoa() <= codPessoa) {
				if (p.prox[i].obj.getCodPessoa() == codPessoa)
					return p.prox[i].obj;
				p = p.prox[i];
			}
		}
		return null;
	}

	static void procurarPessoa() {
		Keyboard.clrscr();
		int codPessoa = Keyboard.readInt("Entrar com o codigo da pessoa: ");
		Pessoa pessoa = procurar(codPessoa);
		if (pessoa == null)
			System.out.println("Codigo inexistente");
		else
			System.out.println(pessoa.getNomePessoa());
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		maxNivel = 5;
		noCabeca = new SkipNo<Pessoa>(maxNivel, null);
		nivelAtual = 0;

		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Incluir Pessoa/Listar Pessoa/Excluir Pessoa/Procurar Pessoa/Imprimir Listas/"
							+ "Terminar");
			switch (opcao) {
			case 1:
				incluirPessoa();
				break;
			case 2:
				listar();
				break;
			case 3:
				excluirPessoa();
				break;
			case 4:
				procurarPessoa();
				break;
			case 5:
				imprimirListas();
				break;
			}
		} while (opcao < 6);

	}

}
