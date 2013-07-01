/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista12;

import Biblioteca.HashEnc;
import Biblioteca.Keyboard;
import Biblioteca.MyIterator;

/**
 *
 * @author basmoura
 */
public class Demo {

    static HashEnc<Aluno> tabela = new HashEnc<>(10);

    static void add() {
        char resp = '`';

        do {
            int cod = Keyboard.readInt("Matricula: ");
            String nome = Keyboard.readString("Nome: ");

            Aluno aluno = new Aluno(cod, nome);

            tabela.add(aluno);
        } while (resp == 's');
    }

    static void listar() {
        Keyboard.clrscr();

        MyIterator<Aluno> it = tabela.iterator();
        Aluno aluno = it.getFirst();

        while (aluno != null) {
            System.out.println(aluno.getNomeAluno());
            aluno = it.getNext();
        }

        Keyboard.waitEnter();
    }

    static void remover() {
        Keyboard.clrscr();

        int codigo = Keyboard.readInt("Codigo: ");

        Aluno aluno = new Aluno(codigo);
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

    public static void main(String[] args) {
        int menu;
        do {
            Keyboard.clrscr();
            menu = Keyboard.menu("add/listar/remover");

            switch (menu) {
                case 1:
                    add();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    remover();
                    break;

            }

        } while (menu < 4);
    }
}
