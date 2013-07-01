/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista05_2;

import Biblioteca.ListaSimpEnc;
import Biblioteca.Sort;
import Biblioteca.MyIterator;
import Biblioteca.ObjectFile;
import Biblioteca.Keyboard;
import java.util.Comparator;

/**
 *
 * @author basmoura
 */
public class App {

    static ListaSimpEnc<Pessoa> pessoas = new ListaSimpEnc<>();
    static ObjectFile arqContas = new ObjectFile("pessoas.dat");

    static class CompareNome implements Comparator<Pessoa> {

        @Override
        public int compare(Pessoa o1, Pessoa o2) {
            return o1.getNome().compareToIgnoreCase(o2.getNome());
        }
    }

    static Pessoa buscarPessoa(int codPessoa) {
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

    static boolean buscarBem(int codPessoa, int codBem) {
        Pessoa pessoa = buscarPessoa(codPessoa);

        MyIterator<Bem> it = pessoa.getBens().iterator();
        Bem bem = it.getFirst();

        while (bem != null) {
            if (bem.getCodBem() == codBem) {
                return true;
            }
            bem = it.getNext();
        }

        return false;
    }

    static double calcularBens(int codPessoa) {
        double totalBens = 0;

        Pessoa pessoa = buscarPessoa(codPessoa);

        MyIterator<Bem> it = pessoa.getBens().iterator();
        Bem bem = it.getFirst();

        while (bem != null) {
            totalBens = totalBens + bem.getValor();

            bem = it.getNext();
        }

        return totalBens;
    }

    static void cadastrarPessoa() {
        char resp;
        do {
            Keyboard.clrscr();

            int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

            if (buscarPessoa(codPessoa) != null) {
                System.out.println("Pessoa já cadastrada");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");

                Pessoa pessoa = new Pessoa(codPessoa, nome);

                pessoas.insertAtEnd(pessoa);
            }
            resp = Keyboard.readChar("Outra pessoa? (s/n): ");
        } while (resp == 's');
    }

    static void excluirPessoa() {
        char resp;
        do {
            Keyboard.clrscr();
            int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

            MyIterator<Pessoa> it = pessoas.iterator();
            Pessoa pessoa = it.getFirst();

            while (pessoa != null) {
                if (pessoa.getCodPessoa() == codPessoa) {
                    char rem = Keyboard.readChar("Deseja remover? (s/n): ");
                    if (rem == 's') {
                        it.remove();
                        System.out.println("Pessoa removida");
                    } else {
                        System.out.println("Pessoa não removida");
                    }
                    break;
                }

                pessoa = it.getNext();
            }
            resp = Keyboard.readChar("Outra pessoa? (s/n): ");
        } while (resp == 's');
    }

    static void cadastrarBens() {
        char resp;
        do {
            Keyboard.clrscr();

            int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");
            Pessoa pessoa = buscarPessoa(codPessoa);
            if (pessoa == null) {
                System.out.println("Pessoa não cadastrada");
            } else {
                int codBem = Keyboard.readInt("Informe o código do bem: ");
                if (buscarBem(codPessoa, codBem)) {
                    System.out.println("Bem já cadastrado");
                } else {
                    String desc = Keyboard.readString("Descrição: ");
                    String dtAquisicao = Keyboard.readData("Dt de Aquisição: ");
                    double valor = Keyboard.readDouble("Valor: ");

                    Bem bem = new Bem(codBem, desc, dtAquisicao, valor);
                    pessoa.getBens().insertAtEnd(bem);
                }
            }
            resp = Keyboard.readChar("Outro bem? (s/n): ");
        } while (resp == 's');
    }

    static void excluirBem() {
        Keyboard.clrscr();

        int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");
        Pessoa pessoa = buscarPessoa(codPessoa);

        if (pessoa == null) {
            System.out.println("Pessoa não cadastrada");
        } else {
            int codBem = Keyboard.readInt("Informe o código do bem: ");

            if (buscarBem(codPessoa, codBem)) {
                MyIterator<Bem> it = pessoa.getBens().iterator();
                Bem bem = it.getFirst();

                while (bem != null) {
                    if (bem.getCodBem() == codBem) {
                        char resp = Keyboard.readChar("Deseja remover? (s/n): ");
                        if (resp == 's') {
                            it.remove();
                            System.out.println("Bem removido");
                        } else {
                            System.out.println("Bem não removido");
                        }
                        break;
                    }

                    bem = it.getNext();
                }
            }
        }
    }

    static void listarPessoas() {
        Keyboard.clrscr();

        int i = 0;

        MyIterator<Pessoa> it = pessoas.iterator();
        Pessoa pessoa = it.getFirst();

        Pessoa[] pessoasTemp = new Pessoa[pessoas.size()];

        while (pessoa != null) {
            pessoasTemp[i] = pessoa;
            i++;
            pessoa = it.getNext();
        }

        Sort.quickSort(pessoasTemp, new CompareNome());

        System.out.println("CodPessoa  Nome da Pessoa           Valor Total dos Bens");
        System.out.println("---------  -----------------------  --------------------");

        for (i = 0; i < pessoasTemp.length; i++) {
            System.out.printf("%5d      %-23s  %.2f\n", pessoasTemp[i].getCodPessoa(),
                    pessoasTemp[i].getNome(),
                    calcularBens(pessoasTemp[i].getCodPessoa()));
        }

        Keyboard.waitEnter();
    }

    static void listarBens() {
        Keyboard.clrscr();

        int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

        Pessoa pessoa = buscarPessoa(codPessoa);

        if (pessoa != null) {
            Keyboard.clrscr();
            
            System.out.printf("Cod Pessoa: %d  Nome da Pessoa: %s\n\n", pessoa.getCodPessoa(),
                    pessoa.getNome());

            System.out.println("CodBem  Descrição do Bem       Data Aquisição  Valor do Bem");
            System.out.println("------  ---------------------  --------------  ------------");

            MyIterator<Bem> it = pessoa.getBens().iterator();
            Bem bem = it.getFirst();

            while (bem != null) {
                System.out.printf("%6d  %-21s    %-3s    %12.2f\n",
                        bem.getCodBem(), bem.getDesc(), bem.getDtAquisicao(),
                        bem.getValor());
                bem = it.getNext();
            }

        } else {
            System.out.println("Pessoa não cadastrada");
        }

        Keyboard.waitEnter();
    }

    static void lerArquivo() {
        if (arqContas.reset()) {
            Pessoa pessoa = (Pessoa) arqContas.read();

            while (pessoa != null) {

                pessoas.insertAtEnd(pessoa);

                pessoa = (Pessoa) arqContas.read();
            }

            arqContas.closeFile();
        }
    }

    static void gravarArquivo() {
        arqContas.rewrite();

        MyIterator<Pessoa> it = pessoas.iterator();
        Pessoa pessoa = it.getFirst();

        while (pessoa != null) {
            arqContas.write(pessoa);
            pessoa = it.getNext();
        }

        arqContas.closeFile();
    }

    public static void main(String[] args) {
        lerArquivo();
        int menu;
        do {
            Keyboard.clrscr();
            menu = Keyboard.menu("Cadastrar Pessoa/Excluir Pessoa"
                    + "/Cadastrar Bens da Pessoa/Excluir Bem/Listar Pessoa/"
                    + "Listar Bens da Pessoa/Terminar");

            switch (menu) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    excluirPessoa();
                    break;
                case 3:
                    cadastrarBens();
                    break;
                case 4:
                    excluirBem();
                    break;
                case 5:
                    listarPessoas();
                    break;
                case 6:
                    listarBens();
                    break;
            }

        } while (menu < 7);
        gravarArquivo();
    }
}
