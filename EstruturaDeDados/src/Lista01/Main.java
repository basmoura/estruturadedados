/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista01;

import Biblioteca.Vetor;
import Biblioteca.Sort;
import Biblioteca.MyIterator;
import Biblioteca.ObjectFile;
import Biblioteca.Keyboard;
import java.util.Comparator;

/**
 *
 * @author 2102104650
 */
public class Main {

    static Vetor<Pessoa> pessoas = new Vetor();
    static ObjectFile arqPessoas = new ObjectFile("pessoas.dat");

    static class CompareCodigo implements Comparator<Pessoa> {

        @Override
        public int compare(Pessoa o1, Pessoa o2) {
            if (o1.getCodPessoa() < o2.getCodPessoa()) {
                return -1;
            }
            if (o1.getCodPessoa() == o2.getCodPessoa()) {
                return 0;
            }
            return 1;
        }
    }

    static class CompareNome implements Comparator<Pessoa> {

        @Override
        public int compare(Pessoa o1, Pessoa o2) {
            return o1.getNome().compareToIgnoreCase(o2.getNome());
        }
    }

    static Pessoa acharPessoa(int codPessoa) {
        MyIterator<Pessoa> it = pessoas.iterator();

        Pessoa pessoa = it.getFirst();

        while (pessoa != null) {
            if (pessoa.getCodPessoa() == codPessoa) {
                return pessoa;
            }
            pessoa = it.getNext();
        }
        return null;
    }

    static void CadastrarPessoa() {
        char resp;
        do {
            int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

            if (acharPessoa(codPessoa) != null) {
                System.out.print("Pessoa já cadastrada\n");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");

                String dataNasc = Keyboard.readData("Informe a data de nasc: ");

                pessoas.insertAtEnd(new Pessoa(codPessoa, nome, dataNasc));
            }

            resp = Keyboard.readChar("Outra pessoa? (s/n): ");
        } while (resp == 's');
    }

    static void ListarPessoa() {

        System.out.println("CodPessoa Nome da Pessoa        DataNascim");
        System.out.println("--------- --------------------- ----------");

        Pessoa[] pessoasTemp = new Pessoa[pessoas.size()];

        for (int i = 0; i < pessoas.size(); i++) {
            pessoasTemp[i] = pessoas.elementAt(i);
        }

        Sort.quickSort(pessoasTemp, new CompareNome());

        for (int i = 0; i < pessoas.size(); i++) {
            System.out.printf("%10d  %-20s  %10s\n\n",
                    pessoasTemp[i].getCodPessoa(), pessoasTemp[i].getNome(),
                    pessoasTemp[i].getDtNascimento());
        }
        Keyboard.waitEnter();
    }

    static void gravar() {
        arqPessoas.rewrite();
        MyIterator<Pessoa> it = pessoas.iterator();
        Pessoa pessoa = it.getFirst();

        while (pessoa != null) {
            arqPessoas.write(pessoa);
            pessoa = it.getNext();
        }
        arqPessoas.closeFile();
    }

    static void ler() {
        if (arqPessoas.reset()) {
            Pessoa pessoa = (Pessoa) arqPessoas.read();
            while (pessoa != null) {
                pessoas.insertAtEnd(pessoa);
                pessoa = (Pessoa) arqPessoas.read();
            }
            arqPessoas.closeFile();
        }
    }

    static void ConsultarPessoa() {

        Keyboard.clrscr();

        int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");
        Pessoa pessoa = acharPessoa(codPessoa);

        if (pessoa == null) {
            System.out.println("Pessoa não cadastrada");
        } else {
            System.out.println("CodPessoa Nome da Pessoa        DataNascim");
            System.out.println("--------- --------------------- ----------");
            System.out.printf("%10d %-20s %10s\n", pessoa.getCodPessoa(),
                    pessoa.getNome(),
                    pessoa.getDtNascimento());
        }
    }

    public static void main(String[] args) {
        int opcao = 0;
        ler();
        do {
            Keyboard.clrscr();

            opcao = Keyboard.menu("Incluir/Consultar/Listar/Terminar");

            switch (opcao) {
                case 1:
                    CadastrarPessoa();
                    break;
                case 2:
                    ConsultarPessoa();
                    break;
                case 3:
                    ListarPessoa();
                    break;
            }
        } while (opcao < 4);
        gravar();
    }
}
