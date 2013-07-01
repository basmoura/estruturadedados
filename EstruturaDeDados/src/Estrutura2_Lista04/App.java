/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_Lista04;

import Biblioteca.Keyboard;
import Biblioteca.ListaEncOrd;
import Biblioteca.MyIterator;
import Estrutura2_Lista05.ArvoreAVL;
import java.util.Comparator;

/**
 *
 * @author basmoura
 */
public class App {

    private static ArvoreAVL<Aluno> alunos = new ArvoreAVL<>();
    private static ArvoreAVL<Disciplina> disciplinas = new ArvoreAVL<>();
    private static ArvoreAVL<Turma> turmas = new ArvoreAVL<>();

    static class CompareNome implements Comparator<Aluno> {

        @Override
        public int compare(Aluno o1, Aluno o2) {
            return o1.getNmAluno().compareToIgnoreCase(o2.getNmAluno());
        }
    }

    static class CompareDisciplina implements Comparator<Disciplina> {

        @Override
        public int compare(Disciplina o1, Disciplina o2) {
            return o1.getNmDisc().compareToIgnoreCase(o2.getNmDisc());
        }
    }

    static void incluirAluno() {
        char resp;

        do {
            Keyboard.clrscr();

            int numMat = Keyboard.readInt("Informe o número de matrícula: ");

            Aluno aluno = new Aluno(numMat);

            if (!alunos.add(aluno)) {
                System.err.println("Aluno já cadastrado\n");
            } else {
                String nmAluno = Keyboard.readString("Informe o nome do aluno: ");

                aluno.setNmAluno(nmAluno);

                alunos.add(aluno);
            }

            resp = Keyboard.readChar("Outro aluno? (s/n): ");
        } while (resp == 's');

    }

    static void listarAlunos() {
        Keyboard.clrscr();

        System.out.println("NumMat  Nome");
        System.out.println("------  --------------------");

        MyIterator<Aluno> it = alunos.iterator();
        Aluno aluno = it.getFirst();

        while (aluno != null) {
            System.out.printf("%6d  %-20s\n", aluno.getNumMat(),
                    aluno.getNmAluno());
            aluno = it.getNext();
        }

        Keyboard.waitEnter();
    }

    static void excluirAlunos() {
        char resp;

        do {
            Keyboard.clrscr();

            int numMat = Keyboard.readInt("Informe o número de matrícula: ");

            Aluno aluno = alunos.retrieve(new Aluno(numMat));

            if (aluno == null) {
                System.err.println("Aluno não cadastrado");
            } else {
                char rem;

                System.out.println("NumMat  Nome");
                System.out.println("------  ------------------");
                System.out.printf("%6d  %-20s\n", aluno.getNumMat(),
                        aluno.getNmAluno());

                rem = Keyboard.readChar("Deseja remover? (s/n): ");

                if (rem == 's') {
                    alunos.remove(aluno);
                    System.err.println("Aluno removido com sucesso");
                } else {
                    System.err.println("Aluno não removido");
                }
            }

            resp = Keyboard.readChar("Outro aluno? (s/n): ");

        } while (resp == 's');

    }

    static void incluirDisciplina() {
        char resp;

        do {
            Keyboard.clrscr();

            int codDisc = Keyboard.readInt("Informe o código da disciplina: ");

            Disciplina disciplina = new Disciplina(codDisc);

            if (!disciplinas.add(disciplina)) {
                System.err.println("Disciplina já cadastrada\n");
            } else {
                String nmDisc = Keyboard.readString("Infome o nome da "
                        + "disciplina: ");

                disciplina.setNmDisc(nmDisc);

                disciplinas.add(disciplina);
            }

            resp = Keyboard.readChar("Outra disciplina? (s/n): ");
        } while (resp == 's');
    }

    static void listarDisciplinas() {
        Keyboard.clrscr();

        MyIterator<Disciplina> it = disciplinas.iterator();
        Disciplina disciplina = it.getFirst();

        System.out.println("CodDisc  Nome");
        System.out.println("-------  --------------------");

        while (disciplina != null) {
            System.out.printf("%7d  %-20s", disciplina.getCodDisc(),
                    disciplina.getNmDisc());

            disciplina = it.getNext();
        }

        Keyboard.waitEnter();
    }

