package com.soumikshah.futuramaquotes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.soumikshah.futuramaquotes.R
import com.soumikshah.futuramaquotes.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:ListViewModel
    var swipeRefreshLayout:SwipeRefreshLayout? = null
    private var recyclerView:RecyclerView? = null
    private var errorMessage:TextView? =null
    private var progressBar:ProgressBar? = null
    private val quotesAdapter = QuotesListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        recyclerView = findViewById(R.id.list_item)
        errorMessage = findViewById(R.id.error_message)
        progressBar = findViewById(R.id.progressBar)

        viewModel =ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerView!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = quotesAdapter
        }

        swipeRefreshLayout!!.setOnRefreshListener {
            swipeRefreshLayout!!.isRefreshing = false
            viewModel.refresh()
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.quotes.observe(this, Observer { quotes ->
            quotes?.let {
                recyclerView!!.visibility = View.VISIBLE
                quotesAdapter.updateQuotes(it)
            }
        })

        viewModel.errorMessage.observe(this, Observer { isError ->
            isError?.let {
                errorMessage!!.visibility = if (it)View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressBar!!.visibility = if (it) View.VISIBLE else View.GONE
                if(it){
                    errorMessage!!.visibility = View.GONE
                    recyclerView!!.visibility = View.GONE
                }
            }
        })
    }
}