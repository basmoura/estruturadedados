/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteListaDubEnc;

import Biblioteca.Keyboard;
import Biblioteca.ListaDupEnc;
import Biblioteca.MyIterator;
import Biblioteca.NoDupEnc;

/**
 *
 * @author basmoura
 */
public class Demo {

    static ListaDupEnc<Pessoa> pessoas = new ListaDupEnc<>();

    static void add() {
        int codPessoa = Keyboard.readInt("Código: ");
        String nome = Keyboard.readString("Nome: ");

        pessoas.insertAtEnd(new Pessoa(codPessoa, nome, ""));
    }

    static void addBegin() {
        int codPessoa = Keyboard.readInt("Código: ");
        String nome = Keyboard.readString("Nome: ");

        pessoas.insertAtBegin(new Pessoa(codPessoa, nome, ""));
    }

    static void addAfter() {
        MyIterator<Pessoa> it = pessoas.iterator();
        Pessoa pessoa = it.getFirst();

        int codPessoa = Keyboard.readInt("Código procura: ");

        NoDupEnc<Pessoa> novoNo = new NoDupEnc<>();

        int novoCod = Keyboard.readInt("Código: ");
        String nome = Keyboard.readString("Nome: ");
        Pessoa pessoaN = new Pessoa(novoCod, nome, "");

        while (pessoa != null) {
            if (pessoa.getCodPessoa() == codPessoa) {
                
                 novoNo.setObj(pessoa);
                 System.out.println(novoNo.getObj().nome + " " + pessoaN.nome);
                pessoas.insertAfter(new Pessoa(novoCod, nome, ""), novoNo);
            }
            pessoa = it.getNext();
        }
    }
    
    static void listar() {
        MyIterator<Pessoa> it = pessoas.iterator();
        Pessoa pessoa = it.getFirst();
         
        while (pessoa != null) {
            System.out.println(pessoa.getCodPessoa() + " " + pessoa.getNome());
            pessoa = it.getNext();
        }
        
        Keyboard.waitEnter();
    }

    public static void main(String[] args) {

        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Add/Add Begin/Add After/Add Before/Listar/Terminar");
            switch (opcao) {
                case 1:
                    add();
                    break;
                case 2:
                    addBegin();
                    break;
                case 3:
                    addAfter();
                    break;
                case 4:
                    break;
                case 5:
                    listar();
                    break;
                           
            }
        } while (opcao < 7);
        System.out.println("\nFim do programa");
    }
}
