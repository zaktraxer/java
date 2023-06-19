package Home1;

public interface VendingMachine {

    void addProduct(Product product);

    Product getProduct(String name);

    Product getProduct(String name, Integer volume, Integer temp);

}