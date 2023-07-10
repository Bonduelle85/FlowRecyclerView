package com.example.mycoroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycoroutines.databinding.ItemPeopleBinding
import com.example.mycoroutines.databinding.ItemShipBinding


abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

class MyAdapter(var list: ArrayList<Any>) : ListAdapter<Item, ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ItemType.SHIP -> {
                val binding = ItemShipBinding.inflate(inflater, parent, false)
                ShipViewHolder(binding)
            }
            ItemType.PEOPLE -> {
                val binding = ItemPeopleBinding.inflate(inflater, parent, false)
                PeopleViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    fun addItem(item: Item){
        list.add(item)
        notifyItemInserted(list.size-1)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        when (holder) {
            is ShipViewHolder -> holder.bind(item as Item.ShipItem)
            is PeopleViewHolder -> holder.bind(item as Item.PeopleItem)
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is Item.ShipItem -> ItemType.SHIP
            is Item.PeopleItem -> ItemType.PEOPLE
            else -> ItemType.PEOPLE
        }
    }

    // ViewHolder для элемента "ship"
    inner class ShipViewHolder(private val binding: ItemShipBinding) : ViewHolder(binding.root) {
        fun bind(item: Item.ShipItem) {
            val ship = item.ship
            binding.shipNameTextView.text = ship.name
            binding.shipLengthTextView.text = ship.length.toString()
        }
    }

    // ViewHolder для элемента "people"
    inner class PeopleViewHolder(private val binding: ItemPeopleBinding) : ViewHolder(binding.root) {
        fun bind(item: Item.PeopleItem) {
            val people = item.people
            binding.peopleNameTextView.text = people.name
            binding.peopleAgeTextView.text = people.age.toString()
        }
    }

    // Типы элементов списка
    object ItemType {
        const val SHIP = 1
        const val PEOPLE = 2
    }

    // Класс для сравнения элементов списка при обновлении
    private class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.viewType == newItem.viewType
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}

