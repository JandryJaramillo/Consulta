package Threads;

public class CalcularValor extends Thread {

    private Matrix m1;
    private Matrix m2;
    private int filaIndex;
    private int colIndex;
    private int element;

    public CalcularValor(Matrix m1, Matrix m2, int filaIndex, int colIndex){

        this.m1 = m1;
        this.m2 = m2;
        this.filaIndex = filaIndex;
        this.colIndex = colIndex;

    }

    @Override
    public void run() {
        element = calcValor(m1.getRow(filaIndex), m2.getColumn(colIndex));
    }

    private int calcValor(int[] row, int[] col){
        int aux = 0;
        for(var i = 0; i < row.length; i++){
            aux += row[i] * col[i];
        }
        return aux;
    }

    public int getElement() {return element;}
    public int getFilaIndex() {return filaIndex;}
    public int getColIndex() {return colIndex;}
}