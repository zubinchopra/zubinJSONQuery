package edu.washington.zubinc.testapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Macbook on 3/7/17.
 */

public class JSONQuery extends AsyncTask{

    private String[] ingredients;
    private String url;
    private List<Dish> dishes;
    private JSONArray jsonArray;

    public JSONQuery(String[] ingredients){
        Log.d("Constructor", "in here!");
        this.ingredients = ingredients;
        this.url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=";
        this.dishes = new ArrayList<Dish>(5);
    }

    public void setUrl(){
        this.url += this.ingredients[0];
        if(this.ingredients.length > 1) {
            for (int i = 1; i < this.ingredients.length; i++) {
                this.url += "%2C" + this.ingredients[i];
            }
        }
        this.url += "&limitLicense=false&number=5&ranking=1";
        Log.d("URL", this.url);
    }

    public List<Dish> getDishes(){
        return this.dishes;
    }

    public void addDishes() throws JSONException {
        Log.d("TAG", "inside add dishes");
        for(int i = 0; i < 5; i++) {
            JSONObject dish = this.jsonArray.getJSONObject(i);
            int id = dish.getInt("id");
            String title = dish.getString("title");
            String image = dish.getString("image");
            this.dishes.add(i, new Dish(id, title, image));
        }
    }

    @Override
    protected Void doInBackground(Object[] params) {

        try{
            Log.d("TAG", "in here");
            HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=apples%2Cflour%2Csugar&limitLicense=false&number=5&ranking=1")
                    .header("X-Mashape-Key", "PU1nIf2t9qmshCbZ00eHoOcXwGWwp1xg73Ijsn2wUTB0udHndc")
                    .header("Accept", "application/json")
                    .asJson();
            this.jsonArray = response.getBody().getArray();
            this.addDishes();
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

    }

}
