abstract class Transport {
    protected String routeName;
    public Transport(String routeName) { this.routeName = routeName; }
    public abstract int calculateFare(int distance);
}

class Bus extends Transport {
    public Bus(String routeName) { super(routeName); }
    @Override 
    public int calculateFare(int distance) { return 15; } // 公車採一段票計價
}

class Taxi extends Transport {
    public Taxi(String routeName) { super(routeName); }
    @Override 
    public int calculateFare(int distance) { return 70 + (distance * 25); } // 計程車跳表
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = {
            new Bus("307"), new Taxi("台灣大車隊"), new Bus("299"), new Taxi("大都會")
        };
        for(Transport t : transports) {
            System.out.printf("路線: %s | 10公里票價: %d%n", t.routeName, t.calculateFare(10));
        }
    }
}