package demoListaMapa;

import diversos.*;

public class Demo {

	static HashMapa<Pessoa, ListaEncOrd<Bem>> pessoasBens = new HashMapa<Pessoa, ListaEncOrd<Bem>>(100);

	public static void main(String[] args) {
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Incluir Pessoa/Listar Pessoas/Incluir Bens/"
					+ "Listar Bens/Terminar");
			switch (opcao) {
			case 1:
				incluirPessoas();
				break;
			case 2:
				listarPessoas();
				break;
			case 3:
				incluirBens();
				break;
			case 4:
				listarBens();
				break;
			}
		} while (opcao != 5);
	}

	private static void listarPessoas() {
		MyIteratorMapa<Pessoa, ListaEncOrd<Bem>> it = pessoasBens.iterator();
		ChaveValor<Pessoa, ListaEncOrd<Bem>> chaveValor = it.getFirst();
		while(chaveValor != null) {
			System.out.println("" + chaveValor.getChave().getCodPessoa() + " " + chaveValor.getChave().getNomePessoa());
			chaveValor = it.getNext();
		}
		Keyboard.waitEnter();
	}

	private static void incluirPessoas() {
		char resp;
		Keyboard.clrscr();
		do {
			
			int codPessoa = Keyboard.readInt("Entrar com o cidgo da pessoa:");
			Pessoa pessoa = new Pessoa(codPessoa);
			if (pessoasBens.contains(pessoa)) {
				System.out.println("Codigo ja existente");
			} else {
				String nomePessoa = Keyboard
						.readString("Entrar com o nome da pessoa:");
				pessoa.setNomePessoa(nomePessoa);
				pessoasBens.put(pessoa, new ListaEncOrd<Bem>());
			}
			resp = Keyboard.readChar("Outra inclusao(s/n)?");
		} while (resp == 's');
	}

	private static void listarBens() {
		// TODO Auto-generated method stub

	}

	private static void incluirBens() {
		Keyboard.clrscr();
		int codPessoa = Keyboard.readInt("Entrar com o codigo da pessoa:");
		ListaEncOrd<Bem> bens = pessoasBens.getValor(new Pessoa(codPessoa));
		if (bens == null) {
			System.out.println("Codigo inexistente.");
			Keyboard.waitEnter();
			return;
		}
		char resp;
		Keyboard.clrscr();
		do {
			int codBem = Keyboard.readInt("Entrar com o codigo do bem:");
			Bem bem = new Bem(codBem);
			if (bens.contains(bem)) {
				System.out.println("Codigo ja existente");
			} else {
				String descricao = Keyboard
						.readString("Entrar com a descricao:");
				float valor = Keyboard.readFloat("Entrar com o valor:");
				bem.setDescricao(descricao);
				bem.setValor(valor);
				bens.add(bem);
			}
			resp = Keyboard.readChar("Outra inclusao(s/n)?");
		} while (resp == 's');

	}
}
