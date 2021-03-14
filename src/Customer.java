public class Customer {

    int arrival;
    int service;
    int departure;
    int timeServiceStarts;
    int responseTime;

    public Customer(int arrival, int service){
        this.arrival = arrival;
        this.service = service;
        this.timeServiceStarts = 0;
        this.responseTime = 0;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public void setService(int service) {
        this.service = service;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public void setTimeServiceStarts(int timeServiceStarts) {
        this.timeServiceStarts = timeServiceStarts;
    }

    public int getArrival() {
        return arrival;
    }

    public int getService() {
        return service;
    }

    public int getDeparture() {
        return departure;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public int getTimeServiceStarts() {
        return timeServiceStarts;
    }
}
