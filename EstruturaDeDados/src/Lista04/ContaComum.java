/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista04;

import Lista03.*;

/**
 *
 * @author basmoura
 */
public class ContaComum extends Conta {

    public ContaComum(int numConta) {
        super(numConta);
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
