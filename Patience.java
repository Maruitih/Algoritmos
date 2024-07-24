/******************************************************************************
 *
 * MAC0122 PRINCÍPIOS DE DESENVOLVIMENTO DE ALGORITMOS
 * Aluno: Henrique Maruiti
 * Numero USP: 12610243
 * Tarefa: E07 Paciência e LIS eficientes
 * Data: 10/12/2023
 * 
 * Baseado em T09
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import java.util.Queue;
import java.util.Stack;

public class Patience {

    private Stack<Card>[] piles; // array of stacks with the piles of cards
    private Queue<Integer>[] pilesQ; // array of queues with the integers on piles
    private int[] lis; // array with the elements in LIS

    PatiencePlain(int[] s) {
        piles = decomposition(s);
        pilesQ = pileQueues();
        lis = lisArray();
    }

    private class Card {
        int val;
        Card next;

        Card(int v, Card n) {
            val = v;
            next = n;
        }

        public String toString() {
            return String.valueOf(val);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Stack<Card>[] decomposition(int[] s) {
        int N = s.length;
        int p = 1; // number of non-empty piles
        Stack<Card>[] dec = (Stack<Card>[]) new Stack[N];
        dec[0] = new Stack<>();
        Card c0 = new Card(s[0], null);
        dec[0].push(c0);

        for (int i = 1; i < N; i++) {
            int j = pile(s[i], dec, p);
            if (j == p)
                dec[p++] = new Stack<Card>();
            Card obstruction = j > 0 ? dec[j - 1].peek() : null;
            Card n = new Card(s[i], obstruction);
            dec[j].push(n);
        }

        Stack<Card>[] decc = (Stack<Card>[]) new Stack[p];
        for (int i = 0; i < p; i++)
            decc[i] = dec[i];
        return decc;
    }

    private static int pile(int x, Stack<Card>[] dec, int p) {
        int low = 0;
        int high = p;
        while (low < high) {
            int mid = low + (high - low) / 2;
    
            if (dec[mid].peek().val < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Queue<Integer>[] pileQueues() {
        int N = piles.length;
        Queue<Integer>[] q = (Queue<Integer>[]) new Queue[N];
        for (int i = 0; i < N; i++)
            q[i] = new Queue<>();
        for (int i = 0; i < N; i++) {
            // t: elementos of q[i] in reverse order
            Stack<Integer> t = new Stack<>();
            for (Card c : piles[i])
                t.push(c.val);
            for (Integer x : t)
                q[i].enqueue(x);
        }
        return q;
    }

    private int[] lisArray() {
        int p = piles.length;
        int[] lis = new int[p];
        Card t = piles[p - 1].peek();
        int i = p;
        while (t != null) {
            lis[--i] = t.val;
            t = t.next;
        }
        return lis;
    }

    public Queue<Integer>[] piles() {
        return pilesQ;
    }

    public int[] lis() {
        return lis;
    }

    public static void main(String[] args)
    {
        int[] s = {6, 3, 5, 10, 11, 2, 9, 14, 13, 7, 4, 8, 12};
        PatiencePlain pp = new PatiencePlain(s);

        Queue<Integer>[] piles = pp.piles();
        int[] lis = pp.lis();

        if (piles.length != lis.length) {
            StdOut.println("Panic: something is wrong!");
            StdOut.println("No of piles:        " + piles.length);
            StdOut.println("No elements in LIS: " + lis.length);
            System.exit(-1);
        }

        StdOut.println("Decomposition in DSs:");
        for (int i = 0; i < piles.length; i++) {
            StdOut.printf("%2d: ", i);
            StdOut.println(piles[i]);
        }

        StdOut.println("A LIS " + "(" + lis.length + " elements):");
        for (Integer x : lis)
            StdOut.print(x + " " );
        StdOut.println();
    }
}
