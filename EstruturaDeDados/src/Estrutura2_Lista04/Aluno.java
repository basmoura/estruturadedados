/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_Lista04;

import Biblioteca.ListaEncOrd;
import Biblioteca.MyIterator;
import Estrutura2_Lista05.ArvoreAVL;

/**
 *
 * @author basmoura
 */
public class Aluno implements Comparable<Aluno> {

    private int numMat;
    private String nmAluno;
    private ArvoreAVL<Turma> turmas = new ArvoreAVL<>();

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

    public ArvoreAVL<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(ArvoreAVL<Turma> turmas) {
        this.turmas = turmas;
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
    
    public ListaEncOrd<Aluno> Listar(int codDisc, String codTurma) {
        ListaEncOrd<Aluno> listaAlunos = new ListaEncOrd<>();
        
        MyIterator<Turma> it = turmas.iterator();
        Turma turma = it.getFirst();
        
        while (turma != null) {
            if (turma.getCodDisc() == codDisc &&
                turma.getCodTurma().equalsIgnoreCase(codTurma))
                
                listaAlunos.add(this);
        }
        
        return listaAlunos;
    }
}