    static void excluirDisciplinas() {
        char resp;

        do {
            Keyboard.clrscr();

            int codDisc = Keyboard.readInt("Informe o código da disciplina: ");

            Disciplina disciplina = disciplinas.retrieve(new Disciplina(codDisc));

            if (disciplina == null) {
                System.err.println("Disciplina não cadastrada");
            } else {
                char rem;

                System.out.println("CodDis  Nome");
                System.out.println("------  ------------------");
                System.out.printf("%6d  %-20s\n", disciplina.getCodDisc(),
                        disciplina.getNmDisc());

                rem = Keyboard.readChar("Deseja remover? (s/n): ");

                if (rem == 's') {
                    disciplinas.remove(disciplina);
                    System.err.println("Disciplina removida com sucesso");
                } else {
                    System.err.println("Disciplina não removido");
                }
            }

            resp = Keyboard.readChar("Outra disciplina? (s/n): ");

        } while (resp == 's');

    }

    static void incluirTurma() {
        char resp;

        do {
            Keyboard.clrscr();

            int codDisc = Keyboard.readInt("Informe o código da disciplina: ");

            Disciplina disciplina = disciplinas.retrieve(new Disciplina(codDisc));

            if (disciplina == null) {
                System.err.println("Disciplina não cadastrada");
            } else {
                String codTurma = Keyboard.readString("Informe o código da turma: ");

                Turma turma = new Turma(codDisc, codTurma);

                if (!turmas.add(turma)) {
                    System.err.println("Turma já cadastrada nessa disciplina");
                } else {
                    turmas.add(turma);
                }
            }

            resp = Keyboard.readChar("Outra turma? (s/n): ");
        } while (resp == 's');
    }

    static void listarTurmas() {
        Keyboard.clrscr();

        ListaEncOrd<Disciplina> listaDisciplinas = new ListaEncOrd<>();

        MyIterator<Turma> it = turmas.iterator();
        Turma turma = it.getFirst();

        System.out.println("Cod Disc  Nome da Disciplina      Turma");
        System.out.println("--------  ----------------------  -----");

        while (turma != null) {
            Disciplina disciplina = disciplinas.retrieve(new Disciplina(turma.getCodDisc()));

            System.out.printf("%8d  %-22s %5s\n", turma.getCodDisc(), disciplina.getNmDisc(),
                    turma.getCodTurma());

            turma = it.getNext();
        }

        Keyboard.waitEnter();
    }

    static void excluirTurmas() {
        char resp;

        do {
            Keyboard.clrscr();

            int codDisc = Keyboard.readInt("Informe o código da disciplina: ");

            Disciplina disciplina = disciplinas.retrieve(new Disciplina(codDisc));

            if (disciplina == null) {
                System.err.println("Disciplina não cadastrada");
            } else {

                String codTurma = Keyboard.readString("Informe o código da turma: ");

                Turma turma = turmas.retrieve(new Turma(codDisc, codTurma));

                if (turma == null) {
                    System.err.println("Turma não cadastrada");
                } else {
                    char rem;

                    System.out.println("CodDis  CodTurma  Nome");
                    System.out.println("------  --------  ------------------");
                    System.out.printf("%6d  %8s  %-20s\n", disciplina.getCodDisc(),
                            turma.getCodTurma(), disciplina.getNmDisc());

                    rem = Keyboard.readChar("Deseja remover? (s/n): ");

                    if (rem == 's') {
                        turmas.remove(turma);
                        System.err.println("Turma removida com sucesso");
                    } else {
                        System.err.println("Turma não removido");
                    }
                }
            }
            resp = Keyboard.readChar("Outra turma? (s/n): ");

        } while (resp == 's');

    }

