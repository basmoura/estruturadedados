package Lista08;

import Biblioteca.ListaEncOrd;
import Biblioteca.MyIterator;

public class Polinomio {

    private ListaEncOrd<TermoPolinomio> listaTermos;

    public Polinomio() {
        listaTermos = new ListaEncOrd<TermoPolinomio>();
    }

    public void insira(float coeficiente, int expoente) {
    }

    public Polinomio[] divida(Polinomio q) {
        return null;
    }

    public Polinomio subtraia(Polinomio q) {
        return null;
    }

    public Polinomio some(Polinomio q) {
        Polinomio r = new Polinomio();
        MyIterator<TermoPolinomio> itP = listaTermos.iterator();
        MyIterator<TermoPolinomio> itQ = q.listaTermos.iterator();
        TermoPolinomio termoP = itP.getFirst();
        TermoPolinomio termoQ = itQ.getFirst();
        while ((termoQ != null) || (termoP != null)) {
            if ((termoP != null) && (termoQ != null)) {
                if (termoP.getExpoente() > termoQ.getExpoente()) {
                    r.insira(termoP.getCoeficiente(), termoP.getExpoente());
                    termoP = itP.getNext();
                } else if (termoQ.getExpoente() > termoP.getExpoente()) {
                    r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
                    termoQ = itQ.getNext();
                } else {
                    if (termoQ.getCoeficiente() + termoP.getCoeficiente() != 0) {
                        r.insira(termoQ.getCoeficiente()
                                + termoP.getCoeficiente(), termoQ
                                .getExpoente());
                    }
                    termoP = itP.getNext();
                    termoQ = itQ.getNext();
                }
            } else if (termoP != null) {
                while (termoP != null) {
                    r.insira(termoP.getCoeficiente(), termoP.getExpoente());
                    termoP = itP.getNext();
                }
            } else if (termoQ != null) {
                while (termoQ != null) {
                    r.insira(termoQ.getCoeficiente(), termoQ
                            .getExpoente());
                    termoQ = itQ.getNext();
                }
            }
        }
        return r;
    }

    public Polinomio multiplique(Polinomio q) {
        Polinomio r = new Polinomio();
        MyIterator<TermoPolinomio> itP = listaTermos.iterator();
        MyIterator<TermoPolinomio> itQ = q.listaTermos.iterator();
        TermoPolinomio termoP = itP.getFirst();
        while (termoP != null) {
            TermoPolinomio termoQ = itQ.getFirst();
            while (termoQ != null) {
                r.insira(termoP.getCoeficiente() * termoQ.getCoeficiente(),
                        termoP.getExpoente() + termoQ.getExpoente());
                termoQ = itQ.getNext();
            }
            termoP = itP.getNext();
        }
        return r;
    }

    public void imprima() {
    }
}
