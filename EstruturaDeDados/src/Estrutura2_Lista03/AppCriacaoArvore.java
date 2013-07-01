package Estrutura2_Lista03;

import Biblioteca.ArvoreBin;
import Biblioteca.NoArvoreBin;
import Biblioteca.Keyboard;
import Biblioteca.MyIterator;

public class AppCriacaoArvore {

    static ArvoreBin<Integer> arvore = new ArvoreBin<>();

    static void inserir() {
        // Para inserir um no na arvore devera ser solicitado o valor que sera
        // armazenado no no, o valor do no que sera o pai do novo no
        // e a direcao em que o no sera inserido
        char resp;
        do {
            Keyboard.clrscr();

            Integer obj = Keyboard.readInt("Informe o valor: ");

            if (arvore.getRaiz() == null) {
                arvore.insert(obj, null, 1);
            } else {
                NoArvoreBin<Integer> pai = arvore.getNo(Keyboard.readInt("Informe o nó pai: "));

                int dir = Keyboard.readInt("Informe a direção: ");

                NoArvoreBin<Integer> no = arvore.insert(obj, pai, dir);

                if (no == null) {
                    System.out.println("Filho já existente");
                }
            }

            resp = Keyboard.readChar("Outro nó? (s/n): ");
        } while (resp == 's');
    }

    static void remover() {
        // Devera ser solicitado o valor armazenado no no que sera removido
        char resp;

        do {
            Integer obj = Keyboard.readInt("Informe o valor:");

            NoArvoreBin<Integer> no = arvore.getNo(obj);
            if (no != null) {
                char remNo = Keyboard.readChar("Deseja remover o nó? (s/n): ");

                if (remNo == 's') {
                    arvore.delete(no);
                    System.out.println("Número removido");
                } else {
                    System.out.println("Número não removido");
                }
            }

            resp = Keyboard.readChar("Outro nó? (s/n): ");
        } while (resp == 's');
    }

    static void listar() {
        // Listar os dados armazenados na arvore usando o iterator

        MyIterator<Integer> it = arvore.iterator();
        Integer no = it.getFirst();
        while (no != null) {
            System.out.println(no.intValue());
            no = it.getNext();
        }
        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Inserir/Remover/Listar/Desenhar/Terminar");
            switch (opcao) {
                case 1:
                    inserir();
                    break;
                case 2:
                    remover();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    Keyboard.clrscr();
                    arvore.desenhe(80);
                    Keyboard.waitEnter();
                    break;

            }
        } while (opcao < 5);
        System.out.println("\nFim do programa");

    }
}
