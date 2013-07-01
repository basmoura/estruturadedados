package lista07_2;

import diversos.*;

public class Demo {

	static ListaEncOrd<Aluno> alunos = new ListaEncOrd<Aluno>();
	static ListaEncOrd<Livro> livros = new ListaEncOrd<Livro>();

	// Início Métodos de Aluno
	static void incluirAluno() {
		Keyboard.clrscr();
		char resp;
		do {
			int numMat = Keyboard.readInt("Digite o número da matrícula: ");
			if (alunos.contains(new Aluno(numMat))) {
				System.out.println("Aluno já existente.");
			} else {
				String nome = Keyboard.readString("Digite o nome do aluno: ");
				alunos.add(new Aluno(numMat, nome));
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');
	}

	static void excluirAluno() {
		Keyboard.clrscr();
		char resp;
		do {
			int numMat = Keyboard.readInt("Digite o número de matrícula: ");
			Aluno aluno = alunos.retrieve(new Aluno(numMat));
			if (aluno != null) {
				resp = Keyboard.readChar("Deseja remover " + aluno.getNome()
						+ " ?");
				if (resp == 's') {
					alunos.remove(aluno);
					System.out.println("Pessoa removida.");
				} else {
					System.out.println("Pessoa não removida.");
				}
			} else {
				System.out.println("Pessoa inexistente.");
			}
			resp = Keyboard.readChar("Outra exclusão?(s/n)");
		} while (resp == 's');
	}

	static void consultarAluno() {
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Digite o número de matrícula: ");
		Aluno aluno = alunos.retrieve(new Aluno(numMat));
		if (aluno != null) {
			System.out.println("Aluno: " + aluno.getNome());
			System.out.println("\n---------- Livros Emprestados ---------");
			System.out.println("\nCod  Nome do Livro  data de empréstimo");
			System.out.println("---  -------------  ----------");
			ListaEncOrd<Livro> livrosTemp = aluno.getLivros();
			MyIterator<Livro> it = livrosTemp.iterator();
			Livro livro = it.getFirst();
			while (livro != null) {
				System.out.printf("%3d  %-13s  %10s\n", livro.getCodigo(),
						livro.getTitulo(), livro.getDataDeEmprestimo());
				livro = it.getNext();
			}
		} else {
			System.out.println("Aluno inexistente.");
		}
		Keyboard.waitEnter();
	}

	static void listarAlunos() {
		Keyboard.clrscr();
		if (!alunos.isEmpty()) {
			System.out.println("Mat  Nome do Aluno");
			System.out.println("---  -------------");
			MyIterator<Aluno> it = alunos.iterator();
			Aluno aluno = it.getFirst();
			while (aluno != null) {
				System.out.printf("%3d  %-13s\n", aluno.getNumMat(),
						aluno.getNome());
				aluno = it.getNext();
			}
		} else {
			System.out.println("Não tem alunos cadastrados.");
		}
		Keyboard.waitEnter();
	}

	// Fim Métodos de Aluno

	// Início Métodos de Livro
	static void incluirLivro() {
		Keyboard.clrscr();
		char resp;
		do {
			int codigo = Keyboard.readInt("Digite o código do livro: ");
			if (livros.contains(new Livro(codigo))) {
				System.out.println("Livro já existente.");
			} else {
				String titulo = Keyboard
						.readString("Digite o título do livro: ");
				livros.add(new Livro(codigo, titulo));
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');
	}

	static void excluirLivro() {
		Keyboard.clrscr();
		char resp;
		do {
			int codigo = Keyboard.readInt("Digite o código do livro: ");
			Livro livro = livros.retrieve(new Livro(codigo));
			if (livro != null) {
				resp = Keyboard.readChar("Deseja remover " + livro.getTitulo()
						+ " ?");
				if (resp == 's') {
					if (livros.retrieve(livro).getAluno() == null) {
						livros.remove(livro);
						System.out.println("Livro removida.");
					} else {
						System.out.println("Livro emprestado no momento.");
					}
				} else {
					System.out.println("Livro não removida.");
				}
			} else {
				System.out.println("Livro inexistente.");
			}
			resp = Keyboard.readChar("Outra exclusão?(s/n)");
		} while (resp == 's');
	}

	static void consultarLivro() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Digite o código do livro: ");
		Livro livro = livros.retrieve(new Livro(codigo));
		if (livro != null) {
			Aluno aluno = livro.getAluno();
			System.out.println("Livro: " + livro.getTitulo());
			if (aluno == null) {
				System.out.println("\n---------- Não Emprestado ---------");
			} else {
				System.out.println("\n---------- Emprestado ---------\n");
				System.out.println("Mat  Nome do Aluno");
				System.out.println("---  -------------");
				System.out.printf("%3d  %-13s\n", aluno.getNumMat(),
						aluno.getNome());
			}
		} else {
			System.out.println("Livro inexistente.");
		}
		Keyboard.waitEnter();
	}

	static void listarLivros() {
		Keyboard.clrscr();
		if (!alunos.isEmpty()) {
			System.out.println("Cod  Titulo do Livro");
			System.out.println("---  ---------------");
			MyIterator<Livro> it = livros.iterator();
			Livro livro = it.getFirst();
			while (livro != null) {
				System.out.printf("%3d  %-15s\n", livro.getCodigo(),
						livro.getTitulo());
				livro = it.getNext();
			}
		} else {
			System.out.println("Não tem livros cadastrados.");
		}
		Keyboard.waitEnter();
	}

	// Fim Métodos de Livro

	// Início Emprestar e Devolver Livro
	static void emprestarLivro() {
		Keyboard.clrscr();
		int numMat = Keyboard
				.readInt("Digite o número de matrícula do aluno: ");
		Aluno aluno = alunos.retrieve(new Aluno(numMat));
		if (aluno != null) {
			int codigo = Keyboard.readInt("Digite o código do livro: ");
			Livro livro = livros.retrieve(new Livro(codigo));
			if (livro != null) {
				if (livro.getAluno() == null) {
					String data = Keyboard
							.readData("Digite a data do empréstimo:");
					livro.setDataDeEmprestimo(data);
					aluno.getLivros().add(livro);
					livros.retrieve(livro).setAluno(aluno);
					System.out.println("Livro emprestado com sucesso!");
				} else {
					System.out.println("Livro já emprestado.");
				}
			} else {
				System.out.println("Livro inexistente.");
			}
		} else {
			System.out.println("Aluno inexistente");
		}
		Keyboard.waitEnter();
	}

	static void devolverLivro() {
		Keyboard.clrscr();
		int numMat = Keyboard
				.readInt("Digite o número de matrícula do aluno: ");
		Aluno aluno = alunos.retrieve(new Aluno(numMat));
		if (aluno != null) {
			int codigo = Keyboard.readInt("Digite o código do livro: ");
			Livro livro = aluno.getLivros().retrieve(new Livro(codigo));
			if (livro != null) {
				livros.retrieve(livro).setDataDeEmprestimo(null);
				livros.retrieve(livro).setAluno(null);
				aluno.getLivros().remove(livro);
				System.out.println("Livro devolvido com sucesso!");
			} else {
				System.out.println("Este livro não está com você.");
			}
		} else {
			System.out.println("Aluno inexistente");
		}
		Keyboard.waitEnter();
	}

	// Fim Emprestar e Devolver Livro

	public static void main(String[] args) {
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Incluir Aluno/Excluir Aluno/Consultar Aluno/"
							+ "Listar Alunos/Incluir Livro/Excluir Livro/"
							+ "Consultar Livro/Listar Livros/Emprestar livro/"
							+ "Devolver Livro/Terminar");
			switch (opcao) {
			case 1:
				incluirAluno();
				break;
			case 2:
				excluirAluno();
				break;
			case 3:
				consultarAluno();
				break;
			case 4:
				listarAlunos();
				break;
			case 5:
				incluirLivro();
				break;
			case 6:
				excluirLivro();
				break;
			case 7:
				consultarLivro();
				break;
			case 8:
				listarLivros();
				break;
			case 9:
				emprestarLivro();
				break;
			case 10:
				devolverLivro();
				break;
			}
		} while (opcao < 11);
		System.out.println("\nFim do programa");
	}
}
