package revisao;

import diversos.Keyboard;
import diversos.MyIterator;
import diversos.ObjectFile;

public class DemoProf {

	static Professor[] professores = new Professor[10];
	static int totProf;
	static ObjectFile arqProfessores = new ObjectFile("professores.dat");

	static class IteratorProfessor implements MyIterator<Professor> {
		private int corrente;

		@Override
		public Professor getFirst() {
			if (totProf == 0)
				return null;
			corrente = 1;
			return professores[0];
		}

		@Override
		public Professor getNext() {
			if (corrente == totProf)
				return null;
			return professores[corrente++];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

	// Exemplo Simples para buscar.
	static boolean busque(int numMat) {
		for (int i = 0; i < totProf; i++) {
			if (numMat == professores[i].getNumMat())
				return true;
		}
		return false;
	}

	// Exemplo M�todo para buscar atrav�s do CompareTo (Comparable)
	static boolean busque1(int numMat) {
		Professor prof = new ProfHorista(numMat, "", 0, 0);
		for (int i = 0; i < totProf; i++) {
			if (professores[i].compareTo(prof) == 0)
				return true;
		}
		return false;
	}

	// Exemplo M�todo para buscar atrav�s do Compare (Comparator)
	static boolean busque2(int numMat) {
		CompareProfessores comp = new CompareProfessores();
		Professor prof = new ProfHorista(numMat, "", 0, 0);
		for (int i = 0; i < totProf; i++) {
			if (comp.compare(prof, professores[i]) == 0) {
				return true;
			}

		}
		return false;
	}

	static void leiaArquivo() {
		if (arqProfessores.reset()) {
			Professor professor = (Professor) arqProfessores.read();
			while (professor != null) {
				if (totProf == professores.length) {
					redimensione();
				}
				professores[totProf] = professor;
				totProf++;
				professor = (Professor) arqProfessores.read();
			}
			arqProfessores.closeFile();
		}
	}

	static void graveArquivo() {
		arqProfessores.rewrite();
		IteratorProfessor it = new IteratorProfessor();
		Professor prof = it.getFirst();
		while (prof != null) {
			arqProfessores.write(prof);
			prof = it.getNext();
		}
		arqProfessores.closeFile();
	}

	static void redimensione() {
		Professor[] profTemp = new Professor[professores.length + 10];
		System.arraycopy(professores, 0, profTemp, 0, professores.length);
		professores = profTemp;
	}

	static void incluir() {
		Keyboard.clrscr();
		Professor prof;
		char resp;
		do {
			if (totProf == professores.length)
				redimensione();
			int numMat = Keyboard.readInt("Digite o numero da matricula: ");
			if (busque2(numMat)) {
				System.out.println("Matricula ja existente ");
			} else {
				String nomeProf = Keyboard
						.readString("Entre com o nome do Professor: ");
				char tipoProf = Keyboard
						.readChar("Entrar com o tipo do Professor, Horista = h / Mensalista = m: ");
				if (tipoProf == 'm') {
					float salMensal = Keyboard
							.readFloat("Entrar com o salario do professor: ");
					prof = new ProfMensalista(numMat, nomeProf, salMensal);
				} else {
					int numHora = Keyboard
							.readInt("Entrar com a quantidade de horas: ");
					float salHora = Keyboard
							.readFloat("Entrar com o valor da hora: ");
					prof = new ProfHorista(numMat, nomeProf, salHora, numHora);
				}
				professores[totProf] = prof;
				totProf++;
			}
			resp = Keyboard.readChar("Deseja cadastrar outra pessoa? (s/n)");

		} while (resp == 's');
	}

	static void listar() {
		Keyboard.clrscr();
		System.out.println("NumMat  Nome do Professor  Tipo  Salario");
		System.out.println("------  -----------------  ----  -------");
		IteratorProfessor it = new IteratorProfessor();
		Professor prof = it.getFirst();
		while (prof != null) {
			char tipoProf;
			if (prof instanceof ProfHorista) {
				tipoProf = 'H';
			} else {
				tipoProf = 'M';
			}
			System.out.printf("%6d  %-20s  %1s  %8.2f\n", prof.getNumMat(),
					prof.getNome(), tipoProf, prof.getSalario());
			prof = it.getNext();
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		int opcao;
		leiaArquivo();
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Incluir/Listar/Terminar");
			switch (opcao) {
			case 1:
				incluir();
				break;
			case 2:
				listar();
				break;
			}

		} while (opcao < 3);
		graveArquivo();
		Keyboard.clrscr();
	}

}
