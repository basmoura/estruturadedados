package DemoHashEnc;

import Biblioteca.Keyboard;
import Biblioteca.ListaEncNaoOrd;
import Biblioteca.MyIterator;

public class DemoHashEnc {

    static ListaEncNaoOrd<Aluno>[] tabela;
    static int numItens;

    // Testa se x eh primo
    static boolean isPrime(int x) {
        int divisor;
        if (x < 4) {
            return true;
        }

        if ((x % 2) == 0) {
            return false;
        }

        divisor = 3;

        while (!((divisor * divisor > x) || (x % divisor == 0))) {
            divisor = divisor + 2;
        }

        if (divisor * divisor > x) {
            return true;
        } else {
            return false;
        }
    }

    // Retorna o proximo numero primo maior do que x
    static int nextPrime(int x) {
        if (x < 2) {
            return 3;
        }

        if (x % 2 == 0) {
            x++;
        } else {
            x = x + 2;
        }

        while (!isPrime(x)) {
            x = x + 2;
        }

        return x;

    }

    static void criarTabela(int tamTabela) {
        if (!isPrime(tamTabela)) {
            tamTabela = nextPrime(tamTabela);
        }

        tabela = new ListaEncNaoOrd[tamTabela];

        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new ListaEncNaoOrd<>();
        }
    }

    static int funcHash(Aluno aluno) {
        return aluno.hashCode() % tabela.length;
    }

    static boolean add(Aluno aluno) {
        int posicao = funcHash(aluno);

        if (tabela[posicao].contains(aluno)) {
            return false;
        }

        tabela[posicao].add(aluno);
        numItens++;
        return true;
    }

    static boolean remove(int numMat) {
        Aluno aluno = new Aluno(numMat);

        int posicao = funcHash(aluno);

        if (tabela[posicao].contains(aluno)) {
            tabela[posicao].remove(aluno);
            numItens--;
            return true;
        }

        return false;
    }

    static void listarAlunos() {
        Keyboard.clrscr();
        System.out.println("NumMat  Nome do Aluno");
        System.out.println("------  --------------------------");

        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i].isEmpty()) {
                continue;
            }
            MyIterator<Aluno> it = tabela[i].iterator();
            Aluno aluno = it.getFirst();

            while (aluno != null) {
                System.out.printf("%4d  %-20s\n", aluno.getNumMat(),
                        aluno.getNomeAluno());
                aluno = it.getNext();
            }
        }

        Keyboard.waitEnter();
    }

    static void incluirAlunos() {
        char resp;
        Keyboard.clrscr();
        do {
            int numMat = Keyboard.readInt("Entrar com o numero de natricula: ");
            String nomeAluno = Keyboard.readString("Entrar com o nome do aluno: ");
            if (!add(new Aluno(numMat, nomeAluno))) {
                System.out.println("Matricula ja existente");
            }
            resp = Keyboard.readChar("Outra inclusao(s/n)? ");
        } while (resp == 's');
    }

    static void excluirAluno() {
        Keyboard.clrscr();
        int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
        char resp = Keyboard.readChar("Deseja remover? ");
        if (resp == 's') {
            if (!remove(numMat)) {
                System.out.println("Matricula inexistente");
            } else {
                System.out.println("Aluno remnovido");
            }
        } else {
            System.out.println("Aluno nao removido");
        }
        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        int opcao;
        int tamTabela = Keyboard.readInt("Entrar com o tamanho da tabela: ");
        criarTabela(tamTabela);
        do {
            Keyboard.clrscr();
            opcao = Keyboard
                    .menu("Incluir alunos/Excluir aluno/Listar alunos/Terminar");
            switch (opcao) {
                case 1:
                    incluirAlunos();
                    break;
                case 2:
                    excluirAluno();
                    break;
                case 3:
                    listarAlunos();
                    break;
            }
        } while (opcao < 4);

        System.out.println("\nFim do programa");

    }
}
