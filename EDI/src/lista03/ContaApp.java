package lista03;

import diversos.Keyboard;
import diversos.ObjectFile;

public class ContaApp {
	private static Conta[] contas = new Conta[100];
	private static int totContas = 0;
	private static ObjectFile arqContas = new ObjectFile("contasSort.dat");

	public static void cadastrarConta() {
		char resp;
		Conta conta;
		String nomeCliente;
		do {
			if (totContas == contas.length)
				redimensione();
			int numConta = Keyboard.readInt("Digite o numero da conta:");
			if (busca(numConta) >= 0) {
				System.out.println("Ja existe");
			} else {
				nomeCliente = Keyboard.readString("Digite o nome do cliente:");
				float saldoConta = Keyboard
						.readFloat("Digite o saldo da conta:");
				String tipoConta = Keyboard
						.readString("Digite o tipo da conta(Comum, Poupança ou Especial:");
				if (tipoConta.equalsIgnoreCase("Especial")) {
					float limite = Keyboard.readFloat("Digite o limite:");
					conta = new ContaEspecial(numConta, nomeCliente,
							saldoConta, limite);
				} else if (tipoConta.equalsIgnoreCase("Poupança")) {
					conta = new ContaPoupanca(numConta, nomeCliente, saldoConta);
				} else {
					conta = new ContaComum(numConta, nomeCliente, saldoConta);
				}
				contas[totContas] = conta;
				totContas++;
			}
			resp = Keyboard.readChar("Outra conta?");
		} while (resp == 's');
	}

	public static int busca(int numeroConta) {
		for (int i = 0; i < totContas; i++) {
			if (contas[i].getNumeroConta() == numeroConta) {
				return i;
			}
		}
		return -1;
	}

	public static void redimensione() {
		Conta[] contaTemp = new Conta[contas.length + 10];
		System.arraycopy(contas, 0, contaTemp, 0, contas.length);
		contas = contaTemp;
	}

	public static int organizar() {
		Keyboard.clrscr();
		System.out.println("Organizar por:");
		int opcao = Keyboard
				.menu("Ordem alfabética?/Saldo da conta(decrescente)?");
		return opcao;
	}

	public static void listarContas() {
		Keyboard.clrscr();
		String tipoDaConta;
		Conta[] temp = new Conta[totContas];
		System.arraycopy(contas, 0, temp, 0, totContas);
		if (temp.length > 0) {
			switch (organizar()) {
			case 1:
				ComparaNomeCliente comparaNome = new ComparaNomeCliente();
				Sort.quickSort(temp, comparaNome);
				break;
			case 2:
				ComparaSaldo comparaSaldo = new ComparaSaldo();
				Sort.quickSort(temp, comparaSaldo);
				break;
			}
		}
		Keyboard.clrscr();
		System.out
				.println("Num Conta  Nome do Cliente                   Saldo       Tipo da Conta");
		System.out
				.println("---------  ------------------------------    ----------  -------------");
		for (int i = 0; i < totContas; i++) {
			if (temp[i] instanceof ContaComum) {
				tipoDaConta = "Comum";
			} else if (temp[i] instanceof ContaEspecial) {
				tipoDaConta = "Especial";
			} else {
				tipoDaConta = "Poupança";
			}
			System.out.printf("%9d  %-30s    %10.2f  %-13s\n",
					temp[i].getNumeroConta(), temp[i].getNomeCliente(),
					temp[i].getSaldoConta(), tipoDaConta);
		}
		Keyboard.waitEnter();
	}

	public static void depositar(int numConta, float valor) {
		int pos = busca(numConta);
		if (pos >= 0) {
			contas[pos].depositar(valor);
			System.out.println("Depositado com sucesso!");
		} else {
			System.out.println("Conta não existe.");
		}
		Keyboard.waitEnter();
	}

	public static void sacar(int numConta, float valor) {
		int pos = busca(numConta);
		if (pos >= 0) {
			if (contas[pos].sacar(valor)) {
				System.out.println("Sacado com sucesso.");
			} else {
				System.out.println("Crédito insuficiente.");
			}
		} else {
			System.out.println("Conta não existe");
		}
		Keyboard.waitEnter();
	}

	public static void consultarConta(int numConta) {
		int pos = busca(numConta);
		String tipoDaConta;
		if (pos >= 0) {
			if (contas[pos] instanceof ContaComum) {
				tipoDaConta = "Comum";
			} else if (contas[pos] instanceof ContaEspecial) {
				tipoDaConta = "Especial";
			} else {
				tipoDaConta = "Poupança";
			}
			System.out
					.println("Num Conta  Nome do Cliente                   Saldo       Tipo da Conta");
			System.out
					.println("---------  ------------------------------    ----------  -------------");
			System.out.printf("%9d  %-30s    %10.2f  %-13s",
					contas[pos].getNumeroConta(), contas[pos].getNomeCliente(),
					contas[pos].getSaldoConta(), tipoDaConta);
		} else {
			System.out.println("Conta não existe");
		}
		Keyboard.waitEnter();
	}

	public static void creditarRendimentos(int numConta, double taxa) {
		int pos = busca(numConta);
		if (pos >= 0) {
			if (contas[pos] instanceof ContaPoupanca) {
				((ContaPoupanca) contas[pos]).creditar(taxa);
			} else {
				System.out.println("Essa conta não é poupança.");
			}
		} else {
			System.out.println("Conta não existente.");
		}
		Keyboard.waitEnter();
	}

	public static void graveArquivo() {
		arqContas.rewrite();
		for (int i = 0; i < totContas; i++) {
			arqContas.write(contas[i]);
		}
		arqContas.closeFile();
	}

	public static void leiaArquivo() {
		Conta conta;
		totContas = 0;
		arqContas.reset();
		conta = (Conta) arqContas.read();
		while (conta != null) {
			if (contas.length == totContas) {
				redimensione();
			}
			contas[totContas] = conta;
			totContas++;
			conta = (Conta) arqContas.read();
		}
		arqContas.closeFile();
	}

	public static void main(String[] args) {
		int opcao;
		int numConta;
		float valor;
		double taxa;
		leiaArquivo();
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Cadastrar Contas/Listar Conta/Efetuar Depósitos/"
							+ "Efetuar Saques/Consultar Conta/Creditar Rendimentos"
							+ "/Terminar");
			switch (opcao) {
			case 1:
				cadastrarConta();
				break;
			case 2:
				listarContas();
				break;
			case 3:
				numConta = Keyboard.readInt("Digite o número da conta:");
				valor = Keyboard.readFloat("Digite o valor a ser depositado:");
				depositar(numConta, valor);
				break;
			case 4:
				numConta = Keyboard.readInt("Digite o número da conta:");
				valor = Keyboard.readFloat("Digite o valor a ser sacado:");
				sacar(numConta, valor);
				break;
			case 5:
				numConta = Keyboard.readInt("Digite o número da conta:");
				consultarConta(numConta);
				break;
			case 6:
				numConta = Keyboard.readInt("Digite o número da conta:");
				taxa = Keyboard.readDouble("Digite a taxa:");
				creditarRendimentos(numConta, taxa);
				break;
			}
		} while (opcao != 7);
		graveArquivo();
		Keyboard.clrscr();
	}
}
