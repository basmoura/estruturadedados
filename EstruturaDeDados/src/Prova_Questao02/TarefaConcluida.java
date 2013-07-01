/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prova_Questao02;

import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public class TarefaConcluida implements Serializable, Comparable<TarefaConcluida> {

    private int codTarefa;
    private String desc;
    private String dataPrev;
    private String dataConc;

    public TarefaConcluida(int codTarefa) {
        this.codTarefa = codTarefa;
    }

    public TarefaConcluida(int codTarefa, String desc, String dataPrev) {
        this.codTarefa = codTarefa;
        this.desc = desc;
        this.dataPrev = dataPrev;
    }

    public int getCodTarefa() {
        return codTarefa;
    }

    public String getDesc() {
        return desc;
    }

    public String getDataPrev() {
        return dataPrev;
    }

    public String getDataConc() {
        return dataConc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDataPrev(String dataPrev) {
        this.dataPrev = dataPrev;
    }

    public void setDataConc(String dataConc) {
        this.dataConc = dataConc;
    }

    @Override
    public int compareTo(TarefaConcluida o) {
        if (this.codTarefa > o.codTarefa) {
            return -1;
        }
        if (this.codTarefa == o.codTarefa) {
            return 0;
        }
        return 1;
    }
}
