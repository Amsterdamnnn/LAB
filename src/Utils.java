public class Utils {

    void show(int [][] array){
        for (int i = 0; i < array.length; i++) {
            if (i < 10)
                System.out.print("  " + (i + 1));
            else
                System.out.print(" " + (i + 1));
        }
        System.out.println();

        for (int[] anArray : array) {
            System.out.print("  ^");
        }
        System.out.println();

        for (int i = 0; i <array.length ; i++) {
            for (int j = 0; j < array.length ; j++) {
                if (array[i][j] < 10)
                    System.out.print("  " +  array[i][j]);
                else System.out.print(" " +  array[i][j]);
            }
            System.out.println("   -> " + (i+1));
        }
    }
}
