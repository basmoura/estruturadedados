package utilitarios;

import java.util.Comparator;

public class Polinomio {
	private ListaEncOrd<TermoPolinomio>	listaTermos;
	
	public static class ComparaTermoPolinomio implements Comparator<TermoPolinomio>{
		@Override
		public int compare(TermoPolinomio o1, TermoPolinomio o2) {
			if(o1.getExpoente() > o2.getExpoente())
				return - 1;
			if(o1.getExpoente() == o2.getExpoente())
				return 0;
			else 
				return 1;
		}		
	}

	public Polinomio() {
		listaTermos = new ListaEncOrd<TermoPolinomio>();
	}
	
	 public  Polinomio (ListaEncOrd<TermoPolinomio> listaTermos){
         this.listaTermos = listaTermos;
	 }
	 public ListaEncOrd<TermoPolinomio> getListaTermos(){
	         return listaTermos;
	 }
	 public void setListaTermos(TermoPolinomio termo){
		 listaTermos.add(termo);
	 }
	 public void setListaTermos(ListaEncOrd<TermoPolinomio> t){
		 listaTermos = t;
	 }

	public void insira(float coeficiente, int expoente) {
		TermoPolinomio tP = new TermoPolinomio(coeficiente, expoente);
		listaTermos.add(tP);
		restruturar();
	}
	
	public Polinomio[] divida(Polinomio q){
		return null;

	}
	
	public Polinomio subtraia(Polinomio q) {
		return q;

	}

	public Polinomio some(Polinomio q) {
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
				}
				else
					if (termoQ.getExpoente() > termoP.getExpoente()) {
						r.insira(termoQ.getCoeficiente(), termoQ.getExpoente());
						termoQ = itQ.getNext();
					}
					else {
						if (termoQ.getCoeficiente() + termoP.getCoeficiente() != 0)
							r.insira(termoQ.getCoeficiente()
									+ termoP.getCoeficiente(), termoQ
									.getExpoente());
						termoP = itP.getNext();
						termoQ = itQ.getNext();
					}
			else
				if (termoP != null)
					while (termoP != null) {
						r.insira(termoP.getCoeficiente(), termoP.getExpoente());
						termoP = itP.getNext();
					}
				else
					if (termoQ != null)
						while (termoQ != null) {
							r.insira(termoQ.getCoeficiente(), termoQ
									.getExpoente());
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
	
	public int grau () {
        return listaTermos.iterator().getFirst().getExpoente();
	}
	
	public Polinomio restruturar(){
      
        ordenarDec();
       
        int i = 0;
        while(i <= grau()){
        		TermoPolinomio inicializar = new TermoPolinomio(0,0);
        		listaTermos.add(inicializar);
                i++;
        }
        MyIterator<TermoPolinomio> it = listaTermos.iterator();		
        TermoPolinomio termoPolinomio = it.getFirst();

		while(termoPolinomio != null) {
            float valor = termoPolinomio.getCoeficiente();
            TermoPolinomio termo = new TermoPolinomio(valor + listaTermos.retrieve(termoPolinomio).getCoeficiente(), termoPolinomio.getExpoente());
            listaTermos.add(termo);
            termoPolinomio = it.getNext();
		}
        return ordenarDec();
	}
	
	public Polinomio ordenarDec (){
        if(listaTermos.size() != 0){
        		listaTermos.sort(new ComparaTermoPolinomio());
                Polinomio res = new Polinomio(listaTermos);
                return res;
        }
        else{
                Polinomio res = new Polinomio();
                TermoPolinomio aux = new TermoPolinomio(0,0);
                res.setListaTermos(aux);
                return res;
        }
	}

	public void imprima() {

	}

}
