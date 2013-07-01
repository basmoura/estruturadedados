/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_ProvaIIUnidade;

import utilitarios.Heap;

/**
 *
 * @author basmoura
 */
public class Orgao implements Comparable<Orgao> {

    private int CodOrgao;
    private String NomeOrgrao;

    public Orgao(int codOrgao) {
        this.CodOrgao = codOrgao;
    }

    public Orgao(int codOrgao, String nmOrgao) {
        this.CodOrgao = codOrgao;
        this.NomeOrgrao = nmOrgao;
    }

    public int getCodOrgao() {
        return CodOrgao;
    }

    public String getNomeOrgrao() {
        return NomeOrgrao;
    }

    public void setNomeOrgrao(String NomeOrgrao) {
        this.NomeOrgrao = NomeOrgrao;
    }

    @Override
    public int compareTo(Orgao o) {
        if (this.CodOrgao < o.getCodOrgao()) {
            return -1;
        }
        if (this.CodOrgao == o.getCodOrgao()) {
            return 0;
        }
        return 1;
    }

    @Override
    public int hashCode() {
        return CodOrgao;
    }
}
