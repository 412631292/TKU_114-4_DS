import java.util.ArrayList;

class Product {
    private String name;
    public Product(String name) { this.name = name; }
    @Override public String toString() { return "Product{" + name + "}"; }
}

class Repository<T> {
    private ArrayList<T> items = new ArrayList<>();

    public void add(T item) { items.add(item); }
    public T get(int index) { 
        if (index >= 0 && index < items.size()) return items.get(index);
        return null;
    }
    public void remove(int index) {
        if (index >= 0 && index < items.size()) items.remove(index);
    }
    public int size() { return items.size(); }
    public void printAll() { System.out.println(items); }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        System.out.println("=== 字串 Repository 測試 ===");
        Repository<String> strRepo = new Repository<>();
        strRepo.add("Java");
        strRepo.add("Python");
        strRepo.printAll();

        System.out.println("\n=== 產品 Repository 測試 ===");
        Repository<Product> prodRepo = new Repository<>();
        prodRepo.add(new Product("Laptop"));
        prodRepo.add(new Product("Mouse"));
        prodRepo.remove(0);
        prodRepo.printAll();
    }
}