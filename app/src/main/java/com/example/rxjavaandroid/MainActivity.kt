package com.example.rxjavaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.rxjavaandroid.adapter.BookListAdapter
import com.example.rxjavaandroid.databinding.ActivityMainBinding
import com.example.rxjavaandroid.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {


	lateinit var viewModel: MainActivityViewModel
	lateinit var bookListAdapter: BookListAdapter
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		val view = binding.root
		setContentView(view)
		initSearchBook()
		initRecyclerView()
	}

	fun initSearchBook() {
		binding.inputBookName.addTextChangedListener(object: TextWatcher{
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				loadAPIData()
			}

			override fun afterTextChanged(s: Editable?) {
			}

		})
	}

	private fun initRecyclerView() {
		binding.recyclerView.apply {
			val layoutManager = LinearLayoutManager(this@MainActivity)
			val decoration = DividerItemDecoration(applicationContext, VERTICAL)
			addItemDecoration(decoration)
			bookListAdapter = BookListAdapter()
			adapter = bookListAdapter
		}
	}

	private fun loadAPIData() {
		viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
		viewModel.getBookListObserver().observe(this, Observer { bookList ->
			if (bookList != null) {
				// update adapter
				bookListAdapter.bookListData = bookList.items
				bookListAdapter.notifyDataSetChanged()
			} else {
				Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
			}
		})
	}
}