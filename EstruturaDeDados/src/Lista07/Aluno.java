/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista07;

import Biblioteca.ListaEncOrd;
import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public class Aluno implements Serializable, Comparable<Aluno> {

    private int numMat;
    private String nome;
    private ListaEncOrd<Livro> livros = new ListaEncOrd<>();

    public Aluno(int numMat) {
        this.numMat = numMat;
    }

    public Aluno(int numMat, String nome) {
        this.numMat = numMat;
        this.nome = nome;
    }

    public int getNumMat() {
        return numMat;
    }

    public String getNome() {
        return nome;
    }

    public ListaEncOrd<Livro> getLivros() {
        return livros;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int compareTo(Aluno o) {
        if (this.numMat < o.numMat) {
            return 1;
        }
        if (this.numMat == o.numMat) {
            return 0;
        }
        return -1;
    }
}
