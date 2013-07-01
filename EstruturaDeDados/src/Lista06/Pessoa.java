/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista06;

import Biblioteca.ListaSimpEnc;
import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public class Pessoa implements Serializable, Comparable<Pessoa> {

    private int codPessoa;
    private String nome;
    private ListaSimpEnc<Bem> bens = new ListaSimpEnc<>();

    public Pessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Pessoa(int codPessoa, String nome) {
        this.codPessoa = codPessoa;
        this.nome = nome;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ListaSimpEnc<Bem> getBens() {
        return bens;
    }

    public void setBens(ListaSimpEnc<Bem> bens) {
        this.bens = bens;
    }

    @Override
    public int compareTo(Pessoa o) {
        if (this.nome.compareToIgnoreCase(o.nome) == -1) {
            return 1;
        }
        if (this.nome.compareToIgnoreCase(o.nome) == 0) {
            return 0;
        }
        return -1;
    }
}
