package Prova_Questa01;

import Biblioteca.Keyboard;
import Biblioteca.NoDupEnc;

public class DemoListaEncadeada {

    static NoDupEnc<Aluno> noCabeca = new NoDupEnc<>();

    static boolean busque(int numMat) {
        NoDupEnc<Aluno> p = noCabeca.getProx();
        while (p != noCabeca) {
            if (p.getObj().getnumMat() == numMat) {
                return true;
            }
            p = p.getProx();
        }
        return false;
    }

    static void incluirAlunos() {
        char resp;
        do {
            Keyboard.clrscr();
            int numMat = Keyboard.readInt("Entrar com o numero de matricula: ");
            if (busque(numMat)) {
                System.out.println("Matricula ja existente");
            } else {
                String nomeAluno = Keyboard.readString("Entrar com o nome: ");
                Aluno aluno = new Aluno(numMat, nomeAluno);

                NoDupEnc<Aluno> p = noCabeca.getProx();
                
                if (p == noCabeca) {
                    new NoDupEnc<Aluno>(aluno, noCabeca, noCabeca.getProx());
                } else {
                    while (p != noCabeca) {
                        if (aluno.compareTo(p.getObj()) == 1 || aluno.compareTo(p.getObj()) == 0) {
                            if (p.getProx() == noCabeca) {
                                new NoDupEnc<Aluno>(aluno, noCabeca, noCabeca.getProx());
                            } else {
                                new NoDupEnc<>(aluno, p, p.getProx());
                            }
                        } else {
                                new NoDupEnc(aluno, noCabeca.getAnt(), noCabeca);
                            }
                        p = p.getProx();
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
        NoDupEnc<Aluno> p = noCabeca.getProx();
        while (p != noCabeca) {
            System.out.printf("%6d  %-30s\n", p.getObj().getnumMat(), p.getObj()
                    .getNomeAluno());
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
