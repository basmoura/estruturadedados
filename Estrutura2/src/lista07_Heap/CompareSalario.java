package lista07_Heap;

import java.io.Serializable;

public class CompareSalario implements Serializable, Comparable<CompareSalario> {

    private Pessoa pessoa;

    public CompareSalario(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int compareTo(CompareSalario o) {
        if (this.pessoa.getSalario() < o.getPessoa().getSalario()) {
            return -1;
        }
        if (this.pessoa.getSalario() == o.getPessoa().getSalario()) {
            return 0;
        }
        return 1;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
}
