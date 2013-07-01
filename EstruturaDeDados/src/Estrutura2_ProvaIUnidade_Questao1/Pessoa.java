/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_ProvaIUnidade_Questao1;

/**
 *
 * @author basmoura
 */
public class Pessoa implements Comparable<Pessoa> {
    private int codPessoa;
    private String nmPessoa;
    private int idade;
    private float salario;

    public Pessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    @Override
    public int compareTo(Pessoa o) {
        if (o.codPessoa < codPessoa)
            return -1;
        if (o.codPessoa == codPessoa)
            return 0;
        return 1;
    }
}
