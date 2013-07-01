/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista05_2;

import Biblioteca.ListaSimpEnc;
import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public class Pessoa implements Serializable {
    private int codPessoa;
    private String nome;
    private ListaSimpEnc<Bem> bens = new ListaSimpEnc<>();

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
}
