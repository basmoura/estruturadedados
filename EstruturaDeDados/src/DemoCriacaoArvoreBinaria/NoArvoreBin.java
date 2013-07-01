package DemoCriacaoArvoreBinaria;

import java.io.Serializable;

public class NoArvoreBin<E> implements Serializable {

    private E obj;
    private int altura;
    private NoArvoreBin<E> esq;
    private NoArvoreBin<E> dir;
    private NoArvoreBin<E> pai;

    public NoArvoreBin(E obj, NoArvoreBin<E> pai) {
        this.obj = obj;
        this.pai = pai;
    }

    public NoArvoreBin(E obj, NoArvoreBin<E> pai, NoArvoreBin<E> esq, NoArvoreBin<E> dir) {
        this(obj, pai);
        this.esq = esq;
        this.dir = dir;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NoArvoreBin<E> getPai() {
        return pai;
    }

    public void setPai(NoArvoreBin<E> pai) {
        this.pai = pai;
    }

    public E getObj() {
        return obj;
    }

    public NoArvoreBin<E> getEsq() {
        return esq;
    }

    public NoArvoreBin<E> getDir() {
        return dir;
    }

    public boolean ehFolha() {
        return (esq == null) && (dir == null);
    }

    public void setObj(E obj) {
        this.obj = obj;
    }

    public void setEsq(NoArvoreBin<E> esq) {
        this.esq = esq;
    }

    public void setDir(NoArvoreBin<E> dir) {
        this.dir = dir;
    }

    public NoArvoreBin<E> getFilho(int direcao) {
        if (direcao < 0) {
            return esq;
        } else {
            return dir;
        }
    }

    public void setFilho(int direcao, NoArvoreBin<E> filho) {
        if (direcao < 0) {
            esq = filho;
        } else {
            dir = filho;
        }
    }
}
