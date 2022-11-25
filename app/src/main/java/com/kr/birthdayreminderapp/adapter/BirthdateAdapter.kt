package com.kr.birthdayreminderapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kr.birthdayreminderapp.databinding.ItemBirthdateRecordBinding
import com.kr.birthdayreminderapp.roomDb.BirthdateModel

class BirthdateAdapter(private var list: MutableList<BirthdateModel>):RecyclerView.Adapter<BirthdateAdapter.BirthDateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthDateViewHolder {
        val binding: ItemBirthdateRecordBinding = ItemBirthdateRecordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BirthDateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BirthDateViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class BirthDateViewHolder(private var itemBinding: ItemBirthdateRecordBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: BirthdateModel) {
            with(itemBinding) {
                this.data = data
                executePendingBindings()
            }
        }
    }
}