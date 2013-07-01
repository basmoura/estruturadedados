/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_ProvaIUnidade_Questao1;

import Biblioteca.ChaveValor;
import Biblioteca.Keyboard;
import Biblioteca.MyIterator;
import Biblioteca.MyIteratorMapa;
import Biblioteca.TextFile;
import java.util.Comparator;

/**
 *
 * @author basmoura
 */
public class App {

    static TextFile txtPessoas = new TextFile("pessoas.txt");
    static SkipList<Pessoa> pessoas = new SkipList<>(200);
    static HashMapa<Integer, SkipList<Pessoa>> idades;
    static HashMapa<Float, SkipList<Pessoa>> salarios;

    static class OrdemAlfabetica implements Comparator<Pessoa> {

        @Override
        public int compare(Pessoa o1, Pessoa o2) {
            return o1.getNmPessoa().compareToIgnoreCase(o2.getNmPessoa());
        }
    }

    static class OrdenarPorIdade implements Comparator<Pessoa> {

        @Override
        public int compare(Pessoa o1, Pessoa o2) {
            if (o1.getIdade() < o2.getIdade()) {
                return -1;
            }
            if (o1.getIdade() == o2.getIdade()) {
                return 0;
            }
            return 1;
        }
    }

    static class OrdenarPorSalario implements Comparator<Pessoa> {

        @Override
        public int compare(Pessoa o1, Pessoa o2) {
            if (o1.getSalario() < o2.getSalario()) {
                return -1;
            }

            if (o1.getSalario() == o2.getSalario()) {
                return 0;
            }

            return 1;
        }
    }

    private static void lerPessoas() {
        if (txtPessoas.reset()) {
            String line;
            while (txtPessoas.eof() != true) {
                line = txtPessoas.readString();
                Pessoa p = new Pessoa(Integer.parseInt(line.substring(0, 8)));
                p.setNmPessoa(line.substring(8, 38));
                p.setIdade(Integer.parseInt(line.substring(39, 41)));
                p.setSalario(Float.parseFloat((line.substring(42, 51)).replace(',', '.')));
                pessoas.add(p);
            }
            txtPessoas.closeFile();
        }
    }

    private static void incluir() {
        int codPessoa = Keyboard.readInt("Informe o código:");

        Pessoa pessoa = pessoas.retrieve(new Pessoa(codPessoa));

        if (pessoa != null) {
            System.err.println("Pessoa já cadastrada");
        } else {
            String nmPessoa = Keyboard.readString("Informe o nome: ");
            int idade = Keyboard.readInt("Informe a idade: ");
            float salario = Keyboard.readFloat("Informe o salário: ");

            pessoa.setNmPessoa(nmPessoa);
            pessoa.setIdade(idade);
            pessoa.setSalario(salario);

            idades.put(idade, pessoas);
            salarios.put(salario, pessoas);

            pessoas.add(pessoa);
        }
        Keyboard.waitEnter();
    }

    private static void excluir() {
        int codPessoa = Keyboard.readInt("Informe o código da pessoa:");

        Pessoa pessoa = pessoas.retrieve(new Pessoa(codPessoa));

        if (pessoa != null) {
            char confirma = Keyboard.readChar("Deseja realmente excluir essa pessoa? s/n");
            if (confirma == 's') {
                pessoas.remove(pessoa);

                MyIteratorMapa<Integer, SkipList<Pessoa>> it = idades.iterator();
                ChaveValor<Integer, SkipList<Pessoa>> pessoaIt = it.getFirst();

                while (pessoaIt != null) {
                    if (pessoaIt.getValor().retrieve(pessoa).getCodPessoa() == pessoa.getCodPessoa()) {
                        it.remove();
                    }
                }

                System.out.println("Removido com Sucesso.");
            } else {
                System.out.println("Pessoa não removida.");
                Keyboard.waitEnter();
                return;
            }
        } else {
            System.out.println("Pessoa Inexistente.");
        }
        Keyboard.waitEnter();
    }

    private static void consultar() {
        int codPessoa = Keyboard.readInt("Digite o código da pessoa: ");
        Pessoa pessoa = pessoas.retrieve(new Pessoa(codPessoa));

        if (pessoa != null) {
            System.out.println("Código: " + pessoa.getCodPessoa() + "\n"
                    + "Nome: " + pessoa.getNmPessoa() + "\n" + "Idade: "
                    + pessoa.getIdade() + "\n" + "Salario: "
                    + pessoa.getSalario());

        } else {
            System.out.println(" Código não cadastrado ");
        }
        Keyboard.waitEnter();
    }

