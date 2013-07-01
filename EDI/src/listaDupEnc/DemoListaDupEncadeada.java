package listaDupEnc;
import diversos.Keyboard;

public class DemoListaDupEncadeada {
	static NoDupEnc<Aluno> noCabeca = new NoDupEnc<>();
	//inserindo o no sempre no final da lista
	
	static void remover() {
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Entrar com o numero de matricula:");
		NoDupEnc<Aluno> p = noCabeca.getProx();
		while (p != noCabeca) {
			if (p.getObj().getnumMat() == numMat) {
			char resp = Keyboard.readChar("Deseja remover " + p.getObj().getNomeAluno() + "?(s/n)");
				if(resp == 's') {
					p.remove();
					System.out.println("Aluno removido.");
				} else {
					System.out.println("Aluno nao removido");
				}
				Keyboard.waitEnter();
				return;
			} else {
				p = p.getProx();
			}
		}
		System.out.println("Aluno inexistente.");
		Keyboard.waitEnter();
	}
	static void incluirAlunos() {
		char resp;
		Keyboard.clrscr();
		do {
			int numMat = Keyboard.readInt("Entrar com o numero de matrícula:");
			String nome = Keyboard.readString("Entrar com o nome:");
			Aluno aluno = new Aluno(numMat, nome);
			//new NoDupEnc<>(aluno, noCabeca.getAnt(), noCabeca);
			new NoDupEnc<Aluno>(aluno, noCabeca, noCabeca.getProx());
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');
	}

	static void listarAlunos() {
		Keyboard.clrscr();
		System.out.println("NumMat  Nome do Aluno");
		System.out.println("------  ------------------------------");
		NoDupEnc<Aluno> p = noCabeca.getProx();
		while (p != noCabeca) {
			System.out.printf("%4d    %-30s\n", p.getObj().getnumMat(), p.getObj().getNomeAluno());
			p = p.getProx();
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Incluir/Listar/Remover/Terminar");
			switch (opcao) {
				case 1:
					incluirAlunos();
					break;
				case 2:
					listarAlunos();
					break;
				case 3:
					remover();
					break;
			}
		} while (opcao < 4);
		System.out.println("\nFim do programa");
	}

}
