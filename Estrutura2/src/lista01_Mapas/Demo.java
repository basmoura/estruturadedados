package lista01_Mapas;
import java.util.Comparator;  
import utilitarios.*;

public class Demo {

	static HashMapa<Pessoa, Agenda> listaDePessoas = new HashMapa<>(10);
	static ObjectFile arqListaPessoa = new ObjectFile("listaPessoas.dat");
	
	static class ComparaNomes implements Comparator<Pessoa> {

		@Override
		public int compare(Pessoa o1, Pessoa o2) {
			return o1.getNomePessoa().compareToIgnoreCase(o2.getNomePessoa());
		}
	}
	
	static void graveArquivo() {
		arqListaPessoa.rewrite();
		arqListaPessoa.write(listaDePessoas);
		arqListaPessoa.closeFile();
	}
	
	static void leiaArquivo() {
		if(arqListaPessoa.reset()) {
			listaDePessoas = (HashMapa<Pessoa, Agenda>) arqListaPessoa.read();
			arqListaPessoa.closeFile();
		}
		else {
			listaDePessoas = new HashMapa<>(10);
		}
	}

	public static void main(String[] args) {
		int opcao;
		leiaArquivo();
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Cadastrar pessoas/Listar pessoas/Excluir pessoas/Incluir tarefas/Concluir tarefas/Listar tarefas a fazer/"
							+ "Listar tarefas concluídas/Terminar");
			switch (opcao) {
			case 1:
				cadastrarPessoas();
				break;
			case 2:
				listarPessoas();
				break;
			case 3:
				excluirPessoas();
				break;
			case 4:
				incluirTarefas();
				break;
			case 5:
				concluirTarefas();
				break;
			case 6:
				listarTarefasFazer();
				break;
			case 7:
				listarTarefasRealizadas();
				break;
			}
		} while (opcao < 8);
		System.out.println("\nFim do programa");
	}	
	
	private static void cadastrarPessoas() {
		char resp;
		Pessoa novaPessoa;
		Agenda novaAgenda;
		do {
			Keyboard.clrscr();
			int codigo = Keyboard.readInt("Entrar com o codigo da pessoa: ");			
			if (listaDePessoas.contains(new Pessoa(codigo)))
				System.out.println("Codigo ja existente");
			else {
				String nome = Keyboard.readString("Entrar com o nome: ");
				novaPessoa = new Pessoa(codigo, nome);
				novaAgenda = new Agenda(codigo);
				listaDePessoas.put(novaPessoa, novaAgenda);
				System.out.println("Pessoa cadastrada.");
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');
		graveArquivo();
	}
	
	private static void listarPessoas() {
		Keyboard.clrscr();
		System.out.println("CodPessoa  Nome da Pessoa      ");
		System.out.println("---------  --------------------");		
		ListaEncOrd<Pessoa> listaTemp = new ListaEncOrd<>();
		MyIteratorMapa<Pessoa, Agenda> it = listaDePessoas.iterator();		
		ChaveValor<Pessoa, Agenda> pessoa = it.getFirst();
		while(pessoa != null) {
			listaTemp.add(pessoa.getChave());
			pessoa = it.getNext();
		}

		Object[] pessoas = listaTemp.toArray();
		Sort.quickSort(pessoas, new ComparaNomes());
		
		for (int i = 0; i < pessoas.length; i++) {
			System.out.printf("%-9d  %-20s\n", 
					((Pessoa)pessoas[i]).getCodPessoa(),
					((Pessoa)pessoas[i]).getNomePessoa());
		}
		
		Keyboard.waitEnter();	
	}
	
	private static void excluirPessoas() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Entrar com o codigo da pessoa: ");
		ChaveValor<Pessoa, Agenda> pessoa = listaDePessoas.getChaveValor(new Pessoa(codigo));
		char resp = Keyboard.readChar("Deseja realmente remover? (S/N)");
		if (resp == 's' | resp == 'S') {
			if (pessoa != null) {
				listaDePessoas.remove(pessoa.getChave());
				System.out.println("Pessoa removida com sucesso");
				Keyboard.waitEnter();
			} else {
				System.out.println("Pessoa não encontrada");
				Keyboard.waitEnter();
			}			
		}
		graveArquivo();
	}
	
	private static void incluirTarefas() {
		char resp;
		do {
			Keyboard.clrscr();
			int codigo = Keyboard.readInt("Entrar com o codigo da pessoa: ");
			ChaveValor<Pessoa, Agenda> pessoaAgenda = listaDePessoas.getChaveValor(new Pessoa(codigo));
			if (pessoaAgenda == null){
				System.out.println("Pessoa não encontrada");
			}
			else {
				String descricao = Keyboard.readString("Entrar com a descrição: ");
				String data = Keyboard.readData("Digite a data prevista: ");
				pessoaAgenda.getValor().getListaDeTarefas().add(new TarefaFazer(pessoaAgenda.getValor().autoIncTarefa(), descricao, data));
				System.out.println("Tarefa incluida com sucesso!");
			}
			resp = Keyboard.readChar("Incluir outra tarefa?(s/n)");
		} while (resp == 's');
		graveArquivo();
	}
	
	private static void concluirTarefas() {
		char resp;
		do {
			Keyboard.clrscr();
			int codigo = Keyboard.readInt("Entrar com o codigo da pessoa:");
			ChaveValor<Pessoa, Agenda> pessoa = listaDePessoas.getChaveValor(new Pessoa(codigo));
			if (pessoa == null) {
				System.out.println("Pessoa não encontrada");

			} else {
				int codTarefa = Keyboard.readInt("Entrar com o codigo da Tarefa:");
				TarefaFazer tarefaFazer = pessoa.getValor().getListaDeTarefas().retrieve(new TarefaFazer(codTarefa));
				if (tarefaFazer == null) {
					System.out.println("Tarefa não encontrada");

				} else {
					MyDate data = new MyDate();
					String dtConclusao = data.getDate();
					pessoa.getValor().getListaTarefasRealizadas().add(new TarefaRealizada(codTarefa, tarefaFazer.getDescricao(), tarefaFazer.getDataRealizacao(), dtConclusao));
					pessoa.getValor().getListaDeTarefas().remove(tarefaFazer);
					System.out.println("Tarefa concluida com sucesso!");
				}
			}
			resp = Keyboard.readChar("Emprestar outro livro?(s/n)");
		} while (resp == 's');
		graveArquivo();
	}
	
	private static void listarTarefasFazer() {
		int codigo = Keyboard.readInt("Entrar com o codigo da pessoa:");
		ChaveValor<Pessoa, Agenda> pessoa = listaDePessoas.getChaveValor(new Pessoa(codigo));
		if (pessoa == null) {
			System.out.println("Pessoa não encontrada");

		} else {
			Keyboard.clrscr();
			System.out.println("CodTarefa  Descrição da tarefa   Data Prevista ");
			System.out.println("---------  --------------------  --------------");	
			
			MyIterator<TarefaFazer> it = pessoa.getValor().getListaDeTarefas().iterator();		
			TarefaFazer tarefaFazer = it.getFirst();
			
			while (tarefaFazer != null) {
				System.out.printf("%9d  %-20s   %14s   \n",
						tarefaFazer.getCodTarefa(), tarefaFazer.getDescricao(),
						tarefaFazer.getDataRealizacao());
				tarefaFazer = it.getNext();
			}
			Keyboard.waitEnter();
		}
	}
	
	private static void listarTarefasRealizadas() {
		int codigo = Keyboard.readInt("Entrar com o codigo da pessoa:");
		ChaveValor<Pessoa, Agenda> pessoa = listaDePessoas.getChaveValor(new Pessoa(codigo));
		if (pessoa == null) {
			System.out.println("Pessoa não encontrada");

		} else {
			Keyboard.clrscr();
			System.out.println("CodTarefa  Descrição da tarefa   Data Prevista   Data conclusão");
			System.out.println("---------  --------------------  --------------  ---------------");	
			
			MyIterator<TarefaRealizada> it = pessoa.getValor().getListaTarefasRealizadas().iterator();		
			TarefaRealizada tarefaRealizada = it.getFirst();
			
			while (tarefaRealizada != null) {
				System.out.printf("%9d  %-20s   %14s  %15s \n",
						tarefaRealizada.getCodTarefa(), tarefaRealizada.getDescricao(),
						tarefaRealizada.getDataPrevista(),tarefaRealizada.getDataRealizada());
				tarefaRealizada = it.getNext();

			}
			Keyboard.waitEnter();
		}
	}
}
