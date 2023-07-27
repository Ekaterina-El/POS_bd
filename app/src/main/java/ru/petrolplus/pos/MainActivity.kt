package ru.petrolplus.pos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
  private val viewModel by lazy {
    ViewModelProvider(this)[TestViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    viewModel.addFakeSetting()
    viewModel.loadAll()
    viewModel.getById(1, showLog = true)
    viewModel.testUpdate()
  }
}