package Threads;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int [][] matrix1Values = new int[][]{
                {8,2},
                {10,4},
                {12,6},
        };
        Matrix m1 = new Matrix(matrix1Values);

        int [][] matrix2Values = {
                {1, 7, 3},
                {9, 11, 5}
        };
        Matrix m2 = new Matrix(matrix2Values);

        Matrix result = m1.multThreads(m2);

        System.out.println(result);
    }
}