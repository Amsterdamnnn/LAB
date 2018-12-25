import java.util.Arrays;

public class LatticeCluster implements IScaling{

    Utils utils = new Utils();

    private final int NODES = 8;

    private int step = 1;

    private int [][] matrix = {

            /*1*/{0, 1, 0, 0, 0, 0, 0, 1},
            /*2*/{1, 0, 1, 1, 0, 0, 0, 1},
            /*3*/{0, 1, 0, 1, 0, 0, 0, 0},
            /*4*/{0, 1, 1, 0, 1, 1, 0, 0},
            /*5*/{0, 0, 0, 1, 0, 1, 0, 0},
            /*6*/{0, 0, 0, 1, 1, 0, 1, 1},
            /*7*/{0, 0, 0, 0, 0, 1, 0, 1},
            /*8*/{1, 1, 0, 0, 0, 1, 1, 0}
    };

    private int [][] logic = {
            {1,  2,  5,  10, 17},
            {4,  3,  6,  11, 18},
            {9,  8,  7,  12, 19},
            {16, 15, 14, 13, 20},
            {25, 24, 23, 22, 21},
    };

    private int[][] clustersContiguity = new int[25][25];

    void initializer(){
        for (int i = 0; i < logic.length; i++) {
            for (int j = 1; j < logic.length; j++) {
                clustersContiguity[logic[i][j-1]-1][logic[i][j]-1] = 1;
            }
        }

        for (int i = 1; i < logic.length; i++) {
            for (int j = 0; j < logic.length; j++) {
                clustersContiguity[logic[i-1][j]-1][logic[i][j]-1] = 2;
            }
        }

        utils.show(clustersContiguity);
        System.out.println("\n");
    }



    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public int[][] scale(int[][] array, int wave) {

        if (wave == 0)
            return matrix;

        int[][] matrix1 = new int[array.length + (3 + (step - 1) * 2) * NODES][array.length + (3 + (step - 1) * 2) * NODES];

        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i],0, matrix1[i],0, array.length);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1){
                    for (int k = 1; k < matrix1.length/NODES; k++) {
                        matrix1[i + k * NODES][j + k * NODES] = 1;
                    }
                }
            }
        }

            for (int i = 0; i < matrix1.length/NODES; i++) {
                for (int j = 0; j < matrix1.length/NODES; j++) {
                        if (clustersContiguity[i][j] == 1) {
                            matrix1[1 + i * NODES][1 + j * NODES] = 1;
                            matrix1[1 + j * NODES][1 + i * NODES] = 1;
                            matrix1[5 + i * NODES][5 + j * NODES] = 1;
                            matrix1[5 + j * NODES][5 + i * NODES] = 1;
                            matrix1[i * NODES][2 + j * NODES] = 1;
                            matrix1[2 + j * NODES][i * NODES] = 1;
                            matrix1[6 + i * NODES][4 + j * NODES] = 1;
                            matrix1[4 + j * NODES][6 + i * NODES] = 1;
                    }
                        if (clustersContiguity[i][j] == 2) {
                            matrix1[1 + i * NODES][1 + j * NODES] = 1;
                            matrix1[1 + j * NODES][1 + i * NODES] = 1;
                            matrix1[5 + i * NODES][5 + j * NODES] = 1;
                            matrix1[5 + j * NODES][5 + i * NODES] = 1;
                            matrix1[6 + i * NODES][j * NODES] = 1;
                            matrix1[j * NODES][6 + i * NODES] = 1;
                            matrix1[4 + i * NODES][2 + j * NODES] = 1;
                            matrix1[2 + j * NODES][4 + i * NODES] = 1;
                    }
                }
            }

        if (++step <= wave)
            matrix1 = scale(matrix1, wave);
        step = 1;
        return matrix1;
    }
}
