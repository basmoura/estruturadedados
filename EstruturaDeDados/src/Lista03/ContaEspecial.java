/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista03;

/**
 *
 * @author basmoura
 */
public class ContaEspecial extends Conta {

    private double limiteCredito;

    public ContaEspecial(double limiteCredito, int numConta) {
        super(numConta);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public boolean efetuarSaque(double valorSaque) {
        double total = limiteCredito + valorSaque;

        if (total > saldo) {
            return false;
        }
        
        saldo = saldo - total;
        return true;
    }
}
