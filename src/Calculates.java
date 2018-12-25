import java.util.Arrays;

public class Calculates {

    Utils utils = new Utils();

    private final int INF = 99;

    CircleCluster cluster1 = new CircleCluster();

    void floydWarshall(int[][] array) {
       //int [][] buf = new int[array.length][array.length];  - нерпавильно, копіюються ссилки
       //System.arraycopy(array, 0, buf, 0, array.length);
        //int [][] buf;
        //buf = Arrays.copyOf(array, array.length);

        int [][] buf = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, buf[i], 0, array.length);
        }

        for (int i = 0; i < buf.length; i++) {
            for (int j = 0; j < buf.length; j++) {
                if (buf[i][j] == 0)
                    buf[i][j] = INF;
            }
        }

        for (int k = 0; k < buf.length; k++) {
            for (int i = 0; i < buf.length; i++) {
                //if (buf[i][j] == 1) {
                    for (int j = 0; j < buf.length; j++) {
                        buf[i][j] = Math.min(buf[i][j], buf[i][k] + buf[k][j]);
                    }
            }
        }


        System.out.println("\n"); //Виводимо матрицю з вагами найкоротших шляхів
        utils.show(buf);


        int sum = 0;
        int D = 0;
        for (int[] array1 : buf) {
            for (int n : array1) {
                sum += n;
                if (n > D)
                    D = n;
            }
        }

      /*  int counter1 = 0, counter2 = 0, counter3 = 0;
        for (int[] array1 : buf) {
            for (int n : array1) {
                if (n == 1)
                    ++counter1;
                if (n == 2)
                    ++counter2;
                if (n == 3)
                    ++counter3;
            }
        }

        System.out.println(counter1 + " " + counter2 + " " + counter3);*/

        double Da = (double)sum/(double) (buf.length * (buf.length - 1)); // average D

        int C = 0;
        int S = 0;
        sum = 0;
        for ( int [] array1 : array) {
            if (sum > S)
                S = sum;
            sum = 0;
            for (int n : array1) {
                if (n == 1)
                    sum += n;
            }
        }

        double T = 2*Da/S;

        for ( int [] array2 : array) {
            for (int n : array2) {
                    C += n;
            }
        }

        System.out.println("\n");
        System.out.println("  Nodes| D | Da | S | T | C");
        System.out.println("   " + array.length + "  | " + D + " | " + Da + "| " + S + " |" + T + "| "  + C/2);
    }
}
