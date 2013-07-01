package utilitarios;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ArvoreBin<E extends Comparable<E>> implements Serializable {

    private NoArvoreBin<E> raiz;

    private class ArvoreIterator implements MyIterator<E> {

        FilaEnc<E> fila;
        private MyIterator<E> it;

        public void simetrica(NoArvoreBin<E> no) {
            if (no != null) {
                simetrica(no.getEsq());
                fila.insira(no.getObj());
                simetrica(no.getDir());
            }
            it = fila.iterator();
        }

        @Override
        public E getFirst() {
            fila = new FilaEnc<E>();
            simetrica(raiz);
            return it.getFirst();
        }

        @Override
        public E getNext() {
            return it.getNext();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }

    public NoArvoreBin<E> insert(E obj, NoArvoreBin<E> pai, int direcao) {

        NoArvoreBin<E> novoNo = new NoArvoreBin<E>(obj, pai);
        if (pai == null) {
            raiz = novoNo;
            return novoNo;
        }

        if (pai.getFilho(direcao) != null) {
            return null;
        }

        pai.setFilho(direcao, novoNo);
        return novoNo;
    }

    public boolean delete(NoArvoreBin<E> no) {
        NoArvoreBin<E> filho;
        // Verifica se o no tem dois filhos
        if ((no.getDir() != null) && (no.getEsq() != null)) {
            NoArvoreBin<E> pTemp = null;
            // Procura na subarvore esquerda o
            // no que se encontra mais a direita
            pTemp = no.getEsq();
            while (pTemp.getDir() != null) {
                pTemp = pTemp.getDir();
            }
            // Copia o objeto do no que se encontra mais
            // a direita no no que desejamos remover
            no.setObj(pTemp.getObj());
            // Faz com que o no a ser removido seja o no
            // que se encontra mais a direira da subarvore
            // esquerda
            no = pTemp;
        }
        // Verifica se esta removendo a raiz
        if (no == raiz) {
            if (raiz.getEsq() != null) {
                raiz = raiz.getEsq();
            } else {
                raiz = raiz.getDir();
            }
            if (raiz != null) {
                raiz.setPai(null);
            }
            return true;
        } else {
            int direcao = 1;
            NoArvoreBin<E> pai = no.getPai();
            if (no == pai.getEsq()) {
                direcao = -1;
            }
            filho = pai.getFilho(direcao);
            if (filho == null) {
                return true;
            }
            if (filho.getEsq() != null) {
                pai.setFilho(direcao, filho.getEsq());
                filho.getEsq().setPai(pai);
            } else {
                pai.setFilho(direcao, filho.getDir());
                if (filho.getDir() != null) {
                    filho.getDir().setPai(pai);
                }
            }
            return true;
        }
    }

    public NoArvoreBin<E> getNo(E obj) {
        // Metodo que retorna a referência do no que contem o objeto com o mesmo
        // valor do objeto passado como parametro. Se o no nao for encontrado,
        // devera ser retornado null.
        // A busca devera ser feita usando o percurso em preordem nao recursivo.
        return preOrdemNaoRec(raiz, obj);
    }

    public NoArvoreBin<E> preOrdemNaoRec(NoArvoreBin<E> raiz, E obj) {
        PilhaEnc<NoArvoreBin<E>> p = new PilhaEnc<NoArvoreBin<E>>();
        NoArvoreBin<E> no = raiz;
        do {
            while (no != null) {
                if (no.getObj().compareTo(obj) == 0) {
                    return no;
                }
                if (no.getDir() != null) {
                    p.empilhe(no.getDir());
                }
                no = no.getEsq();
            }
            no = p.desempilhe();
        } while (no != null);
        return null;
    }

    public NoArvoreBin<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(NoArvoreBin<E> raiz) {
        this.raiz = raiz;
    }

    public MyIterator<E> iterator() {
        return new ArvoreIterator();
    }

    // *************************************************************
    // Desenho da arvore
    // *************************************************************
    static class DescNo<E> {

        int nivel;
        int ident;
        NoArvoreBin<E> no;
    }

    public void desenhe(int larguraTela) {
        if (raiz == null) {
            return;
        }
        StringBuffer brancos1 = new StringBuffer();
        String brancos = "                                                        ";
        brancos1.append(brancos);
        brancos1.append(brancos);
        brancos1.append(brancos);
        brancos1.append(brancos);
        int largTela = larguraTela;
        int ident = largTela / 2;
        int nivelAnt = 0;
        int nivel = 0;
        int offset;
        NoArvoreBin<E> pTemp;
        StringBuffer linha1 = new StringBuffer(200);
        String linha = "";

        DescNo<E> descNo;
        FilaEnc<DescNo<E>> fila = new FilaEnc<DescNo<E>>();

        descNo = new DescNo<E>();

        descNo.nivel = 0;
        descNo.ident = ident;
        descNo.no = raiz;
        fila.insira(descNo);
        while (!fila.isEmpty()) {
            descNo = fila.remova();
            ident = descNo.ident;
            pTemp = descNo.no;
            nivel = descNo.nivel;
            if (nivel == nivelAnt) {
                linha1.append(brancos1.substring(0, ident - linha1.length())
                        + pTemp.getObj().toString());
                linha = linha + brancos1.substring(0, ident - linha.length())
                        + pTemp.getObj().toString();
            } else {
                System.out.println(linha + "\n\n");
                linha1.setLength(0);
                linha1.append(brancos1.substring(0, ident)
                        + pTemp.getObj().toString());
                linha = brancos1.substring(0, ident) + pTemp.getObj().toString();
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
        System.out.println(linha1);
    }
}
