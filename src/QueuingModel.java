public class QueuingModel {
    public static void main(String[] args){
        //Initiate single server simulation instance and import data
        SingleServerSimulation sss = new SingleServerSimulation();
        sss.importData();

        sss.printInitialData();

        System.out.println("\n\n Single Server Simulation Started!\n\n");

        //Then start the single server simulation
        sss.startSimulation();

        System.out.println("\n\n Single Server Simulation Ended!\n\n");

        //Print results
        sss.printResults();
        sss.printServerUtilization();

        //Two server simulation initialization
        TwoServerSimulation tss = new TwoServerSimulation();
        tss.importData();

        tss.printInitialData();

        System.out.println("\n\n Two Server Simulation Started!\n\n");

        //Then start the single server simulation
        tss.startSimulation();

        System.out.println("\n\n Two Server Simulation Ended!\n\n");

        //Print results
        tss.printResults();
        tss.printServerUtilization();

    }
}
