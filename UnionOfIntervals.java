/******************************************************************************
 *
 * MAC0122 PRINCÍPIOS DE DESENVOLVIMENTO DE ALGORITMOS
 * Aluno: Henrique Maruiti
 * Numero USP: 12610243
 * Tarefa: E06 Union of intervals
 * Data: 18/11/2023
 * 
 * Baseado nas aulas
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/
import java.util.Arrays;

public class UnionOfIntervals {
        public static void main (String[] args){
        boolean verbose = false;
        if(args.length > 0) verbose = true;
        int j = 0, k = 0;
        double length = 0, line = 0.1;
        double[] read = StdIn.readAllDoubles();
        Interval1D[] intervals = new Interval1D[read.length/2];
        Interval1D[] intervals_final = new Interval1D[read.length/2];

        for(int i = 0; i < read.length/2; i++){
            intervals[i] = new Interval1D(read[j], read[j+1]);
            j = j + 2;
        }
        Arrays.sort(intervals, Interval1D.MIN_ENDPOINT_ORDER);
        intervals_final[0] = intervals[0];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].intersects(intervals_final[k])){
                if(intervals_final[k].max() < intervals[i].max()){
                    intervals_final[k] = new Interval1D(intervals_final[k].min(), intervals[i].max());
                }
            }
            else{
                k++;
                intervals_final[k] = intervals[i];
            }
        }
        for(int i = 0; i < intervals.length; i++){
            if(intervals_final[i] == null) break;
            length += intervals_final[i].length();
        }

        StdOut.println("Total length: " + length + " [" + (k+1) + " components]");
        if(verbose == true){
            for (int i = 0; i < intervals.length; i++){
                if(intervals_final[i] == null) break;
                    StdOut.println(intervals_final[i]);
            }
            StdDraw.clear();
            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(StdDraw.BLACK);
            for(int i = 0; i < read.length; i = i+2){
                StdDraw.line(read[i], line, read[i+1], line);
                line += 0.01;
            }
            line+= 0.05;
            StdDraw.setPenRadius(0.001);
            for(int i = 0; i + 1 < read.length; i++){
                StdDraw.line(read[i], 0, read[i], 1);
                StdDraw.line(read[i+1], 0, read[i+1], 1);
            }
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.005);
            for(int i = 0; i < intervals.length; i++){
                if(intervals_final[i] == null) break;
                StdDraw.line(intervals_final[i].min(), line, intervals_final[i].max(), line);
                line += 0.01;
            }
                StdDraw.show();
        }
}   
}
