package lista07_1;

import diversos.*;

public class DemoListaEncadeada {

	static NoSimpEnc<Aluno> inicioAluno, fimAluno;

	static boolean busque(int numMat) {
		NoSimpEnc<Aluno> p = inicioAluno;
		while (p != null) {
			if (p.getObj().getnumMat() == numMat)
				return true;
			p = p.getProx();
		}
		return false;
	}

	static void incluirAlunos() {
		char resp;
		do {
			Keyboard.clrscr();
			int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
			if (busque(numMat))
				System.out.println("Matricula ja existente");
			else {
				String nomeAluno = Keyboard.readString("Entrar com o nome: ");
				NoSimpEnc<Aluno> novoNo = new NoSimpEnc<Aluno>(new Aluno(
						numMat, nomeAluno));
				if (inicioAluno == null)
					inicioAluno = fimAluno = novoNo;
				else {
					NoSimpEnc<Aluno> p = inicioAluno;
					NoSimpEnc<Aluno> pAnt = null;
					if (novoNo.getObj().getnumMat() < inicioAluno.getObj()
							.getnumMat()) {
						novoNo.setProx(p);
						inicioAluno = novoNo;
					} else if (novoNo.getObj().getnumMat() > fimAluno.getObj()
							.getnumMat()) {
						fimAluno.setProx(novoNo);
						fimAluno = novoNo;
					} else {
						while (p.getObj().getnumMat() < novoNo.getObj()
								.getnumMat()) {
							pAnt = p;
							p = p.getProx();
						}
						pAnt.setProx(novoNo);
						novoNo.setProx(p);
					}
				}
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');
	}

	static void listarAlunos() {
		Keyboard.clrscr();
		System.out.println("NumMat  Nome do Aluno");
		System.out.println("------  ------------------------------");
		NoSimpEnc<Aluno> p = inicioAluno;
		while (p != null) {
			System.out.printf("%6d  %-30s\n", p.getObj().getnumMat(), p
					.getObj().getNomeAluno());
			p = p.getProx();
		}
		Keyboard.waitEnter();
	}

	static void removerAluno() {
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Digite o número da matrícula:");
		NoSimpEnc<Aluno> p = inicioAluno;
		NoSimpEnc<Aluno> pAnt = null;
		while (p != null) {
			if (numMat == p.getObj().getnumMat()) {
				char resp = Keyboard.readChar("Deseja remover o aluno "
						+ p.getObj().getNomeAluno() + " ?");
				if (resp == 's') {
					if (pAnt == null) {
						inicioAluno = inicioAluno.getProx();
						if (inicioAluno == null)
							fimAluno = null;
					} else {
						if (p == fimAluno) {
							fimAluno = pAnt;
							pAnt.setProx(null);
						} else {
							pAnt.setProx(p.getProx());
						}
					}
					System.out.println("Aluno removido.");
				}// if resp
				else {
					System.out.println("Aluno não removido.");
				}
				Keyboard.waitEnter();
				return;
			} else {
				pAnt = p;
				p = p.getProx();
			}
		}
		System.out.println("Aluno inexistente.");
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
				removerAluno();
				break;
			}
		} while (opcao < 4);
		System.out.println("\nFim do programa");
	}

}
