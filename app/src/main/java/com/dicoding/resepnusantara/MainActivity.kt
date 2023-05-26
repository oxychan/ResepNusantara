package com.dicoding.resepnusantara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private lateinit var rvRecipes: RecyclerView
    private val listRecipe: MutableList<Recipe> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rgRecipeType: RadioGroup = findViewById(R.id.rg_recipe_type)
        val ivProfilePhotoAboutPage: ImageView = findViewById(R.id.iv_about_page)

        rvRecipes = findViewById(R.id.rv_recipes)
        rvRecipes.setHasFixedSize(true)

        rgRecipeType.check(R.id.rb_food)
        rgRecipeType.setOnCheckedChangeListener(this)
        ivProfilePhotoAboutPage.setOnClickListener {
            val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(aboutIntent)
        }

        listRecipe.addAll(getListFoodRecipes())
        showRecyclerList()
    }

    private fun getListFoodRecipes(): ArrayList<Recipe> {
        val dataTitle = resources.getStringArray(R.array.data_food_title)
        val dataDesc = resources.getStringArray(R.array.data_food_description)
        val dataIngredients = resources.getStringArray(R.array.data_food_ingredients)
        val dataSteps = resources.getStringArray(R.array.data_food_steps)
        val dataPhoto = resources.obtainTypedArray(R.array.data_food_photo)

        val tempRecipes = ArrayList<Recipe>()
        for (index in dataTitle.indices) {
            val recipe = Recipe(
                title = dataTitle[index],
                desc = dataDesc[index],
                ingredients = dataIngredients[index],
                steps = dataSteps[index],
                photo = dataPhoto.getResourceId(index, -1)
            )

            tempRecipes.add(recipe)
        }

        return tempRecipes
    }

    private fun getListDrinkRecipes(): ArrayList<Recipe> {
        val dataTitle = resources.getStringArray(R.array.data_drink_title)
        val dataDesc = resources.getStringArray(R.array.data_drink_description)
        val dataIngredients = resources.getStringArray(R.array.data_drink_ingredients)
        val dataSteps = resources.getStringArray(R.array.data_drink_steps)
        val dataPhoto = resources.obtainTypedArray(R.array.data_drink_photo)

        val tempRecipes = ArrayList<Recipe>()
        for (index in dataTitle.indices) {
            val recipe = Recipe(
                title = dataTitle[index],
                desc = dataDesc[index],
                ingredients = dataIngredients[index],
                steps = dataSteps[index],
                photo = dataPhoto.getResourceId(index, -1)
            )

            tempRecipes.add(recipe)
        }

        return tempRecipes
    }

    private fun showRecyclerList() {
        rvRecipes.layoutManager = GridLayoutManager(this, 2)

        val listRecipeAdapter = ListRecipeAdapter(listRecipe)
        rvRecipes.adapter = listRecipeAdapter

        listRecipeAdapter.setOnItemClickCallback(object : ListRecipeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Recipe) {
                showSelectedRecipeDetail(data)
            }
        })
    }

    private fun showSelectedRecipeDetail(recipe: Recipe) {
        val detailRecipeIntent = Intent(this@MainActivity, DetailRecipeActivity::class.java)
        detailRecipeIntent.putExtra(DetailRecipeActivity.EXTRA_RECIPE, recipe)
        startActivity(detailRecipeIntent)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_food -> {
                listRecipe.clear()
                listRecipe.addAll(getListFoodRecipes())
                showRecyclerList()
            }

            R.id.rb_drink -> {
                listRecipe.clear()
                listRecipe.addAll(getListDrinkRecipes())
                showRecyclerList()
            }
        }
    }
}