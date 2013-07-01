package lista06;


import diversos.*;

public class Demo {

	static ListaSeqOrd<Pessoa> pessoas;
	
	static ObjectFile arqPessoas = new ObjectFile("lista06.dat");
	
	@SuppressWarnings("unchecked")
	static void leiaArquivo() {
		if (arqPessoas.reset()) {
			pessoas = (ListaSeqOrd<Pessoa>) arqPessoas.read();
			arqPessoas.closeFile();
		} else {
			pessoas = new ListaSeqOrd<>();
		}
	}
	
	static void graveArquivo() {
		arqPessoas.rewrite();
		arqPessoas.write(pessoas);
		arqPessoas.closeFile();
	}

	static void cadastrarPessoa() {
		Keyboard.clrscr();
		char resp;
		do {
			int codigo = Keyboard.readInt("Insira o código da pessoa:");
			if (pessoas.contains(new Pessoa(codigo))) {
				System.out.println("Pessoa já existe.");
			} else {
				String nome = Keyboard.readString("Insira o nome da pessoa:");
				pessoas.add(new Pessoa(codigo, nome));
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)?");
		} while (resp == 's');
	}

	static void excluirPessoa() {
		Keyboard.clrscr();
		char resp;
		do {
			int codigo = Keyboard.readInt("Digite o código da pessoa: ");
			Pessoa pessoa = pessoas.retrieve(new Pessoa(codigo));
			if (pessoa != null) {
				resp = Keyboard.readChar("Deseja remover " + pessoa.getNome()
						+ " ?");
				if (resp == 's') {
					pessoas.remove(pessoa);
					System.out.println("Pessoa removida.");
				}// if resp
				else {
					System.out.println("Pessoa não removida.");
				}
				Keyboard.waitEnter();
				return;
			} else {
				System.out.println("Pessoa inexistente.");
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)?");
		} while (resp == 's');
		Keyboard.waitEnter();
	}

	static void cadastrarBensDaPessoa() {
		Keyboard.clrscr();
		int codigoPessoa = Keyboard.readInt("Digite o codigo da pessoa:");
		char resp;
		Pessoa pessoa = pessoas.retrieve(new Pessoa(codigoPessoa));
		if (pessoa != null) {
			do {
				int codigoBem = Keyboard.readInt("Digite o codigo do bem:");
				if (pessoa.getBens().contains(new Bem(codigoBem))) {
					System.out.println("Bem já existente.");
				} else {
					String descricao = Keyboard
							.readString("Digite a descricao do bem:");
					String dataDeAquisicao = Keyboard
							.readData("Digite a data de aquisicao");
					double valor = Keyboard
							.readDouble("Digite o valor do bem:");
					Bem bem = new Bem(codigoBem, descricao, dataDeAquisicao,
							valor);
					ListaSeqOrd<Bem> bens = pessoa.getBens();
					bens.add(bem);
					System.out.println("Bem adicionado com sucesso.");
				}
				resp = Keyboard.readChar("Outra inclusao (s/n)?");
			} while (resp == 's');
		} else {
			System.out.println("Pessoa inexistente.");
			Keyboard.waitEnter();
		}
	}

	static void excluirBens() {
		Keyboard.clrscr();
		int codigoPessoa = Keyboard.readInt("Digite o codigo da pessoa:");
		char resp;
		Pessoa pessoa = pessoas.retrieve(new Pessoa(codigoPessoa));
		if (pessoa != null) {
			do {
				int codigoBem = Keyboard.readInt("Digite o codigo do bem:");
				if (pessoa.getBens().contains(new Bem(codigoBem))) {
					ListaSeqOrd<Bem> bens = pessoa.getBens();
					Bem bem = bens.retrieve(new Bem(codigoBem));
					if (bem != null) {
						resp = Keyboard.readChar("Deseja remover "
								+ bem.getCodigo() + " ?");
						if (resp == 's') {
							bens.remove(bem);
							System.out.println("Bem removido.");
						}// if resp
						else {
							System.out.println("Bem não removido.");
						}
						Keyboard.waitEnter();
						return;
					}
				} else {
					System.out.println("Bem Inexistente.");
				}
				resp = Keyboard.readChar("Excluir outro bem (s/n)?");
			} while (resp == 's');
		} else {
			System.out.println("Pessoa inexistente.");
		}
		Keyboard.waitEnter();
	}

	static void listarPessoas() {
		Keyboard.clrscr();
		System.out
				.println("CodPessoa  Nome da Pessoa           Valor Total dos Bens");
		System.out
				.println("---------  -----------------------  --------------------");
		ComparaNomePessoa c = new ComparaNomePessoa();
		Object[] pessoasTemp = pessoas.sort(c);
		double valorTotalDosBens = 0;
		for (int i = 0; i < pessoas.size(); i++) {
			ListaSeqOrd<Bem> bens = ((Pessoa) pessoasTemp[i]).getBens();
			MyIterator<Bem> itBem = bens.iterator();
			Bem bem = itBem.getFirst();
			while (bem != null) {
				valorTotalDosBens += bem.getValor();
				bem = itBem.getNext();
			}
			System.out.printf("%9d  %-23s  %20.2f\n",
					((Pessoa) pessoasTemp[i]).getCodigo(),
					((Pessoa) pessoasTemp[i]).getNome(), valorTotalDosBens);
			valorTotalDosBens = 0;
		}
		Keyboard.waitEnter();
	}

	static void listarBensDaPessoa() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Digite o código da pessoa:");
		Keyboard.clrscr();
		Pessoa p = pessoas.retrieve(new Pessoa(codigo));
		if (p != null) {
			System.out.println("Cod Pessoa: " + p.getCodigo()
					+ "  Nome da Pessoa: " + p.getNome());
			System.out
					.println("\nCodBem  Descrição do Bem      Data Aquisição  Valor Do Bem");
			System.out
					.println("------  --------------------  --------------  ------------");
			ListaSeqOrd<Bem> bens = p.getBens();
			MyIterator<Bem> it = bens.iterator();
			Bem bem = it.getFirst();
			while (bem != null) {
				System.out.printf("%6d  %-20s  %10s  %12.2f\n",
						bem.getCodigo(), bem.getDescricao(),
						bem.getDataDeAquisicao(), bem.getValor());
				bem = it.getNext();
			}
		} else {
			System.out.println("Pessoa Inexistente.");
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		leiaArquivo();
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Cadastrar Pessoa/Excluir Pessoa/Cadastrar Bens das pessoas/Excluir Bens/Listar Pessoas/Listar Bens da Pessoa/Terminar");
			switch (opcao) {
			case 1:
				cadastrarPessoa();
				break;
			case 2:
				excluirPessoa();
				break;
			case 3:
				cadastrarBensDaPessoa();
				break;
			case 4:
				excluirBens();
				break;
			case 5:
				listarPessoas();
				break;
			case 6:
				listarBensDaPessoa();
				break;
			}
		} while (opcao < 7);
		graveArquivo();
		System.out.println("\nFim do programa");
	}
}