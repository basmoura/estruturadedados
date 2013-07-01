package DemoListaDupEncadeada;

import Biblioteca.Keyboard;

public class DemoListaDupEncadeada {

    static NoDupEnc<Aluno> noCabeca = new NoDupEnc<>();

    static void remover() {
        Keyboard.clrscr();

        int numMat = Keyboard.readInt("Informe o numero de matricula: ");

        NoDupEnc<Aluno> p = noCabeca.getProx();

        while (p != noCabeca) {
            if (p.getObj().getnumMat() == numMat) {

                p.remove();
                break;
            }
            p = p.getProx();
        }
    }

    static void incluirAlunos() {
        char resp;
        do {
            Keyboard.clrscr();

            int numMat = Keyboard.readInt("Informe o numero  de matricula: ");
            String nome = Keyboard.readString("Informe o nome do aluno: ");
            Aluno aluno = new Aluno(numMat, nome);
            
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
            System.out.printf("%4d    %-20s\n", p.getObj().getnumMat(),
                    p.getObj().getNomeAluno());
            p = p.getProx();
        }

        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Incluir/Listar/Terminar");
            switch (opcao) {
                case 1:
                    incluirAlunos();
                    break;
                case 2:
                    listarAlunos();
                    break;
            }
        } while (opcao < 3);
        System.out.println("\nFim do programa");
    }
}
