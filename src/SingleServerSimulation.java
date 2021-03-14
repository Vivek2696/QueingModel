import java.util.LinkedList;

public class SingleServerSimulation {
    LinkedList<Customer> QueueData;
    LinkedList<Customer> Results;
    int serverIdleTime = 0;
    int timer = 0;

    public SingleServerSimulation(){
        this.QueueData = new LinkedList<>();
        this.Results = new LinkedList<>();
    }

    public void importData(){
        //Data from assignment
        this.QueueData.add(new Customer(12,40));
        this.QueueData.add(new Customer(31,32));
        this.QueueData.add(new Customer(63,55));
        this.QueueData.add(new Customer(95,48));
        this.QueueData.add(new Customer(99,18));
        this.QueueData.add(new Customer(154,50));
        this.QueueData.add(new Customer(198,47));
        this.QueueData.add(new Customer(221,18));
        this.QueueData.add(new Customer(304,28));
        this.QueueData.add(new Customer(346,54));
        this.QueueData.add(new Customer(411,40));
        this.QueueData.add(new Customer(455,72));
        this.QueueData.add(new Customer(537,12));
    }

    public void printInitialData(){
        for (Customer customer:
             this.QueueData) {
            System.out.println(customer.getArrival() + "\t" + customer.getService() + "\t" +
                    customer.getResponseTime() + "\t" + customer.getDeparture());
        }
    }

    public void startSimulation(){
        boolean isServerBusy = false;
        Customer current = null;
        LinkedList<Customer> waitingLine = new LinkedList<>();
        while(!this.QueueData.isEmpty() || !waitingLine.isEmpty() || isServerBusy){
            if(!this.QueueData.isEmpty()){
                if(this.QueueData.getFirst().getArrival() == timer){
                    if(!isServerBusy && !waitingLine.isEmpty()){
                        current = this.QueueData.removeFirst();
                        System.out.println("Serving new customer from queue!");
                        isServerBusy = true;
                    }
                    else{
                        waitingLine.add(this.QueueData.removeFirst());
                    }
                }
            }
            if(!isServerBusy && !waitingLine.isEmpty()){
                current = waitingLine.removeFirst();
                System.out.println("Serving new customer from wait line!");
                isServerBusy = true;
            }
            timer++;
            //increment the wait time for each customer in line
            for (Customer customer: waitingLine) {
                customer.setTimeServiceStarts(customer.getTimeServiceStarts() + 1);
            }
            //check server
            if(isServerBusy) {
                if(current.getService() + current.getArrival() + current.getTimeServiceStarts() == timer){
                    this.Results.add(current);
                    this.Results.getLast().setDeparture(timer);
                    this.Results.getLast().setResponseTime(this.Results.getLast().getTimeServiceStarts() +
                            this.Results.getLast().getService());
                    isServerBusy = false;
                    current = null;
                    System.out.println("Customer Served!");
                }
            }
            else{
                this.serverIdleTime++;
            }
        }
    }

    public void printResults(){
        for (Customer customer:
             this.Results) {
            System.out.println(customer.getArrival() + "\t" + customer.getService() + "\t" +
                    customer.getResponseTime() + "\t" + customer.getDeparture());
        }
    }

    public void printServerUtilization(){
        System.out.println("Server Idle Time = " + serverIdleTime);
        System.out.println("Global Timer = " +timer);
        System.out.println("Server Utilization: " + (double)(timer - serverIdleTime)/timer);
    }
}