package lista07;

import diversos.*;
import diversos.Heap.TipoHeap;

public class Main {

	public enum Ordem {
		Pessoa, Salario, Idade
	};

	private static Treap<Pessoa> pessoas = new Treap<Pessoa>(false);
	private static Treap<ChaveValor<Float, Pessoa>> salarios = new Treap<ChaveValor<Float, Pessoa>>(
			true);
	private static Heap<ChaveValor<Integer, Pessoa>> idades = new Heap<ChaveValor<Integer, Pessoa>>(
			50, TipoHeap.MaxHeap);

	private static boolean Incluir(Pessoa pessoa) {
		if (!pessoas.contains(pessoa)) {
			ChaveValor<Float, Pessoa> salario = new ChaveValor<Float, Pessoa>(
					pessoa.getSalario(), pessoa);
			ChaveValor<Integer, Pessoa> idade = new ChaveValor<Integer, Pessoa>(
					pessoa.getIdade(), pessoa);
			pessoas.add(pessoa);
			salarios.add(salario);
			idades.add(idade);
			return true;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	private static void Listar(MyIterator iterator) {
		Keyboard.clrscr();
		Object obj = iterator.getFirst();
		if (obj == null) {
			return;
		}
		Ordem ordem = GetOrdem(obj);
		PrintOrdenado(ordem);
		while (obj != null) {
			Pessoa pessoa = ObjectToPessoa(obj);
			PrintOrdenado(ordem, pessoa);
			obj = iterator.getNext();
		}
	}

	@SuppressWarnings("rawtypes")
	private static Ordem GetOrdem(Object obj) {
		if (obj instanceof Pessoa) {
			return Ordem.Pessoa;
		}
		if (obj instanceof ChaveValor) {
			if (((ChaveValor) obj).getChave() instanceof Float) {
				return Ordem.Salario;
			} else {
				return Ordem.Idade;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private static Pessoa ObjectToPessoa(Object obj) {
		Ordem ordem = GetOrdem(obj);
		switch (ordem) {
		case Pessoa:
			return (Pessoa) obj;
		default:
			return (Pessoa) ((ChaveValor) obj).getValor();
		}
	}

	private static void PrintOrdenado(Ordem ordem) {
		System.out.println("--------------- " + ordem + " ---------------");
		switch (ordem) {
		case Pessoa:
			System.out.println("Nome                Sal�rio      Idade");
			System.out.println("------------------  -----------  -----");
			break;
		case Salario:
			System.out.println("Sal�rio      Nome                 Idade");
			System.out.println("-----------  -------------------  -----");
			break;
		case Idade:
			System.out.println("Idade  Nome                Sal�rio    ");
			System.out.println("-----  ------------------  ----------");
			break;
		}
	}

	private static void PrintOrdenado(Ordem ordem, Pessoa pessoa) {
		switch (ordem) {
		case Pessoa:
			System.out.printf("%-18s  %-11.2f  %-5d\n", pessoa.getNome(),
					pessoa.getSalario(), pessoa.getIdade());
			break;
		case Salario:
			System.out.printf("%-11.2f  %-19s  %-5d\n", pessoa.getSalario(),
					pessoa.getNome(), pessoa.getIdade());
			break;
		case Idade:
			System.out.printf("%-5d  %-18s  %-10.2f\n", pessoa.getIdade(),
					pessoa.getNome(), pessoa.getSalario());
			break;
		}
	}

	private static boolean Remover() {
		if (idades.size() > 0) {
			ChaveValor<Integer, Pessoa> pessoaRemovida = idades.remove();
			return Remover(pessoaRemovida.getValor(), true);
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	private static boolean Remover(Pessoa pessoa, boolean removerDeSalario) {
		if (!pessoas.remove(pessoa)) {
			return false;
		}
		MyIterator iterator;

		if (removerDeSalario) {
			iterator = salarios.iterator();
		} else {
			iterator = idades.iterator();
		}

		Object obj = iterator.getFirst();
		while (obj != null) {
			Pessoa p = ObjectToPessoa(obj);
			if (pessoa.compareTo(p) == 0) {
				iterator.remove();
			}
			obj = iterator.getNext();
		}

		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean Remover(float salario) {
		boolean achou = false;
		MyIterator iterator = salarios.iterator();
		ChaveValor<Float, Pessoa> obj = (ChaveValor) iterator.getFirst();
		while (obj != null) {
			if (obj.getChave() == salario) {
				Remover(obj.getValor(), false);
				iterator.remove();
				achou = true;
			}
			obj = (ChaveValor) iterator.getNext();
		}
		return achou;
	}

	private static void MenuIncluir() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Insira o c�digo da pessoa: ");
		String nome = Keyboard.readString("Insira o nome da pessoa: ");
		float salario = Keyboard.readFloat("Insira o sal�rio da pessoa: ");
		int idade = Keyboard.readInt("Insira a idade da pessoa: ");
		Pessoa pessoa = new Pessoa(codigo, nome, salario, idade);
		if (Incluir(pessoa)) {
			System.out.println("Pessoa adicionada com sucesso!");
		} else {
			System.out.println("Erro ao acidionar a pessoa.");
		}
		Keyboard.waitEnter();
		if (Keyboard.readChar("Gostaria de adicionar outra pessoa? (S/N) = ") == 'S') {
			MenuIncluir();
		}
	}

	@SuppressWarnings("rawtypes")
	private static void MenuListar() {
		Keyboard.clrscr();
		int ordemInt = Keyboard.menu("Pessoa/Sal�rio/Idade");
		System.out.println(Ordem.values()[ordemInt - 1]);
		Ordem ordem = Ordem.values()[ordemInt - 1];
		MyIterator iterator;
		if (ordem != Ordem.Idade) {
			iterator = ordem == Ordem.Pessoa ? pessoas.iterator() : salarios
					.iterator();
		} else {
			iterator = idades.iterator();
		}
		Listar(iterator);
		Keyboard.waitEnter();
	}

	private static void MenuRemover() {
		Keyboard.clrscr();
		if (Remover()) {
			System.out.println("Removido com sucesso!");
		} else {
			System.out.println("Erro ao remover.");
		}
		Keyboard.waitEnter();
		if (Keyboard.readChar("Gostaria de remover novamente? (S/N) = ") == 'S') {
			MenuRemover();
		}
	}

	private static void MenuRemoverSalario() {
		Keyboard.clrscr();
		float salario = Keyboard.readFloat("Insira o valor do sal�rio: ");
		if (Remover(salario)) {
			System.out.println("Removido com sucesso!");
		} else {
			System.out.println("Erro ao remover.");
		}
		Keyboard.waitEnter();
		if (Keyboard
				.readChar("Gostaria de remover outras pessoas com sal�rio? (S/N) = ") == 'S') {
			MenuRemoverSalario();
		}
	}

	private static int Menu() {
		Keyboard.clrscr();
		return Keyboard
				.menu("Incluir Pessoa/Listar Pessoas/Remover/Remover por sal�rio/Sair");
	}

	public static void main(String[] args) {

		int menu = Menu();
		while (menu != 5) {
			switch (menu) {
			case 1:
				MenuIncluir();
				break;
			case 2:
				MenuListar();
				break;
			case 3:
				MenuRemover();
				break;
			case 4:
				MenuRemoverSalario();
				break;
			}
			menu = Menu();
		}

		System.out.println("Programa Finalizado.");
	}

}
