package diversos;

@SuppressWarnings("serial")
public class Treap<E extends Comparable<E>> extends ArvoreBusca<E> {

	private boolean permiteDadosRepetidos;
	
	public Treap() {
		this(false);
	}
	
	public Treap(boolean permiteDadosRepetidos) {
		this.permiteDadosRepetidos = permiteDadosRepetidos;
	}

	public boolean isPermiteDadosRepetidos() {
		return permiteDadosRepetidos;
	}
	
	protected void rotacaoParaEsquerda(NoArvoreBin<E> no) {
		if (no == arvore.getRaiz()) {
			arvore.setRaiz(no.getDir());
			arvore.getRaiz().setPai(null);
		}
		NoArvoreBin<E> pai = no;
		no = no.getDir();
		pai.setDir(no.getEsq());
		if (pai.getDir() != null)
			pai.getDir().setPai(pai);
		no.setPai(pai.getPai());
		if (no.getPai() != null) {
			if (no.getPai().getEsq() == pai)
				no.getPai().setEsq(no);
			else
				no.getPai().setDir(no);
		}
		no.setEsq(pai);
		pai.setPai(no);
	}

	protected void rotacaoParaDireita(NoArvoreBin<E> no) {
		if (no == arvore.raiz) {
			arvore.raiz = no.getEsq();
			arvore.raiz.setPai(null);
		}
		NoArvoreBin<E> pai = no;
		no = no.getEsq();
		pai.setEsq(no.getDir());
		if (pai.getEsq() != null)
			pai.getEsq().setPai(pai);
		no.setPai(pai.getPai());
		if (no.getPai() != null) {
			if (no.getPai().getEsq() == pai)
				no.getPai().setEsq(no);
			else
				no.getPai().setDir(no);
		}
		no.setDir(pai);
		pai.setPai(no);
	}

	@Override
	public boolean add(E obj) {
		int direcao = -1;
		NoArvoreBin<E> p, pai;
		int c;

		p = arvore.getRaiz();
		pai = null;

		if (arvore.getRaiz() != null) {
			while (true) {
				c = obj.compareTo(p.getObj());

				if (c == 0) {
					if (permiteDadosRepetidos) {
						if (p.getDir() == null) {
							pai = p;
							direcao = 1;
							break;
						} else {
							p = p.getDir();
							continue;
						}
					} else {
						return false;
					}
				}
				if (c < 0) {
					if (p.getEsq() == null) {
						pai = p;
						direcao = -1;
						break;
					} else {
						p = p.getEsq();
					}
				} else {
					if (p.getDir() == null) {
						pai = p;
						direcao = 1;
						break;
					} else {
						p = p.getDir();
					}
				}
			}
		}
		arvore.insert(obj, pai, direcao);
		numItens++;
		borbulhamento(obj);
		return true;
	}

	private void borbulhamento(E obj) {
		NoArvoreBin<E> no = arvore.getNo(obj);
		if (no != null) {
			while (no.getPai() != null
					&& no.getPai().getPrioridade() > no.getPrioridade()) {
				if (no.getPai().getDir() != null
						&& no.getPai().getDir().getPrioridade() == no
								.getPrioridade()) {
					rotacaoParaEsquerda(no.getPai());
				} else if (no.getPai().getEsq() != null) {
					rotacaoParaDireita(no.getPai());
				}
			}
			if (no.getPai() == null) {
				arvore.raiz = no;
			}
		}
	}

	private void descerNo(NoArvoreBin<E> no) {
		while (no.getDir() != null || no.getEsq() != null) {
			if (no.getEsq() == null) {
				rotacaoParaEsquerda(no);
			} else if (no.getDir() == null) {
				rotacaoParaDireita(no);
			} else if (no.getEsq().getPrioridade() < no.getDir()
					.getPrioridade()) {
				rotacaoParaDireita(no);
			} else {
				rotacaoParaEsquerda(no);
			}
			if (arvore.raiz.getObj().compareTo(no.getObj()) == 0) {
				arvore.raiz = no.getPai();
			}
		}
	}

	@Override
	public boolean remove(E obj) {
		NoArvoreBin<E> no = arvore.getNo(obj);
		if (no != null) {
			descerNo(no);
			arvore.delete(no);
			return true;
		}
		return false;
	}
	
	/* --- Remove todos os valores iguais.
	public boolean remove(E obj) {
		boolean found = false;
		NoArvoreBin<E> no = arvore.getNo(obj);
		while(no != null) {
			descerNo(no);
			arvore.delete(no);
			no = arvore.getNo(obj);
			found = true;
		}
		return found;
	}
	*/

	// *************************************************************
	// Impressao da arvore na forma normal
	// *************************************************************
	@SuppressWarnings("hiding")
	private class DescNo<E> {
		int nivel;
		int ident;
		NoArvoreBin<E> no;
	}

	public void desenhe(int larguraTela) {
		if (arvore.raiz == null)
			return;
		String brancos = "                                                        "
		      + "                                                        "
		      + "                                                        ";
		int largTela = larguraTela;
		int ident = largTela / 2;
		int nivelAnt = 0;
		int nivel = 0;
		int offset;
		NoArvoreBin<E> pTemp;
		String linha = "";

		DescNo<E> descNo;
		FilaEnc<DescNo<E>> fila = new FilaEnc<DescNo<E>>();

		descNo = new DescNo<E>();

		descNo.nivel = 0;
		descNo.ident = ident;
		descNo.no = arvore.raiz;
		fila.insira(descNo);

		while (!fila.isEmpty()) {
			descNo = fila.remova();
			ident = descNo.ident;
			pTemp = descNo.no;
			nivel = descNo.nivel;
			if (nivel == nivelAnt) {
				String prio = String.format("%5.3f", pTemp.getPrioridade());
				linha = linha + brancos.substring(0, ident - linha.length())
				      + pTemp.getObj().toString() + " " + prio;
			}
			else {
				System.out.println(linha + "\n\n");
				String prio = String.format("%5.3f", pTemp.getPrioridade());
				linha = brancos.substring(0, ident) + pTemp.getObj().toString()
				      + " " + prio;
				nivelAnt = nivel;
			}
			nivel = nivel + 1;
			offset = (int) (largTela / Math.round(Math.pow(2, nivel + 1)));
			if (pTemp.getEsq() != null) {
				descNo = new DescNo<E>();
				descNo.ident = ident - offset;
				descNo.nivel = nivel;
				descNo.no = pTemp.getEsq();
				fila.insira(descNo);
			}
			if (pTemp.getDir() != null) {
				descNo = new DescNo<E>();
				descNo.ident = ident + offset;
				descNo.nivel = nivel;
				descNo.no = pTemp.getDir();
				fila.insira(descNo);
			}
		}
		System.out.println(linha);
	}

}
