/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista04;

import Biblioteca.Vetor;
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

    static Vetor<Conta> contas = new Vetor<>();
    static int totalContas = 0;
    static int nrConta;
    static ObjectFile arqContas = new ObjectFile("contasVetor.dat");

    static class CompareSaldo implements Comparator<Conta> {

        @Override
        public int compare(Conta o1, Conta o2) {
            if (o1.getSaldo() < o2.getSaldo()) {
                return 1;
            }
            if (o1.getSaldo() == o2.getSaldo()) {
                return 0;
            }
            return -1;
        }
    }

    static class CompareNome implements Comparator<Conta> {

        @Override
        public int compare(Conta o1, Conta o2) {
            return o1.getNmCliente().compareToIgnoreCase(o2.getNmCliente());
        }
    }

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

                        contas.insertAtEnd(contaComum);
                        break;
                    case 2:
                        double limiteCredito = Keyboard.readDouble("Informe o limite de crédito: ");

                        ContaEspecial contaEspecial = new ContaEspecial(limiteCredito, numConta);
                        contaEspecial.setNmCliente(nmCliente);

                        contas.insertAtEnd(contaEspecial);
                        break;
                    case 3:
                        ContaPoupanca contaPoupanca = new ContaPoupanca(numConta);
                        contaPoupanca.setNmCliente(nmCliente);

                        contas.insertAtEnd(contaPoupanca);
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

    static void efetuarDeposito() {
        Keyboard.clrscr();

        int numConta = Keyboard.readInt("Informe o número da conta: ");
        Conta conta = buscarConta(numConta);

        if (conta != null) {
            double valor = Keyboard.readDouble("Informe o valor a ser depositado: ");

            conta.efetuarDeposito(valor);
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

        if (conta != null) {
            double valorSacado = Keyboard.readDouble("Informe o valor a ser sacado: ");

            if (conta.efetuarSaque(valorSacado)) {
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
        Conta conta = buscarConta(numConta);

        if (conta != null) {
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

        for (int i = 0; i < totalContas; i++) {
            if (contas.elementAt(i) instanceof ContaPoupanca) {
                ContaPoupanca contaPoupanca = (ContaPoupanca) contas.elementAt(i);
                contaPoupanca.creditarRendimento(txJuros);
            }
        }

        System.out.println("Redimento creditado");
        Keyboard.waitEnter();
    }

    static void imprimirOrdenado(int metodoOrdenacao) {
        Keyboard.clrscr();

        System.out.println("Num Conta  Nome do Cliente            Saldo  Tipo da Conta");
        System.out.println("---------  --------------------  ----------  -------------");

        Conta[] contasTemp = new Conta[contas.size()];

        for (int i = 0; i < contasTemp.length; i++) {
            contasTemp[i] = contas.elementAt(i);
        }

        switch (metodoOrdenacao) {
            case 7:
                Sort.quickSort(contasTemp, new CompareNome());
                break;
            case 8:
                Sort.quickSort(contasTemp, new CompareSaldo());
                break;
        }

        for (int i = 0; i < contas.size(); i++) {
            System.out.printf("%9s  %-20s  %10.2f", contasTemp[i].getNumConta(),
                    contasTemp[i].getNmCliente(),
                    contasTemp[i].getSaldo());

            String tipo = "";

            if (contasTemp[i] instanceof ContaComum) {
                tipo = "Comum";
            }
            if (contasTemp[i] instanceof ContaEspecial) {
                tipo = "Especial";
            }
            if (contasTemp[i] instanceof ContaPoupanca) {
                tipo = "Poupança";
            }
            System.out.printf("  %-12s\n", tipo);

        }

        Keyboard.waitEnter();
    }

    static void removerConta() {
        Keyboard.clrscr();
        
        char resp;

        int numConta = Keyboard.readInt("Informe o número da conta: ");

        MyIterator<Conta> it = contas.iterator();
        Conta conta = it.getFirst();

        while (conta != null) {
            if (conta.getNumConta() == numConta) {
                System.out.printf("\n\nConta: %d\nCliente: %s\n\n", conta.getNumConta(), conta.getNmCliente());

                resp = Keyboard.readChar("Deseja remover esta conta? (s/n): ");

                if (resp == 's') {
                    it.remove();
                } else {
                    break;
                }
            }
            conta = it.getNext();
        }
    }

    static void lerArquivo() {
        if (arqContas.reset()) {
            Conta conta = (Conta) arqContas.read();

            while (conta != null) {

                contas.insertAtEnd(conta);

                conta = (Conta) arqContas.read();
            }

            arqContas.closeFile();
        }
    }

    static void gravarArquivo() {
        arqContas.rewrite();

        MyIterator<Conta> it = contas.iterator();
        Conta conta = it.getFirst();

        while (conta != null) {
            arqContas.write(conta);
            conta = it.getNext();
        }

        arqContas.closeFile();
    }

    public static void main(String[] args) {
        int opcao;
        lerArquivo();
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Cadastrar Contas/Listar Contas/Efetuar "
                    + "Depósitos/Efetuar Saques/Consultar "
                    + "Conta/Creditar Rendimentos/"
                    + "Imprimir em Ordem Alfabética/"
                    + "Imprimir Ordem do Saldo/"
                    + "Remover Conta/Terminar");
            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    listarContas();
                    break;
                case 3:
                    efetuarDeposito();
                    break;
                case 4:
                    efetuarSaque();
                    break;
                case 5:
                    consultarConta();
                    break;
                case 6:
                    creditarRendimentos();
                    break;
                case 7:
                    imprimirOrdenado(7);
                    break;
                case 8:
                    imprimirOrdenado(8);
                    break;
                case 9:
                    removerConta();
                    break;
            }
        } while (opcao < 10);
        gravarArquivo();
    }
}