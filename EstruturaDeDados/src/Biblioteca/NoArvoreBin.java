package Biblioteca;

import java.io.Serializable;

public class NoArvoreBin<E> implements Serializable {

    /**
     * Contem o objeto associado a esse no
     */
    private E obj;
    /**
     * Referencia a subarvore esquerda
     */
    private NoArvoreBin<E> esq;
    /**
     * referencia a subarvore direita
     */
    private NoArvoreBin<E> dir;
    // ** referencia ao pai do no
    private NoArvoreBin<E> pai;
    protected int altura;
    protected boolean deletado;

    /**
     * Cria um no com filhos nulos (folha)
     */
    public NoArvoreBin(E obj, NoArvoreBin<E> pai) {
        this.obj = obj;
        this.pai = pai;
        deletado = false;
    }

    /**
     * Cria um no com as respectivas subarvores esquerda e direita.
     */
    public NoArvoreBin(E obj, NoArvoreBin<E> pai, NoArvoreBin<E> esq,
            NoArvoreBin<E> dir) {
        this(obj, pai);
        this.esq = esq;
        this.dir = dir;
    }

    public NoArvoreBin(E obj) {
        this.obj = obj;
    }

    public NoArvoreBin<E> getPai() {
        return pai;
    }

    public void setPai(NoArvoreBin<E> pai) {
        this.pai = pai;
    }

    /**
     * Retorna o objeto associado a esse na.
     */
    public E getObj() {
        return obj;
    }

    /**
     * Retorna a referencia da subarvore esquerda.
     */
    public NoArvoreBin<E> getEsq() {
        return esq;
    }

    /**
     * Retorna a referencia da subarvore direita.
     */
    public NoArvoreBin<E> getDir() {
        return dir;
    }

    /**
     * Substitui o objeto armazenado no no por obj.
     */
    public void setObj(E obj) {
        this.obj = obj;
    }

    /**
     * Substitui a referoncia da subarvore esquerda por esq.
     */
    public void setEsq(NoArvoreBin<E> esq) {
        this.esq = esq;
    }

    /**
     * Substitui a referencia da subarvore direita por dir.
     */
    public void setDir(NoArvoreBin<E> dir) {
        this.dir = dir;
    }

    /**
     * Retorna o filho do no de acordo com o valor de direcao. Se direcao < 0
     * retorna o filho esquerdo caso contrario retorna o filho direito.
     */
    public NoArvoreBin<E> getFilho(int direcao) {
        if (direcao < 0) {
            return esq;
        } else {
            return dir;
        }
    }

    /**
     * Atribui o filho do no de acordo com o valor de direcao. Se direcao < 0
     * atribui o filho esquerdo, caso contrario, atribui o filho direito.
     */
    public void setFilho(int direcao, NoArvoreBin<E> filho) {
        if (direcao < 0) {
            esq = filho;
        } else {
            dir = filho;
        }
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }
}
