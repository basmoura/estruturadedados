package Lista06_1;

import Lista02.*;
import java.io.Serializable;

/**
 *
 * @author basmoura
 */
public abstract class Conta implements Serializable, Comparable<Conta> {

    private int numConta;
    private String nmCliente;
    protected double saldo;

    public Conta(int numConta) {
        this.numConta = numConta;
    }

    public int getNumConta() {
        return numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public void efetuarDeposito(double valor) {
        this.saldo = this.saldo + valor;
    }

    public abstract boolean efetuarSaque(double valorSaque);

    @Override
    public int compareTo(Conta c) {
        if (numConta < c.numConta) {
            return -1;
        }
        if (numConta == c.numConta) {
            return 0;
        }
        return 1;
    }
}
