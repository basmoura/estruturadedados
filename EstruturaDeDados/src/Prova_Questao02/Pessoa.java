/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prova_Questao02;

import Biblioteca.ListaEncOrd;
import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public class Pessoa implements Serializable, Comparable<Pessoa> {

    private int codPessoa;
    private String nome;
    private ListaEncOrd<TarefaFazer> tarefasFazer = new ListaEncOrd<>();
    private ListaEncOrd<TarefaConcluida> tarefasConcluidas = new ListaEncOrd<>();
    private int totalTarefas;

    public Pessoa(int codPessoa) {
        this.codPessoa = codPessoa;
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

    public ListaEncOrd<TarefaFazer> getTarefasFazer() {
        return tarefasFazer;
    }

    public ListaEncOrd<TarefaConcluida> getTarefasConcluidas() {
        return tarefasConcluidas;
    }

    public int getTotalTarefas() {
        return totalTarefas;
    }

    public void setTotalTarefas(int totalTarefas) {
        this.totalTarefas = totalTarefas;
    }

    @Override
    public int compareTo(Pessoa o) {
        if (this.codPessoa > o.codPessoa) {
            return -1;
        }
        if (this.codPessoa == o.codPessoa) {
            return 0;
        }
        return 1;
    }
}
