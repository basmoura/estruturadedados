package Prova_Questa01;

public class Aluno implements Comparable<Aluno> {

    private int numMat;
    private String nomeAluno;

    public Aluno(int numMat, String nomeAluno) {
        super();
        this.numMat = numMat;
        this.nomeAluno = nomeAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getnumMat() {
        return numMat;
    }

    @Override
    public int compareTo(Aluno o) {
        if (this.numMat > o.numMat) {
            return -1;
        }
        if (this.numMat == o.numMat) {
            return 0;
        }
        return 1;
    }
}
