/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_ProvaIUnidade_Questao2;

/**
 *
 * @author basmoura
 */
public class Notas implements Comparable<Notas> {

    private int numMat;
    private int codDisc;
    private String codTurma;
    private float nota1, nota2;

    public Notas(int numMat, int codDisc, String codTurma, float nota1, float nota2) {
        this.numMat = numMat;
        this.codDisc = codDisc;
        this.codTurma = codTurma;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public int getCodDisc() {
        return codDisc;
    }

    public void setCodDisc(int codDisc) {
        this.codDisc = codDisc;
    }

    public String getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(String codTurma) {
        this.codTurma = codTurma;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public int getNumMat() {
        return numMat;
    }

    @Override
    public int compareTo(Notas notas) {

        if (nota1 == notas.getNota1() && nota2 == notas.getNota2()
                && codDisc == notas.getCodDisc()
                && codTurma.equalsIgnoreCase(notas.getCodTurma())
                && numMat == notas.getNumMat())  {
            return 0;
        }
        return 2;
    }
}
