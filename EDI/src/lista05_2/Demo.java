package lista05_2;

import diversos.*;

public class Demo {

	private static ListaSimpEnc<Pessoa> pessoas = new ListaSimpEnc<Pessoa>();

	static NoSimpEnc<Pessoa> busquePessoa(int codigo) {
		NoSimpEnc<Pessoa> p = pessoas.getInicio();
		while (p != null) {
			if (p.getObj().getCodigo() == codigo) {
				return p;
			} else {
				p = p.getProx();
			}
		}
		return null;
	}

	static boolean busqueBem(int codigo) {
		NoSimpEnc<Pessoa> p = pessoas.getInicio();
		while (p != null) {
			ListaSimpEnc<Bem> bens = p.getObj().getBens();
			NoSimpEnc<Bem> pBem = bens.getInicio();
			while (pBem != null) {
				if (pBem.getObj().getCodigo() == codigo) {
					return true;
				} else {
					pBem = pBem.getProx();
				}
			}
			p = p.getProx();
		}
		return false;
	}

	static void cadastrarPessoa() {
		Keyboard.clrscr();
		char resp;
		do {
			int codigo = Keyboard.readInt("Insira o código da pessoa:");
			if (busquePessoa(codigo) != null) {
				System.out.println("Pessoa já existe.");
			} else {
				String nome = Keyboard.readString("Insira o nome da pessoa:");
				pessoas.insertAtEnd(new Pessoa(codigo, nome));
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)?");
		} while (resp == 's');
	}

	static void excluirPessoa() {
		Keyboard.clrscr();
		char resp;
		do {
			int codigo = Keyboard.readInt("Digite o código da pessoa: ");
			if (busquePessoa(codigo) != null) {
				NoSimpEnc<Pessoa> p = pessoas.getInicio();
				NoSimpEnc<Pessoa> pAnt = null;
				while (p != null) {
					if (p.getObj().getCodigo() == codigo) {
						resp = Keyboard.readChar("Deseja remover "
								+ p.getObj().getNome() + " ?");
						if (resp == 's') {
							if (pAnt == null) {
								pessoas.removeFromBegin();
							} else {
								pessoas.removeAfter(pAnt);
							}
							System.out.println("Pessoa removida.");
						}// if resp
						else {
							System.out.println("Pessoa não removida.");
						}
						Keyboard.waitEnter();
						return;
					} else {
						pAnt = p;
						p = p.getProx();
					}
				}
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
		NoSimpEnc<Pessoa> pessoa = busquePessoa(codigoPessoa);
		if (pessoa != null) {
			do {
				int codigoBem = Keyboard.readInt("Digite o codigo do bem:");
				if (busqueBem(codigoBem)) {
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
					ListaSimpEnc<Bem> bens = pessoa.getObj().getBens();
					bens.insertAtEnd(bem);
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
		NoSimpEnc<Pessoa> pessoa = busquePessoa(codigoPessoa);
		if (pessoa != null) {
			do {
				int codigoBem = Keyboard.readInt("Digite o codigo do bem:");
				if (busqueBem(codigoBem)) {
					ListaSimpEnc<Bem> bens = pessoa.getObj().getBens();
					NoSimpEnc<Bem> pBem = bens.getInicio();
					NoSimpEnc<Bem> pAnt = null;
					while (pBem != null) {
						if (pBem.getObj().getCodigo() == codigoBem) {
							resp = Keyboard.readChar("Deseja remover "
									+ pBem.getObj().getCodigo() + " ?");
							if (resp == 's') {
								if (pAnt == null) {
									bens.removeFromBegin();
								} else {
									bens.removeAfter(pAnt);
								}
								System.out.println("Bem removido.");
							}// if resp
							else {
								System.out.println("Bem não removido.");
							}
							Keyboard.waitEnter();
							return;
						} else {
							pAnt = pBem;
							pBem = pBem.getProx();
						}
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
		NoSimpEnc<Pessoa> p = pessoas.getInicio();
		Pessoa[] pessoasTemp = new Pessoa[pessoas.size()];
		int cont = 0;
		while (p != null) {
			pessoasTemp[cont] = p.getObj();
			cont++;
			p = p.getProx();
		}
		ComparaNomePessoa c = new ComparaNomePessoa();
		Sort.bolha(pessoasTemp, c);
		double valorTotalDosBens = 0;
		for (int i = 0; i < pessoas.size(); i++) {
			ListaSimpEnc<Bem> bens = pessoasTemp[i].getBens();
			NoSimpEnc<Bem> pBem = bens.getInicio();
			while (pBem != null) {
				if (pBem.getProx() != null) {
				valorTotalDosBens = pBem.getObj().getValor()
						+ pBem.getProx().getObj().getValor();
				pBem = pBem.getProx().getProx();
				} else {
					valorTotalDosBens += pBem.getObj().getValor();
					break;
				}
			}
			System.out.printf("%9d  %-23s  %20.2f\n", pessoasTemp[i].getCodigo(), pessoasTemp[i].getNome(), valorTotalDosBens);
			valorTotalDosBens = 0;
		}
		Keyboard.waitEnter();
	}

	static void listarBensDaPessoa() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Digite o código da pessoa:");
		Keyboard.clrscr();
		NoSimpEnc<Pessoa> p = busquePessoa(codigo);
		if (p != null) {
			System.out.println("Cod Pessoa: " + p.getObj().getCodigo()
					+ "  Nome da Pessoa: " + p.getObj().getNome());
			System.out
					.println("\nCodBem  Descrição do Bem      Data Aquisição  Valor Do Bem");
			System.out
					.println("------  --------------------  --------------  ------------");
			ListaSimpEnc<Bem> bens = p.getObj().getBens();
			NoSimpEnc<Bem> pBem = bens.getInicio();
			while (pBem != null) {
				System.out.printf("%6d  %-20s  %10s  %12.2f\n", pBem.getObj()
						.getCodigo(), pBem.getObj().getDescricao(), pBem
						.getObj().getDataDeAquisicao(), pBem.getObj()
						.getValor());
				pBem = pBem.getProx();
			}
		} else {
			System.out.println("Pessoa Inexistente.");
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
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
			}
		} while (opcao < 7);
		System.out.println("\nFim do programa");
	}
}
