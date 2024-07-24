/******************************************************************************
 *
 * MAC0122 PRINCÍPIOS DE DESENVOLVIMENTO DE ALGORITMOS
 * Aluno: Henrique Maruiti
 * Numero USP: 12610243
 * Tarefa: E02
 * Data: 24/09/2023
 * 
 * Baseado em T04 - Expressões bem formadas
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class E02 {
    static long[] memo = new long[35];
    public static long n_ebfR(int N){
        long soma = 0;
        if(N==0){
            return 1;
        }
        if(N%2 == 1){
            for(int i = 0; i <= N/2;i++){
                if(i == N/2){
                    soma = 2*soma + n_ebfR(i)*n_ebfR(i);
                }
                else{
                    soma = soma + (n_ebfR(i)* n_ebfR(N-1-i));
                }
            }
        }
        if(N%2 == 0){
            for(int i = 0; i < N/2;i++){
                soma = soma +(n_ebfR(i) * n_ebfR(N-1-i));
            }
            soma = 2*soma;
        }
        memo[N] = soma;
        return soma;
    }
    public static long n_ebfM(int N){
        memo[0] = 1;
        if(memo[N] == 0){
            long soma = 0;
            if(N%2 == 1){
                for(int i = 0; i <= N/2;i++){
                    if(i == N/2){
                        soma = 2*soma + n_ebfM(i)*n_ebfM(i);
                    }
                    else{
                        soma = soma + (n_ebfM(i)* n_ebfM(N-1-i));
                    }
                }
            }
            if(N%2 == 0){
                for(int i = 0; i < N/2;i++){
                    soma = soma +(n_ebfM(i) * n_ebfM(N-1-i));
                }
                soma = 2*soma;
            }
            memo[N] = soma;
        }
        return memo[N];
    }
}
