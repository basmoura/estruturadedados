/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExercicioListaEncadeada;

import java.io.Serializable;

/**
 *
 * @author 2102104650
 */
public class Pessoa implements Serializable {

    int codPessoa;
    String nome;
    String dtNascimento;

    public Pessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

   public Pessoa(int codPessoa, String nome, String data) {
        this.codPessoa = codPessoa;
        this.nome = nome;
        this.dtNascimento = data;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
