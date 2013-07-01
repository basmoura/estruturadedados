package prova_questao1;

import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

import lista04_ArvoreBusca.Aluno;
import utilitarios.*;

public class Demo {	
	static ListaEncOrd<Pessoa> leoPessoas = new ListaEncOrd<>();
	static HashMapa<Integer, SkipList<Pessoa>> hmIdadePessoas = new HashMapa<>(10);
	static HashMapa<Float, SkipList<Pessoa>> hmSalPessoas = new HashMapa<>(10);
	
	static class ComparaNomes implements Comparator<Pessoa> {

		@Override
		public int compare(Pessoa o1, Pessoa o2) {
			return o1.getNomePessoa().compareToIgnoreCase(o2.getNomePessoa());
		}
	}

	public static void main(String[] args) {
		
		try {
			FileReader arq = new FileReader("pessoas.txt");
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();

			while (linha != null) {
				int codigo = Integer.parseInt(linha.substring(0, 8).trim());
				String nome = linha.substring(8, 38).trim();
				int idade = Integer.parseInt(linha.substring(38, 41).trim());
				float salario = Float.parseFloat(linha.substring(41, 51).replace(",", ".").trim());
				
				incluirPessoa(codigo, nome, idade, salario);
				linha = lerArq.readLine();
			}

			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}
	
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Incluir Pessoa/Excluir Pessoa/Consultar por código/Listar por código/Listar em ordem alfabética/"
							+ "Listar por idade/Listar por salário/Listar Idade e Salario/Acrescimo 10%/Terminar");
			switch (opcao) {
			case 1:
				incluirPessoa();
				break;
			case 2:
				excluirPessoa();
				break;
			case 3:
				consultarPorCodigo();
				break;
			case 4:
				listarPorCodigo();
				break;
			case 5:
				listarEmOrdemAlfabetica();
				break;
			case 6:
				listarPorIdade();
				break;
			case 7:
				listarPorSalarioAsc();
				break;
			case 8:
				solicitaIdadeSalario();
				break;
			case 9:
				acrescimoSalario();
			}
		} while (opcao < 10);
		System.out.println("\nFim do programa");
	}
	
	

	//Este metodo lista por salário e em ordem Ascendente
	private static void listarPorSalarioAsc() {
		Keyboard.clrscr();
		System.out.println("Codigo  Nome                            Idade  Salário");
		System.out.println("------  ------------------------------  -----  ----------");
		ListaEncOrd<Float> listaTemp = new ListaEncOrd<>();
		MyIteratorMapa<Float, SkipList<Pessoa>> it = hmSalPessoas.iterator();
		ChaveValor<Float, SkipList<Pessoa>> pessoas = it.getFirst();
		while (pessoas != null) {
			listaTemp.add(pessoas.getChave());
			pessoas = it.getNext();
		}
		if (!listaTemp.isEmpty()) {
			Object[] chaves = listaTemp.toArray();
			for (int i = 0; i < chaves.length; i++) {
				MyIterator<Pessoa> itSkip = hmSalPessoas.getValor((Float)chaves[i]).iterator();
				Pessoa pessoa = itSkip.getFirst();
				int count = 0;
				while(pessoa != null) {
					System.out.printf("%6d  %-30s  %5s  %10.2f\n",
							pessoa.getCodPessoa(), pessoa.getNomePessoa(),
							pessoa.getIdadePessoa(), pessoa.getSalarioPessoa());
					if(count == 20)
					{
						Keyboard.waitEnter();
						System.out.println("Codigo  Nome                            Idade  Salário");
						System.out.println("------  ------------------------------  -----  ----------");
						count = 0;
					}
					pessoa = itSkip.getNext();
					count++;
				}
			}
		}
		Keyboard.waitEnter();
	}

	//Este metodo está listando por salário, mas não lista em ordem Ascendente
	private static void listarPorSalario() {
		Keyboard.clrscr();
		System.out.println("Codigo  Nome                            Idade  Salário");
		System.out.println("------  ------------------------------  -----  ----------");
		MyIteratorMapa<Float, SkipList<Pessoa>> it = hmSalPessoas.iterator();
		ChaveValor<Float, SkipList<Pessoa>> pessoas = it.getFirst();
		while (pessoas != null) {
			MyIterator<Pessoa> itSkip = pessoas.getValor().iterator();		
			Pessoa pessoa = itSkip.getFirst();
			int count = 0;
			while(pessoa != null) {
				System.out.printf("%6d  %-30s  %5s  %10.2f\n",
						pessoa.getCodPessoa(), pessoa.getNomePessoa(),
						pessoa.getIdadePessoa(), pessoa.getSalarioPessoa());
				if(count == 20)
				{
					Keyboard.waitEnter();
					System.out.println("Codigo  Nome                            Idade  Salário");
					System.out.println("------  ------------------------------  -----  ----------");
					count = 0;
				}
				pessoa = itSkip.getNext();
				count++;
			}
			pessoas = it.getNext();
			
		}
		Keyboard.waitEnter();
	}
	
	//Este metodo lista por idade e em ordem Ascendente
	private static void listarPorIdadeAsc() {
		Keyboard.clrscr();
		System.out.println("Codigo  Nome                            Idade  Salário");
		System.out.println("------  ------------------------------  -----  ----------");
		ListaEncOrd<Integer> listaTemp = new ListaEncOrd<>();
		MyIteratorMapa<Integer, SkipList<Pessoa>> it = hmIdadePessoas.iterator();
		ChaveValor<Integer, SkipList<Pessoa>> pessoas = it.getFirst();
		while (pessoas != null) {
			listaTemp.add(pessoas.getChave());
			pessoas = it.getNext();
		}
		if (!listaTemp.isEmpty()) {
			Object[] chaves = listaTemp.toArray();
			for (int i = 0; i < chaves.length; i++) {
				MyIterator<Pessoa> itSkip = hmIdadePessoas.getValor((Integer)chaves[i]).iterator();
				Pessoa pessoa = itSkip.getFirst();
				int count = 0;
				while(pessoa != null) {
					System.out.printf("%6d  %-30s  %5s  %10.2f\n",
							pessoa.getCodPessoa(), pessoa.getNomePessoa(),
							pessoa.getIdadePessoa(), pessoa.getSalarioPessoa());
					if(count == 20)
					{
						Keyboard.waitEnter();
						System.out.println("Codigo  Nome                            Idade  Salário");
						System.out.println("------  ------------------------------  -----  ----------");
						count = 0;
					}
					pessoa = itSkip.getNext();
					count++;
				}
			}
		}
		Keyboard.waitEnter();		
	}

	//Este metodo está listando por idade, mas não lista em ordem Ascendente
	private static void listarPorIdade() {
		/*
		Keyboard.clrscr();
		System.out.println("Codigo  Nome                            Idade  Salário");
		System.out.println("------  ------------------------------  -----  ----------");
		MyIteratorMapa<Integer, SkipList<Pessoa>> it = hmIdadePessoas.iterator();
		ChaveValor<Integer, SkipList<Pessoa>> pessoas = it.getFirst();
		while (pessoas != null) {
			MyIterator<Pessoa> itSkip = pessoas.getValor().iterator();		
			Pessoa pessoa = itSkip.getFirst();
			int count = 0;
			while(pessoa != null) {
				System.out.printf("%6d  %-30s  %5s  %10.2f\n",
						pessoa.getCodPessoa(), pessoa.getNomePessoa(),
						pessoa.getIdadePessoa(), pessoa.getSalarioPessoa());
				if(count == 20)
				{
					Keyboard.waitEnter();
					System.out.println("Codigo  Nome                            Idade  Salário");
					System.out.println("------  ------------------------------  -----  ----------");
					count = 0;
				}
				pessoa = itSkip.getNext();
				count++;
			}
			pessoas = it.getNext();			
		}
		Keyboard.waitEnter();
		*/	
		Keyboard.clrscr();
		int idade = Keyboard.readInt("Entrar com a idade");
		ChaveValor<Integer, SkipList<Pessoa>> cvIdade = hmIdadePessoas.getChaveValor(idade);
		if (cvIdade == null) {
			System.out.println("Não existem pessoas com esta idade");
			Keyboard.waitEnter();
		} else {
			System.out.println("Codigo  Nome                            Idade  Salário");
			System.out.println("------  ------------------------------  -----  ----------");
			MyIterator<Pessoa> it = cvIdade.getValor().iterator();
			Pessoa pessoa = it.getFirst();			
			ListaEncOrd<Pessoa> listaTemp = new ListaEncOrd<Pessoa>();
			while (pessoa != null) {
				listaTemp.add(pessoa);
				pessoa = it.getNext();
			}
			Object[] pessoas = listaTemp.toArray();
			Sort.quickSort(pessoas, new ComparaNomes());
			
			for (int i = 0; i < pessoas.length; i++) {
				System.out.printf("%6d  %-30s  %5s  %10.2f\n",
						((Pessoa)pessoas[i]).getCodPessoa(), 
						((Pessoa)pessoas[i]).getNomePessoa(), 
						((Pessoa)pessoas[i]).getIdadePessoa(), 
						((Pessoa)pessoas[i]).getSalarioPessoa());
			}
			Keyboard.waitEnter();
		}		
		
	}

	private static void listarEmOrdemAlfabetica() {
		Keyboard.clrscr();
		System.out.println("Codigo  Nome                            Idade  Salário");
		System.out.println("------  ------------------------------  -----  ----------");
		if (!leoPessoas.isEmpty()) {
			Object[] pessoas = leoPessoas.toArray();
			Sort.quickSort(pessoas, new ComparaNomes());
			int count = 0;
			for (int i = 0; i < pessoas.length; i++) {
				System.out.printf("%6d  %-30s  %5s  %10.2f\n",
						((Pessoa)pessoas[i]).getCodPessoa(), ((Pessoa)pessoas[i]).getNomePessoa(),
						((Pessoa)pessoas[i]).getIdadePessoa(), ((Pessoa)pessoas[i]).getSalarioPessoa());
				if(count == 20)
				{
					Keyboard.waitEnter();
					System.out.println("Codigo  Nome                            Idade  Salário");
					System.out.println("------  ------------------------------  -----  ----------");
					count = 0;
				}
				count++;
			}
		}
		Keyboard.waitEnter();
	}	

	private static void consultarPorCodigo() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Entrar com o codigo da pessoa: ");
		Pessoa pessoa = leoPessoas.retrieve(new Pessoa(codigo));
		
		if (pessoa != null){
			System.out.println("Codigo  Nome                            Idade  Salário");
			System.out.println("------  ------------------------------  -----  ----------");	
			System.out.printf("%6d  %-30s  %5s  %10.2f\n",
					pessoa.getCodPessoa(), pessoa.getNomePessoa(),
					pessoa.getIdadePessoa(), pessoa.getSalarioPessoa());
			Keyboard.waitEnter();
		}
		else {
			System.out.println("Pessoa não encontrada");
			Keyboard.waitEnter();
		}		
	}

	private static void excluirPessoa() {
		Keyboard.clrscr();
		int codigo = Keyboard.readInt("Entrar com o codigo da pessoa: ");
		Pessoa pessoa = leoPessoas.retrieve(new Pessoa(codigo));
		if (pessoa != null){
			hmIdadePessoas.getChaveValor(pessoa.getIdadePessoa()).getValor().remove(pessoa);
			hmSalPessoas.getChaveValor(pessoa.getSalarioPessoa()).getValor().remove(pessoa);
			leoPessoas.remove(pessoa);
			System.out.println("Pessoa removida com sucesso");
			Keyboard.waitEnter();
		}
		else {
			System.out.println("Pessoa não encontrada");
			Keyboard.waitEnter();
		}
	}

	private static void incluirPessoa() {
		char resp;
		Pessoa novaPessoa;
		do {
			Keyboard.clrscr();
			int codigo = Keyboard.readInt("Entrar com o codigo da pessoa: ");
			if (leoPessoas.contains(new Pessoa(codigo)))
				System.out.println("Codigo ja existente");
			else {
				String nome = Keyboard.readString("Entrar com o nome: ");
				int idade = Keyboard.readInt("Entrar com a idade: ");
				float salario = Keyboard.readFloat("Entrar com o salário: ");
				novaPessoa = new Pessoa(codigo, nome, idade, salario);
				ChaveValor<Integer, SkipList<Pessoa>> cvIdade = hmIdadePessoas.getChaveValor(idade);
				ChaveValor<Float, SkipList<Pessoa>> cvSalario = hmSalPessoas.getChaveValor(salario);
				if (cvIdade != null)
					cvIdade.getValor().add(novaPessoa);
				else
					hmIdadePessoas.put(idade, new SkipList<Pessoa>(10));								
				if(cvSalario != null)
					cvSalario.getValor().add(novaPessoa);
				else
					hmSalPessoas.put(salario, new SkipList<Pessoa>(10));
				leoPessoas.add(novaPessoa);
				System.out.println("Pessoa cadastrada.");
			}
			resp = Keyboard.readChar("Outra inclusao (s/n)? ");
		} while (resp == 's');	
	}

	private static void incluirPessoa(int codigo, String nome, int idade,
			float salario) {
		Pessoa novaPessoa;
		if (leoPessoas.contains(new Pessoa(codigo)))
			return;
		else {
			novaPessoa = new Pessoa(codigo, nome, idade, salario);
			ChaveValor<Integer, SkipList<Pessoa>> cvIdade = hmIdadePessoas.getChaveValor(idade);
			ChaveValor<Float, SkipList<Pessoa>> cvSalario = hmSalPessoas.getChaveValor(salario);
			if (cvIdade != null)
				cvIdade.getValor().add(novaPessoa);
			else
				hmIdadePessoas.put(idade, new SkipList<Pessoa>(10));
			if(cvSalario != null)
				cvSalario.getValor().add(novaPessoa);
			else
				hmSalPessoas.put(salario, new SkipList<Pessoa>(10));
			leoPessoas.add(novaPessoa);
		}
	}
	
	
	
	private static ArvoreBusca<Pessoa> Idade_e_Salario (int idade, float salario){		
		ArvoreBusca<Pessoa> idadeSalario = new ArvoreBusca<>();
		ChaveValor<Integer, SkipList<Pessoa>> cvIdade = hmIdadePessoas.getChaveValor(idade);		
		
		MyIterator<Pessoa> itPessoa = cvIdade.getValor().iterator();		
		Pessoa pessoa = itPessoa.getFirst();		
		
		while (pessoa != null) {
			if (pessoa.getIdadePessoa() == idade && pessoa.getSalarioPessoa() == salario) {
				idadeSalario.add(pessoa);				
			}
			pessoa = itPessoa.getNext();
		}
		return idadeSalario;	
				
		}		
	
	
	private static void solicitaIdadeSalario(){
		Keyboard.clrscr();
		int idade = Keyboard.readInt("Entrar com a idade da pessoa:");
		ChaveValor<Integer, SkipList<Pessoa>> cvIdade = hmIdadePessoas.getChaveValor(idade);
		if (cvIdade == null) {
			System.out.println("Não existem pessoas com esta idade");
			Keyboard.waitEnter();			
		} else {
			float salario = Keyboard.readFloat("Entrar com o salário da pessoa:");
			ChaveValor<Float, SkipList<Pessoa>> cvSalario = hmSalPessoas.getChaveValor(salario);
			if (cvSalario == null) {
				System.out.println("Não existem pessoas com este salário");
				Keyboard.waitEnter();		
			} else {
				ArvoreBusca<Pessoa> pessoasIdadeSalario = Idade_e_Salario(idade, salario);
				
				Keyboard.clrscr();
				System.out.println("Codigo  Nome                            Idade  Salário");
				System.out.println("------  ------------------------------  -----  ----------");	
				MyIterator<Pessoa> it = pessoasIdadeSalario.iterator();
				Pessoa pessoaIdadeSalario = it.getFirst();
				while (pessoaIdadeSalario != null) {
					System.out.printf("%6d  %-30s  %5s  %10.2f\n",
							pessoaIdadeSalario.getCodPessoa(), pessoaIdadeSalario.getNomePessoa(),
							pessoaIdadeSalario.getIdadePessoa(), pessoaIdadeSalario.getSalarioPessoa());
					pessoaIdadeSalario = it.getNext();
				}
				
				Keyboard.waitEnter();
			}
		}		
	}
	
	private static void listarPorCodigo() {
		Keyboard.clrscr();
		System.out.println("Codigo  Nome                            Idade  Salário");
		System.out.println("------  ------------------------------  -----  ----------");	
		
		MyIterator<Pessoa> it = leoPessoas.iterator();		
		Pessoa pessoa = it.getFirst();
		int count = 0;
		while(pessoa != null) {
			System.out.printf("%6d  %-30s  %5s  %10.2f\n",
					pessoa.getCodPessoa(), pessoa.getNomePessoa(),
					pessoa.getIdadePessoa(), pessoa.getSalarioPessoa());
			if(count == 20)
			{
				Keyboard.waitEnter();
				System.out.println("Codigo  Nome                            Idade  Salário");
				System.out.println("------  ------------------------------  -----  ----------");
				count = 0;
			}
			pessoa = it.getNext();
			count++;
		}
		Keyboard.waitEnter();	
	}	
	
	private static void acrescimoSalario() {
		Keyboard.clrscr();
		int idade = Keyboard.readInt("Entrar com a idade da pessoa:");		
		
		ChaveValor<Integer, SkipList<Pessoa>> cvIdade = hmIdadePessoas.getChaveValor(idade);		
		MyIterator<Pessoa> it = cvIdade.getValor().iterator();
		Pessoa pessoa = it.getFirst();
		
		while (pessoa != null) {
			if (pessoa.getIdadePessoa() >= idade) {
				float novoSalario = pessoa.getSalarioPessoa();
				novoSalario = novoSalario + (float) (novoSalario * 0.1);
				pessoa.setSalarioPessoa(novoSalario);					
			}
			pessoa = it.getNext();
		}		
		
		System.out.println("Acrescimos efetuados");
		Keyboard.waitEnter();
	}
	
	
}
