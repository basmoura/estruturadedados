package Lista11;

import Biblioteca.FilaArray;
import Biblioteca.Keyboard;
import Biblioteca.MyIterator;

public class Demo {

    static FilaArray filaAlunos = new FilaArray(5, 5);

    public static void main(String[] args) {
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard
                    .menu("Incluir/Remover/Remover na Posicao/Listar/Terminar");
            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    removerPrimeiro();
                    break;
                case 3:
                    removerPosicao();
                    break;
                case 4:
                    listar();
                    break;
            }

        } while (opcao < 5);
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
        if (filaAlunos.isEmpty()) {
            System.out.println("Fila vazia");
        } else {
            Aluno aluno = (Aluno) filaAlunos.remova();
            System.out.println("Aluno removido da fila");
            System.out.println("Num Mat: " + aluno.getNumMat());
            System.out.println("Nome do aluno: " + aluno.getNome());
        }
        Keyboard.waitEnter();
    }

    private static void removerPosicao() {
        int numMat = Keyboard.readInt("Informa a matricula: ");

        MyIterator<Aluno> it = filaAlunos.iterator();
        Aluno aluno = it.getFirst();

        while (aluno != null) {
            if (aluno.getNumMat() == numMat) {
                it.remove();
                break;
            }
            aluno = it.getNext();
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
            
            if (!filaAlunos.insira(aluno)){
                System.out.println("Fila está cheia");
            } 
           
            resp = Keyboard.readChar("Outro aluno(s/n)?: ");

        } while (resp == 's');
        System.out.println("\nFim do programa");
    }
}
