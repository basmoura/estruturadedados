package lista08;

import diversos.*;

public class Polinomio {
	private ListaEncOrd<TermoPolinomio> listaTermos;

	public Polinomio() {
		listaTermos = new ListaEncOrd<TermoPolinomio>();
	}

	public void insira(float coeficiente, int expoente) {
		TermoPolinomio novoTermo = new TermoPolinomio(coeficiente, expoente);
		MyIterator<TermoPolinomio> it = listaTermos.iterator();
		TermoPolinomio p = it.getFirst();
		while (p != null) {
			if (novoTermo.compareTo(p) == 0) {
				p.setCoeficiente(p.getCoeficiente() + coeficiente);
				if (p.getCoeficiente() == 0)
					it.remove();
				return;
			}
			p = it.getNext();
		}
		listaTermos.add(novoTermo);
	}

	public Polinomio[] divida(Polinomio q) {
		Polinomio[] divisao = new Polinomio[2];
		Polinomio quociente = new Polinomio();
		Polinomio quocienteResult = new Polinomio();
		Polinomio resto = this;
		Polinomio dividendo = this;
		MyIterator<TermoPolinomio> itDivs = q.listaTermos.iterator();
		TermoPolinomio termoDivs = itDivs.getFirst();
		float coeficiente;
		int expoente;
		MyIterator<TermoPolinomio> dividendoIt = dividendo.listaTermos
				.iterator();
		TermoPolinomio termoDivd = dividendoIt.getFirst();
		while (termoDivd != null) {
			coeficiente = termoDivd.getCoeficiente()
					/ termoDivs.getCoeficiente();
			if (termoDivd.getExpoente() > termoDivs.getExpoente())
				expoente = termoDivd.getExpoente() - termoDivs.getExpoente();
			else
				expoente = termoDivd.getExpoente() - termoDivd.getExpoente();
			quociente.insira((coeficiente), expoente);
			quocienteResult.insira(coeficiente, expoente);
			resto = (quociente.multiplique(q));
			dividendo = dividendo.subtraia(resto);
			dividendoIt = dividendo.listaTermos.iterator();
			termoDivd = dividendoIt.getFirst();
			if (dividendo.listaTermos.size() < q.listaTermos.size()) {
				break;
			}
			quociente.listaTermos.clear();
		}
		divisao[0] = dividendo;
		divisao[1] = quocienteResult;
		return divisao;
	}

	public Polinomio subtraia(Polinomio q) {
		Polinomio r = new Polinomio();
		MyIterator<TermoPolinomio> itP = listaTermos.iterator();
		MyIterator<TermoPolinomio> itQ = q.listaTermos.iterator();
		TermoPolinomio termoP = itP.getFirst();
		TermoPolinomio termoQ = itQ.getFirst();
		while ((termoQ != null) || (termoP != null)) {
			if ((termoP != null) && (termoQ != null))
				if (termoP.getExpoente() > termoQ.getExpoente()) {
					r.insira(termoP.getCoeficiente(), termoP.getExpoente());
					termoP = itP.getNext();
				} else if (termoQ.getExpoente() > termoP.getExpoente()) {
					r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
					termoQ = itQ.getNext();
				} else {
					if (termoQ.getCoeficiente() - termoP.getCoeficiente() != 0)
						r.insira(
								termoQ.getCoeficiente()
										- termoP.getCoeficiente(),
								termoQ.getExpoente());
					termoP = itP.getNext();
					termoQ = itQ.getNext();
				}
			else if (termoP != null)
				while (termoP != null) {
					r.insira(termoP.getCoeficiente(), termoP.getExpoente());
					termoP = itP.getNext();
				}
			else if (termoQ != null)
				while (termoQ != null) {
					r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
					termoQ = itQ.getNext();
				}
		}
		return r;
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
					if (termoQ.getCoeficiente() + termoP.getCoeficiente() != 0)
						r.insira(
								termoQ.getCoeficiente()
										+ termoP.getCoeficiente(),
								termoQ.getExpoente());
					termoP = itP.getNext();
					termoQ = itQ.getNext();
				}
			} else if (termoP != null)
				while (termoP != null) {
					r.insira(termoP.getCoeficiente(), termoP.getExpoente());
					termoP = itP.getNext();
				}
			else if (termoQ != null)
				while (termoQ != null) {
					r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
					termoQ = itQ.getNext();
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
		MyIterator<TermoPolinomio> itP = listaTermos.iterator();
		TermoPolinomio termoIt = itP.getFirst();
		String polinomio = "";
		while (termoIt != null) {
			polinomio += termoIt.getCoeficiente();
			if (termoIt.getExpoente() > 1) {
				polinomio += "x^" + termoIt.getExpoente();
			} else if (termoIt.getExpoente() == 1) {
				polinomio += "x";
			}
			termoIt = itP.getNext();
			if ((termoIt != null)) {
				if (termoIt.getCoeficiente() > 0)
					polinomio += " +";
			}
		}
		System.out.println(polinomio);
	}

}
