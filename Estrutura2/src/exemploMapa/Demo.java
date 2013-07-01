package exemploMapa;

import utilitarios.Keyboard;

import utilitarios.*;

public class Demo {

    static ListaMapa<Pessoa, ListaEncOrd<Bem>> pessoasBens = new ListaMapa<>();

    static void incluirPessoas() {
        char resp;
        Keyboard.clrscr();
        do {
            int codPessoa = Keyboard.readInt("Entrar com o codigo da pessoa: ");
            Pessoa pessoa = new Pessoa(codPessoa);
            if (pessoasBens.contains(pessoa)) {
                System.out.println("Codigo ja existente");
            } else {
                String nomePessoa = Keyboard
                        .readString("Entrar com o nome da pessoa:");
                pessoa.setNomePessoa(nomePessoa);
                pessoasBens.put(pessoa, new ListaEncOrd<Bem>());
            }
            resp = Keyboard.readChar("Outra inclusao(s/n)?");

        } while (resp == 's');
    }

    static void listarPessoas() {
    }

    static void incluirBens() {
        Keyboard.clrscr();
        int codPessoa = Keyboard.readInt("Entrar com o codigo da pessoa: ");

        ListaEncOrd<Bem> bens = pessoasBens.getValor(new Pessoa(codPessoa));
        if (bens == null) {
            System.out.println("Codigo inexistente");
            Keyboard.waitEnter();
            return;
        }
        char resp;
        Keyboard.clrscr();
        do {
            int codBem = Keyboard.readInt("Entrar com o codigo do bem: ");
            Bem bem = new Bem(codBem);
            if (bens.contains(bem)) {
                System.out.println("Codigo ja existente");
            } else {
                String descricao = Keyboard
                        .readString("Entrar com o descricao do bem: ");
                float valor = Keyboard.readFloat("Entrar com o valor:");
                bem.setDescricao(descricao);
                bem.setValor(valor);
                bens.add(bem);
            }
            resp = Keyboard.readChar("Outra inclusao (s/n)?");
        } while (resp == 's');
    }

    static void ListarBens() {
    }

    public static void main(String[] args) {
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Incluir Pessoa/Listar Pessoas/Incluir Bens/Listar Bens/Terminar");
            switch (opcao) {
                case 1:
                    incluirPessoas();
                    break;
                case 2:
                    break;
                case 3:
                    incluirBens();
                    break;
                case 4:
                    break;
            }
        } while (opcao < 5);

    }
}
