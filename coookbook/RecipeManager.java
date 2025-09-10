/*
Isaac Cadena
CSCE 314
Spring 2025
Homework 5
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RecipeManager{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        String filename = "recipes.json";
        ArrayList<Recipe> recipes = new ArrayList<>();
        Set<String> ingredientSet = new HashSet<>();
        Set<Ingredient> ingredientObjSet = new HashSet<>();
        Set<String> authorSet = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line.trim()).append(" ");
            }
            
            String json = jsonContent.toString().trim();
            json = json.substring(json.indexOf("[") + 1, json.lastIndexOf("]")).trim();
            String[] records = json.split("},");
            
            int id = 1;
            for (String record : records) {
                record = record.replace("{", "").replace("}", "").trim();
                String[] noName = record.split("\"Name\": ");
                String[] noUrl = noName[1].split(", \"url\": ");
                String[] noDescr = noUrl[1].split(", \"Description\": ");
                String[] noAuthor = noDescr[1].split(", \"Author\": ");
                String[] noIngredients = noAuthor[1].split(", \"Ingredients\": ");
                String[] noMethod = noIngredients[1].split(", \"Method\": ");
                String name = noUrl[0].trim().replace("\"", ""), 
                        url = noDescr[0].trim().replace("\"", ""), 
                        description = noAuthor[0].trim().replace("\"", ""), 
                        author = noIngredients[0].trim().replace("\"", ""), 
                        ingredients = noMethod[0].trim().replace("\"", ""), 
                        method = noMethod[1].trim().replace("\"", "");
                        String[] seperateIngr = noMethod[0].split("\", \"");
                        for (String ingr : seperateIngr){
                            ingr = ingr.trim().replace("\"", "");
                            ingr = ingr.trim().replace("[","");
                            ingr = ingr.trim().replace("]","");
                            Ingredient in = new Ingredient();
                            in.setIngrName(ingr);
                            int check = 0;
                            for (String elem : ingredientSet){
                                if (elem.equals(in.getIngrName())){check = 1;}
                            }
                            if (check == 0){in.addID(id); ingredientObjSet.add(in);}
                            else {for (Ingredient elem : ingredientObjSet){if (elem.getIngrName().equals(ingr)){elem.addID(id);}}}
                            ingredientSet.add(ingr);
                        }
                authorSet.add(author);

                Recipe recipe = new Recipe();
                recipe.setName(name); recipe.setUrl(url); recipe.setDescription(description); recipe.setAuthor(author);
                recipe.setIngredients(ingredients); recipe.setMethod(method); recipe.setID(id); ++id;
                recipes.add(recipe);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean end = false;
        System.out.println("========== RECIPE MANAGER ==========");
        while (end == false){
            System.out.println("\nChoose an option to search by and obtain the recipe id you want");
            System.out.println("Then you can search by recipe id to get the full recipe!\n");

            System.out.println("1. Search by Ingredient");
            System.out.println("2. Search by Author");
            System.out.println("3. Search by Name");
            System.out.println("4. Search by Method");
            System.out.println("5. Search by ID");
            System.out.println("6. Summary (quantity, authors, ingredients)");
            System.out.println("7. Exit");
            System.out.print("Please type an option: ");
            String answer = scan.nextLine();
            switch(answer){
                case "1":
                    System.out.print("Enter ingredients you would like to search by (use commas): ");
                    String ingrSearch = scan.nextLine();
                    String[] ingredientAnswer = ingrSearch.split(",");
                    ArrayList<Recipe> recipeSearch = new ArrayList<>(recipes);
                    ArrayList<Recipe> recipesToRemove = new ArrayList<>();
                    for (String elem : ingredientAnswer){
                        for (Recipe each : recipeSearch){
                            if (each.getIngredients().contains(elem) == false){
                                recipesToRemove.add(each);
                            }
                        }
                    }
                    recipeSearch.removeAll(recipesToRemove);
                    if (recipeSearch.size() == 1617){System.out.println("We got nothing. Please try again."); break;}
                    for (Recipe elem : recipeSearch){
                        System.out.println("- Name: " + elem.getName() + ", Author: " + elem.getAuthor() + ", ID: " + elem.getID());
                    }
                    break;
                case "2":
                    System.out.print("Enter part of, or the whole, Author name you would like to search by: ");
                    String authorSearch = scan.nextLine();
                    ArrayList<Recipe> recipeSearch2 = new ArrayList<>(recipes);
                    ArrayList<Recipe> recipesToRemove2 = new ArrayList<>();
                    for (Recipe each : recipeSearch2){
                        if (each.getAuthor().contains(authorSearch) == false){
                            recipesToRemove2.add(each);
                        }
                    }
                    recipeSearch2.removeAll(recipesToRemove2);
                    if (recipeSearch2.size() == 1617){System.out.println("We got nothing. Please try again."); break;}
                    for (Recipe elem : recipeSearch2){
                        System.out.println("- Name: " + elem.getName() + ", Author: " + elem.getAuthor() + ", ID: " + elem.getID());
                    }
                    break;
                case "3":
                    System.out.print("Enter part of, or the whole, name of the recipe you would like to search by: ");
                    String nameSearch = scan.nextLine();
                    ArrayList<Recipe> recipeSearch3 = new ArrayList<>(recipes);
                    ArrayList<Recipe> recipesToRemove3 = new ArrayList<>();
                    for (Recipe each : recipeSearch3){
                        if (each.getName().contains(nameSearch) == false){
                            recipesToRemove3.add(each);
                        }
                    }
                    recipeSearch3.removeAll(recipesToRemove3);
                    if (recipeSearch3.size() == 1617){System.out.println("We got nothing. Please try again."); break;}
                    for (Recipe elem : recipeSearch3){
                        System.out.println("- Name: " + elem.getName() + ", Author: " + elem.getAuthor() + ", ID: " + elem.getID());
                    }
                    break;
                case "4":
                    System.out.print("Enter keywords of the method you would like to search by: ");
                    String methodSearch = scan.nextLine();
                    ArrayList<Recipe> recipeSearch4 = new ArrayList<>(recipes);
                    ArrayList<Recipe> recipesToRemove4 = new ArrayList<>();
                    for (Recipe each : recipeSearch4){
                        if (each.getMethod().contains(methodSearch) == false){
                            recipesToRemove4.add(each);
                        }
                    }
                    recipeSearch4.removeAll(recipesToRemove4);
                    if (recipeSearch4.size() == 1617){System.out.println("We got nothing. Please try again."); break;}
                    for (Recipe elem : recipeSearch4){
                        System.out.println("- Name: " + elem.getName() + ", Author: " + elem.getAuthor() + ", ID: " + elem.getID());
                    }
                    break;
                case "5":
                    System.out.print("Enter the ID number of the recipe you would like to search: ");
                    int idSearch = scanInt.nextInt();
                    if ((idSearch > 1617) || (idSearch < 1)){System.out.println("We got nothing. Please try again.");}
                    else {
                        System.out.println("- Name: " + recipes.get(idSearch-1).getName());
                        System.out.println("- ID: " + recipes.get(idSearch-1).getID());
                        System.out.println("- url: " + recipes.get(idSearch-1).getUrl());
                        System.out.println("- Author: " + recipes.get(idSearch-1).getAuthor());
                        System.out.println("- Description: " + recipes.get(idSearch-1).getDescription());
                        System.out.println("- Ingredients: " + recipes.get(idSearch-1).getIngredients());
                        System.out.println("- Method: " + recipes.get(idSearch-1).getMethod());
                    }
                    break;
                case "6":
                    System.out.println("\n- Unique Authors: " + authorSet.size() + ", Number of Recipes: " + Recipe.getQuantity() + ", Ingredients: ");
                    for (Ingredient element : ingredientObjSet){System.out.println(element.getIngrName() + element.getIDs());}
                    break;
                case "7":
                    System.out.print("\nExiting");
                    end = true;
            }
        }
        scan.close();
        scanInt.close();
    }
}