package lista11;
import diversos.*;

public class Demo {

	private static Fila<Aluno> filaAlunos = new FilaArray<Aluno>(10);
   //private static Fila<Aluno> filaAlunos = new FilaEnc<Aluno>();

	public static void main(String[] args) {
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Incluir/Remover/Listar/Terminar");
			switch (opcao) {
				case 1:
					incluir();
					break;
				case 2:
					removerPrimeiro();
					break;
				case 3:
					listar();
					break;
			}

		} while (opcao < 4);
		System.out.println("\nFim do programa");
	}

	private static void listar() {
		Keyboard.clrscr();
		System.out.println("Num Mat  Nome do aluno");
		System.out.println("-------  ------------------------------");
		MyIterator<Aluno> it = filaAlunos.iterator();
		Aluno aluno = it.getFirst();
		while (aluno != null) {
			System.out.printf("%7d  %-30s\n", aluno.getNumMat(), aluno.getNome());
			aluno = it.getNext();
		}
		Keyboard.waitEnter();
	}

	private static void removerPrimeiro() {
		Keyboard.clrscr();
		if (filaAlunos.isEmpty())
			System.out.println("Fila vazia");
		else {
			Aluno aluno = filaAlunos.remova();
			System.out.println("Aluno removido da fila");
			System.out.println("Num Mat: " + aluno.getNumMat());
			System.out.println("Nome do aluno: " + aluno.getNome());
		}
		Keyboard.waitEnter();
	}

	private static void incluir() {
		char resp;
		do {
			Keyboard.clrscr();
			int nMat = Keyboard.readInt("Entrar com o numero de matricula: ");
			Aluno aluno = new Aluno(nMat);
			String nomeAluno = Keyboard.readString("Entrar com o nome: ");
			aluno.setNome(nomeAluno);
			filaAlunos.insira(aluno);
			resp = Keyboard.readChar("Outro aluno(s/n)?: ");

		} while (resp == 's');
		System.out.println("\nFim do programa");
	}

}
