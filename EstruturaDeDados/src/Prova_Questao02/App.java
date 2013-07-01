/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prova_Questao02;

import Biblioteca.Keyboard;
import Biblioteca.ListaEncOrd;
import Biblioteca.MyIterator;
import Biblioteca.ObjectFile;

/**
 *
 * @author basmoura
 */
public class App {

    static ListaEncOrd<Pessoa> pessoas = new ListaEncOrd<>();
    static ObjectFile arquivo = new ObjectFile("prova.dat");

    static void cadastrarPessoa() {
        char resp;

        do {
            Keyboard.clrscr();

            int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

            Pessoa pessoa = new Pessoa(codPessoa);

            if (pessoas.contains(pessoa)) {
                System.err.println("Pessoa já cadastrada");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");
                pessoa.setNome(nome);

                pessoas.add(pessoa);
            }
            resp = Keyboard.readChar("Deseja cadastrar outra pessoa? (s/n): ");
        } while (resp == 's');
    }

    static void listarPessoas() {
        Keyboard.clrscr();

        System.out.println("Codigo  Nome");
        System.out.println("------  ------------------");

        MyIterator<Pessoa> it = pessoas.iterator();
        Pessoa pessoa = it.getFirst();

        while (pessoa != null) {
            System.out.printf("%6d  %-20s\n", pessoa.getCodPessoa(), pessoa.getNome());
            pessoa = it.getNext();
        }

        Keyboard.waitEnter();
    }

    static void excluirPessoa() {
        Keyboard.clrscr();

        int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

        Pessoa pessoa = pessoas.retrieve(new Pessoa(codPessoa));

        if (pessoa == null) {
            System.err.println("Pessoa não cadastrada");
        } else {
            Keyboard.clrscr();
            char resp;

            System.out.println("Código: " + pessoa.getCodPessoa());
            System.out.println("Nome: " + pessoa.getNome() + "\n");
            resp = Keyboard.readChar("Deseja remover? (s/n): ");

            if (resp == 's') {
                System.out.println("Pessoa removida");
                pessoas.remove(pessoa);
            } else {
                System.out.println("Pessoa não removida");
            }
        }
        Keyboard.waitEnter();
    }

    static void incluirTarefa() {
        char resp;

        do {
            Keyboard.waitEnter();
            
            int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

            Pessoa pessoa = pessoas.retrieve(new Pessoa(codPessoa));

            if (pessoa == null) {
                System.err.println("Pessoa não cadastrada");
            } else {
                TarefaFazer tarefaFazer;

                if (pessoa.getTarefasFazer().isEmpty()) {
                    int codTarefa = 1;

                    tarefaFazer = new TarefaFazer(codTarefa);
                    pessoa.setTotalTarefas(codTarefa);
                } else {
                    int codTarefa = pessoa.getTotalTarefas() + 1;

                    tarefaFazer = new TarefaFazer(codTarefa);
                    pessoa.setTotalTarefas(pessoa.getTotalTarefas() + 1);
                }

                String desc = Keyboard.readString("Informe a descrição: ");
                tarefaFazer.setDesc(desc);

                String data = Keyboard.readData("Data para ser realizada: ");
                tarefaFazer.setData(data);

                pessoa.getTarefasFazer().add(tarefaFazer);
            }

            resp = Keyboard.readChar("Outra tarefa? (s/n): ");
        } while (resp == 's');
    }

