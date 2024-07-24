/******************************************************************************
 *
 * MAC0122 PRINCÍPIOS DE DESENVOLVIMENTO DE ALGORITMOS
 * Aluno: Henrique Maruiti
 * Numero USP: 12610243
 * Tarefa: E05
 * Data: 26/10/2023
 * 
 * Baseado em T06 - T06 Genes e o programa GeneFindImproved.java
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


public class FindGene {
    static int[] Gene_map;// Essa variável irá mapear quais códons são de entrada (1) ou de saída (2)

    public static boolean isStop(String codon) {
	return codon.equals("TAA") || codon.equals("TAG") || codon.equals("TGA");
    }

    public static void Gene_read(String dna){
        String start = "ATG";
        for(int i = 0; i < dna.length() - 2; i++){
            String codon = dna.substring(i, i + 3);
            if(codon.equals(start)) Gene_map[i] = 1;
            if(isStop(codon)) Gene_map[i] = 2;
        }
    }
    public static void main(String[] args)  {

        String genome = StdIn.readString();
        Gene_map = new int[genome.length() - 2];
        Gene_read(genome);
        for(int i = 0; i < genome.length() - 2; i++){
            if(Gene_map[i] == 1){
                for (int j = i + 3; j < genome.length() - 2; j = j + 3){
                    if(Gene_map[j] == 2){
                        System.out.println(genome.substring(i, j + 3));
                        break;
                    }
                }
            }
        }
    }
}
