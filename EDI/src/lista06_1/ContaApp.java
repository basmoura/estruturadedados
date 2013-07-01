package lista06_1;

import diversos.*;

public class ContaApp {

	private static ListaSeqNaoOrd<Conta> lista = new ListaSeqNaoOrd<Conta>();
	private static ListaSeqNaoOrd<Movimento> listaMovimentos = new ListaSeqNaoOrd<Movimento>();
	private static ObjectFile arqContas = new ObjectFile("contasLista06.dat");
	private static ObjectFile arqMovimentos = new ObjectFile("movimentos.dat");

	public static void cadastrarConta() {
		char resp;
		Conta conta;
		String nomeCliente;
		do {
			int numConta = Keyboard.readInt("Digite o numero da conta:");
			Conta contaTemp = new ContaComum(numConta);
			if (lista.retrieve(contaTemp) != null) {
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
				lista.add(conta);
			}
			resp = Keyboard.readChar("Outra conta?");
		} while (resp == 's');
	}

	public static void listarContas() {
		Keyboard.clrscr();
		Conta conta;
		String tipoDaConta;
		System.out
				.println("Num Conta  Nome do Cliente                   Saldo       Tipo da Conta");
		System.out
				.println("---------  ------------------------------    ----------  -------------");
		MyIterator<Conta> it = lista.iterator();
		conta = it.getFirst();
		while (conta != null) {
			if (conta instanceof ContaComum) {
				tipoDaConta = "Comum";
			} else if (conta instanceof ContaEspecial) {
				tipoDaConta = "Especial";
			} else {
				tipoDaConta = "Poupança";
			}
			System.out.printf("%9d  %-30s    %10.2f  %-13s\n",
					conta.getNumeroConta(), conta.getNomeCliente(),
					conta.getSaldoConta(), tipoDaConta);
			conta = it.getNext();
		}
		Keyboard.waitEnter();
	}

	public static void depositar() {
		Keyboard.clrscr();
		int numConta = Keyboard.readInt("Digite o número da conta:");
		float valor = Keyboard.readFloat("Digite o valor a ser depositado:");
		Conta contaBusca = new ContaComum(numConta);
		Conta conta = lista.retrieve(contaBusca);
		Movimento movimento;
		if (conta != null) {
			conta.depositar(valor);
			String data = Keyboard.readData("Digite a data do crédito:");
			movimento = new Movimento(numConta, data, 'c', valor);
			listaMovimentos.add(movimento);
			System.out.println("Depositado com sucesso!");
		} else {
			System.out.println("Conta não existe.");
		}
		Keyboard.waitEnter();
	}

	public static void sacar() {
		Keyboard.clrscr();
		int numConta = Keyboard.readInt("Digite o número da conta:");
		float valor = Keyboard.readFloat("Digite o valor a ser sacado:");
		Conta contaBusca = new ContaComum(numConta);
		Conta conta = lista.retrieve(contaBusca);
		Movimento movimento;
		if (conta != null) {
			if (conta.sacar(valor)) {
				String data = Keyboard.readData("Digite a data do débito: ");
				movimento = new Movimento(numConta, data, 'd', valor);
				listaMovimentos.add(movimento);
				System.out.println("Sacado com sucesso.");
			} else {
				System.out.println("Crédito insuficiente.");
			}
		} else {
			System.out.println("Conta não existe");
		}
		Keyboard.waitEnter();
	}

	public static void consultarConta() {
		Keyboard.clrscr();
		int numConta = Keyboard.readInt("Digite o número da conta:");
		Conta contaBusca = new ContaComum(numConta);
		Conta conta = lista.retrieve(contaBusca);
		String tipoDaConta;
		if (conta != null) {
			if (conta instanceof ContaComum) {
				tipoDaConta = "Comum";
			} else if (conta instanceof ContaEspecial) {
				tipoDaConta = "Especial";
			} else {
				tipoDaConta = "Poupança";
			}
			System.out
					.println("Num Conta  Nome do Cliente                   Saldo       Tipo da Conta");
			System.out
					.println("---------  ------------------------------    ----------  -------------");
			System.out.printf("%9d  %-30s    %10.2f  %-13s",
					conta.getNumeroConta(), conta.getNomeCliente(),
					conta.getSaldoConta(), tipoDaConta);
		} else {
			System.out.println("Conta não existe");
		}
		Keyboard.waitEnter();
	}

