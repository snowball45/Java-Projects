
public class Recipe{
    private String name;
    private String url;
    private String description;
    private String author;
    private String ingredients;
    private String method;
    private int id;
    private static int quantity;

    public Recipe(){
        quantity += 1;
    }
    public void setName(String nam){
        this.name = nam;
    }
    public String getName(){
        return name;
    }

    public void setUrl(String ur){
        this.url = ur;
    }
    public String getUrl(){
        return url;
    }

    public void setDescription(String descr){
        this.description = descr;
    }
    public String getDescription(){
        return description;
    }

    public void setAuthor(String auth){
        this.author = auth;
    }
    public String getAuthor(){
        return author;
    }

    public void setIngredients(String ingr){
        this.ingredients = ingr;
    }
    public String getIngredients(){
        return ingredients;
    }

    public void setMethod(String metho){
        this.method = metho;
    }
    public String getMethod(){
        return method;
    }

    public void setID(Integer iD){
        this.id = iD;
    }
    public Integer getID(){
        return id;
    }

    public static Integer getQuantity(){
        return quantity;
    }
}