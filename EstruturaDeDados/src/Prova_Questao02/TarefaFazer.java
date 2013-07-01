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
public class TarefaFazer implements Serializable, Comparable<TarefaFazer> {

    private int codTarefa;
    private String desc;
    private String data;

    public TarefaFazer(int codTarefa) {
        this.codTarefa = codTarefa;
    }

    public TarefaFazer(String desc, String data) {
        this.desc = desc;
        this.data = data;
    }

    public int getCodTarefa() {
        return codTarefa;
    }

    public String getDesc() {
        return desc;
    }

    public String getData() {
        return data;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int compareTo(TarefaFazer o) {
        if (this.codTarefa > o.codTarefa) {
            return -1;
        }
        if (this.codTarefa == o.codTarefa) {
            return 0;
        }
        return 1;
    }
}
