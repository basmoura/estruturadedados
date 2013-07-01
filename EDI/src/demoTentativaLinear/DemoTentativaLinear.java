package demoTentativaLinear;

import java.util.Comparator;

import diversos.*;

public class DemoTentativaLinear {

	static int tamTabela, numItens;
	static Pessoa[] tabela;

	static Pessoa deletado = new Pessoa(-1);

	static class ComparePessoas implements Comparator<Pessoa> {

		@Override
		public int compare(Pessoa pessoa1, Pessoa pessoa2) {
			return pessoa1.getNomePessoa().compareToIgnoreCase(
					pessoa2.getNomePessoa());
		}
	}

	// Testa se x � primo
	public static boolean isPrime(int x) {
		int divisor;
		if (x < 4)
			return true;

		if ((x % 2) == 0)
			return false;

		divisor = 3;

		while (!((divisor * divisor > x) || (x % divisor == 0)))
			divisor = divisor + 2;

		if (divisor * divisor > x)
			return true;
		else
			return false;
	}

	// Retorna o pr�ximo n�mero primo maior do que x
	public static int nextPrime(int x) {
		if (x < 2)
			return 3;

		if (x % 2 == 0)
			x++;
		else
			x = x + 2;

		while (!isPrime(x))
			x = x + 2;

		return x;

	}

	static int funcHash(int codigo) {
		return codigo % tamTabela;
	}

	static boolean add(Pessoa pessoa) {
		int quad = 1;
		if ((float) numItens / tamTabela >= 0.7f)
			redimensione();
		int k = funcHash(pessoa.getCodPessoa());
		int inicio = k;
		int x = k;
		// Procura a pr�xima posi��o dispon�vel
		while ((tabela[x] != null) && (tabela[x] != deletado)) {
			if (pessoa.getCodPessoa() == tabela[x].getCodPessoa()) {
				return false;
			}
			x = (k + (quad * quad)) % tamTabela;
			quad++;
		}
		// O dado vai ser armazenado na posi��o j
		int j = x;
		quad = 1;
		// Verifica se o dado j� existe na tabela
		while (tabela[x] != null) {
			if (x == inicio)
				break;
			if ((tabela[x] != deletado)
					&& (pessoa.getCodPessoa() == tabela[x].getCodPessoa())) {
				return false;
			}
			x = (k + (quad * quad)) % tamTabela;
			quad++;
		}

		// Armazena o dado na posi��o j
		tabela[j] = pessoa;
		numItens++;
		return true;

	}

	static boolean contains(Pessoa pessoa) {
		return (retrieve(pessoa) != null);
	}

	static Pessoa retrieve(Pessoa pessoa) {
		int k = funcHash(pessoa.getCodPessoa());
		int inicio = k;
		int quad = 1;
		int x = k;
		while (tabela[x] != null) {
			if ((tabela[x] != deletado)
					&& (pessoa.getCodPessoa() == tabela[x].getCodPessoa())) {
				return tabela[x];
			}
			x = (k + (quad * quad)) % tamTabela;
			quad++;
			if (x == inicio)
				break;
		}
		return null;
	}

	static boolean remove(Pessoa pessoa) {
		// Remove a pessoa da tabela marcando-a como deletado
		int k = funcHash(pessoa.getCodPessoa());
		int inicio = k;
		int quad = 1;
		int x = k;
		while (tabela[x] != null) {
			if ((tabela[x] != deletado)
					&& (pessoa.getCodPessoa() == tabela[x].getCodPessoa())) {
				tabela[x] = deletado;
				numItens--;
				return true;
			}
			x = (k + (quad * quad)) % tamTabela;
			quad++;
			if (x == inicio)
				break;
		}
		return false;
	}

	static void redimensione() {
		tamTabela = nextPrime((int) (tamTabela * 1.5));
		Pessoa[] tabelaTemp = new Pessoa[tamTabela];
		for (int i = 0; i < tabela.length; i++) {
			if ((tabela[i] != null) && (tabela[i] != deletado)) {
				int k = funcHash(tabela[i].getCodPessoa());
				int inicio = k;
				int quad = 1;
				int x = k;
				while (tabelaTemp[x] != null) {
					x = (k + (quad * quad)) % tamTabela;
					quad++;
					if (x == inicio)
						break;
				}
				tabelaTemp[x] = tabela[i];
			}
		}
		tabela = tabelaTemp;
	}

	static void incluir() {
		char resp;
		do {
			Keyboard.clrscr();
			int codigo = Keyboard.readInt("Entrar com o codigo: ");
			Pessoa pessoa = new Pessoa(codigo);
			if (contains(pessoa))
				System.out.println("Pessoa ja existente");
			else {
				String nome = Keyboard.readString("Entrar com o nome: ");
				pessoa.setNomePessoa(nome);
				add(pessoa);
			}
			resp = Keyboard.readChar("Outra pessoa(s/n)?: ");

		} while (resp == 's');

	}

	static void excluir() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Entrar com o codigo: ");
		Pessoa pessoa = new Pessoa(codigo);
		pessoa = retrieve(pessoa);
		if (pessoa == null)
			System.out.println("Pessoa inexistente");
		else {
			System.out.println(pessoa.getNomePessoa());
			char resp = Keyboard.readChar("Deseja excluir(s/n)? ");
			if (resp == 's') {
				remove(pessoa);
				System.out.println("Pessoa excluida");
			} else
				System.out.println("Pessoa nao excluida");
		}

		Keyboard.waitEnter();
	}

	static void impimirTabela() {
		// Imprime o conte�do da tabela
		Keyboard.clrscr();
		System.out.println("indice  Codigo  Nome da Pessoa");
		System.out.println("------  ------  -----------------------------");
		for (int i = 0; i < tamTabela; i++) {
			System.out.printf("%4d", i);
			if (tabela[i] == deletado)
				System.out.println("    deletado");
			else if (tabela[i] == null)
				System.out.println();
			else
				System.out.printf("    %4d    %-30s\n",
						tabela[i].getCodPessoa(), tabela[i].getNomePessoa());
		}
		Keyboard.waitEnter();
	}

	static void imprimrAlfabetica() {
		// Imprime em ordem alfab�tica as pessoas armazenadas
		// na tabela
		Pessoa[] pessoas = new Pessoa[numItens];
		int j = -1;
		for (int i = 0; i < tamTabela; i++) {
			if ((tabela[i] != null) && (tabela[i] != deletado)) {
				j++;
				pessoas[j] = tabela[i];
			}
		}
		Sort.insercao(pessoas, new ComparePessoas());
		Keyboard.clrscr();
		System.out.println("Codigo  Nome da Pessoa");
		System.out.println("------  -----------------------------");
		for (int i = 0; i < pessoas.length; i++) {
			System.out.printf("%4d    %-30s\n", pessoas[i].getCodPessoa(),
					pessoas[i].getNomePessoa());
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		int opcao;
		tamTabela = Keyboard.readInt("Entrar com o tamanho da tabela: ");
		if (isPrime(tamTabela)) {
			tabela = new Pessoa[tamTabela];
		} else {
			tamTabela = nextPrime(tamTabela);
			tabela = new Pessoa[tamTabela];
		}
		Keyboard.waitEnter();
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Incluir/Excluir/Imprimir Tabela/"
					+ "Imprimir em ordem alfabetica/Terminar");
			switch (opcao) {
			case 1:
				incluir();
				break;
			case 2:
				excluir();
				break;
			case 3:
				impimirTabela();
				break;
			case 4:
				imprimrAlfabetica();
				break;
			}

		} while (opcao < 5);
		System.out.println("\nFim do programa");
	}

}
