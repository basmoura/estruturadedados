/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_ProvaIIUnidade;

import utilitarios.Treap;

/**
 *
 * @author basmoura
 */
public class Paciente implements Comparable<Paciente> {

    private int CodPaciente;
    private String NomePaciente;
    private Treap<Orgao> orgaos = new Treap(false);

    public Paciente() {
    }

    public Treap<Orgao> getOrgaos() {
        return orgaos;
    }

    public void setOrgaos(Treap<Orgao> orgaos) {
        this.orgaos = orgaos;
    }
    
    public Paciente(int codigo) 
    {
        this.CodPaciente = codigo;
    }

    public int getCodPaciente() {
        return CodPaciente;
    }

    public String getNomePaciente() {
        return NomePaciente;
    }

    public void setNomePaciente(String NomePaciente) {
        this.NomePaciente = NomePaciente;
    }
    
    @Override
    public int compareTo(Paciente o) {
        if (this.CodPaciente < o.getCodPaciente()) {
            return -1;
        }
        if (this.CodPaciente == o.getCodPaciente()) {
            return 0;
        }
        return 1;
    }
}
