/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_ProvaIUnidade_Questao2;

import Biblioteca.ListaEncOrd;
import Biblioteca.MyIterator;

/**
 *
 * @author basmoura
 */
public class Aluno implements Comparable<Aluno> {

    private int numMat;
    private String nmAluno;
    private ArvoreBusca<Turma> turmas = new ArvoreBusca<>();
    private ArvoreBusca<Notas> notas = new ArvoreBusca<>();

    public Aluno(int numMat) {
        this.numMat = numMat;
    }

    public int getNumMat() {
        return numMat;
    }

    public String getNmAluno() {
        return nmAluno;
    }

    public void setNmAluno(String nmAluno) {
        this.nmAluno = nmAluno;
    }

    public ArvoreBusca<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(ArvoreBusca<Turma> turmas) {
        this.turmas = turmas;
    }

    public ArvoreBusca<Notas> getNotas() {
        return notas;
    }

    public void setNotas(ArvoreBusca<Notas> notas) {
        this.notas = notas;
    }

    @Override
    public int compareTo(Aluno o) {
        if (o.numMat < numMat) {
            return -1;
        }
        if (o.numMat == numMat) {
            return 0;
        }
        return 1;
    }    
}
