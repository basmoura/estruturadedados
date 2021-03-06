/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_Lista04;

/**
 *
 * @author basmoura
 */
public class Turma implements Comparable<Turma> {

    private int codDisc;
    private String codTurma;

    public Turma(int codDisc, String codTurma) {
        this.codDisc = codDisc;
        this.codTurma = codTurma;
    }

    public int getCodDisc() {
        return codDisc;
    }

    public String getCodTurma() {
        return codTurma;
    }

    @Override
    public int compareTo(Turma o) {
        if (o.codDisc < codDisc && o.codTurma.compareTo(codTurma) < 0) {
            return -1;
        }
        if (o.codDisc == codDisc && o.codTurma.compareTo(codTurma) == 0) {
            return 0;
        }
        return 1;
    }
}
