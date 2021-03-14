import java.util.LinkedList;

public class TwoServerSimulation {
    LinkedList<Customer> QueueData;
    LinkedList<Customer> Results;
    int server1IdleTime = 0;
    int server2IdleTime = 0;
    int timer = 0;

    public TwoServerSimulation(){
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
        boolean isServer1Busy = false;
        boolean isServer2Busy = false;
        Customer current1 = null;
        Customer current2 = null;
        LinkedList<Customer> waitingLine = new LinkedList<>();
        while(!this.QueueData.isEmpty() || !waitingLine.isEmpty() || isServer1Busy || isServer2Busy){
            if(!this.QueueData.isEmpty()){
                if(this.QueueData.getFirst().getArrival() == timer){
                    if(!isServer1Busy && !waitingLine.isEmpty()){
                        current1 = this.QueueData.removeFirst();
                        System.out.println("Serving new customer from queue!");
                        isServer1Busy = true;
                    }
                    else if(isServer1Busy && !isServer2Busy){
                        current2 = this.QueueData.removeFirst();
                        System.out.println("Serving new customer from queue!");
                        isServer2Busy = true;
                    }
                    else{
                        waitingLine.add(this.QueueData.removeFirst());
                    }
                }
            }
            if(!isServer1Busy && !waitingLine.isEmpty()){
                current1 = waitingLine.removeFirst();
                System.out.println("Serving new customer from wait line!");
                isServer1Busy = true;
            }
            if(!isServer2Busy && isServer1Busy && !waitingLine.isEmpty()){
                current2 = waitingLine.removeFirst();
                System.out.println("Serving new customer from wait line!");
                isServer2Busy = true;
            }
            timer++;
            //increment the wait time for each customer in line
            for (Customer customer: waitingLine) {
                customer.setTimeServiceStarts(customer.getTimeServiceStarts() + 1);
            }
            //check server
            if(isServer1Busy) {
                if(current1.getService() + current1.getArrival() + current1.getTimeServiceStarts() == timer){
                    this.Results.add(current1);
                    this.Results.getLast().setDeparture(timer);
                    this.Results.getLast().setResponseTime(this.Results.getLast().getTimeServiceStarts() +
                            this.Results.getLast().getService());
                    isServer1Busy = false;
                    current1 = null;
                    System.out.println("Customer Served!");
                }
            }
            else{
                this.server1IdleTime++;
            }

            if(isServer2Busy){
                if(current2.getService() + current2.getArrival() + current2.getTimeServiceStarts() == timer){
                    this.Results.add(current2);
                    this.Results.getLast().setDeparture(timer);
                    this.Results.getLast().setResponseTime(this.Results.getLast().getTimeServiceStarts() +
                            this.Results.getLast().getService());
                    isServer2Busy = false;
                    current2 = null;
                    System.out.println("Customer Served!");
                }
            }
            else{
                this.server2IdleTime++;
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
        System.out.println("Server1 idle time: "+ server1IdleTime);
        System.out.println("Server2 idle time: "+ server2IdleTime);
        System.out.println("Global Timer = " +timer);
        System.out.println("Server 1 Utilization: " + (double)(timer - server1IdleTime)/timer);
        System.out.println("Server 2 Utilization: "+ (double)(timer - server2IdleTime)/timer);
    }
}
