package com.dudarenkavitali.jetsearchhelper.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dudarenkavitali.domain.entity.Feed
import com.dudarenkavitali.jetsearchhelper.databinding.ItemResultBinding

class SearchResultsAdapter :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    private val items: MutableList<Feed> = mutableListOf()
    private lateinit var listener: (Feed) -> Unit

    fun initListener(listener: (Feed) -> Unit) {
        this.listener = listener
    }

    fun setItems(items: MutableList<Feed>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemResultBinding = ItemResultBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemResultBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Feed = items[position]
        val context: Context = holder.itemView.context

        holder.tvRoute.text = StringBuilder(item.from).append(" - ").append(item.to)
        holder.tvAircraft.text = item.airCraft
        holder.tvCompany.text = item.airCompany

        holder.itemView.setOnClickListener {
            listener(item)
        }

    }

    override fun getItemCount() = items.size

    class ViewHolder(itemResultBinding: ItemResultBinding) :
        RecyclerView.ViewHolder(itemResultBinding.root) {

        val tvRoute: TextView = itemResultBinding.tvRoute
        val tvAircraft: TextView = itemResultBinding.tvAircraft
        val tvCompany: TextView = itemResultBinding.tvCompany

    }

}