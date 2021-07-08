package com.example.cleanexample.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.cleanexample.R
import com.example.cleanexample.databinding.ActivityMainBinding
import com.example.cleanexample.presentation.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = MainAdapter()

    private val mainViewModel: MainViewModel by viewModels {
        // getInstance 의 내부 구현은
        // 데이터소스(레트로핏) -> 레파지토리(매핑) -> 유스케이스로부터 차례로 가져옴
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 데이터바인딩
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter

        // viewModel 에 데이터를 가지고있음
        mainViewModel.getUserList()
        setObserver()
    }

    private fun setObserver() {
        // viewModel 에 있는 livedata 를 observe
        mainViewModel.userList.observe(this) { userList ->
            with(adapter) {
                // adapter 의 list
                list.clear()
                list.addAll(userList)
                notifyDataSetChanged()

                if (list.size > 0) {
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}