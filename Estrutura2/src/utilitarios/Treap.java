package utilitarios;

import java.util.Comparator;

public class Treap<E extends Comparable<E>> extends ArvoreBusca<E> {

    private boolean permiteDadosRepetidos;

    public Treap(boolean permiteDadosRepetidos) {
        this.permiteDadosRepetidos = permiteDadosRepetidos;
    }

    protected void rotacaoParaEsquerda(NoArvoreBin<E> no) {
        if (no.getDir() == null) {
            return;
        }
        if (no == arvore.getRaiz()) {
            arvore.setRaiz(no.getDir());
            arvore.getRaiz().setPai(null);
        }
        NoArvoreBin<E> pai = no;
        no = no.getDir();
        pai.setDir(no.getEsq());
        if (pai.getDir() != null) {
            pai.getDir().setPai(pai);
        }
        no.setPai(pai.getPai());
        if (no.getPai() != null) {
            if (no.getPai().getEsq() == pai) {
                no.getPai().setEsq(no);
            } else {
                no.getPai().setDir(no);
            }
        }
        no.setEsq(pai);
        pai.setPai(no);
    }

    protected void rotacaoParaDireita(NoArvoreBin<E> no) {
        if (no.getEsq() == null) {
            return;
        }
        if (no == arvore.getRaiz()) {
            arvore.setRaiz(no.getEsq());
            arvore.getRaiz().setPai(null);
        }
        NoArvoreBin<E> pai = no;
        no = no.getEsq();
        pai.setEsq(no.getDir());
        if (pai.getEsq() != null) {
            pai.getEsq().setPai(pai);
        }
        no.setPai(pai.getPai());
        if (no.getPai() != null) {
            if (no.getPai().getEsq() == pai) {
                no.getPai().setEsq(no);
            } else {
                no.getPai().setDir(no);
            }
        }
        no.setDir(pai);
        pai.setPai(no);
    }

    private void rotacoesAdd(NoArvoreBin<E> no) {

        NoArvoreBin<E> noEsq = no.getEsq();
        NoArvoreBin<E> noDir = no.getDir();

        if (noEsq == null && noDir == null) {
            return;
        }

        if (noEsq != null && noDir != null) {
            if (noEsq.getPrioridade() > noDir.getPrioridade()) {
                rotacaoParaEsquerda(no);
            } else {
                rotacaoParaDireita(no);
            }
        } else {
            if (noEsq == null) {
                rotacaoParaEsquerda(no);
            } else {
                rotacaoParaDireita(no);
            }
        }
        rotacoesAdd(no);

    }

    private void rotacoesDel(NoArvoreBin<E> no) {
        NoArvoreBin<E> noEsq = no.getEsq();
        NoArvoreBin<E> noDir = no.getDir();

        if (noEsq == null && noDir == null) {
            return;
        }

        if (noEsq != null && noDir != null) {
            if (noEsq.getPrioridade() > noDir.getPrioridade()) {
                rotacaoParaEsquerda(no);
            } else {
                rotacaoParaDireita(no);
            }
        } else {
            if (noEsq == null) {
                rotacaoParaEsquerda(no);
            } else {
                rotacaoParaDireita(no);
            }
        }
        rotacoesDel(no);
    }

    @Override
    public boolean add(E obj) {
        int direcao = -1;
        NoArvoreBin<E> pai, p;
        int c;
        p = arvore.getRaiz();
        pai = null;
        if (arvore.getRaiz() != null) {
            while (true) {
                c = obj.compareTo(p.getObj());
                if (c == 0 && permiteDadosRepetidos == false) {
                    return false;
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
        NoArvoreBin<E> rtn = arvore.insert(obj, pai, direcao);
        if (rtn.getPai() != null) {
            if (rtn.getPrioridade() < rtn.getPai().getPrioridade()) {
                rotacoesAdd(rtn);
            }
        }
        numItens++;
        return true;
    }

    @Override
    public boolean remove(E obj) {
        int c;
        NoArvoreBin<E> p = arvore.getRaiz();
        while (p != null) {
            c = obj.compareTo(p.getObj());
            if (c == 0) {
                rotacoesDel(p);
                if (arvore.delete(p)) {
                    numItens--;
                    return true;
                }
            } else if (c > 0) {
                p = p.getDir();
            } else {
                p = p.getEsq();
            }
        }
        return false;
    }

    // *************************************************************
    // Impressao da arvore na forma normal
    // *************************************************************
    private class DescNo<E> {

        int nivel;
        int ident;
        NoArvoreBin<E> no;
    }

    public void desenhe(int larguraTela) {
        if (arvore.getRaiz() == null) {
            return;
        }
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
        descNo.no = arvore.getRaiz();
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
            } else {
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
