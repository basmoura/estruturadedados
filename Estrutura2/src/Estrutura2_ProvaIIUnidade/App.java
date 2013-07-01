/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura2_ProvaIIUnidade;

import utilitarios.ChaveValor;
import utilitarios.HashMapa;
import utilitarios.Heap;
import utilitarios.Keyboard;
import utilitarios.MyIterator;
import utilitarios.MyIteratorMapa;
import utilitarios.Treap;

/**
 *
 * @author basmoura
 */
public class App {

    static Treap<Paciente> listaPacientes = new Treap<>(false); // Pacientes que irão fazer transplante
    static HashMapa<Orgao, Heap<FilaPrioridade>> listaOrgaos = new HashMapa<>(10);

    static void inserirOrgao() {
        char resp;
        do {
            Keyboard.clrscr();

            int codOrgao = Keyboard.readInt("Inform o código do órgão: ");
            Orgao orgao = new Orgao(codOrgao);

            if (listaOrgaos.contains(orgao)) {
                System.out.println("Órgão já cadastrado");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");
                orgao.setNomeOrgrao(nome);
                listaOrgaos.put(orgao, new Heap<FilaPrioridade>(10, Heap.TipoHeap.MaxHeap));
            }
            resp = Keyboard.readChar("Outro orgao? (s/n)");
        } while (resp == 's');
    }

    static void inserirPaciente() {
        char resp;
        do {
            Keyboard.clrscr();

            int cod = Keyboard.readInt("Informe o código do paciente: ");

            Paciente paciente = new Paciente(cod);

            if (listaPacientes.contains(paciente)) {
                System.out.println("Paciente já cadastrado");
            } else {
                String nome = Keyboard.readString("Informe o nome: ");

                paciente.setNomePaciente(nome);

                listaPacientes.add(paciente);

                int prioridade = Keyboard.readInt("Nível de prioridade: ");

                FilaPrioridade filaPrioridade = new FilaPrioridade(prioridade, paciente);

                int codigoOrgao = Keyboard.readInt("Informe o código do orgao: ");

                Orgao orgao = new Orgao(codigoOrgao);
                Heap<FilaPrioridade> filaOrgao = listaOrgaos.getValor(orgao);

                if (filaOrgao == null) {
                    System.out.println("Órgão não cadastrado");
                } else {
                    if (paciente.getOrgaos().contains(orgao)) {
                        System.out.println("Paciente já cadastrado.");
                    } else {
                        filaOrgao.add(filaPrioridade);
                        paciente.getOrgaos().add(orgao);
                        System.out.println("Paciente cadastrado na fila.");
                    }
                }
            }
            resp = Keyboard.readChar("Outro paciente (s/n): ");
        } while (resp == 's');

    }

    static void listarPacientes() {
        MyIterator<Paciente> iterator = listaPacientes.iterator();
        Paciente p = iterator.getFirst();
        System.out.println("Código  Nome                  Nº de filas");
        System.out.println("------  --------------------  -----------");
        while (p != null) {
            System.out.printf("%6d  %-20s  %11d\n", p.getCodPaciente(),
                    p.getNomePaciente(), p.getOrgaos().size());
            p = iterator.getNext();
        }
        Keyboard.waitEnter();
    }

    static void listarOrgaos() {
        Keyboard.clrscr();

        MyIteratorMapa<Orgao, Heap<FilaPrioridade>> iterator = listaOrgaos.iterator();
        ChaveValor<Orgao, Heap<FilaPrioridade>> cv = iterator.getFirst();
        System.out.println("Código  Nome                  Tamanho da Fila");
        System.out.println("------  --------------------  ---------------");
        while (cv != null) {
            System.out.printf("%6d  %-20s  %15d\n", cv.getChave().getCodOrgao(),
                    cv.getChave().getNomeOrgrao(), cv.getValor().size());
            cv = iterator.getNext();
        }

        Keyboard.waitEnter();
    }

    static void removerPacienteFila() {
        char resp;
        do {
            Keyboard.clrscr();

            int codOrgao = Keyboard.readInt("Informe o código do orgão: ");
            Orgao orgao = new Orgao(codOrgao);

            if (listaOrgaos.contains(orgao)) {
                Heap<FilaPrioridade> filaOrgao = listaOrgaos.getValor(orgao);
                if (filaOrgao.size() == 0 || filaOrgao == null) {
                    System.out.println("Nenhum paciente cadastrado");
                } else {
                    MyIterator<FilaPrioridade> it = filaOrgao.iterator();

                    Paciente paciente = it.getFirst().getPaciente();

                    System.out.println("Código  Nome                  Nº de filas");
                    System.out.println("------  --------------------  -----------");

                    System.out.printf("%6d  %-20s  %11d\n", paciente.getCodPaciente(),
                            paciente.getNomePaciente(), paciente.getOrgaos().size());

                    filaOrgao.remove();
                    
                    if (paciente.getOrgaos().isEmpty()) {
                        listaPacientes.remove(paciente);
                    }
                }
            }
            resp = Keyboard.readChar("Outro paciente? (s/n): ");
        } while (resp == 's');
    }

    static void listarFilas() {
        MyIteratorMapa<Orgao, Heap<FilaPrioridade>> iterator = listaOrgaos.iterator();
        ChaveValor<Orgao, Heap<FilaPrioridade>> cv = iterator.getFirst();
        System.out.println("CodOrgao  Nome do Orgao         CodPaciente  Nome do Paciente");
        System.out.println("--------  --------------------  -----------  --------------------");
        while (cv != null) {
            System.out.printf("%8d  %-20s  ", cv.getChave().getCodOrgao(), cv.getChave().getNomeOrgrao());
            MyIterator<FilaPrioridade> it = cv.getValor().iterator();
            FilaPrioridade fila = it.getFirst();
            while (fila != null) {
                System.out.printf("%11d  %-20s\n", fila.getPaciente().getCodPaciente(), fila.getPaciente().getNomePaciente());
                fila = it.getNext();
            }
            cv = iterator.getNext();
        }
        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Inserir órgão/Inserir paciente/Listar pacientes/"
                    + "Listar orgaos/Remover Paciente da Fila/Lista Filas/Sair");
            switch (opcao) {
                case 1:
                    inserirOrgao();
                    break;
                case 2:
                    inserirPaciente();
                    break;
                case 3:
                    listarPacientes();
                    break;
                case 4:
                    listarOrgaos();
                    break;
                case 5:
                    removerPacienteFila();
                    break;
                case 6:
                    listarFilas();
                    break;

            }
        } while (opcao < 7);
        System.out.println("\nFim do programa");
    }
}
