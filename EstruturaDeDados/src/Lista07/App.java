/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista07;

import Biblioteca.Keyboard;
import Biblioteca.ListaEncOrd;
import Biblioteca.MyIterator;
import Biblioteca.ObjectFile;

/**
 *
 * @author basmoura
 */
public class App {

    static ListaEncOrd<Aluno> alunos = new ListaEncOrd<>();
    static ListaEncOrd<Livro> livros = new ListaEncOrd<>();
    static ObjectFile arquivo = new ObjectFile("alunosLivros.dat");

    static void incluirAluno() {
        char resp;

        do {
            Keyboard.clrscr();

            int numMat = Keyboard.readInt("Informe a matrícula: ");
            Aluno aluno = new Aluno(numMat);

            if (alunos.contains(aluno)) {
                System.out.println("Aluno já cadastrado");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");
                aluno.setNome(nome);

                alunos.add(aluno);
            }
            resp = Keyboard.readChar("Deseja cadastrar outro aluno? (s/n): ");
        } while (resp == 's');
    }

    static void excluirAluno() {
        Keyboard.clrscr();

        int numMat = Keyboard.readInt("Informe a matrícula: ");

        Aluno aluno = new Aluno(numMat);

        if (alunos.contains(aluno)) {
            Keyboard.clrscr();

            aluno = alunos.retrieve(aluno);

            System.out.println("Matricula: " + aluno.getNumMat());
            System.out.println("Nome: " + aluno.getNome());

            char resp = Keyboard.readChar("Deseja remover? (s/n): ");

            if (resp == 's') {
                alunos.remove(aluno);
                System.out.println("Aluno removido");
            } else {
                System.out.println("Aluno não removido");
            }
        } else {
            System.out.println("Aluno não encontrado");
        }
        Keyboard.waitEnter();
    }

    static void consultarAluno() {
        Keyboard.clrscr();

        int numMat = Keyboard.readInt("Informe a matrícula: ");

        Aluno aluno = alunos.retrieve(new Aluno(numMat));

        if (aluno != null) {
            Keyboard.clrscr();

            System.out.println("Matricula: " + aluno.getNumMat());
            System.out.println("Nome: " + aluno.getNome() + "\n");

            MyIterator<Livro> it = aluno.getLivros().iterator();

            Livro livro = it.getFirst();
            System.out.println("Empréstimos");
            System.out.println("Codigo  Titulo                Empréstimo");
            System.out.println("------  --------------------  ----------");

            while (livro != null) {
                System.out.printf("%6d  %-20s  %s\n", livro.getCodLivro(),
                        livro.getTitulo(), livro.getDtEmprestimo());

                livro = it.getNext();
            }
        } else {
            System.out.println("Aluno não encontrado");
        }
        Keyboard.waitEnter();
    }

    static void listarAlunos() {
        Keyboard.clrscr();

        System.out.println("NumMat  Nome                  QtdLivros");
        System.out.println("------  --------------------  ---------");

        MyIterator<Aluno> it = alunos.iterator();
        Aluno aluno = it.getFirst();

        while (aluno != null) {
            int totalLivros;

            if (aluno.getLivros() != null) {
                totalLivros = aluno.getLivros().size();
            } else {
                totalLivros = 0;
            }

            System.out.printf("%6d  %-20s  %5d\n", aluno.getNumMat(), aluno.getNome(),
                    totalLivros);

            aluno = it.getNext();
        }

        Keyboard.waitEnter();
    }

    static void incluirLivro() {
        char resp;

        do {
            Keyboard.clrscr();

            int codLivro = Keyboard.readInt("Informe o código: ");
            Livro livro = new Livro(codLivro);

            if (livros.contains(livro)) {
                System.out.println("Livro já cadastrado");
            } else {
                String titulo = Keyboard.readString("Informe o título: ");
                livro.setTitulo(titulo);

                livros.add(livro);
            }
            resp = Keyboard.readChar("Deseja cadastrar outro livro? (s/n): ");
        } while (resp == 's');
    }

    static void excluirLivro() {
        Keyboard.clrscr();

        int codLivro = Keyboard.readInt("Informe a código: ");

        Livro livro = new Livro(codLivro);

        if (livros.contains(livro)) {
            Keyboard.clrscr();

            livro = livros.retrieve(livro);

            System.out.println("Matricula: " + livro.getCodLivro());
            System.out.println("Nome: " + livro.getTitulo());

            char resp = Keyboard.readChar("Deseja remover? (s/n): ");

            if (resp == 's') {
                livros.remove(livro);
                System.out.println("Livro removido");
            } else {
                System.out.println("Livro não removido");
            }
        } else {
            System.out.println("Livro não encontrado");
        }
        Keyboard.waitEnter();
    }

    static void consultarLivro() {
        Keyboard.clrscr();

        int codLivro = Keyboard.readInt("Informe o código: ");

        Livro livro = livros.retrieve(new Livro(codLivro));

        if (livro != null) {
            Keyboard.clrscr();

            System.out.println("Codigo: " + livro.getCodLivro());
            System.out.println("Titulo: " + livro.getTitulo());

            if (livro.getAluno() != null) {
                System.out.printf("Aluno: %d - %s\n", livro.getAluno().getNumMat(),
                        livro.getAluno().getNome());
                System.out.println("Emprestimo: " + livro.getDtEmprestimo());
            } else {
                System.out.println("Disponivel para empréstimo");
            }
        }
        Keyboard.waitEnter();
    }

