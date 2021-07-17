package product;

public class Product {
private int price;
private String name;

    public Product(int price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        Product p =(Product)obj;
        if(this.price == p.price)
            return true;
        return false;
    }
    
    

    
    
}
