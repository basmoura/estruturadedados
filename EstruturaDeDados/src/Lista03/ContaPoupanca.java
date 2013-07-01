/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista03;

/**
 *
 * @author basmoura
 */
public class ContaPoupanca extends Conta {

    public ContaPoupanca(int numConta) {
        super(numConta);
    }

    public double creditarRendimento(double txJuros) {
        return saldo = saldo + ((saldo * txJuros) / 100);
    }

    @Override
    public boolean efetuarSaque(double valorSaque) {
        if (valorSaque > saldo) {
            return false;
        }

        saldo = saldo - valorSaque;
        return true;
    }
}
