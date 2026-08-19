// 檔名：PayrollPolymorphismSystem.java
abstract class Employee {
    protected String name;
    public Employee(String name) { this.name = name; }
    public abstract int calculatePay();
    public String getName() { return name; }
}

class MonthlyEmployee extends Employee {
    public MonthlyEmployee(String name) { super(name); }
    @Override public int calculatePay() { return 50000; }
}

class HourlyEmployee extends Employee {
    private int hours;
    public HourlyEmployee(String name, int hours) { super(name); this.hours = hours; }
    @Override public int calculatePay() { return hours * 180; }
}

class SalesEmployee extends Employee {
    private int sales;
    public SalesEmployee(String name, int sales) { super(name); this.sales = sales; }
    @Override public int calculatePay() { return 30000 + (sales / 10); }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new MonthlyEmployee("Alice"),
            new HourlyEmployee("Bob", 120),
            new SalesEmployee("Charlie", 500000)
        };

        int totalPay = 0;
        Employee highestPaid = employees[0];

        for (Employee e : employees) {
            int pay = e.calculatePay();
            totalPay += pay;
            if (pay > highestPaid.calculatePay()) highestPaid = e;
            System.out.printf("員工: %-8s | 薪資: %d%n", e.getName(), pay);
        }
        System.out.println("總薪資支出: " + totalPay + " | 最高薪資員工: " + highestPaid.getName());
    }
}