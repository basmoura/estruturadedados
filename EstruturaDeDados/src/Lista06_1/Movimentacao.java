/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista06_1;

import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public class Movimentacao implements Serializable, Comparable<Movimentacao> {
    private int numConta;
    private String dataMov;
    private String tipoMov;
    private double valorMov;

    public Movimentacao(int numConta, String dataMov, String tipoMov, double valorMov) {
        this.numConta = numConta;
        this.dataMov = dataMov;
        this.tipoMov = tipoMov;
        this.valorMov = valorMov;
    }

    public int getNumConta() {
        return numConta;
    }

    public String getDataMov() {
        return dataMov;
    }

    public void setDataMov(String dataMov) {
        this.dataMov = dataMov;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    public double getValorMov() {
        return valorMov;
    }

    public void setValorMov(double valorMov) {
        this.valorMov = valorMov;
    }

    @Override
    public int compareTo(Movimentacao o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