    static void concluirTarefa() {
        char resp;
        
        Keyboard.clrscr();
        
        do {

        int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

        Pessoa pessoa = pessoas.retrieve(new Pessoa(codPessoa));

        if (pessoa == null) {
            System.err.println("Pessoa não cadastrada");
        } else {
            int codTarefa = Keyboard.readInt("Informe o código da tarefa: ");

            TarefaFazer tarefaFazer = pessoa.getTarefasFazer().retrieve(new TarefaFazer(codTarefa));

            if (tarefaFazer == null) {
                System.err.println("Tarefa não cadastrada");
            } else {
                int codTarefaConcluida = tarefaFazer.getCodTarefa();

                TarefaConcluida tarefaConcluida = new TarefaConcluida(codTarefaConcluida);

                tarefaConcluida.setDesc(tarefaFazer.getDesc());
                tarefaConcluida.setDataPrev(tarefaFazer.getData());

                String dataConclusao = Keyboard.readData("Data da conclusão: ");

                tarefaConcluida.setDataConc(dataConclusao);

                pessoa.getTarefasConcluidas().add(tarefaConcluida);
                pessoa.getTarefasFazer().remove(tarefaFazer);
            }
        }
        resp = Keyboard.readChar("Outra tarefa? (s/n): ");
    } while (resp == 's');
        }
    static void listarTarefasFazer() {
        Keyboard.clrscr();

        int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

        Pessoa pessoa = pessoas.retrieve(new Pessoa(codPessoa));

        if (pessoa == null) {
            System.err.println("Pessoa não cadastrada");
        } else if (pessoa.getTarefasFazer().isEmpty()) {
            System.err.println("Sem tarefas cadastradas");
        } else {
            System.out.println("Pessoa: " + pessoa.getNome());
            System.out.println("");
            System.out.println("Codigo  Descricao                   Data");
            System.out.println("------  --------------------  ----------");

            MyIterator<TarefaFazer> it = pessoa.getTarefasFazer().iterator();
            TarefaFazer tarefaFazer = it.getFirst();

            while (tarefaFazer != null) {
                System.out.printf("%6d  %-20s  %s\n", tarefaFazer.getCodTarefa(),
                        tarefaFazer.getDesc(), tarefaFazer.getData());
                tarefaFazer = it.getNext();
            }
        }
        Keyboard.waitEnter();
    }

    static void listarTarefasConcluidas() {
        Keyboard.clrscr();

        int codPessoa = Keyboard.readInt("Informe o código da pessoa: ");

        Pessoa pessoa = pessoas.retrieve(new Pessoa(codPessoa));

        if (pessoa == null) {
            System.err.println("Pessoa não cadastrada");
        } else if (pessoa.getTarefasConcluidas().isEmpty()) {
            System.err.println("Sem tarefas cadastradas");
        } else {
            System.out.println("Pessoa: " + pessoa.getNome());
            System.out.println("");
            System.out.println("Codigo  Descricao               DataPrev  DataConclusao");
            System.out.println("------  --------------------  ----------  -------------");

            MyIterator<TarefaConcluida> it = pessoa.getTarefasConcluidas().iterator();
            TarefaConcluida tarefaConcluida = it.getFirst();

            while (tarefaConcluida != null) {
                System.out.printf("%6d  %-20s  %-10s  %s\n", tarefaConcluida.getCodTarefa(),
                        tarefaConcluida.getDesc(), tarefaConcluida.getDataPrev(),
                        tarefaConcluida.getDataConc());
                tarefaConcluida = it.getNext();
            }
        }
        Keyboard.waitEnter();
    }

    static void lerArquivo() {
        if (arquivo.reset()) {
            pessoas = (ListaEncOrd<Pessoa>) arquivo.read();

            arquivo.closeFile();
        } else {
            pessoas = new ListaEncOrd<>();
        }
    }

    static void gravarArquivo() {
        arquivo.rewrite();

        arquivo.write(pessoas);

        arquivo.closeFile();

    }

    public static void main(String[] args) {
        int menu;
        lerArquivo();
        do {
            Keyboard.clrscr();

            menu = Keyboard.menu("Cadastrar Pessoa/Listar Pessoa/"
                    + "Excluir Pessoa/Incluir Tarefa/Concluir Tarefa/"
                    + "Listar Tarefas a Fazer/Listar Tarefas Concluidas/"
                    + "Sair");

            switch (menu) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    excluirPessoa();
                    break;
                case 4:
                    incluirTarefa();
                    break;
                case 5:
                    concluirTarefa();
                    break;
                case 6:
                    listarTarefasFazer();
                    break;
                case 7:
                    listarTarefasConcluidas();
                    break;
            }
        } while (menu < 8);
        gravarArquivo();
    }
}
