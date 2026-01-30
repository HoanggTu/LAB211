package models;

/**
 *
 * @author HOANGANH
 */
public class FeastMenu {

    String code;
    String name;
    int price;
    String ingredients;

    public FeastMenu(String code, String name, int price, String ingredients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void display() {
        System.out.format("%-15s:%s\n", "Code", this.getCode());
        System.out.format("%-15s:%s\n", "Name", this.getName());
        System.out.format("%-15s:%s\n", "Price", this.getPrice() + "Vnd");
        System.out.println("Ingredients:");
        System.out.println(this.getIngredients());
    }

}
