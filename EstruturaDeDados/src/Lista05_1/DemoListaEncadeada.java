package Lista05_1;

import Biblioteca.Sort;
import Biblioteca.Keyboard;
import Biblioteca.NoSimpEnc;
import java.util.Comparator;

public class DemoListaEncadeada {

    static NoSimpEnc<Aluno> inicioAluno, fimAluno;
    static NoSimpEnc<Curso> inicioCurso, fimCurso;
    static int totalAlunos;

    static boolean busque(int numMat) {
        NoSimpEnc<Aluno> p = inicioAluno;
        while (p != null) {
            if (p.getObj().getnumMat() == numMat) {
                return true;
            }
            p = p.getProx();
        }
        return false;
    }

    static Curso buscarCurso(int codCurso) {
        NoSimpEnc<Curso> p = inicioCurso;
        while (p != null) {
            if (p.getObj().getCodCurso() == codCurso) {
                return p.getObj();
            }
            p = p.getProx();
        }
        return null;
    }

    static class CompararAluno implements Comparator<Aluno> {

        @Override
        public int compare(Aluno o1, Aluno o2) {
            return o1.getNomeAluno().compareToIgnoreCase(o2.getNomeAluno());
        }
    }

    static void incluirAlunos() {
        char resp;
        do {
            Keyboard.clrscr();
            int codCurso = Keyboard.readInt("Informe o código do curso: ");

            Curso curso = buscarCurso(codCurso);

            if (curso == null) {
                System.out.println("Curso inexistente");
            } else {
                int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
                if (busque(numMat)) {
                    System.out.println("Matricula ja existente");
                } else {
                    String nomeAluno = Keyboard.readString("Entrar com o nome: ");
                    NoSimpEnc<Aluno> novoNo = new NoSimpEnc<>(new Aluno(numMat,
                            nomeAluno));

                    novoNo.getObj().setCurso(curso);

                    if (inicioAluno == null) {
                        inicioAluno = fimAluno = novoNo;
                        totalAlunos++;
                    } else {
                        fimAluno.setProx(novoNo);
                        fimAluno = novoNo;
                        totalAlunos++;
                    }
                }
            }
            resp = Keyboard.readChar("Outra inclusao (s/n)? ");
        } while (resp == 's');
    }

    static void listarAlunos() {
        Keyboard.clrscr();
        System.out.println("NumMat  Nome do Aluno                            Curso");
        System.out.println("------  ------------------------------  --------------");
        NoSimpEnc<Aluno> p = inicioAluno;

        Aluno[] alunos = new Aluno[totalAlunos];

        for (int i = 0; i < alunos.length; i++) {
            alunos[i] = p.getObj();
            p = p.getProx();
        }

        Sort.quickSort(alunos, new CompararAluno());

        for (int i = 0; i < alunos.length; i++) {
            System.out.printf("%6d  %-30s  %14s\n", alunos[i].getnumMat(),
                    alunos[i].getNomeAluno(), alunos[i].getCurso().getNomeCurso());
        }

        Keyboard.waitEnter();
    }

    static void incluirCurso() {
        char resp;
        do {
            Keyboard.clrscr();
            int codCurso = Keyboard.readInt("Entrar com o código do curso: ");
            if (buscarCurso(codCurso) != null) {
                System.out.println("Curso ja existente");
            } else {
                String nomeCurso = Keyboard.readString("Entrar com o nome: ");
                NoSimpEnc<Curso> novoNo = new NoSimpEnc<>(new Curso(codCurso,
                        nomeCurso));
                if (inicioCurso == null) {
                    inicioCurso = fimCurso = novoNo;
                } else {
                    fimCurso.setProx(novoNo);
                    fimCurso = novoNo;
                }
            }
            resp = Keyboard.readChar("Outra inclusao (s/n)? ");
        } while (resp == 's');
    }

    static void listarCurso() {
        Keyboard.clrscr();
        System.out.println("CodCur  Nome do Curso");
        System.out.println("------  ------------------------------");
        NoSimpEnc<Curso> p = inicioCurso;
        while (p != null) {
            System.out.printf("%6d  %-30s\n", p.getObj().getCodCurso(), p.getObj()
                    .getNomeCurso());
            p = p.getProx();
        }
        Keyboard.waitEnter();
    }

    static void listarAlunosMatriculados() {
        Keyboard.clrscr();
        int codCurso = Keyboard.readInt("Informe o código do curso: ");

        Curso curso = buscarCurso(codCurso);

        if (curso == null) {
            System.out.println("Curso inexistente");
        } else {
            System.out.printf("\n\nCurso: %s\n\n", curso.getNomeCurso());
            System.out.println("NumMat  Nome do Aluno");
            System.out.println("------  ------------------------------");

            NoSimpEnc<Aluno> p = inicioAluno;

            while (p != null) {
                if (p.getObj().getCurso().getCodCurso() == codCurso) {
                    System.out.printf("%6d  %-30s\n", p.getObj().getnumMat(), p.getObj()
                            .getNomeAluno());
                }
                p = p.getProx();
            }


        }
        Keyboard.waitEnter();
    }

    public static void removerAluno() {
        Keyboard.clrscr();

        int numMat = Keyboard.readInt("Informe a matrícula: ");

        NoSimpEnc<Aluno> p = inicioAluno;
        NoSimpEnc<Aluno> pAnt = null;

        while (p != null) {
            if (p.getObj().getnumMat() == numMat) {
                if (p == inicioAluno) {
                    inicioAluno = inicioAluno.getProx();
                    if (inicioAluno == null) {
                        fimAluno = null;
                    }
                    totalAlunos--;
                } else {
                    if (p == fimAluno) {
                        fimAluno = pAnt;
                    }
                    pAnt.setProx(p.getProx());
                    totalAlunos--;
                }
                System.out.println("Aluno removido");
            }
            pAnt = p;
            p = p.getProx();
        }
    }

    public static void main(String[] args) {
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Incluir Aluno/Listar Aluno/"
                    + "Incluir Curso/Listar Curso/"
                    + "Listar Alunos Matriculados/"
                    + "Remover/Terminar");
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
                    listarCurso();
                    break;
                case 5:
                    listarAlunosMatriculados();
                    break;
                case 6:
                    removerAluno();
                    break;
            }
        } while (opcao < 7);
        System.out.println("\nFim do programa");
    }
}
