/******************************************************************************
 *
 * MAC0122 PRINCÍPIOS DE DESENVOLVIMENTO DE ALGORITMOS
 * Aluno: Henrique Maruiti
 * Numero USP: 12610243
 * Tarefa: E04
 * Data: 15/10/2023
 * 
 * Baseado em BeckTrack
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class Divide {
    static int min_unfair; // Melhor opção possível
    static int[] fair_division; //Vetor que organizará a distribuição de val

    public static void becktrack(int[] val, int[] temp, int start){
        if(start == temp.length){
            int Alice = 0, Beto = 0, Carla = 0, sum = 0;
            for (int i = 0; i < temp.length; i++){
                if(temp[i] == 0){
                    Alice += val[i];
                }
                if(temp[i] == 1){
                    Beto += val[i];
                }
                if(temp[i] == 2){
                    Carla += val[i];
                }
                sum += val[i];
            }
            if(Alice < sum/3 || Beto < sum/3 || Carla < sum/3){
                return;
            }
            int min = Alice;
            if(Beto < min){
                min = Beto;
            }
            if(Carla < min){
                min = Carla;
            }
            if(min > min_unfair){
                min_unfair = min;
                for(int i = 0; i < temp.length; i++){
                    fair_division[i] = temp[i];
                }
            }
            return;
        }
        temp[start] = 0;
        becktrack(val, temp, start + 1);
        temp[start] = 1;
        becktrack(val, temp, start + 1);
        temp[start] = 2;
        becktrack(val, temp, start + 1);        
    }

    public static int solve(int[] val, int[] temp ,int persons){
        //Encontrar a melhor opção possível
        becktrack(val, temp, 0); 
        return min_unfair;
    }
    public static void main(String[] args){
        // O vetor temp é um vetor que armazenará temporariamente as combinações de val
        //int val[] = {34 ,27, 67, 69, 22, 60, 21, 80, 58, 40, 24, 18}, temp[] = new int[val.length];
        //int val[] = {34 ,27 ,167 ,169 ,122, 160, 121, 80 ,58 ,40 ,24 ,118 }, temp[] = new int[val.length];
        int val[] = StdIn.readAllInts(), temp[] = new int[val.length];
        fair_division = new int[val.length];
        int persons = 3, sum = 0;
        boolean print = args.length> 0;
        int min_unfair = solve(val, temp, persons);
        for(int i = 0; i < val.length; i++){
            sum += val[i];
        }
        boolean solution = (min_unfair >= sum/3);
        if(solution == true){
            System.out.println("There is a solution");
            if(print == true){
                System.out.println("Optimal value: "+ min_unfair + " (sum:" + sum + " / want: " + (sum/3) + " / mod 3 = " + (sum%3) + ")");
                for (int i = 0; i < persons ; i++){
                    System.out.print(i + ": ");
                    sum = 0;
                    for (int j = 0; j < fair_division.length; j ++){
                        if(fair_division[j] == i){
                            System.out.print(val[j] + " ");
                            sum += val[j]; 
                        }
                    }
                    System.out.println("(sum: " + sum + ")");
                }
        }
        } 
        else{
            System.out.println("There is no solution");
            
            if(print == true){
                System.out.println("Optimal value: 0 (sum:" + sum + " / want: " + (sum/3) + " / mod 3 = " + (sum%3) + ")");
                for (int i = 0; i < persons ; i++){
                    if(i == 0){
                        System.out.print(i + ": ");
                        for(int j = 0; j < val.length; j++){
                            System.out.print(val[j] + " ");
                        }
                        System.out.println("(sum: " + sum + ")");
                    }
                    else{
                        System.out.println(i + ": " + "(sum: 0)");
                    }
                }
            }
        }
    }
}