    static void listarLivros() {
        Keyboard.clrscr();

        System.out.println("CodLivro  Titulo                  Situação");
        System.out.println("--------  --------------------  ----------");

        MyIterator<Livro> it = livros.iterator();
        Livro livro = it.getFirst();

        while (livro != null) {

            String emprestado;

            if (livro.getAluno() != null) {
                emprestado = "Emprestado";
            } else {
                emprestado = "Disponível";
            }

            System.out.printf("%8d  %-20s  %-10s\n", livro.getCodLivro(), livro.getTitulo(),
                    emprestado);

            livro = it.getNext();
        }

        Keyboard.waitEnter();
    }

    static void realizarEmprestimo() {
        char resp;

        do {
            Keyboard.clrscr();

            int numMat = Keyboard.readInt("Informe a matricula: ");

            Aluno aluno = alunos.retrieve(new Aluno(numMat));

            if (aluno == null) {
                System.out.println("Aluno não encontrado");
            } else {
                int codLivro = Keyboard.readInt("Informe o código: ");

                Livro livro = livros.retrieve(new Livro(codLivro));

                if (livro == null) {
                    System.out.println("Livro não encontrado");
                } else if (livro.getAluno() != null) {
                    System.out.println("Livro não disponível para empréstimo");
                } else {
                    String dtEmprestimo = Keyboard.readData("Data: ");

                    livro.setDtEmprestimo(dtEmprestimo);
                    livro.setAluno(aluno);

                    aluno.getLivros().add(livro);
                }
            }
            resp = Keyboard.readChar("Outro empréstimo? (s/n): ");
        } while (resp == 's');
    }

    static void devolverLivro() {
        Keyboard.clrscr();

        int numMat = Keyboard.readInt("Informe a matrícula: ");

        Aluno aluno = alunos.retrieve(new Aluno(numMat));

        if (aluno != null) {
            Keyboard.clrscr();

            System.out.println("Matricula: " + aluno.getNumMat());
            System.out.println("Nome: " + aluno.getNome() + "\n");

            MyIterator<Livro> it = aluno.getLivros().iterator();

            Livro livro = it.getFirst();
            System.out.println("Empréstimos");
            System.out.println("Codigo  Titulo                Empréstimo");
            System.out.println("------  --------------------  ----------");

            while (livro != null) {
                System.out.printf("%6d  %-20s  %s\n", livro.getCodLivro(),
                        livro.getTitulo(), livro.getDtEmprestimo());

                livro = it.getNext();
            }

            int codLivro = Keyboard.readInt("Informe o código do livro para devolução: ");

            livro = livros.retrieve(new Livro(codLivro));

            aluno.getLivros().remove(livro);
            livro.setAluno(null);

            System.out.println("Devolução concluída");
        } else {
            System.out.println("Aluno não encontrado");
        }

        Keyboard.waitEnter();
    }

    static void listarLivrosEmprestados() {
        MyIterator<Livro> it = livros.iterator();
        Livro livro = it.getFirst();
        
        System.out.println("CodLivro  Titulo               Aluno");
        System.out.println("--------  -------------------  --------------------");
        
        while (livro != null) {
            if (livro.getAluno() != null) {
                System.out.printf("%8d  %-20s  %-20s\n", livro.getCodLivro(),
                        livro.getTitulo(), livro.getAluno().getNome());
            }
            livro = it.getNext();
        }
        
        Keyboard.waitEnter();
    }

    static void lerArquivo() {
        if (arquivo.reset()) {
            alunos = (ListaEncOrd<Aluno>) arquivo.read();
            livros = (ListaEncOrd<Livro>) arquivo.read();

            arquivo.closeFile();
        } else {
            alunos = new ListaEncOrd<>();
            livros = new ListaEncOrd<>();
        }
    }

    static void gravarArquivo() {
        arquivo.rewrite();

        arquivo.write(alunos);
        arquivo.write(livros);

        arquivo.closeFile();
    }

    public static void main(String[] args) {
        int menu;
        lerArquivo();

        do {
            Keyboard.clrscr();

            menu = Keyboard.menu("Alunos/Livros/Empréstimo/Devolução/"
                    + "Listar Livros Emprestados/Terminar");

            switch (menu) {
                case 1:
                    Keyboard.clrscr();

                    int menuAluno = Keyboard.menu("Incluir/Excluir/"
                            + "Consultar/Listar/Voltar");

                    switch (menuAluno) {
                        case 1:
                            incluirAluno();
                            break;
                        case 2:
                            excluirAluno();
                            break;
                        case 3:
                            consultarAluno();
                            break;
                        case 4:
                            listarAlunos();
                            break;
                        case 5:
                            break;
                    }
                    break;

                case 2:
                    Keyboard.clrscr();

                    int menuLivro = Keyboard.menu("Incluir/Excluir/"
                            + "Consultar/Listar/Voltar");

                    switch (menuLivro) {
                        case 1:
                            incluirLivro();
                            break;
                        case 2:
                            excluirLivro();
                            break;
                        case 3:
                            consultarLivro();
                            break;
                        case 4:
                            listarLivros();
                            break;
                        case 5:
                            break;
                    }
                    break;

                case 3:
                    realizarEmprestimo();
                    break;
                case 4:
                    devolverLivro();
                    break;
                case 5:
                    listarLivrosEmprestados();
                    break;
            }
        } while (menu < 6);
        gravarArquivo();
    }
}
