/******************************************************************************
 *
 * MAC0122 PRINCÍPIOS DE DESENVOLVIMENTO DE ALGORITMOS
 * Aluno: Henrique Maruiti
 * Numero USP: 12610243
 * Tarefa: E03 - Variante do Creative Ex. 2.3.17 (Permutations)
 * Data: 07/10/2023
 * 
 * Baseado em Biblioteca Hashset e Set (https://www.javatpoint.com/java-hashset)
 *            
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


//Bibliotecas para armazenar as permutações e verificar a não repetição
import java.util.HashSet;
import java.util.Set;

public class PermutationsDeluxe {
    public static int generatePermutations(String s, boolean print) {
        Set<String> permutations = new HashSet<>();
        int n = s.length();
        char[] chars = s.toCharArray(); // Converte o string em um Array de caracteres
        generatePermutationsRecursive(chars, permutations, 0, n - 1); // Gera as permutações por meio de recursão
        if(print == false){
            for (String permutation : permutations) {
                System.out.println(permutation);
            }
        }
        return permutations.size(); // retorno do número de permutações
    }

    private static void generatePermutationsRecursive(char[] chars, Set<String> permutations, int start, int end) { // Gera as permutações por meio de recursão
        if (start == end) {
            permutations.add(new String(chars)); //Adiciona uma permutação para o arranjo
        } else {
            for (int i = start; i <= end; i++) {
                swap(chars, start, i); // Troca de caracteres para gerar a permutação
                generatePermutationsRecursive(chars, permutations, start + 1, end);
                swap(chars, start, i); // Reversão para o formato inicial
            }
        }
    }

    private static void swap(char[] chars, int i, int j) { // Troca de caracteres para gerar a permutação
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String input = StdIn.readString();
        boolean print = args.length > 0;
        int permutationsCount = generatePermutations(input, print);
        System.out.print(permutationsCount + " permutation");
        if(permutationsCount > 1){
            System.out.print ("s");
        }
    }
}