    static void matricularAluno() {
        char resp;

        do {
            Keyboard.clrscr();

            int numMat = Keyboard.readInt("Informe o número de matrícula: ");

            Aluno aluno = alunos.retrieve(new Aluno(numMat));

            if (aluno == null) {
                System.err.println("Aluno não cadastrado");
            } else {
                int codDisc = Keyboard.readInt("Informe o código da disciplina: ");

                Disciplina disciplina = disciplinas.retrieve(new Disciplina(codDisc));

                if (disciplina == null) {
                    System.err.println("Disciplina não cadastrada");
                } else {
                    String codTurma = Keyboard.readString("Informe o código da turma: ");

                    Turma turma = turmas.retrieve(new Turma(codDisc, codTurma));

                    if (turma == null) {
                        System.err.println("Turma não cadastrada");
                    } else {

                        MyIterator<Turma> it = aluno.getTurmas().iterator();
                        Turma turmaAluno = it.getFirst();

                        while (turmaAluno != null) {
                            if (turmaAluno.getCodDisc() == codDisc
                                    && turmaAluno.getCodTurma().equalsIgnoreCase(codTurma)) {
                                System.err.println("Este aluno já está matriculado"
                                        + "nesta turma");
                                break;
                            }

                            turmaAluno = it.getNext();
                        }

                        aluno.getTurmas().add(turma);
                    }
                }
            }

            resp = Keyboard.readChar("Deseja matricular outro aluno? (s/n): ");
        } while (resp == 's');
    }

    static void listarAlunosMatriculados() {
        Keyboard.clrscr();

        int codDisc = Keyboard.readInt("Informe o código da disciplina: ");

        Disciplina disciplina = disciplinas.retrieve(new Disciplina(codDisc));

        if (disciplina == null) {
            System.err.println("Disciplina não cadastrada");
        } else {
            String codTurma = Keyboard.readString("Informe o código da turma: ");

            Turma turma = turmas.retrieve(new Turma(codDisc, codTurma));

            if (turma == null) {
                System.err.println("Turma não cadastrada");
            } else {
                System.out.println("Cod Disc: " + disciplina.getCodDisc()
                        + " Cod da Turma: " + turma.getCodTurma()
                        + " Nome da Disc: " + disciplina.getNmDisc());

                System.out.println("NumMat  Nome do Aluno");
                System.out.println("------  --------------------------");

                ListaEncOrd<Aluno> listaAlunos = new ListaEncOrd<>();

                MyIterator<Aluno> it = alunos.iterator();
                Aluno aluno = it.getFirst();

                while (aluno != null) {
                    MyIterator<Turma> tIt = aluno.getTurmas().iterator();
                    Turma turmaIt = tIt.getFirst();

                    while (turmaIt != null) {

                        if (turmaIt.getCodDisc() == codDisc
                                && turmaIt.getCodTurma().equalsIgnoreCase(codTurma)) {

                            listaAlunos.add(aluno);
                        }
                        turmaIt = tIt.getNext();
                    }
                    aluno = it.getNext();
                }

                Object[] vetorAlunos = listaAlunos.sort(new CompareNome());

                for (int i = 0; i < vetorAlunos.length; i++) {
                    Aluno alunoOrd = (Aluno) vetorAlunos[i];

                    System.out.printf("%6d  %-30s\n", alunoOrd.getNumMat(),
                            alunoOrd.getNmAluno());
                }
            }
        }

        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Incluir Aluno/Listar Alunos/Excluir Alunos"
                    + "/Incluir Disciplina/Listar Disciplinas/Excluir Disciplinas"
                    + "/Incluir Turmas/Listar Turmas/Excluir Turmas/"
                    + "Matricular Aluno/Listar alunos matriculados/Terminar");

            switch (opcao) {
                case 1:
                    incluirAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    excluirAlunos();
                    break;
                case 4:
                    incluirDisciplina();
                    break;
                case 5:
                    listarDisciplinas();
                    break;
                case 6:
                    excluirDisciplinas();
                    break;
                case 7:
                    incluirTurma();
                    break;
                case 8:
                    listarTurmas();
                    break;
                case 9:
                    excluirTurmas();
                    break;
                case 10:
                    matricularAluno();
                    break;
                case 11:
                    listarAlunosMatriculados();
                    break;
            }

        } while (opcao < 12);
        System.out.println("\nFim do programa");
    }
}
