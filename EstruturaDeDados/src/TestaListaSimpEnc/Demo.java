/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestaListaSimpEnc;

import Biblioteca.Keyboard;
import Biblioteca.ListaSimpEnc;
import Biblioteca.MyIterator;

/**
 *
 * @author basmoura
 */
public class Demo {
    static ListaSimpEnc<Integer> produtos = new ListaSimpEnc<>();
    
    static void cadastrar() {
        int codigo = Keyboard.readInt("Codigo: ");
        
        produtos.insertAtBegin(codigo);
    }
    
    static void listar() {
        MyIterator<Integer> p = produtos.iterator();
        
        
        System.out.println("Produtos cadastrado: " + p.getFirst() + "\nTotal: " + produtos.size());
    }
    
    static void remover() {        
        
        produtos.removeFromBegin();
        
    }
    
    public static void main(String[] args) {
        int menu;
        
        do {
            menu = Keyboard.menu("Cadastrar/Listar/Remover");
            switch (menu) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    remover();
                    break;
            }
        } while(menu < 4);
    }
}
