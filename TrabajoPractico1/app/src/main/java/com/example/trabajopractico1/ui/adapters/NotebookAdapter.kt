package com.example.trabajopractico1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopractico1.R
import com.example.trabajopractico1.models.Notebook

class NotebookAdapter(
    private val notebookList: ArrayList<Notebook>,
    private val listener: OnContactClickListener
) : RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder>() {

    class NotebookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lblViewTitle= itemView.findViewById<TextView>(R.id.lblViewTitle)
        private var lblViewNotebook = itemView.findViewById<TextView>(R.id.lblViewNotebook)
        private var btnDeleteNotebook = itemView.findViewById<ImageButton>(R.id.btnDeleteNotebook)
        private var btnEditNotebook = itemView.findViewById<ImageButton>(R.id.btnEditNotebook)
        private var btnSelectColorNotebook = itemView.findViewById<ImageButton>(R.id.btnSelectColorNotebook)

        fun bind(notebook: Notebook, listener: OnContactClickListener) {
            lblViewTitle.text = notebook.title
            lblViewNotebook.text = notebook.content

            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, notebook.color))

            btnEditNotebook.setOnClickListener {
                listener.onContactEditClickListener(notebook)
            }
            btnDeleteNotebook.setOnClickListener {
                listener.onContactDeleteClickListener(notebook)
            }
            btnSelectColorNotebook.setOnClickListener {
                listener.onContactEditColorListener(notebook)
            }
        }
    }

    fun itemDeleted(notebook: Notebook){
        val index = notebookList.indexOf(notebook)
        notebookList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun itemUpdated(notebook: Notebook){
        val index = notebookList.indexOf(notebook)
        notebookList[index] = notebook
        notifyItemChanged(index)
    }

    fun itemAdded(notebook: Notebook){
        notebookList.add(1,notebook)
        notifyItemInserted(1)
    }

    override fun getItemCount(): Int {
        return notebookList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notebook_item, parent, false)
        return NotebookViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotebookViewHolder, position: Int) {
        holder.bind(notebookList[position],listener)
    }

    public interface OnContactClickListener{
        fun onContactEditClickListener(notebook: Notebook)
        fun onContactDeleteClickListener(notebook: Notebook)
        fun onContactEditColorListener(notebook: Notebook)
    }
}
