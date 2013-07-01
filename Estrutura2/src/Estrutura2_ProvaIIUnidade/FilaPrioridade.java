/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_ProvaIIUnidade;

/**
 *
 * @author basmoura
 */
public class FilaPrioridade implements Comparable<FilaPrioridade> {

    private int prioridade;
    private Paciente paciente;

    public FilaPrioridade(int prioridade, Paciente paciente) {
        this.prioridade = prioridade;
        this.paciente = paciente;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    @Override
    public int compareTo(FilaPrioridade o) {
        if (this.prioridade != o.prioridade) {
            return this.prioridade > o.prioridade ? 1 : -1;
        }
        return 0;
    }
}
