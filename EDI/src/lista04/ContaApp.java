package lista04;

import diversos.*;

public class ContaApp {
	private static Vetor<Conta> contas = new Vetor<Conta>(100);
	private static ObjectFile arqContas = new ObjectFile("contaslista04.dat");

	public static void cadastrarConta() {
		Keyboard.clrscr();
		char resp;
		Conta conta;
		String nomeCliente;
		do {
			int numConta = Keyboard.readInt("Digite o numero da conta:");
			if (busca(numConta) >= 0) {
				System.out.println("Ja existe");
			} else {
				nomeCliente = Keyboard.readString("Digite o nome do cliente:");
				float saldoConta = Keyboard
						.readFloat("Digite o saldo da conta:");
				String tipoConta = Keyboard
						.readString("Digite o tipo da conta(Comum, Poupança ou Especial):");
				if (tipoConta.equalsIgnoreCase("Especial")) {
					float limite = Keyboard.readFloat("Digite o limite:");
					conta = new ContaEspecial(numConta, nomeCliente,
							saldoConta, limite);
				} else if (tipoConta.equalsIgnoreCase("Poupança")) {
					conta = new ContaPoupanca(numConta, nomeCliente, saldoConta);
				} else {
					conta = new ContaComum(numConta, nomeCliente, saldoConta);
				}
				contas.insertAtEnd(conta);
			}
			resp = Keyboard.readChar("Outra conta(s/n)?");
		} while (resp == 's');
	}

	public static int busca(int numeroConta) {
		for (int i = 0; i < contas.size(); i++) {
			if (contas.elementAt(i).getNumeroConta() == numeroConta) {
				return i;
			}
		}
		return -1;
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
		Conta[] contasTemp = new Conta[contas.size()];
		for (int i = 0; i < contasTemp.length; i++) {
			contasTemp[i] = contas.elementAt(i);
		}
		switch (organizar()) {
		case 1:
			ComparaNomeCliente comparaNome = new ComparaNomeCliente();
			Sort.quickSort(contasTemp, comparaNome);
			break;
		case 2:
			ComparaSaldo comparaSaldo = new ComparaSaldo();
			Sort.quickSort(contasTemp, comparaSaldo);
			break;
		}
		Keyboard.clrscr();
		System.out
				.println("Num Conta  Nome do Cliente                   Saldo       Tipo da Conta");
		System.out
				.println("---------  ------------------------------    ----------  -------------");
		for (int i = 0; i < contas.size(); i++) {
			if (contasTemp[i] instanceof ContaComum) {
				tipoDaConta = "Comum";
			} else if (contasTemp[i] instanceof ContaEspecial) {
				tipoDaConta = "Especial";
			} else {
				tipoDaConta = "Poupança";
			}
			System.out.printf("%9d  %-30s    %10.2f  %-13s\n",
					contasTemp[i].getNumeroConta(),
					contasTemp[i].getNomeCliente(),
					contasTemp[i].getSaldoConta(), tipoDaConta);
		}
		Keyboard.waitEnter();
	}

	public static void depositar(int numConta, float valor) {
		int pos = busca(numConta);
		if (pos >= 0) {
			contas.elementAt(pos).depositar(valor);
			System.out.println("Depositado com sucesso!");
		} else {
			System.out.println("Conta não existe.");
		}
		Keyboard.waitEnter();
	}

	public static void sacar(int numConta, float valor) {
		int pos = busca(numConta);
		if (pos >= 0) {
			if (contas.elementAt(pos).sacar(valor)) {
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
		Keyboard.clrscr();
		String tipoDaConta;
		if (pos >= 0) {
			if (contas.elementAt(pos) instanceof ContaComum) {
				tipoDaConta = "Comum";
			} else if (contas.elementAt(pos) instanceof ContaEspecial) {
				tipoDaConta = "Especial";
			} else {
				tipoDaConta = "Poupança";
			}
			System.out
					.println("Num Conta  Nome do Cliente                   Saldo       Tipo da Conta");
			System.out
					.println("---------  ------------------------------    ----------  -------------");
			System.out.printf("%9d  %-30s    %10.2f  %-13s",
					contas.elementAt(pos).getNumeroConta(),
					contas.elementAt(pos).getNomeCliente(),
					contas.elementAt(pos).getSaldoConta(), tipoDaConta);
		} else {
			System.out.println("Conta não existe");
		}
		Keyboard.waitEnter();
	}

	public static void creditarRendimentos(int numConta, double taxa) {
		int pos = busca(numConta);
		if (pos >= 0) {
			if (contas.elementAt(pos) instanceof ContaPoupanca) {
				((ContaPoupanca) contas.elementAt(pos)).creditar(taxa);
				System.out.println("Rendimento creditado com sucesso.");
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
		MyIterator<Conta> it = contas.iterator();
		Conta conta = it.getFirst();
		while (conta != null) {
			arqContas.write(conta);
			conta = it.getNext();
		}
		arqContas.closeFile();
	}

	public static void leiaArquivo() {
		if (arqContas.reset()) {
			Conta conta = (Conta) arqContas.read();
			while (conta != null) {
				contas.insertAtEnd(conta);
				conta = (Conta) arqContas.read();
			}
			arqContas.closeFile();
		}
	}

	public static void remover(int numeroConta) {
		int pos = busca(numeroConta);
		Keyboard.clrscr();
		if (pos >= 0) {
			Conta conta = contas.removeAt(pos);
			System.out.println("Conta " + conta.getNumeroConta()
					+ " removida com sucesso");
		} else {
			System.out.println("Conta não existente.");
		}
		Keyboard.waitEnter();
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
							+ "/Remover/Terminar");
			switch (opcao) {
			case 1:
				cadastrarConta();
				break;
			case 2:
				listarContas();
				break;
			case 3:
				Keyboard.clrscr();
				numConta = Keyboard.readInt("Digite o número da conta:");
				valor = Keyboard.readFloat("Digite o valor a ser depositado:");
				depositar(numConta, valor);
				break;
			case 4:
				Keyboard.clrscr();
				numConta = Keyboard.readInt("Digite o número da conta:");
				valor = Keyboard.readFloat("Digite o valor a ser sacado:");
				sacar(numConta, valor);
				break;
			case 5:
				Keyboard.clrscr();
				numConta = Keyboard.readInt("Digite o número da conta:");
				consultarConta(numConta);
				break;
			case 6:
				Keyboard.clrscr();
				numConta = Keyboard.readInt("Digite o número da conta:");
				taxa = Keyboard.readDouble("Digite a taxa:");
				creditarRendimentos(numConta, taxa);
				break;
			case 7:
				Keyboard.clrscr();
				numConta = Keyboard.readInt("Digite o número da conta:");
				remover(numConta);
				break;
			}
		} while (opcao != 8);
		graveArquivo();
		Keyboard.clrscr();
	}
}
