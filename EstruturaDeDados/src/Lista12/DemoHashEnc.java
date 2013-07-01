package Lista12;

import Biblioteca.HashEnc;
import Biblioteca.Keyboard;
import Biblioteca.MyIterator;
import java.util.Comparator;

public class DemoHashEnc {

    static HashEnc<Aluno> tabela;

    static class CompareNome implements Comparator<Aluno> {

        @Override
        public int compare(Aluno o1, Aluno o2) {
            return o1.getNomeAluno().compareToIgnoreCase(o2.getNomeAluno());
        }
    }

    static void incluir() {
        char resp;
        do {
            Keyboard.clrscr();

            int numMat = Keyboard.readInt("Informe a matrícula: ");

            Aluno aluno = new Aluno(numMat);

            if (tabela.contains(aluno)) {
                System.out.println("Aluno já cadastrado");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");
                aluno.setNomeAluno(nome);

                tabela.add(aluno);
            }

            resp = Keyboard.readChar("Outro aluno? (s/n): ");
        } while (resp == 's');
    }

    static void excluir() {
        Keyboard.clrscr();

        int numMat = Keyboard.readInt("Codigo: ");

        Aluno aluno = new Aluno(numMat);
        if (!tabela.contains(aluno)) {
            System.out.println("Aluno não cadastrado");
        } else {
            char resp = Keyboard.readChar("Deseja remover? (s/n): ");

            if (resp == 's') {
                tabela.remove(aluno);
                System.out.println("Aluno removido");
            } else {
                System.out.println("Aluno não removido");
            }
        }
        Keyboard.waitEnter();
    }

    static void listar() {
        MyIterator<Aluno> it = tabela.iterator();
        Aluno aluno = it.getFirst();

        while (aluno != null) {
            System.out.println(aluno.getNumMat() + " " + aluno.getNomeAluno());
            aluno = it.getNext();
        }

        Keyboard.waitEnter();
    }

    static void ordemAlfabetica() {
        Object[] sort = tabela.sort(new CompareNome());

        for (int i = 0; i < sort.length; i++) {
            Aluno a = (Aluno) sort[i];
            System.out.println(a.getNumMat() + " " + a.getNomeAluno());
        }

        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        int opcao;
        int tamTabela = Keyboard.readInt("Entrar com o tamanho da tabela: ");
        tabela = new HashEnc<>(tamTabela);
        do {
            Keyboard.clrscr();
            opcao = Keyboard
                    .menu("Incluir alunos/Excluir aluno/Listar alunos/Ordem Alfabetica/Terminar");
            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    excluir();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    ordemAlfabetica();
                    break;
            }
        } while (opcao < 5);
        System.out.println("\nFim do programa");
    }
}
