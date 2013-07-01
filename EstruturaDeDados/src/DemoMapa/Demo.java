/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoMapa;

import Biblioteca.ChaveValor;
import Biblioteca.Keyboard;
import Biblioteca.ListaEncOrd;
import Biblioteca.MyIterator;
import Biblioteca.MyIteratorMapa;

/**
 *
 * @author basmoura
 */
public class Demo {

    static ListaMapa<Pessoa, ListaEncOrd<Bem>> pessoasBens = new ListaMapa<>();

    private static void incluirPessoas() {
        char resp;

        do {

            Keyboard.clrscr();

            int codPessoa = Keyboard.readInt("Informe o código: ");

            Pessoa pessoa = new Pessoa(codPessoa);

            if (pessoasBens.contains(pessoa)) {
                System.out.println("Pessoa já cadastrada");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");

                pessoa.setNmPessoa(nome);

                pessoasBens.put(pessoa, new ListaEncOrd<Bem>());
            }

            resp = Keyboard.readChar("Outra pessoa? (s/n): ");
        } while (resp == 's');
    }

    private static void listarPessoas() {
        MyIteratorMapa<Pessoa, ListaEncOrd<Bem>> it = pessoasBens.iterator();
        ChaveValor<Pessoa, ListaEncOrd<Bem>> pessoa = it.getFirst();
        
        while (pessoa != null) {
            System.out.println(pessoa.getChave().getNmPessoa() + "\n");
            pessoa = it.getNext();
        }
        
        Keyboard.waitEnter();
    }

    private static void incluirBens() {
        Keyboard.clrscr();

        char resp;

        int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

        ListaEncOrd<Bem> bens = pessoasBens.getValor(new Pessoa(codPessoa));

        if (bens == null) {
            System.out.println("Codigo inexistente");
            return;
        }

        do {
            Keyboard.clrscr();

            int codBem = Keyboard.readInt("Informe o código: ");

            Bem bem = new Bem(codBem);

            if (bens.contains(bem)) {
                System.out.println("Código já existente");
            } else {
                String descricao = Keyboard.readString("Informe a descrição: ");
                float valor = Keyboard.readFloat("Informe o valor: ");

                bem.setDescricao(descricao);
                bem.setValor(valor);
                
                bens.add(bem);
            }
            resp = Keyboard.readChar("Outro bem? (s/n): ");

        } while (resp == 's');

    }

    private static void listarBens() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        int opcao;

        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Incluir Pessoas/Listar Pessoa"
                    + "Incluir Bens/Listar Bens/Terminar");

            switch (opcao) {
                case 1:
                    incluirPessoas();
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    incluirBens();
                    break;
                case 4:
                    listarBens();
                    break;
            }
        } while (opcao < 5);
    }
}
