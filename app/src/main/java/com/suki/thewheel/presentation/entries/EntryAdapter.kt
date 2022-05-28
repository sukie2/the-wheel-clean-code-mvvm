package com.suki.thewheel.presentation.entries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suki.thewheel.R
import com.suki.thewheel.domain.model.WheelEntry

class EntryAdapter(private val onClick: (WheelEntry) -> Unit) :
    ListAdapter<WheelEntry, EntryAdapter.EntryViewHolder>(EntryDiffCallback) {

    class EntryViewHolder(itemView: View, val onClick: (WheelEntry) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val entryTextView: TextView = itemView.findViewById(R.id.textViewEntry)
        private val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)

        fun bind(entry: WheelEntry) {
            entryTextView.text = entry.name
            deleteButton.setOnClickListener {
                onClick(entry)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entry, parent, false)
        return EntryViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = getItem(position)
        holder.bind(entry)
    }
}

object EntryDiffCallback : DiffUtil.ItemCallback<WheelEntry>() {
    override fun areItemsTheSame(oldItem: WheelEntry, newItem: WheelEntry): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WheelEntry, newItem: WheelEntry): Boolean {
        return oldItem.id == newItem.id
    }
}