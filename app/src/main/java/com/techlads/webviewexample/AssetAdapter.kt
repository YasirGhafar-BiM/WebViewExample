package com.techlads.webviewexample

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.techlads.webviewexample.model.Asset
import kotlinx.android.synthetic.main.item_model.view.*
import java.net.URL


class AssetAdapter(var context: Context) : RecyclerView.Adapter<AssetAdapter.AssetViewHolder>() {

    var assets: MutableList<Asset> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        return AssetViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        holder.setModel(assets[position])
    }

    override fun getItemCount(): Int {
        return assets.size
    }

    inner class AssetViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        fun setModel(asset: Asset) {
            itemView.assetNameTv.text = asset.name
            Picasso.get().load(asset.thumbnails.images?.get(0)?.url).fit().into(itemView.assetIV)
        }

        override fun onClick(v: View?) {
                val asset = assets[adapterPosition]
            val url = "https://sketchfab.com/models/7e056adc8da94a97a50cab9f93f796da/embed"
            context.startActivity(
                //WebViewActivity.startActivity(context, asset.embedUrl)

                WebViewActivity.startActivity(context, url)
            )
        }
    }


    fun updateAdapter(assets: List<Asset>) {
        this.assets = assets.toMutableList()
        notifyDataSetChanged()
    }

}

