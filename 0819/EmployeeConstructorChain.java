abstract class EmployeeBase {
    protected int id;
    protected String name;
    
    public EmployeeBase(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("-> 執行 EmployeeBase Constructor");
    }
    public abstract double calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private double salary;
    
    public FullTimeEmployee(int id, String name, double salary) {
        super(id, name);
        this.salary = Math.max(0, salary);
        System.out.println("-> 執行 FullTimeEmployee Constructor");
    }
    public double calculatePay() { return salary; }
}

class PartTimeEmployee extends EmployeeBase {
    private double wage;
    private double hours;
    
    public PartTimeEmployee(int id, String name, double wage, double hours) {
        super(id, name);
        this.wage = Math.max(0, wage);
        this.hours = Math.max(0, hours);
        System.out.println("-> 執行 PartTimeEmployee Constructor");
    }
    public double calculatePay() { return wage * hours; }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        System.out.println("【建立全職員工】");
        new FullTimeEmployee(1, "Alice", 50000);
        
        System.out.println("\n【建立兼職員工】");
        new PartTimeEmployee(2, "Bob", 180, 40);
    }
}