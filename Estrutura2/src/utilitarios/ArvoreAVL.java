package utilitarios;

public class ArvoreAVL<E extends Comparable<E>> extends ArvoreBusca<E> {

	private int height(NoArvoreBin<E> no) {
		return no == null ? -1 : no.getAltura();
	}

	private NoArvoreBin<E> rotacaoParaDireita(NoArvoreBin<E> no) {
		NoArvoreBin<E> p = no.getEsq();
		no.setEsq(p.getDir());
		p.setDir(no);
		no.setAltura(Math.max(height(no.getEsq()), height(no.getDir())) + 1);
		p.setAltura(Math.max(height(p.getEsq()), no.getAltura()) + 1);
		return p;
	}

	private NoArvoreBin<E> rotacaoParaEsquerda(NoArvoreBin<E> no) {
		NoArvoreBin<E> p = no.getDir();
		no.setDir(p.getEsq());
		p.setEsq(no);
		no.setAltura(Math.max(height(no.getEsq()), height(no.getDir())) + 1);
		p.setAltura(Math.max(height(p.getDir()), no.getAltura()) + 1);
		return p;
	}

	private NoArvoreBin<E> rotacaoDuplaEsquerdaDireita(NoArvoreBin<E> no) {
		no.setEsq(rotacaoParaEsquerda(no.getEsq()));
		return rotacaoParaDireita(no);
	}

	private NoArvoreBin<E> rotacaoDuplaDireitaEsquerda(NoArvoreBin<E> no) {
		no.setDir(rotacaoParaDireita(no.getDir()));
		return rotacaoParaEsquerda(no);
	}

	private NoArvoreBin<E> insira(E obj, NoArvoreBin<E> no) {

		if (no == null) {
			no = new NoArvoreBin<E>(obj);
		} else if (((Comparable<E>) obj).compareTo(no.getObj()) < 0) {
			no.setEsq(insira(obj, no.getEsq()));
			if (height(no.getEsq()) - height(no.getDir()) == 2)
				if (((Comparable<E>) obj).compareTo(no.getEsq().getObj()) < 0)
					no = rotacaoParaDireita(no);
				else
					no = rotacaoDuplaEsquerdaDireita(no);
		} else if (((Comparable<E>) obj).compareTo(no.getObj()) > 0) {
			no.setDir(insira(obj, no.getDir()));
			if (height(no.getDir()) - height(no.getEsq()) == 2)
				if (((Comparable<E>) obj).compareTo(no.getDir().getObj()) > 0)
					no = rotacaoParaEsquerda(no);
				else
					no = rotacaoDuplaDireitaEsquerda(no);
		}

		no.setAltura(Math.max(height(no.getEsq()), height(no.getDir())) + 1);
		return no;
	}

	@Override
	public boolean add(E obj) {

		NoArvoreBin<E> p;
		int c;
		p = arvore.getRaiz();
		while (p != null) {
			c = obj.compareTo(p.getObj());
			if (c == 0)
				break;
			if (c < 0)
				p = p.getEsq();
			else
				p = p.getDir();
		}

		if (p != null) {
			if (p.isDeletado()){
		    	p.setDeletado(false);
		    	p.setObj(obj);
			    return true;
			}   
		}
		

		NoArvoreBin<E> no = insira(obj, arvore.getRaiz());
		arvore.setRaiz(no);
		numItens++;
		return true;

	}

	@Override
	public boolean remove(E obj) {
		NoArvoreBin<E> p;
		int c;
		p = arvore.getRaiz();
		while (p != null) {
			c = obj.compareTo(p.getObj());
			if (c == 0)
				break;
			if (c < 0)
				p = p.getEsq();
			else
				p = p.getDir();
		}

		if (p == null) {
			return false;
		}

		p.setDeletado(true);
		return true;
	}

	public void desenhe() {
		Keyboard.clrscr();
		arvore.desenhe(80);
		Keyboard.waitEnter();
	}

}