	public static void creditarRendimentos() {
		int numConta = Keyboard.readInt("Digite o número da conta:");
		double taxa = Keyboard.readDouble("Digite a taxa:");
		Conta contaBusca = new ContaComum(numConta);
		Conta conta = lista.retrieve(contaBusca);
		if (conta != null) {
			if (conta instanceof ContaPoupanca) {
				((ContaPoupanca) conta).creditar(taxa);
			} else {
				System.out.println("Essa conta não é poupança.");
			}
		} else {
			System.out.println("Conta não existente.");
		}
		Keyboard.waitEnter();
	}

	public static void removerConta() {
		int numConta = Keyboard.readInt("Digite o número da conta:");
		Conta conta = new ContaComum(numConta);
		char resp = Keyboard.readChar("Deseja remover a conta "
				+ conta.getNumeroConta() + " ?(s/n)");
		if (resp == 's') {
			if (lista.remove(conta)) {
				MyIterator<Movimento> it = listaMovimentos.iterator();
				Movimento movimentoBusca = new Movimento(numConta);
				Movimento movimento = it.getFirst();
				while (movimento != null) {
					if (movimentoBusca.compareTo(movimento) == 0) {
						it.remove();
					}
					movimento = it.getNext();
				}
				System.out.println("Conta removida com sucesso!");
			} else {
				System.out.println("Conta inexistente");
			}
		} else {
			System.out.println("Conta não excluída!");
		}
		Keyboard.waitEnter();
	}

	public static void extrato() {
		Keyboard.clrscr();
		int numConta = Keyboard.readInt("Digite o número da conta:");
		Conta contaBusca = new ContaComum(numConta);
		Conta conta = lista.retrieve(contaBusca);
		String tipoDaConta;
		if (conta != null) {
			if (conta instanceof ContaComum) {
				tipoDaConta = "Comum";
			} else if (conta instanceof ContaEspecial) {
				tipoDaConta = "Especial";
			} else {
				tipoDaConta = "Poupança";
			}
			System.out.println("Número da Conta: " + numConta);
			System.out.println("Tipo da Conta: " + tipoDaConta);
			System.out.println("Nome do Cliente: " + conta.getNomeCliente());
			MyIterator<Movimento> it = listaMovimentos.iterator();
			Movimento movimento = it.getFirst();
			System.out.println("\nData Mov     Valor   Tipo Mov");
			while (movimento != null) {
				if (movimento.getNumeroConta() == numConta) {
					System.out.printf("%10s   %5.2f    %1s\n",
							movimento.getData(), movimento.getValor(),
							movimento.getTipo());
				}
				movimento = it.getNext();
			}
			System.out.println("\nSaldo atual: " + conta.getSaldoConta());
			Keyboard.waitEnter();
		}
	}

	public static void graveArquivo() {
		arqContas.rewrite();
		MyIterator<Conta> it = lista.iterator();
		Conta conta = it.getFirst();
		while (conta != null) {
			arqContas.write(conta);
			conta = it.getNext();
		}
		arqContas.closeFile();
		// Arquivo dos movimentos
		arqMovimentos.rewrite();
		MyIterator<Movimento> itMovimentos = listaMovimentos.iterator();
		Movimento movimento = itMovimentos.getFirst();
		while (movimento != null) {
			arqMovimentos.write(movimento);
			movimento = itMovimentos.getNext();
		}
		arqMovimentos.closeFile();
	}

	public static void leiaArquivo() {
		Conta conta;
		arqContas.reset();
		conta = (Conta) arqContas.read();
		while (conta != null) {
			lista.add(conta);
			conta = (Conta) arqContas.read();
		}
		arqContas.closeFile();
		// Arquivo dos movimentos
		Movimento movimento;
		arqMovimentos.reset();
		movimento = (Movimento) arqMovimentos.read();
		while (movimento != null) {
			listaMovimentos.add(movimento);
			movimento = (Movimento) arqMovimentos.read();
		}
		arqMovimentos.closeFile();
	}

	public static void main(String[] args) {
		int opcao;
		leiaArquivo();
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Cadastrar Contas/Listar Conta/Efetuar Depósitos/"
							+ "Efetuar Saques/Consultar Conta/Creditar Rendimentos"
							+ "/Remover conta/Imprimir extrato/Terminar");
			switch (opcao) {
			case 1:
				cadastrarConta();
				break;
			case 2:
				listarContas();
				break;
			case 3:
				depositar();
				break;
			case 4:
				sacar();
				break;
			case 5:
				consultarConta();
				break;
			case 6:
				creditarRendimentos();
				break;
			case 7:
				removerConta();
				break;
			case 8:
				extrato();
				break;
			}
		} while (opcao != 9);
		graveArquivo();
		Keyboard.clrscr();
	}
}
