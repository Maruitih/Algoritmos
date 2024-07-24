/******************************************************************************
 *
 * MAC0122 PRINCÍPIOS DE DESENVOLVIMENTO DE ALGORITMOS
 * Aluno: Henrique Maruiti
 * Numero USP: 12610243
 * Tarefa: E01
 * Data: 09/09/2023
 * 
 * Baseado em T03-Hanoi4
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class Hanoi4 {
    public static int Hanoi4_solve (int N ,int original_tower, int auxiliar_tower_1, int auxiliar_tower_2, int final_tower, int allow_to_print){
        int moves_1 = 0, moves_2 = 0, moves_3 = 0, moves_total = 0, Hanoi4 = 0;
        int num = 1, numdisc = 0, i = 2;
        if(N == 0){
            return 0;
        }
        if(N == 1){
            if(allow_to_print == 1){
                System.out.print(N + " " + final_tower + "  ");
            }
            return 1;
        }
        while(num <= N){
            if(num <= N){
                numdisc++;
            }
            num = num + i;
            i++;
        }
        Hanoi4 = N - numdisc;
        moves_1 = Hanoi4_solve(N - numdisc , original_tower, final_tower, auxiliar_tower_1, auxiliar_tower_2, allow_to_print);
        moves_2 = Hanoi3_solve(numdisc, original_tower, auxiliar_tower_1, final_tower,allow_to_print, Hanoi4);
        moves_3 = Hanoi4_solve(N - numdisc , auxiliar_tower_2, original_tower, auxiliar_tower_1, final_tower, allow_to_print);
        moves_total = moves_1 + moves_2 + moves_3;
        return moves_total;
    }

    public static int Hanoi3_solve(int N,int original_tower, int auxiliar_tower_1, int final_tower, int allow_to_print, int Hanoi4){
        int moves_1 = 0, moves_2 = 0, moves_total = 0;
        if(N == 0){
            return 0;
        }
        if(N == 1 && Hanoi4 == 0){
            if(allow_to_print == 1){
                System.out.print((N + Hanoi4) + " " + final_tower + "  ");
            }
            return 1;
        }
        moves_1 = Hanoi3_solve(N-1, original_tower, final_tower, auxiliar_tower_1, allow_to_print, Hanoi4);
        if(allow_to_print == 1){
            System.out.print((N + Hanoi4) + " " + final_tower + "  ");
        }
        moves_2 = Hanoi3_solve(N-1, auxiliar_tower_1, original_tower, final_tower, allow_to_print, Hanoi4);
        moves_total = moves_1 + moves_2 + 1;
        return moves_total;
    }
    public static void main (String[] args){
        int N = Integer.parseInt(args[0]);
        boolean mode = args.length > 1;
        int original_tower = 0, auxiliar_tower_1 = 2, auxiliar_tower_2 = 3, final_tower = 1;
        int allow_to_print = 0;
        int moves = Hanoi4_solve(N, original_tower, auxiliar_tower_1, auxiliar_tower_2, final_tower, allow_to_print);
        if(mode == true){
            System.out.println(moves + " moves");
        }
        allow_to_print = 1;
        moves = Hanoi4_solve(N, original_tower, auxiliar_tower_1, auxiliar_tower_2, final_tower, allow_to_print);
    }
}
