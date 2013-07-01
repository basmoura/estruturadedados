package lista01;

import java.util.Scanner;

public class ManipulaApp {
	public static Scanner scan = new Scanner(System.in);

	public static void menu() {
		System.out.println("::: Menu :::");
		System.out.println("1 - Cadastrar Pessoa.");
		System.out.println("2 - Listar Pessoa.");
		System.out.println("3 - Consultar Pessoa.");
		System.out.println("0 - Terminar.");
	}

	public static Pessoa read() {
		System.out.println("Digite o código:");
		int codigo = scan.nextInt();
		System.out.println("Digite o nome:");
		String nome = scan.next();
		System.out.println("Digite a data de nascimento(dd/mm/aaaa):");
		String dataDeNascimento = scan.next();
		Pessoa p = new Pessoa(codigo, nome, dataDeNascimento);
		return p;
	}

	public static void main(String[] args) {
		Manipula manipula = new Manipula();
		manipula.leiaArquivo();
		int opcao;
		do {
			menu();
			opcao = scan.nextInt();
			try {
				switch (opcao) {
				case 1:
					if (manipula.cadastrar(read())) {
						System.out.println("Cadastrado com sucesso!");
					}
					break;
				case 2:
					System.out.print("Código:\t\t");
					System.out.print("Nome:\t\t");
					System.out.println("Data de Nascimento:");
					System.out.print("-------------\t");
					System.out.print("-------------\t");
					System.out.println("-------------");
					for (int i = 0; i < manipula.getTamanhoReal(); i++) {
						System.out.println(manipula.listar()[i].toString());
					}
					break;
				case 3:
					System.out.println("Digite o código:");
					int codigo = scan.nextInt();
					System.out.print("Código:\t\t");
					System.out.print("Nome:\t\t");
					System.out.println("Data de Nascimento:");
					System.out.print("-------------\t");
					System.out.print("-------------\t");
					System.out.println("-------------");
					System.out.println(manipula.consultar(codigo));
					break;
				case 0:
					System.out.println("Programa finalizado.");
					break;
				default:
					System.out.println("Escolha inexistente.");

				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		} while (opcao != 0);
		manipula.gravaArquivo();
	}
}
