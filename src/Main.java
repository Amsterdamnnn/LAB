public class Main {
    public static void main(String[] args) {

        Utils utils = new Utils();

        Calculates calculates = new Calculates();

        /*CircleCluster circleCluster = new CircleCluster();
        utils.show(circleCluster.scale(circleCluster.getMatrix(), IScaling.wave));
        calculates.floydWarshall(circleCluster.scale(circleCluster.getMatrix(), IScaling.wave));*/


        TreeClaster treeClaster = new TreeClaster();
        utils.show(treeClaster.scale(treeClaster.getMatrix(), IScaling.wave));
        calculates.floydWarshall(treeClaster.scale(treeClaster.getMatrix(), IScaling.wave));

        /*LatticeCluster latticeCluster = new LatticeCluster();
        latticeCluster.initializer();
        utils.show(latticeCluster.scale(latticeCluster.getMatrix(), IScaling.wave));
        calculates.floydWarshall((latticeCluster.scale(latticeCluster.getMatrix(), IScaling.wave)));*/

        }
    }

