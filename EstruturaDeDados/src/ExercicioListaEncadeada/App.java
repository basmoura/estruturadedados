/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExercicioListaEncadeada;

import Biblioteca.Keyboard;
import Biblioteca.NoSimpEnc;

/**
 *
 * @author basmoura
 */
public class App {

    static NoSimpEnc<Pessoa> inicio, fim;

    static boolean buscarPessoa(int codPessoa) {
        NoSimpEnc<Pessoa> p = inicio;
        while (p != null) {
            if (p.getObj().getCodPessoa() == codPessoa) {
                return true;
            }
            p = p.getProx();
        }
        return false;
    }

    static void inserirPessoas() {
        char resp;
        do {
            Keyboard.clrscr();

            int codPessoa = Keyboard.readInt("Informe o código: ");

            if (buscarPessoa(codPessoa)) {
                System.out.println("Pessoa já cadastrada");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");
                String data = Keyboard.readData("Informe a data de nascimento: ");

                NoSimpEnc<Pessoa> novoNo = new NoSimpEnc(new Pessoa(codPessoa, nome, data));

                if (inicio == null) {
                    inicio = fim = novoNo;
                } else {
                    fim.setProx(novoNo);
                    fim = novoNo;
                }
            }
            resp = Keyboard.readChar("Outra pessoa? (s/n): ");
        } while (resp == 's');
    }

    static void listar() {
        Keyboard.clrscr();
        System.out.println("Codigo  Nome da Pessoa          DataNasc");
        System.out.println("------  --------------------  ----------");

        NoSimpEnc<Pessoa> p = inicio;

        while (p != null) {
            System.out.printf("%6d  %-20s  %10s\n", p.getObj().getCodPessoa(),
                    p.getObj().getNome(),
                    p.getObj().getDtNascimento());

            p = p.getProx();
        }
        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        int menu;
        do {
            Keyboard.clrscr();
            menu = Keyboard.menu("Cadastrar/Listar/Sair");

            switch (menu) {
                case 1:
                    inserirPessoas();
                    break;
                case 2:
                    listar();
                    break;
            }
        } while (menu < 3);
    }
}
