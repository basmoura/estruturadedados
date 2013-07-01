package lista07_Heap;

import utilitarios.*;

public class Demo {

    static Treap<Pessoa> pessoasTreap;
    static Treap<CompareSalario> pessoasTreapSalario;
    static Heap<CompareIdade> pessoasHeap;
    static ObjectFile arqPessoas = new ObjectFile("Pessoas.dat");

    public static void main(String[] args) {
        lerArquivos();
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard
                    .menu("Incluir pessoas/Listar pessoas/Remover pessoa/Excluir pessoas por salario/Sair");
            switch (opcao) {
                case 1:
                    incluirPessoa();
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    removerPessoa();
                    break;
                case 4:
                    excluirPessoasPorSalario();
                    break;
            }
        } while (opcao < 5);
        Keyboard.clrscr();
        System.out.println("\nFim do programa.");
    }

    private static void excluirPessoasPorSalario() {
        Keyboard.clrscr();
        float salario = Keyboard.readFloat("Entrar com o salario: ");
        MyIterator<CompareIdade> it = pessoasHeap.iterator();
        CompareIdade compareIdade = it.getFirst();
        while (compareIdade != null) {
            if (compareIdade.getPessoa().getSalario() == salario) {
                pessoasTreap.remove(compareIdade.getPessoa());
                pessoasTreapSalario.remove(new CompareSalario(compareIdade.getPessoa()));
                it.remove();
            }
            compareIdade = it.getNext();
        }
        Keyboard.waitEnter();
        gravarArquivos();
    }

    private static void listarPessoas() {
        Keyboard.clrscr();
        int listar = Keyboard.readInt("Deseja listar por (1- C�digo/2- Sal�rio/3- Idade): ");
        System.out.println("Codigo  Nome                            Idade  Sal�rio");
        System.out.println("------  ------------------------------  -----  ----------");
        switch (listar) {
            case 1:
                MyIterator<Pessoa> itPessoa = pessoasTreap.iterator();
                Pessoa pessoa = itPessoa.getFirst();
                while (pessoa != null) {
                    System.out.printf("%6d  %-30s  %5s  %10.2f\n",
                            pessoa.getCodigo(), pessoa.getNome(),
                            pessoa.getIdade(), pessoa.getSalario());
                    pessoa = itPessoa.getNext();
                }
                break;
            case 2:
                MyIterator<CompareSalario> itSalario = pessoasTreapSalario.iterator();
                CompareSalario compareSalario = itSalario.getFirst();
                while (compareSalario != null) {
                    System.out.printf("%6d  %-30s  %5s  %10.2f\n",
                            compareSalario.getPessoa().getCodigo(), compareSalario.getPessoa().getNome(),
                            compareSalario.getPessoa().getIdade(), compareSalario.getPessoa().getSalario());
                    compareSalario = itSalario.getNext();
                }
                break;
            case 3:
                MyIterator<CompareIdade> itIdade = pessoasHeap.iterator();
                CompareIdade compareIdade = itIdade.getFirst();
                while (compareIdade != null) {
                    System.out.printf("%6d  %-30s  %5s  %10.2f\n",
                            compareIdade.getPessoa().getCodigo(), compareIdade.getPessoa().getNome(),
                            compareIdade.getPessoa().getIdade(), compareIdade.getPessoa().getSalario());
                    compareIdade = itIdade.getNext();
                }
                break;
        }
        Keyboard.waitEnter();
    }

    private static void incluirPessoa() {
        char resp;
        Keyboard.clrscr();
        do {
            int codigo = Keyboard.readInt("Entrar com o codigo da pessoa: ");
            String nome = Keyboard.readString("Entrar com o nome: ");
            float salario = Keyboard.readFloat("Entrar com o salario: ");
            int idade = Keyboard.readInt("Entrar com a idade: ");
            Pessoa pessoa = new Pessoa(codigo, nome, salario, idade);
            CompareSalario compareSalario = new CompareSalario(pessoa);
            CompareIdade compareIdade = new CompareIdade(pessoa);
            pessoasTreap.add(pessoa);
            pessoasTreapSalario.add(compareSalario);
            pessoasHeap.add(compareIdade);
            System.out.println("Pessoa cadastrada.");
            System.out.println();
            resp = Keyboard.readChar("Outra inclus�o (s/n)? ");
        } while (resp == 's');
        gravarArquivos();
    }

    private static void removerPessoa() {
        char resp;
        Keyboard.clrscr();
        do {
            CompareIdade compareIdade = pessoasHeap.remove();
            pessoasTreap.remove(compareIdade.getPessoa());
            pessoasTreapSalario.remove(new CompareSalario(compareIdade.getPessoa()));
            if (compareIdade != null) {
                System.out.println(compareIdade.getPessoa().getNome() + " foi removido(a) com sucesso.");
            }
            System.out.println();
            resp = Keyboard.readChar("Outra exclusao (s/n)? ");
        } while (resp == 's');
        gravarArquivos();
    }

    private static void gravarArquivos() {
        arqPessoas.rewrite();
        arqPessoas.write(pessoasTreap);
        arqPessoas.write(pessoasTreapSalario);
        arqPessoas.write(pessoasHeap);
        arqPessoas.closeFile();
    }

    @SuppressWarnings({"unchecked"})
    private static void lerArquivos() {
        if (arqPessoas.reset()) {
            pessoasTreap = (Treap<Pessoa>) arqPessoas.read();
            pessoasTreapSalario = (Treap<CompareSalario>) arqPessoas.read();
            pessoasHeap = (Heap<CompareIdade>) arqPessoas.read();
            arqPessoas.closeFile();
        } else {
            pessoasTreap = new Treap<>(false);
            pessoasTreapSalario = new Treap<>(true);
            pessoasHeap = new Heap<>(10, Heap.TipoHeap.MaxHeap);
        }
    }
}
