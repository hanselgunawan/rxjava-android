package com.example.rxjavaandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavaandroid.R
import com.example.rxjavaandroid.network.VolumeInfo

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

	var bookListData = ArrayList<VolumeInfo>()

	class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

		private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
		private val tvPublisher: TextView = view.findViewById(R.id.tvPublisher)
		private val tvDesc: TextView = view.findViewById(R.id.tvDesc)
		private val thumbImage: ImageView = view.findViewById(R.id.thumbImageView)

		fun bind(data : VolumeInfo) {
			tvTitle.text = data.volumeInfo?.title
			tvPublisher.text = data.volumeInfo?.publisher
			tvDesc.text = data.volumeInfo?.description

			val url = data.volumeInfo?.imageLinks?.smallThumbnail

			Glide.with(thumbImage.context)
				.load(url)
				.circleCrop()
				.into(thumbImage)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)
		return MyViewHolder(view)
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.bind(bookListData[position])
	}

	override fun getItemCount(): Int {
		return bookListData.size
	}
}