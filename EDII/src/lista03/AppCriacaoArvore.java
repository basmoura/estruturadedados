package lista03;
import diversos.*;
public class AppCriacaoArvore {

	static ArvoreBin<Integer> arvore = new ArvoreBin<>();

	static void inserir() {
		// Para inserir um no na arvore devera ser solicitado o valor que sera
		// armazenado no no, o valor do no que sera o pai do novo no
		// e a direcao em que o no sera inserido
		int num = Keyboard.readInt("Digite o número a ser adicionado:");
		NoArvoreBin<Integer> noPai = arvore.getNo(Keyboard.readInt("Digite o valor do pai:"));
		int direcao = Keyboard.readInt("Digite a direção:(>=0 direita/ <0 esquerda):");
		NoArvoreBin<Integer> result = arvore.insert(num, noPai, direcao);
		if(result != null)
			System.out.println("Número adicionado com sucesso.");
		else
			System.out.println("Filho ja existente na direção selecionada.");
		Keyboard.waitEnter();
	}

	static void remover() {
		// Devera ser solicitado o valor armazenado no no que sera removido
		int num = Keyboard.readInt("Digite o valor a ser removido:");
		NoArvoreBin<Integer> no = arvore.getNo(num);
		if(no != null) {
			char resp = Keyboard.readChar("Deseja realmente remover o número " + no.getObj().intValue() + "?(s/n)");
			if(resp == 's'){
				arvore.delete(no);
				System.out.println("Número deletado com sucesso");
			} else {
				System.out.println("Número não deletado.");
			}
		}
	}

	static void listar() {
		MyIterator<Integer> it = arvore.iterator();
		Integer p = it.getFirst();
		while(p != null) {
			System.out.println(p.intValue());
			p = it.getNext();
		}
		Keyboard.waitEnter();
	}

	public static void main(String[] args) {
		int opcao;
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Inserir/Remover/Listar/Desenhar/Terminar");
			switch (opcao) {
				case 1:
					inserir();
					break;
				case 2:
					remover();
					break;
				case 3:
					listar();
					break;
				case 4:
					Keyboard.clrscr();
					arvore.desenhe(80);
					Keyboard.waitEnter();
					break;

			}
		} while (opcao < 5);
		System.out.println("\nFim do programa");
	}

}
