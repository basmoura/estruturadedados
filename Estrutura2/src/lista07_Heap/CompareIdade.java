package lista07_Heap;

import java.io.Serializable;

public class CompareIdade implements Serializable, Comparable<CompareIdade> {

    private Pessoa pessoa;

    public CompareIdade(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int compareTo(CompareIdade o) {
        if (this.pessoa.getIdade() < o.getPessoa().getIdade()) {
            return -1;
        }
        if (this.pessoa.getIdade() == o.getPessoa().getIdade()) {
            return 0;
        }
        return 1;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
}
