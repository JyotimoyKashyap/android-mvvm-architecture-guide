package com.jyotimoykashyap.coughittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jyotimoykashyap.coughittest.databinding.ActivityMainBinding
import com.jyotimoykashyap.coughittest.respository.RestRepository
import com.jyotimoykashyap.coughittest.ui.RestViewModel
import com.jyotimoykashyap.coughittest.ui.RestViewModelProviderFactory
import com.jyotimoykashyap.coughittest.util.Resource
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RestViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = RestRepository()
        val viewModelProviderFactory = RestViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(RestViewModel::class.java)

        viewModel.getTodos()
        binding.textView.movementMethod = ScrollingMovementMethod()
        // make the network call
        viewModel.todos.observe(this, Observer {
            when(it){
                is Resource.Success ->{
                    it.data?.let {response->
                        Log.d("Response" , Arrays.toString(response.toArray()))
                        var reso = ""
                        for (todoItem in response){
                            reso = reso + "\n" + "Title: "+ todoItem.title + "\n" +
                                    "User Id: " + todoItem.userId + "\n" +
                                    "Status: " + todoItem.completed + "\n" +
                                    "ID: " + todoItem.id + "\n"
                        }
                        binding.textView.text = reso

                    }
                }
            }
        })
    }
}