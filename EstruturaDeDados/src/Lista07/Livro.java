/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista07;

import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public class Livro implements Serializable, Comparable<Livro> {

    private int codLivro;
    private String titulo;
    private String dtEmprestimo; 
    private Aluno aluno = null;

    public Livro(int codLivro) {
        this.codLivro = codLivro;
    }

    public Livro(int codLivro, String titulo) {
        this.codLivro = codLivro;
        this.titulo = titulo;
    }

    public int getCodLivro() {
        return codLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getDtEmprestimo() {
        return dtEmprestimo;
    }

    public void setDtEmprestimo(String dtEmprestimo) {
        this.dtEmprestimo = dtEmprestimo;
    }

    @Override
    public int compareTo(Livro o) {
        if (this.codLivro < o.codLivro) {
            return 1;
        }
        if (this.codLivro == o.codLivro) {
            return 0;
        }
        return -1;
    }
}
