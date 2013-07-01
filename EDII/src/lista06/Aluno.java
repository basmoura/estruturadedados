/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lista06;

import diversos.*;

public class Aluno implements Comparable<Aluno> {

	private int numMat;
	private String nmAluno;
	private ArvoreBusca<Turma> turmas = new ArvoreBusca<>();

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

	@Override
	public String toString() {
		return getNmAluno().toString();
	}
}
