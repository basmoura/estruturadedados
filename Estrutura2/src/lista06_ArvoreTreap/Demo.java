package lista06_ArvoreTreap;

import java.util.Comparator;
import utilitarios.*;

public class Demo {

    static Treap<Aluno> alunos;
    static Treap<Disciplina> disciplinas;
    static Treap<Turma> turmas;
    static ObjectFile arqUniversidade = new ObjectFile("Universidade.dat");

    static class ComparaNomes implements Comparator<Aluno> {

        @Override
        public int compare(Aluno o1, Aluno o2) {
            return o1.getNome().compareToIgnoreCase(o2.getNome());
        }
    }

    static class ComparaDiscs implements Comparator<Disciplina> {

        @Override
        public int compare(Disciplina o1, Disciplina o2) {
            return o1.getNome().compareToIgnoreCase(o2.getNome());
        }
    }

    public static void main(String[] args) {
        lerArquivos();
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard
                    .menu("Incluir Aluno/Listar Alunos/Excluir Aluno/Incluir Disciplina/"
                    + "Listar Disciplinas/Excluir Disciplina/Incluir Turma/Listar Turmas/Excluir Turma/"
                    + "Matricular Aluno/Listar Alunos Matriculados/Sair");
            switch (opcao) {
                case 1:
                    incluirAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    excluirAluno();
                    break;
                case 4:
                    incluirDisciplina();
                    break;
                case 5:
                    listarDisciplinas();
                    break;
                case 6:
                    excluirDisciplina();
                    break;
                case 7:
                    incluirTurma();
                    break;
                case 8:
                    listarTurmas();
                    break;
                case 9:
                    excluirTurma();
                    break;
                case 10:
                    matricularAluno();
                    break;
                case 11:
                    listarAlunosMatriculados();
                    break;
            }
        } while (opcao < 12);
        gravarArquivos();
        Keyboard.clrscr();
        System.out.println("\nFim do programa.");
    }

    private static void matricularAluno() {
        char resp;
        Keyboard.clrscr();
        do {
            int matricula = Keyboard.readInt("Entrar com o numero de matr�cula: ");
            Aluno aluno = alunos.retrieve(new Aluno(matricula));
            if (aluno == null) {
                System.out.println("Aluno n�o encontrado.");
            } else {
                int codDisc = Keyboard.readInt("Entrar com o codigo da disciplina: ");
                if (!disciplinas.contains(new Disciplina(codDisc))) {
                    System.out.println("Disciplina n�o encontrada.");
                } else {
                    String codTurma = Keyboard.readString("Entrar com o codigo da turma: ");
                    Turma turma = turmas.retrieve(new Turma(codTurma, codDisc));
                    if (turma == null) {
                        System.out.println("Turma n�o encontrada.");
                    } else {
                        if (aluno.adicionarTurma(turma)) {
                            System.out.println("Aluno matriculado com sucesso.");
                        } else {
                            System.out.println("Aluno j� matriculado.");
                        }
                    }
                }
            }

            System.out.println();
            resp = Keyboard.readChar("Outra matricula (s/n)? ");
        } while (resp == 's');
    }

    private static void incluirAluno() {
        char resp;
        Keyboard.clrscr();
        do {
            int matricula = Keyboard.readInt("Entrar com o numero de matr�cula: ");
            Aluno aluno = alunos.retrieve(new Aluno(matricula));
            if (aluno != null) {
                System.out.println("Matricula j� existente.");
            } else {
                String nome = Keyboard.readString("Entrar com o nome: ");
                alunos.add(new Aluno(matricula, nome));
                System.out.println("Aluno cadastrado com sucesso.");
            }

            System.out.println();
            resp = Keyboard.readChar("Outra inclus�o (s/n)? ");
        } while (resp == 's');
    }

    private static void incluirTurma() {
        char resp;
        Keyboard.clrscr();
        do {
            int codDisciplina = Keyboard.readInt("Entrar com o codigo da disciplina: ");
            Disciplina disciplina = disciplinas.retrieve(new Disciplina(
                    codDisciplina));
            if (disciplina == null) {
                System.out.println("Disciplina n�o encontrada.");
            } else {
                String codTurma = Keyboard.readString("Informe o codigo da turma: ");
                Turma turma = turmas.retrieve(new Turma(codTurma, codDisciplina));
                if (turma != null) {
                    System.out.println("Turma j� cadastrada.");
                } else {
                    turmas.add(new Turma(codTurma, codDisciplina));
                    System.out.println("Turma cadastrada.");
                }
            }
            System.out.println();
            resp = Keyboard.readChar("Outra inclusao (s/n)? ");
        } while (resp == 's');
    }

    private static void incluirDisciplina() {
        char resp;
        Keyboard.clrscr();
        do {
            int codigo = Keyboard.readInt("Entrar com o codigo da disciplina: ");
            Disciplina disciplina = disciplinas.retrieve(new Disciplina(codigo));
            if (disciplina != null) {
                System.out.println("Disciplina j� cadastrada.");
            } else {
                String nome = Keyboard
                        .readString("Entrar com o nome da disciplina: ");
                disciplinas.add(new Disciplina(codigo, nome));
                System.out.println("Disciplina cadastrada.");
            }

            System.out.println();
            resp = Keyboard.readChar("Outra inclusao (s/n)? ");
        } while (resp == 's');
    }

    private static void excluirAluno() {
        char resp;
        Keyboard.clrscr();
        do {
            int matricula = Keyboard.readInt("Entrar com a matr�cula: ");
            if (alunos.remove(new Aluno(matricula))) {
                System.out.println("Aluno removido com sucesso.");
            } else {
                System.out.println("Aluno n�o encontrado.");
            }
            System.out.println();
            resp = Keyboard.readChar("Outra exclusao (s/n)? ");
        } while (resp == 's');
    }

    private static void excluirTurma() {
        char resp;
        Keyboard.clrscr();
        do {
            int codDisc = Keyboard.readInt("Informe o codigo da disciplina: ");
            String codTurma = Keyboard
                    .readString("Informe o codigo da turma: ");
            Turma turma = turmas.retrieve(new Turma(codTurma, codDisc));
            if (turma != null) {
                boolean excluir = true;
                MyIterator<Aluno> it = alunos.iterator();
                Aluno aluno = it.getFirst();
                while (aluno != null) {
                    if (aluno.temTurma(turma)) {
                        System.out.println("Nao e possivel remover uma turma com aluno matriculado.");
                        excluir = false;
                        break;
                    }
                    aluno = it.getNext();
                }

                if (excluir) {
                    turmas.remove(turma);
                    System.out.println("Exclusao efetuada.");
                }
            } else {
                System.out.println("Turma inexistente.");
            }
            System.out.println();
            resp = Keyboard.readChar("Outra exclusao (s/n)? ");
        } while (resp == 's');
    }

    private static void excluirDisciplina() {
        char resp;
        Keyboard.clrscr();
        do {
            int codigo = Keyboard.readInt("Informe o codigo: ");
            if (disciplinas.contains(new Disciplina(codigo))) {
                boolean excluir = true;
                MyIterator<Turma> it = turmas.iterator();
                Turma turma = it.getFirst();
                while (turma != null) {
                    if (turma.getCodigoDisciplina() == codigo) {
                        System.out.println("Nao e possivel remover uma disciplina associada a uma turma.");
                        excluir = false;
                        break;
                    }
                    turma = it.getNext();
                }
                if (excluir) {
                    if (disciplinas.remove(new Disciplina(codigo))) {
                        System.out.println("Disciplina Excluida.");
                    }
                }
            } else {
                System.out.println("Codigo invalido.");
            }
            System.out.println();
            resp = Keyboard.readChar("Outra exclusao (s/n)? ");
        } while (resp == 's');
    }

    private static void listarTurmas() {
        Keyboard.clrscr();
        System.out.println("Cod Disc  Nome da Disciplina              Turma");
        System.out.println("--------  ------------------------------  -----");
        Object[] discsTemp = disciplinas.sort(new ComparaDiscs());
        for (int i = 0; i < discsTemp.length; i++) {
            MyIterator<Turma> it = turmas.iterator();
            Turma turma = it.getFirst();
            while (turma != null) {
                if (turma.getCodigoDisciplina() == ((Disciplina) discsTemp[i]).getCodigo()) {
                    System.out.printf("%8d  %-30s  %5s\n", turma.getCodigoDisciplina(),
                            ((Disciplina) discsTemp[i]).getNome(), turma.getCodigoTurma());
                }
                turma = it.getNext();
            }
        }
        Keyboard.waitEnter();
    }

    private static void listarDisciplinas() {
        Keyboard.clrscr();
        System.out.println("Codigo  Nome");
        System.out.println("------  ------------------------------");
        MyIterator<Disciplina> it = disciplinas.iterator();
        Disciplina disciplina = it.getFirst();
        while (disciplina != null) {
            System.out.printf("%6d  %-30s\n", disciplina.getCodigo(),
                    disciplina.getNome());
            disciplina = it.getNext();
        }
        Keyboard.waitEnter();
    }

    private static void listarAlunos() {
        Keyboard.clrscr();
        System.out.println("Matricula  Nome");
        System.out.println("---------  ------------------------------");
        MyIterator<Aluno> it = alunos.iterator();
        Aluno aluno = it.getFirst();
        while (aluno != null) {
            System.out.printf("%9d  %-30s\n", aluno.getMatricula(),
                    aluno.getNome());
            aluno = it.getNext();
        }
        Keyboard.waitEnter();
    }

    private static void listarAlunosMatriculados() {
        Keyboard.clrscr();
        int codDisc = Keyboard.readInt("Informe o codigo da disciplina: ");
        String codTurma = Keyboard.readString("Informe o codigo da turma: ");
        Turma turma = turmas.retrieve(new Turma(codTurma, codDisc));
        if (turma != null) {
            Keyboard.clrscr();
            System.out.println("Cod Disc: "
                    + turma.getCodigoDisciplina()
                    + " Cod da Turma: "
                    + turma.getCodigoTurma()
                    + "Nome da Disc: "
                    + disciplinas.retrieve(new Disciplina(turma.getCodigoDisciplina())).getNome());
            System.out.println();
            System.out.println("Matricula  Nome do aluno");
            System.out.println("---------  ------------------------------");
            Object[] alunosTemp = alunos.sort(new ComparaNomes());
            for (int i = 0; i < alunosTemp.length; i++) {
                if (((Aluno) alunosTemp[i]).temTurma(turma)) {
                    System.out.printf("%9d  %-30s\n", ((Aluno) alunosTemp[i]).getMatricula(),
                            ((Aluno) alunosTemp[i]).getNome());
                }
            }
        } else {
            System.out.println("Turma inexistente.");
        }
        Keyboard.waitEnter();
    }

    private static void gravarArquivos() {
        arqUniversidade.rewrite();
        arqUniversidade.write(alunos);
        arqUniversidade.write(disciplinas);
        arqUniversidade.write(turmas);
        arqUniversidade.closeFile();
    }

    @SuppressWarnings({"unchecked"})
    private static void lerArquivos() {
        if (arqUniversidade.reset()) {
            alunos = (Treap<Aluno>) arqUniversidade.read();
            disciplinas = (Treap<Disciplina>) arqUniversidade.read();
            turmas = (Treap<Turma>) arqUniversidade.read();
            arqUniversidade.closeFile();
        } else {
            alunos = new Treap<>(true);
            disciplinas = new Treap<>(true);
            turmas = new Treap<>(true);
        }
    }
}
