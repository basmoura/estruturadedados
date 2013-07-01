/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista05_2;

import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public class Bem implements Serializable{
    private int codBem;
    private String desc;
    private String dtAquisicao;
    private double valor;

    public Bem(int codBem, String desc, String dtAquisicao, double valor) {
        this.codBem = codBem;
        this.desc = desc;
        this.dtAquisicao = dtAquisicao;
        this.valor = valor;
    }

    public int getCodBem() {
        return codBem;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDtAquisicao() {
        return dtAquisicao;
    }

    public void setDtAquisicao(String dtAquisicao) {
        this.dtAquisicao = dtAquisicao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
