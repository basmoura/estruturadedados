/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoArvoreBusca;

import Biblioteca.ArvoreBin;
import Biblioteca.Keyboard;
import Biblioteca.MyIterator;
import Biblioteca.NoArvoreBin;

/**
 *
 * @author basmoura
 */
public class DemoArvoreBusca {

    static ArvoreBin<Integer> arvore = new ArvoreBin<>();

    static boolean insira(int valor) {
        int direcao = -1;
        NoArvoreBin<Integer> p = arvore.getRaiz();
        NoArvoreBin<Integer> pai = null;

        if (p != null) {
            while (true) {
                if (p.getObj() == valor) {
                    return false;
                }

                if (valor < p.getObj()) {
                    if (p.getEsq() == null) {
                        pai = p;
                        direcao = -1;

                        break;
                    }
                    p = p.getEsq();

                } else {
                    if (p.getDir() == null) {
                        pai = p;
                        direcao = 1;

                        break;
                    }
                    p = p.getDir();
                }
            }
        }

        arvore.insert(valor, pai, direcao);

        return true;
    }

    static void inserir() {
        char resp;

        do {
            Keyboard.clrscr();
            
            int valor = Keyboard.readInt("Informe o valor: ");

            if (!insira(valor)) {
                System.out.println("Valor já cadastrado");
            }

            resp = Keyboard.readChar("Outro valor? (s/n): ");
        } while (resp == 's');
    }

    static boolean remova(int valor) {
        NoArvoreBin<Integer> p = arvore.getNo(valor);
        
        if (p == null)
            return false;
        
        arvore.delete(p);

        return true;
    }
    
    static void remover() {
        char resp;
        
        do {
            Keyboard.clrscr();
            
            int valor = Keyboard.readInt("Informe o valor: ");
            
            if (!remova(valor)) {
                System.out.println("Valor inexistente");
            }
            
            resp = Keyboard.readChar("Outro valor? (s/n): ");
        } while (resp == 's');
        
    }

    static void listar() {
        Keyboard.clrscr();
        
        MyIterator<Integer> it = arvore.iterator();
        Integer vl = it.getFirst();
        
        while (vl != null) {
            System.out.println(vl.intValue());
            
            vl = it.getNext();
        }
        
        Keyboard.waitEnter();
    }

    public static void main(String[] args) {
        int opcao;
        do {
            Keyboard.clrscr();
            opcao = Keyboard.menu("Inserir/Remover/Listar/Desenhar/Terminar");
            switch (opcao) {
                case 1:
                    inserir();
                    break;
                case 2:
                    remover();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    Keyboard.clrscr();
                    arvore.desenhe(80);
                    Keyboard.waitEnter();
                    break;

            }
        } while (opcao < 5);
        System.out.println("\nFim do programa");

    }
}
