public class TreeClaster implements IScaling{

    private final int NODES = 6;

    private int step = 1;
    private int buf = 6;

    private int [][] matrix = {

            /*1*/{0, 1, 1, 0, 0, 0},
            /*2*/{1, 0, 1, 1, 1, 0},
            /*3*/{1, 1, 0, 0, 1, 1},
            /*4*/{0, 1, 0, 0, 1, 0},
            /*5*/{0, 1, 1, 1, 0, 1},
            /*6*/{0, 0, 1, 0, 1, 0}};

    int[][] getMatrix() {
        return matrix;
    }


    @Override
    public int[][] scale(int[][] array, int wave) {

        if (wave == 0)
            return matrix;

        int [][] matrix1 = new int[(int) (array.length + Math.pow(2, step) * NODES)][(int) (array.length + Math.pow(2, step) * NODES)];

        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i],0, matrix1[i],0, array.length);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1){
                for (int k = 0; k < Math.pow(2, step); k++) {
                        matrix1[i + (int)(6*(Math.pow(2,step) - 1)) + k * NODES][j + (int)(6*(Math.pow(2,step) - 1)) + k * NODES] = 1;
                    }
                }
            }
        }


        for (int k = 0; k < Math.pow(2, step); k++) {
            if (k % 2 == 0) {
                matrix1[(int) (6 * (Math.pow(2, step) - 1)) + k * NODES][(int) (6 * (Math.pow(2, step) - 1)) + k * NODES - buf] = 1;
                matrix1[(int) (6 * (Math.pow(2, step) - 1)) + k * NODES - buf][(int)  (6 * (Math.pow(2, step) - 1)) + k * NODES] = 1;
                matrix1[(int) (4 + 6 * (Math.pow(2, step) - 1)) + k * NODES][(int) (4 + 6 * (Math.pow(2, step) - 1)) + k * NODES - buf] = 1;
                matrix1[(int) (4 + 6 * (Math.pow(2, step) - 1)) + k * NODES - buf][(int)(4 + 6 * (Math.pow(2, step) - 1)) + k * NODES] = 1;

                matrix1[(int) (1 + 6 * (Math.pow(2, step) - 1)) + k * NODES][(int) (8 + 6 * (Math.pow(2, step) - 1)) + k * NODES] = 1;
                matrix1[(int) (8 + 6 * (Math.pow(2, step) - 1)) + k * NODES][(int) (1 + 6 * (Math.pow(2, step) - 1)) + k * NODES] = 1;
                matrix1[(int) (3 + 6 * (Math.pow(2, step) - 1)) + k * NODES][(int) (11 + 6 * (Math.pow(2, step) - 1)) + k * NODES] = 1;
                matrix1[(int) (11 + 6 * (Math.pow(2, step) - 1)) + k * NODES][(int) (3 + 6 * (Math.pow(2, step) - 1)) + k * NODES] = 1;
                buf += 6;
            }
            else {
                matrix1[(int) (6 * (Math.pow(2, step) - 1)) + k * NODES][(int) (6 * (Math.pow(2, step) - 1)) + k * NODES - buf] = 1;
                matrix1[(int) (6 * (Math.pow(2, step) - 1)) + k * NODES - buf][(int)  (6 * (Math.pow(2, step) - 1)) + k * NODES] = 1;
                matrix1[(int) (4 + 6 * (Math.pow(2, step) - 1)) + k * NODES][(int) (4 + 6 * (Math.pow(2, step) - 1)) + k * NODES - buf] = 1;
                matrix1[(int) (4 + 6 * (Math.pow(2, step) - 1)) + k * NODES - buf][(int) (4 + 6 * (Math.pow(2, step) - 1)) + k * NODES] = 1;
            }
        }

        if (++step <= wave)
            matrix1 = scale(matrix1, wave);
        step = 1;
        buf = 6;
return matrix1;
    }
}
