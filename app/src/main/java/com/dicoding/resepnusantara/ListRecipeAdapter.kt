package com.dicoding.resepnusantara

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListRecipeAdapter(private val listRecipe: MutableList<Recipe>) :
    RecyclerView.Adapter<ListRecipeAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Recipe)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: ImageView = itemView.findViewById(R.id.iv_recipe)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_recipe_title)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_recipe_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_recipe, parent, false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, desc, _, _, photo) = listRecipe[position]
        holder.imgItem.setImageResource(photo)
        holder.tvTitle.text = title
        holder.tvDesc.text = desc

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listRecipe[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listRecipe.size
}