package Lista05_1;

public class Aluno {

    private int numMat;
    private String nomeAluno;
    private Curso curso;

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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
