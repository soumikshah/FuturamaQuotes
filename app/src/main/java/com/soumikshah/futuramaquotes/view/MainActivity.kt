package com.soumikshah.futuramaquotes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.soumikshah.futuramaquotes.R
import com.soumikshah.futuramaquotes.model.Character
import com.soumikshah.futuramaquotes.viewmodel.CharacterViewModel
import com.soumikshah.futuramaquotes.viewmodel.ListViewModel
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:ListViewModel
    lateinit var characterViewModel: CharacterViewModel
    var swipeRefreshLayout:SwipeRefreshLayout? = null
    private var recyclerView:RecyclerView? = null
    private var errorMessage:TextView? =null
    private var progressBar:ProgressBar? = null
    private var buttonGroup:ThemedToggleButtonGroup? = null


    private val quotesAdapter = QuotesListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        recyclerView = findViewById(R.id.list_item)
        errorMessage = findViewById(R.id.error_message)
        progressBar = findViewById(R.id.progressBar)
        buttonGroup = findViewById(R.id.character_group)

        viewModel =ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        characterViewModel.refresh()

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

        characterViewModel.character.observe(this, Observer { characters ->
            characters?.let {
                for(character in characters){
                    if(character.screenName!= null){
                        Log.d("Futurama","Character ${character.screenName}")
                        val button = ThemedButton(buttonGroup!!.context)
                        button.text = character.screenName.toString()
                        buttonGroup!!.addView(button,
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        ))
                    }
                }
            }
        })
    }
}