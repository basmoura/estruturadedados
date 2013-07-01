/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoMapa;

/**
 *
 * @author basmoura
 */
public class Pessoa implements Comparable<Pessoa> {
    private int codPessoa;
    private String nmPessoa;
    
    public Pessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    @Override
    public int compareTo(Pessoa o) {
        if (codPessoa < o.codPessoa)
            return -1;
        if (codPessoa == o.codPessoa)
            return 0;
        return 1;
    }
}
