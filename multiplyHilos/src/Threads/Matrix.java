package Threads;
import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private int[][] values;

    public Matrix(int[][] values) {
        this.values = values;
    }
    public Matrix multThreads(Matrix mat2) throws InterruptedException {
        List<CalcularValor> threads = new ArrayList<>();

        if (values.length == mat2.values[0].length){
            int[][] output = new int[values.length][mat2.values[0].length];

            for (var i = 0; i < output.length; i++){
                for (var j = 0; j < output[0].length; j++) {
                    CalcularValor thread = new CalcularValor(this,mat2,i,j);
                    thread.start();
                    threads.add(thread);
                }
            }
            setWaitThreads(threads);
            for (var t : threads) {
                output[t.getFilaIndex()][t.getColIndex()] = t.getElement();
            }
            return new Matrix(output);
        }else{
            throw new IllegalArgumentException("No se puede multiplicar");
        }
    }
    private void setWaitThreads(List<CalcularValor> threads) throws InterruptedException{
        for (var t: threads){
            t.join();
        }
    }

    public int[] getRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < values.length) {
            return values[rowIndex];
        } else {
            throw new IllegalArgumentException("Indice no válido");
        }
    }
    // metodo saca la columna
    public int[] getColumn(int colIndex) {
        int[] output = new int[values.length];
        if (colIndex < values[0].length) {
            for (var i = 0; i < values.length; i++) {
                output[i] = values[i][colIndex];
            }
        }
        return output;
    }
    // format matriz
    public String toString() {
        String output = "";
        for (var fila : values) {
            output += "{";
            for (var value : fila) {
                output += value + "\t";
            }
            output +="}\n";
        }
        return "{\n"+ output +"}";
    }
}