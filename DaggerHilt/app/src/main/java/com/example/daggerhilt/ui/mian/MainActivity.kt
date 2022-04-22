package com.example.daggerhilt.ui.mian

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerhilt.data.model.User
import com.example.daggerhilt.databinding.ActivityMainBinding
import com.example.daggerhilt.utils.status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var adapter: MainAdater
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        setupUi()
        setupObserver()
    }

    fun setupUi() {
        mainActivityBinding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdater(arrayListOf())
            rvUser.addItemDecoration(
                DividerItemDecoration(
                    rvUser.context,
                    (rvUser.layoutManager as LinearLayoutManager).orientation
                )
            )
            rvUser.adapter = adapter
        }
    }

    private fun setupObserver(){
        mainViewModel.users.observe(this, Observer {
            when(it.status){
                status.SUCCESS -> {
                    mainActivityBinding.progressBar.visibility  = View.GONE
                    it.data?.let {
                        it -> renderList(it)
                    }
                }
                status.LOADING -> {
                    mainActivityBinding.progressBar.visibility = View.VISIBLE
                    mainActivityBinding.rvUser.visibility = View.GONE
                }
                status.ERROR -> {
                    mainActivityBinding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext,"${it.message}",Toast.LENGTH_LONG).show()
                }
            }
        })

    }


    private fun renderList(users:List<User>){
        adapter.addData(users)
        adapter.notifyDataSetChanged()

    }
}