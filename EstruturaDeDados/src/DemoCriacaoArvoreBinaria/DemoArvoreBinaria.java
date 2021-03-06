package DemoCriacaoArvoreBinaria;

import Biblioteca.FilaEnc;

public class DemoArvoreBinaria {

    static NoArvoreBin<Integer> raiz;

    static NoArvoreBin<Integer> insert(Integer obj, NoArvoreBin<Integer> pai,
            int direcao) {

        NoArvoreBin<Integer> novoNo = new NoArvoreBin<>(obj, pai);
        if (pai == null) {
            raiz = novoNo;
            return novoNo;
        }

        if (pai.getFilho(direcao) != null) {
            return null;
        }

        pai.setFilho(direcao, novoNo);
        return novoNo;
    }

    static boolean delete(NoArvoreBin<Integer> no) {
        NoArvoreBin<Integer> filho;
        if ((no.getDir() != null) && (no.getEsq() != null)) {
            NoArvoreBin<Integer> pTemp = null;
            pTemp = no.getEsq();
            while (pTemp.getDir() != null) {
                pTemp = pTemp.getDir();
            }
            no.setObj(pTemp.getObj());
            no = pTemp;
        }
        if (no == raiz) {
            if (raiz.getEsq() != null) {
                raiz = raiz.getEsq();
            } else {
                raiz = raiz.getDir();
            }
            if (raiz != null) {
                raiz.setPai(null);
            }
            return true;
        } else {
            int direcao = 1;
            NoArvoreBin<Integer> pai = no.getPai();
            if (no == pai.getEsq()) {
                direcao = -1;
            }
            filho = pai.getFilho(direcao);
            if (filho == null) {
                return true;
            }
            if (filho.getEsq() != null) {
                pai.setFilho(direcao, filho.getEsq());
                filho.getEsq().setPai(pai);
            } else {
                pai.setFilho(direcao, filho.getDir());
                if (filho.getDir() != null) {
                    filho.getDir().setPai(pai);
                }
            }
            return true;
        }
    }

    static class DescNo<E> {

        int nivel;
        int ident;
        NoArvoreBin<E> no;
    }

    static void desenhe(int larguraTela) {
        if (raiz == null) {
            return;
        }
        StringBuffer brancos1 = new StringBuffer();
        String brancos = "                                                        ";
        brancos1.append(brancos);
        brancos1.append(brancos);
        brancos1.append(brancos);
        brancos1.append(brancos);
        int largTela = larguraTela;
        int ident = largTela / 2;
        int nivelAnt = 0;
        int nivel = 0;
        int offset;
        NoArvoreBin<Integer> pTemp;
        StringBuffer linha1 = new StringBuffer(200);

        DescNo<Integer> descNo;
        FilaEnc<DescNo<Integer>> fila = new FilaEnc<>();

        descNo = new DescNo<>();

        descNo.nivel = 0;
        descNo.ident = ident;
        descNo.no = raiz;
        fila.insira(descNo);
        String pai;
        while (!fila.isEmpty()) {
            descNo = fila.remova();
            ident = descNo.ident;
            pTemp = descNo.no;
            nivel = descNo.nivel;
            if (pTemp.getPai() == null) {
                pai = "  ";
            } else {
                pai = pTemp.getPai().getObj().toString();
            }
            if (nivel == nivelAnt) {
                linha1.append(brancos1.substring(0, ident - linha1.length())
                        + pTemp.getObj().toString());
            } else {
                System.out.println(linha1 + "\n\n");
                linha1.setLength(0);
                linha1.append(brancos1.substring(0, ident)
                        + pTemp.getObj().toString());
                nivelAnt = nivel;
            }
            nivel = nivel + 1;
            offset = (int) (largTela / Math.round(Math.pow(2, nivel + 1)));
            if (pTemp.getEsq() != null) {
                descNo = new DescNo<Integer>();
                descNo.ident = ident - offset;
                descNo.nivel = nivel;
                descNo.no = pTemp.getEsq();
                fila.insira(descNo);
            }
            if (pTemp.getDir() != null) {
                descNo = new DescNo<Integer>();
                descNo.ident = ident + offset;
                descNo.nivel = nivel;
                descNo.no = pTemp.getDir();
                fila.insira(descNo);
            }
        }
        System.out.println(linha1);
    }

    static void preOrdem(NoArvoreBin no) {
        if (no != null) {
            System.out.println(no.getObj());
            preOrdem(no.getEsq());
            preOrdem(no.getDir());
        }
    }

    static void simetrica(NoArvoreBin no) {
        if (no != null) {
            simetrica(no.getEsq());
            System.out.println(no.getObj());
            simetrica(no.getDir());
        }
    }

    static void posOrdem(NoArvoreBin no) {
        if (no != null) {
            posOrdem(no.getEsq());
            posOrdem(no.getDir());
            System.out.println(no.getObj());
        }
    }

    static void emNiveis(NoArvoreBin raiz) {
        FilaEnc<NoArvoreBin> fila = new FilaEnc<>();

        if (raiz == null) {
            return;
        }

        fila.insira(raiz);
        while (!fila.isEmpty()) {
            NoArvoreBin no = fila.remova();

            System.out.println(no.getObj());

            if (no.getEsq() != null) {
                fila.insira(no.getEsq());
            }

            if (no.getDir() != null) {
                fila.insira(no.getDir());
            }
        }
    }

    public static void main(String[] args) {
        NoArvoreBin<Integer> no10 = insert(10, null, 1);

        NoArvoreBin<Integer> no20 = insert(20, raiz, -1);
        insert(40, no20, -1);

        NoArvoreBin<Integer> no50 = insert(50, no20, 1);
        insert(70, no50, 1);

        NoArvoreBin<Integer> no30 = insert(30, raiz, 1);
        insert(60, no30, 1);

        desenhe(80);
        
        System.out.println("Pré Ordem");
        preOrdem(no10);
        System.out.println("\n");

        System.out.println("Simétrica");
        simetrica(no10);
        System.out.println("\n");

        System.out.println("Pós Ordem");
        posOrdem(no10);
        System.out.println("\n");

        System.out.println("Em niveis");
        emNiveis(no10);
    }
}
