package com.dicoding.resepnusantara

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailRecipeActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_RECIPE = "extra_recipe"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_recipe)

        val tvTitle: TextView = findViewById(R.id.tv_recipe_title_detail)
        val imgRecipe: ImageView = findViewById(R.id.iv_recipe_detail)
        val tvDesc: TextView = findViewById(R.id.tv_recipe_desc_detail)
        val tvIngredients: TextView = findViewById(R.id.tv_ingredients_detail)
        val tvSteps: TextView = findViewById(R.id.tv_steps_detail)
        val btnShare: Button = findViewById(R.id.action_share)

        btnShare.setOnClickListener {
            val recipeIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(DetailRecipeActivity.EXTRA_RECIPE, "${tvTitle.text}\nDesc:\n${tvDesc.text}\nIngredients:\n${tvIngredients.text}\nSteps:\n${tvSteps.text}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(recipeIntent, null)
            startActivity(shareIntent)
        }

        val recipe = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_RECIPE, Recipe::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_RECIPE)
        }

        if (recipe != null) {
            tvTitle.text = recipe.title
            imgRecipe.setImageResource(recipe.photo)
            tvDesc.text = recipe.desc
            tvIngredients.text = recipe.ingredients
            tvSteps.text = recipe.steps
        }
    }
}