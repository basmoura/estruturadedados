package Lista06_1;

import Biblioteca.Keyboard;
import Biblioteca.ListaSeqNaoOrd;
import Biblioteca.MyIterator;
import Biblioteca.ObjectFile;

/**
 *
 * @author haroldofurtado
 */
public class App {

    static ListaSeqNaoOrd<Conta> contas = new ListaSeqNaoOrd<>(20, 40);
    static ListaSeqNaoOrd<Movimentacao> movimentacoes = new ListaSeqNaoOrd<>(20, 40);
    static int nrConta;
    static ObjectFile arqContas = new ObjectFile("contaLista6_1.dat");

    static Conta buscarConta(int numConta) {
        MyIterator<Conta> it = contas.iterator();

        Conta conta = it.getFirst();

        while (conta != null) {
            if (conta.getNumConta() == numConta) {
                return conta;
            }
            conta = it.getNext();
        }

        return null;
    }

    static void cadastrarConta() {
        char resp;
        int tipo;
        do {
            Keyboard.clrscr();

            int numConta = Keyboard.readInt("Informe o número da conta: ");

            if (buscarConta(numConta) != null) {
                System.out.println("Conta já cadastrada");
            } else {
                String nmCliente = Keyboard.readString("Informe o nome do cliente: ");
                tipo = Keyboard.menu("Comum/Especial/Poupança");

                switch (tipo) {
                    case 1:
                        ContaComum contaComum = new ContaComum(numConta);
                        contaComum.setNmCliente(nmCliente);
                        contas.add(contaComum);
                        break;
                    case 2:
                        double limiteCredito = Keyboard.readDouble("Informe o limite de crédito: ");
                        ContaEspecial contaEspecial = new ContaEspecial(limiteCredito, numConta);
                        contaEspecial.setNmCliente(nmCliente);
                        contas.add(contaEspecial);
                        break;
                    case 3:
                        ContaPoupanca contaPoupanca = new ContaPoupanca(numConta);
                        contaPoupanca.setNmCliente(nmCliente);
                        contas.add(contaPoupanca);
                        break;
                }
            }
            resp = Keyboard.readChar("Outra conta? (s/n): ");
        } while (resp == 's');
    }

    static void listarContas() {
        Keyboard.clrscr();

        System.out.println("Num Conta  Nome do Cliente            Saldo  Tipo da Conta");
        System.out.println("---------  --------------------  ----------  -------------");

        MyIterator<Conta> it = contas.iterator();
        Conta conta = it.getFirst();

        while (conta != null) {
            System.out.printf("%9s  %-20s  %10.2f", conta.getNumConta(),
                    conta.getNmCliente(),
                    conta.getSaldo());
            String tipo = "";

            if (conta instanceof ContaComum) {
                tipo = "Comum";
            }
            if (conta instanceof ContaEspecial) {
                tipo = "Especial";
            }
            if (conta instanceof ContaPoupanca) {
                tipo = "Poupança";
            }
            System.out.printf("  %-12s\n", tipo);

            conta = it.getNext();
        }
        Keyboard.waitEnter();
    }

    static void removerConta() {
        Keyboard.clrscr();

        int numConta = Keyboard.readInt("Informe o número da conta: ");

        Conta conta = buscarConta(numConta);

        if (conta != null) {
            char resp = Keyboard.readChar("Deseja remover a conta? (s/n): ");

            if (resp == 's') {
                contas.remove(conta);
                System.out.println("Conta removida com sucesso");
            } else {
                System.out.println("Conta não removida");
            }

        }
        Keyboard.waitEnter();
    }

    static void efetuarDeposito() {
        Keyboard.clrscr();

        int numConta = Keyboard.readInt("Informe o número da conta: ");

        Conta conta = buscarConta(numConta);

        if (contas.contains(conta)) {
            double valor = Keyboard.readDouble("Informe o valor a ser depositado: ");
            String dataMov = Keyboard.readData("Data da movimentação: ");
            String tipoMov = "C";

            conta.efetuarDeposito(valor);

            Movimentacao movimentacao = new Movimentacao(numConta, dataMov, tipoMov, valor);

            movimentacoes.add(movimentacao);

            System.out.println("Depósito realizado com sucesso");
        } else {
            System.out.println("Conta inexistente.");
        }
        Keyboard.waitEnter();
    }

