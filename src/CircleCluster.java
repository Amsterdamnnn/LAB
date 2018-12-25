public class CircleCluster implements IScaling {
    //Circle topology
    private final int NODES = 8;

    private int step = 1;

    private int [][] matrix = {

            /*1*/{0, 1, 1, 0, 0, 0, 1, 0},
            /*2*/{1, 0, 1, 0, 1, 0, 0, 0},
            /*3*/{1, 1, 0, 1, 0, 0, 0, 1},
            /*4*/{0, 0, 1, 0, 1, 0, 0, 0},
            /*5*/{0, 1, 0, 1, 0, 1, 0, 0},
            /*6*/{0, 0, 0, 0, 1, 0, 1, 0},
            /*7*/{1, 0, 0, 0, 0, 1, 0, 1},
            /*8*/{0, 0, 1, 0, 0, 0, 1, 0}
    };

    int[][] getMatrix() {
        return matrix;
    }

    @Override
    public int[][] scale(int [][] array, int wave) {
        if (wave == 0)
            return matrix;

        int [][] matrix1 = new int[(step + 1) * NODES][(step + 1) * NODES];

        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i],0, matrix1[i],0, array.length);
        }

        //System.arraycopy(array,0,matrix1,0,array.length);

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = array.length; j < matrix1.length; j++) { //прописуєм зв'язки з наступним кластером за одноіменними вершинами
                if (((j - NODES == i) )){
                    matrix1[i][j] = 1;
                    matrix1[j][i] = 1;
                }
            }}

        for (int i = 0; i < matrix1.length; i++) {  //прописуємо зв'язки перехресні міжкластерні зв'язки
            for (int j = 0; j < matrix1.length; j++) {
                if((j == step * NODES + 6) & (i == step * NODES) ||
                (i == step * NODES + 1) & (j == step * NODES + 4)){
                        matrix1[i][j] = 1;
                        matrix1[j][i] = 1;
                }
            }}

        //arraycopy

        for (int i = array.length; i < matrix1.length; i++) { //копіюємо першу матрицю 8*8 в правий нижній кут кінцевої матриці
            for (int j = array.length; j < matrix1.length; j++) {
                matrix1[i][j] = array[i - step * NODES][j - step * NODES];
            }
        }

       if (++step <= wave)
           matrix1 = scale(matrix1, wave);

        for (int i = 0; i < NODES; i++) {
            for (int j = matrix1.length - NODES; j < matrix1.length; j++) { //прописуєм зв'язки першого та останнього кластера
                if (((j - (step-1) * NODES == i) )){
                    matrix1[i][j] = 1;
                    matrix1[j][i] = 1;
                }
            }}
       step = 1;
       return matrix1;
    }
}

