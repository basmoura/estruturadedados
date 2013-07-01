/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoMapa;

/**
 *
 * @author basmoura
 */
public class Bem implements Comparable<Bem> {
    private int codBem;
    private String descricao;
    private float valor;

    public Bem(int codBem) {
        this.codBem = codBem;
    }

    public int getCodBem() {
        return codBem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Bem o) {
        if (codBem < o.codBem) {
            return -1;
        }
        if (codBem == o.codBem) {
            return 0;
        }
        return 1;
    }
}
