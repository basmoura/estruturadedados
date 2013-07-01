/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista03;

import Biblioteca.Keyboard;
import Biblioteca.ObjectFile;
import Biblioteca.Sort;
import java.util.Comparator;

/**
 *
 * @author basmoura
 */
public class App {

    static Conta[] contas = new Conta[20];
    static int totalContas = 0;
    static int nrConta;
    static ObjectFile arqContas = new ObjectFile("contas.dat");

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

    static void redimensionar() {
        Conta[] contasTemp = new Conta[contas.length * 10];
        System.arraycopy(contas, 0, contasTemp, 0, contas.length);
        contas = contasTemp;
    }

    static boolean buscarConta(int numConta) {
        for (int i = 0; i < totalContas; i++) {
            if (numConta == contas[i].getNumConta()) {
                nrConta = i;
                return true;
            }
        }
        return false;
    }

    static void cadastrarConta() {
        char resp;
        int tipo;
        do {
            Keyboard.clrscr();

            if (contas.length == totalContas) {
                redimensionar();
            }

            int numConta = Keyboard.readInt("Informe o número da conta: ");

            if (buscarConta(numConta)) {
                System.out.println("Conta já cadastrada");
            } else {
                String nmCliente = Keyboard.readString("Informe o nome do cliente: ");
                tipo = Keyboard.menu("Comum/Especial/Poupança");

                switch (tipo) {
                    case 1:
                        ContaComum contaComum = new ContaComum(numConta);
                        contaComum.setNmCliente(nmCliente);

                        contas[totalContas] = contaComum;
                        totalContas++;
                        break;
                    case 2:
                        double limiteCredito = Keyboard.readDouble("Informe o limite de crédito: ");

                        ContaEspecial contaEspecial = new ContaEspecial(limiteCredito, numConta);
                        contaEspecial.setNmCliente(nmCliente);

                        contas[totalContas] = contaEspecial;
                        totalContas++;
                        break;
                    case 3:
                        ContaPoupanca contaPoupanca = new ContaPoupanca(numConta);
                        contaPoupanca.setNmCliente(nmCliente);

                        contas[totalContas] = contaPoupanca;
                        totalContas++;
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

        for (int i = 0; i < totalContas; i++) {
            System.out.printf("%9s  %-20s  %10.2f", contas[i].getNumConta(),
                    contas[i].getNmCliente(),
                    contas[i].getSaldo());

            String tipo = "";

            if (contas[i] instanceof ContaComum) {
                tipo = "Comum";
            }
            if (contas[i] instanceof ContaEspecial) {
                tipo = "Especial";
            }
            if (contas[i] instanceof ContaPoupanca) {
                tipo = "Poupança";
            }
            System.out.printf("  %-12s\n", tipo);
        }

        Keyboard.waitEnter();
    }

    static void efetuarDeposito() {
        Keyboard.clrscr();

        int conta = Keyboard.readInt("Informe o número da conta: ");

        if (buscarConta(conta)) {
            double valor = Keyboard.readDouble("Informe o valor a ser depositado: ");

            contas[nrConta].efetuarDeposito(valor);
            System.out.println("Depósito realizado com sucesso");
        } else {
            System.out.println("Conta inexistente.");
        }
        Keyboard.waitEnter();
    }

    static void efetuarSaque() {
        Keyboard.clrscr();

        int conta = Keyboard.readInt("Informe o número da conta: ");

        if (buscarConta(conta)) {
            double valorSacado = Keyboard.readDouble("Informe o valor a ser sacado: ");
            if (contas[nrConta].efetuarSaque(valorSacado)) {
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

        if (buscarConta(numConta)) {
            System.out.println("Num Conta  Nome do Cliente            Saldo  Tipo da Conta");
            System.out.println("---------  --------------------  ----------  -------------");
            System.out.printf("%9s  %-20s  %10.2f", contas[nrConta].getNumConta(),
                    contas[nrConta].getNmCliente(),
                    contas[nrConta].getSaldo());

            String tipo = "";
            if (contas[nrConta] instanceof ContaComum) {
                tipo = "Comum";
            }

            if (contas[nrConta] instanceof ContaEspecial) {
                tipo = "Especial";
            }

            if (contas[nrConta] instanceof ContaPoupanca) {
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
            if (contas[i] instanceof ContaPoupanca) {
                ContaPoupanca contaPoupanca = (ContaPoupanca) contas[i];
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

        Conta[] contasTemp = new Conta[totalContas];
        System.arraycopy(contas, 0, contasTemp, 0, contasTemp.length);

        switch (metodoOrdenacao) {
            case 7:
                Sort.quickSort(contasTemp, new CompareNome());
                break;
            case 8:
                Sort.quickSort(contasTemp, new CompareSaldo());
                break;
        }

        for (int i = 0; i < totalContas; i++) {
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

    static void lerArquivo() {
        if (arqContas.reset()) {
            Conta conta = (Conta) arqContas.read();

            while (conta != null) {

                if (totalContas == contas.length) {
                    redimensionar();
                }

                contas[totalContas] = conta;
                totalContas++;
                conta = (Conta) arqContas.read();
            }
            arqContas.closeFile();
        }
    }

    static void gravarArquivo() {
        arqContas.rewrite();

        for (int i = 0; i < totalContas; i++) {
            arqContas.write(contas[i]);
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
                    + "Imprimir Ordem do Saldo/Terminar");
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
            }
        } while (opcao < 9);
        gravarArquivo();
    }
}