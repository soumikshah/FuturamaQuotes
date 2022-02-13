package com.soumikshah.futuramaquotes.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.soumikshah.futuramaquotes.R
import com.soumikshah.futuramaquotes.model.Quote
import com.soumikshah.futuramaquotes.util.getProgressDrawable
import com.soumikshah.futuramaquotes.util.loadImage

class QuotesListAdapter(var quotes: ArrayList<Quote>):RecyclerView.Adapter<QuotesListAdapter.QuotesViewAdapter>() {
    fun updateQuotes(newQuotes: List<Quote>){
        quotes.clear()
        quotes.addAll(newQuotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuotesViewAdapter(LayoutInflater.from(parent.context).inflate(R.layout.item_quote,parent,false))

    override fun onBindViewHolder(holder: QuotesViewAdapter, position: Int) {
        holder.bind(quotes[position])
        holder.parentLayout.setOnLongClickListener (OnLongClickListener {
            copyToClipboard(holder.quoteDialog.text.toString()+"\n -"+holder.quoteAuthor.text,holder.parentLayout)
            Toast.makeText(holder.parentLayout.context,"Copied quote to the clipboard",Toast.LENGTH_SHORT).show()
            true
        })
    }

    private fun copyToClipboard(text: CharSequence, view:View){
        val clipboard = ContextCompat.getSystemService(view.context, ClipboardManager::class.java)
        clipboard?.setPrimaryClip(ClipData.newPlainText("",text))
    }
    override fun getItemCount() = quotes.size

    class QuotesViewAdapter(view:View):RecyclerView.ViewHolder(view){
        val parentLayout: LinearLayout = view.findViewById(R.id.parent_layout)
        val quoteDialog: TextView = view.findViewById(R.id.quote_dialog)
        val quoteAuthor: TextView = view.findViewById(R.id.quote_author)
        private val imageView:ImageView = view.findViewById(R.id.imageView)
        private val progressBar= getProgressDrawable(view.context)

        fun bind(quote:Quote){
            quoteDialog.text = quote.dialog
            quoteAuthor.text = String.format("- %s",quote.characterName)
            imageView.loadImage(quote.image,progressBar)
        }
    }

}