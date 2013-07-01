package lista05;

import diversos.*;

public class DemoListaEncadeada {

	static NoSimpEnc<Aluno> inicioAluno, fimAluno;
	static NoSimpEnc<Curso> inicioCurso, fimCurso;

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
				int codigoCurso = Keyboard
						.readInt("Entre com o codigo do curso:");
				Curso curso = busqueCurso(codigoCurso);
				if (curso == null) {
					System.out.println("Curso não existente.");
				} else {
					NoSimpEnc<Aluno> novoNo = new NoSimpEnc<Aluno>(new Aluno(
							numMat, nomeAluno, curso));
					if (inicioAluno == null)
						inicioAluno = fimAluno = novoNo;
					else {
						fimAluno.setProx(novoNo);
						fimAluno = novoNo;
					}
				}
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');
	}

	static Curso busqueCurso(int codigo) {
		NoSimpEnc<Curso> p = inicioCurso;
		while (p != null) {
			if (p.getObj().getCodigo() == codigo)
				return p.getObj();
			p = p.getProx();
		}
		return null;
	}

	static void incluirCurso() {
		char resp;
		do {
			Keyboard.clrscr();
			int codigo = Keyboard.readInt("Entrar com o codigo do curso: ");
			if (busqueCurso(codigo) != null)
				System.out.println("Curso ja existente");
			else {
				String nomeCurso = Keyboard
						.readString("Entrar com o nome do curso: ");
				NoSimpEnc<Curso> novoNo = new NoSimpEnc<Curso>(new Curso(
						codigo, nomeCurso));
				if (inicioCurso == null)
					inicioCurso = fimCurso = novoNo;
				else {
					fimCurso.setProx(novoNo);
					fimCurso = novoNo;
				}
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');
	}

	static void listarAlunos() {
		Keyboard.clrscr();
		System.out.println("NumMat  Nome do Aluno                   Curso");
		System.out
				.println("------  ------------------------------  ------------");
		NoSimpEnc<Aluno> p = inicioAluno;
		while (p != null) {
			System.out.printf("%6d  %-30s  %-12s\n", p.getObj().getnumMat(), p
					.getObj().getNomeAluno(), p.getObj().getCurso().getNome());
			p = p.getProx();
		}
		Keyboard.waitEnter();
	}

	static void listarCursos() {
		Keyboard.clrscr();
		System.out.println("Codigo  Nome do Curso");
		System.out.println("------  ------------------------------");
		NoSimpEnc<Curso> p = inicioCurso;
		while (p != null) {
			System.out.printf("%6d  %-30s\n", p.getObj().getCodigo(), p
					.getObj().getNome());
			p = p.getProx();
		}
		Keyboard.waitEnter();
	}

	static void listarAlunosPorCurso() {
		Keyboard.clrscr();
		int codigoCurso = Keyboard.readInt("Digite o codigo do curso:");
		if (busqueCurso(codigoCurso) == null) {
			System.out.println("Curso inexistente.");
			Keyboard.waitEnter();
			return;
		}
		Keyboard.clrscr();
		System.out.println("Codigo  Nome do Curso                   Aluno");
		System.out.println("------  ------------------------------  ------------");
		NoSimpEnc<Aluno> p = inicioAluno;
		while (p != null) {
			if (p.getObj().getCurso().getCodigo() == codigoCurso)
				System.out.printf("%6d  %-30s  %-12s\n",
						p.getObj().getCurso().getCodigo(), p.getObj().getCurso().getNome(), p
								.getObj().getNomeAluno());
			p = p.getProx();
		}
		Keyboard.waitEnter();
	}

	static void removerAluno() {
		Keyboard.clrscr();
		int numMat = Keyboard.readInt("Entrar com o número de matrícula:");
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

	static boolean cursoIsEmpty(int codigoCurso) {
		NoSimpEnc<Aluno> p = inicioAluno;
		while (p != null) {
			if (p.getObj().getCurso().getCodigo() == codigoCurso) {
				return true;
			} else {
				p = p.getProx();
			}
		}
		return false;
	}

	static void removerCurso() {
		Keyboard.clrscr();
		int codigoCurso = Keyboard.readInt("Entrar com o codigo do curso:");
		if (cursoIsEmpty(codigoCurso)) {
			System.out
					.println("Impossível remover curso com aluno matriculado.");
			Keyboard.waitEnter();
			return;
		}
		NoSimpEnc<Curso> p = inicioCurso;
		NoSimpEnc<Curso> pAnt = null;
		while (p != null) {
			if (codigoCurso == p.getObj().getCodigo()) {
				char resp = Keyboard.readChar("Deseja remover o curso "
						+ p.getObj().getNome() + " ?(s/n)");
				if (resp == 's') {
					if (pAnt == null) {
						inicioCurso = inicioCurso.getProx();
						if (inicioCurso == null)
							fimCurso = null;
					} else {
						if (p == fimCurso) {
							fimCurso = pAnt;
							pAnt.setProx(null);
						} else {
							pAnt.setProx(p.getProx());
						}
					}
					System.out.println("Curso removido.");
				}// if resp
				else {
					System.out.println("Curso não removido.");
				}
				Keyboard.waitEnter();
				return;
			} else {
				pAnt = p;
				p = p.getProx();
			}
		}
		System.out.println("Curso inexistente.");
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Incluir Aluno/Listar Alunos/Incluir Curso/Listar Cursos/Listar Alunos por curso/Remover Aluno/Remover Curso/Terminar");
			switch (opcao) {
			case 1:
				incluirAlunos();
				break;
			case 2:
				listarAlunos();
				break;
			case 3:
				incluirCurso();
				break;
			case 4:
				listarCursos();
				break;
			case 5:
				listarAlunosPorCurso();
				break;
			case 6:
				removerAluno();
				break;
			case 7:
				removerCurso();
				break;
			}
		} while (opcao < 8);
		System.out.println("\nFim do programa");
	}

}