    static void listarPorCodigo() {

        int cont = 0;
        if (pessoas.isEmpty()) {
            System.out.println("Não há pessoas cadastradas");
        } else {

            MyIterator<Pessoa> it = pessoas.iterator();
            Pessoa pessoa = it.getFirst();

            System.out.println("Codigo    Nome                                  Idade Salario");
            System.out.println("--------- ------------------------------------- ----- -------");
            while (pessoa != null) {
                pessoa = it.getNext();
                System.out.printf("%9d  %-35s  %5d %8.2f \n", pessoa.getCodPessoa(), pessoa.getNmPessoa(), pessoa.getIdade(), pessoa.getSalario());

                cont++;
                if (cont == 20) {
                    Keyboard.waitEnter();
                    cont = 0;
                    Keyboard.clrscr();
                    System.out.println("  Codigo  Nome                                  Idade Salario");
                    System.out.println("--------- ------------------------------------- ----- -------");
                }
            }
            Keyboard.waitEnter();
        }
    }

    static void listarPorOrdAlfabetica() {
        Object[] array = null;
        int cont = 0;

        if (pessoas.isEmpty()) {
            System.out.println("Não há pessoas cadastradas.");
            Keyboard.waitEnter();
        } else {
            array = pessoas.sort(new OrdemAlfabetica());
            System.out.println("  Codigo  Nome                                  Idade Salario");
            System.out.println("--------- ------------------------------------- ----- -------");
            for (int i = 0; i < array.length; i++) {
                Pessoa pessoa = (Pessoa) array[i];
                System.out.printf("%9d %-35s  %5d %8.2f \n", (pessoa.getCodPessoa()), pessoa.getNmPessoa(), pessoa.getIdade(), pessoa.getSalario());

                cont++;
                if (cont == 20) {
                    Keyboard.waitEnter();
                    cont = 0;
                    Keyboard.clrscr();
                    System.out.println("  Codigo  Nome                                  Idade Salario");
                    System.out.println("--------- ------------------------------------- ----- -------");
                }
            }
        }
    }

    static void listarPorIdade() {
        Object[] array = null;
        int cont = 0;

        if (pessoas.isEmpty()) {
            System.out.println("Não há pessoas cadastradas.");
        } else {
            array = pessoas.sort(new OrdenarPorIdade());
            System.out.println("  Codigo  Nome                                  Idade Salario");
            System.out.println("--------- ------------------------------------- ----- -------");
            for (int i = 0; i < array.length; i++) {
                Pessoa pessoa = (Pessoa) array[i];
                System.out.printf("%9d %-35s  %5d %8.2f \n", (pessoa.getCodPessoa()), pessoa.getNmPessoa(), pessoa.getIdade(), pessoa.getSalario());

                cont++;
                if (cont == 20) {
                    Keyboard.waitEnter();
                    cont = 0;
                    Keyboard.clrscr();
                    System.out.println("  Codigo  Nome                                  Idade Salario");
                    System.out.println("--------- ------------------------------------- ----- -------");
                }
            }
        }
        Keyboard.waitEnter();
    }

    static void listarPorSalario() {
        Object[] array = null;
        int cont = 0;

        if (pessoas.isEmpty()) {
            System.out.println("Não há pessoas cadastradas.");
        } else {
            array = pessoas.sort(new OrdenarPorSalario());
            System.out.println("  Codigo  Nome                                  Idade Salario");
            System.out.println("--------- ------------------------------------- ----- -------");
            for (int i = 0; i < array.length; i++) {
                Pessoa pessoa = (Pessoa) array[i];
                System.out.printf("%9d %-35s  %5d %8.2f \n", (pessoa.getCodPessoa()), pessoa.getNmPessoa(), pessoa.getIdade(), pessoa.getSalario());

                cont++;
                if (cont == 20) {
                    Keyboard.waitEnter();
                    cont = 0;
                    Keyboard.clrscr();
                    System.out.println("  Codigo  Nome                                  Idade Salario");
                    System.out.println("--------- ------------------------------------- ----- -------");
                }
            }
        }
        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        lerPessoas();
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Incluir/Excluir/Consultar/Listar Por Código/Listar em Ordem Alfabética/Listar por Idade/Listar Por Salário/Terminar");
            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    excluir();
                    break;
                case 3:
                    consultar();
                    break;
                case 4:
                    listarPorCodigo();
                    break;
                case 5:
                    listarPorOrdAlfabetica();
                    break;
                case 6:
                    listarPorIdade();
                    break;
                case 7:
                    listarPorSalario();
            }
        } while (opcao < 8);
    }
}