    static void efetuarSaque() {
        Keyboard.clrscr();

        int numConta = Keyboard.readInt("Informe o número da conta: ");
        Conta conta = buscarConta(numConta);

        if (contas.contains(conta)) {
            double valorSacado = Keyboard.readDouble("Informe o valor a ser sacado: ");
            String dataMov = Keyboard.readData("Data da movimentação: ");
            String tipoMov = "D";

            if (conta.efetuarSaque(valorSacado)) {
                Movimentacao movimentacao = new Movimentacao(numConta, dataMov, tipoMov, valorSacado);

                movimentacoes.add(movimentacao);

                System.out.println("Saque efetuado com sucesso");
            } else {
                System.out.println("Saldo insuficiente");
            }
        }
        Keyboard.waitEnter();
    }

    static void consultarConta() {
        Keyboard.clrscr();

        int numConta = Keyboard.readInt("Informe o número da conta: ");

        Conta conta = contas.retrieve(new ContaComum(numConta));

        if (contas.contains(new ContaComum(numConta))) {
            System.out.println("Num Conta  Nome do Cliente            Saldo  Tipo da Conta");
            System.out.println("---------  --------------------  ----------  -------------");
            System.out.printf("%9s  %-20s  %10.2f", conta.getNumConta(),
                    conta.getNmCliente(),
                    conta.getSaldo());

            String tipo = "";
            if (conta instanceof ContaComum) {
                tipo = "Comum";
            }

            if (conta instanceof ContaEspecial) {
                tipo = "Especial";
            }

            if (conta instanceof ContaPoupanca) {
                tipo = "Poupança";
            }

            System.out.printf("  %-12s\n", tipo);
        } else {
            System.out.println("Conta inexistente");
        }
        Keyboard.waitEnter();
    }

    static void creditarRendimentos() {
        double txJuros = Keyboard.readDouble("Informe a taxa de juros: ");

        Conta contaPoupanca = contas.iterator().getFirst();

        while (contaPoupanca != null) {

            if (contaPoupanca instanceof ContaPoupanca) {
                ContaPoupanca contP = (ContaPoupanca) contaPoupanca;
                contP.creditarRendimento(txJuros);
            }
        }
        System.out.println("Redimento creditado");
        Keyboard.waitEnter();
    }

    static void consultarExtrato() {
        Keyboard.clrscr();

        int numConta = Keyboard.readInt("Informe o número da conta: ");

        Conta conta = buscarConta(numConta);

        if (conta != null) {
            String tipo = "";

            if (conta instanceof ContaComum) {
                tipo = "Conta Comum";
            }
            if (conta instanceof ContaEspecial) {
                tipo = "Conta Especial";
            }
            if (conta instanceof ContaPoupanca) {
                tipo = "Conta Poupança";
            }

            Keyboard.clrscr();

            System.out.println("Número da conta: " + conta.getNumConta());
            System.out.println("Tipo da conta: " + tipo);
            System.out.println("Nome do cliente: " + conta.getNmCliente() + "\n");
        }

        MyIterator<Movimentacao> it = movimentacoes.iterator();
        Movimentacao movimentacao = it.getFirst();

        System.out.println("Data Mov       Valor  TipoMov");
        System.out.println("----------  --------  -------");

        while (movimentacao != null) {
            if (movimentacao.getNumConta() == conta.getNumConta()) {
                System.out.printf("%-10s  %.2f  %4s\n", movimentacao.getDataMov(),
                        movimentacao.getValorMov(), movimentacao.getTipoMov());
            }
            movimentacao = it.getNext();
        }

        System.out.println("Saldo atual: " + conta.getSaldo());

        Keyboard.waitEnter();
    }

    static void lerArquivo() {
        if (arqContas.reset()) {
            contas = (ListaSeqNaoOrd<Conta>) arqContas.read();
            movimentacoes = (ListaSeqNaoOrd<Movimentacao>) arqContas.read();

            arqContas.closeFile();
        } else {
            contas = new ListaSeqNaoOrd<>(20, 40);
            movimentacoes = new ListaSeqNaoOrd<>(20, 40);
        }
    }

    static void gravarArquivo() {
        arqContas.rewrite();

        arqContas.write(contas);
        arqContas.write(movimentacoes);

        arqContas.closeFile();
    }

    public static void main(String[] args) {
        int opcao;
        lerArquivo();
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Cadastrar Contas/Listar Contas/Remover Conta/"
                    + "Efetuar Depósitos/Efetuar Saques/Consultar Conta/Consultar Extrato/"
                    + "Creditar Rendimentos/Terminar");
            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    listarContas();
                    break;
                case 3:
                    removerConta();
                    break;
                case 4:
                    efetuarDeposito();
                    break;
                case 5:
                    efetuarSaque();
                    break;
                case 6:
                    consultarConta();
                    break;
                case 7:
                    consultarExtrato();
                    break;
                case 8:
                    creditarRendimentos();
                    break;
            }
        } while (opcao < 9);
        gravarArquivo();
    }
}
