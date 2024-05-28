package com.systems.testtask.recyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.systems.testtask.R
import com.systems.testtask.data.remoteDataClass.SeachResults
import com.systems.testtask.data.remoteDataClass.Search
import java.util.zip.Inflater

class MovieAdapter(val moviesList:List<Search>, val context:Context):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val item = moviesList[position]
        holder.tvMovieName.text= item.Title
        Glide.with(context).load(item.Poster).into(holder.ivPoster)
    }

    override fun getItemCount()=moviesList.size

    class MovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val ivPoster = itemView.findViewById<ImageView>(R.id.ivMoviePoster)
        val tvMovieName = itemView.findViewById<TextView>(R.id.tvMovieName)
    }
}