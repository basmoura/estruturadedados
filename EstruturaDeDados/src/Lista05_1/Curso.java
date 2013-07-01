/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista05_1;

/**
 *
 * @author basmoura
 */
public class Curso {
    private int codCurso;
    private String nomeCurso;
     
    
    public Curso(int codCurso, String nomeCurso) {
        this.codCurso = codCurso;
        this.nomeCurso = nomeCurso;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }
}
