package loucuras;

import revisao.ProfHorista;
import revisao.Professor;

public class DemoTeste {

	public static void main(String[] args) {
		Teste<Professor, Integer> teste = new Teste<Professor, Integer>();

		teste.setObj1(new ProfHorista(1, "", 200, 400));
		teste.setObj2(1);
	}
}
