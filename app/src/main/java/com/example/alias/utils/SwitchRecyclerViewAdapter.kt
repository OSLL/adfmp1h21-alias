package com.example.alias.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alias.R
import kotlinx.android.synthetic.main.switch_item.view.*

class SwitchRecyclerViewAdapter(
    private val data: List<AnswerWord>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<SwitchRecyclerViewAdapter.SwitchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwitchViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.switch_item, parent, false)

        return SwitchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SwitchViewHolder, position: Int) {
        val currentItem = data[position]

        holder.switch.isChecked = currentItem.isGuessed
        holder.textView.text = currentItem.word
    }

    override fun getItemCount(): Int = data.size

    inner class SwitchViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val switch: SwitchCompat = itemView.answerSwitch
        val textView: TextView = itemView.answerText

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}