import java.util.ArrayList;

public class Ingredient{
    private String ingredientName;
    private ArrayList<Integer> recipes = new ArrayList<>();

    public void addID(Integer id){
        recipes.add(id);
    }
    public ArrayList<Integer> getIDs(){
        return recipes;
    }

    public void setIngrName(String name){
        this.ingredientName = name;
    }
    public String getIngrName(){
        return ingredientName;
    }
}