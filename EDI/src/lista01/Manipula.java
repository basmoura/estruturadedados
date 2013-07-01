package lista01;

import diversos.ObjectFile;

public class Manipula {
	private final int CONSTANTE = 10;
	private Pessoa[] pessoas = new Pessoa[CONSTANTE];
	private int tamanhoReal = 0;
	private ObjectFile arqPessoa = new ObjectFile(
			"/home/marcos/workspace/Unit - EDI/src/testando.dat");

	public int getTamanhoReal() {
		return tamanhoReal;
	}

	private int busca(Pessoa p) {
		for (int i = 0; i < tamanhoReal; i++) {
			if (pessoas[i].equals(p)) {
				return i;
			}
		}
		return -1;
	}

	public boolean cadastrar(Pessoa p) throws Exception {
		int pos = busca(p);
		if (pos >= 0) {
			throw new Exception("Contato já cadastrado.");
		}
		pessoas[tamanhoReal] = p;
		tamanhoReal++;
		return true;
	}

	public Pessoa[] listar() {
		return pessoas;
	}

	public String consultar(int codigo) throws Exception {
		for (int i = 0; i < tamanhoReal; i++) {
			if (codigo == pessoas[i].getCodigo()) {
				return pessoas[i].toString();
			}
		}
		throw new Exception("Pessoa já cadastrada.");
	}

	public void leiaArquivo() {
		Pessoa pessoa;
		tamanhoReal = 0;
		arqPessoa.reset();
		pessoa = (Pessoa) arqPessoa.read();
		while (pessoa != null) {
			pessoas[tamanhoReal] = pessoa;
			tamanhoReal++;
			pessoa = (Pessoa) arqPessoa.read();
		}
		arqPessoa.closeFile();
	}

	public void gravaArquivo() {
		arqPessoa.rewrite();
		for (int i = 0; i < tamanhoReal; i++) {
			arqPessoa.write(pessoas[i]);
		}
		arqPessoa.closeFile();
	}
}
