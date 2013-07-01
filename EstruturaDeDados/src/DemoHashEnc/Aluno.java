package DemoHashEnc;

public class Aluno implements Comparable<Aluno> {

    private int numMat;
    private String nomeAluno;

    public Aluno(int numMat) {
        super();
        this.numMat = numMat;
    }

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

    public int getNumMat() {
        return numMat;
    }

    @Override
    public int hashCode() {
        return numMat;
    }

    @Override
    public int compareTo(Aluno o) {
        if (numMat < o.numMat) {
            return -1;
        }
        if (numMat == o.numMat) {
            return 0;
        }
        return 1;
    }
}
