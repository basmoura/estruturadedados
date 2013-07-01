/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lista06;

public class Disciplina implements Comparable<Disciplina> {

	private int codDisc;
	private String nmDisc;

	public Disciplina(int codDisc) {
		this.codDisc = codDisc;
	}

	public int getCodDisc() {
		return codDisc;
	}

	public String getNmDisc() {
		return nmDisc;
	}

	public void setNmDisc(String nmDisc) {
		this.nmDisc = nmDisc;
	}

	@Override
	public int compareTo(Disciplina o) {
		if (o.codDisc < codDisc) {
			return -1;
		}
		if (o.codDisc == codDisc) {
			return 0;
		}
		return 1;
	}
	
	@Override
	public String toString() {
		return getNmDisc().toString();
	}
}
