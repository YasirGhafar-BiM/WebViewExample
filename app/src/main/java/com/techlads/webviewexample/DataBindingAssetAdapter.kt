package com.techlads.webviewexample

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.techlads.webviewexample.databinding.ItemModelBinding
import com.techlads.webviewexample.model.Asset
import kotlinx.android.synthetic.main.item_model.view.*

class DataBindingAssetAdapter(val context: Context): RecyclerView.Adapter<DataBindingAssetAdapter.AssetViewHolder>() {

    var assets: MutableList<Asset> = arrayListOf()

    inner class AssetViewHolder(val binding: ItemModelBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val assetBinding = DataBindingUtil.inflate<ItemModelBinding>(
            LayoutInflater.from(parent.context), R.layout.item_model, parent, false
        )
        return AssetViewHolder(assetBinding)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val asset = assets.get(position)

        holder.binding.asset = asset
        holder.itemView.setOnClickListener {
            context.startActivity(
                WebViewActivity.startActivity(context, asset.embedUrl)
            )
        }
    }

    override fun getItemCount(): Int {
        return assets.size
    }

    fun updateAdapter(assets: List<Asset>) {
        this.assets = assets.toMutableList()
        notifyDataSetChanged()
    }
}

object DataBindingAdapters {

    @BindingAdapter("imgRes")
    @JvmStatic
    fun setImageResource(imageView: ImageView, resource: String) {
        Picasso.get().load(resource).fit().into(imageView.assetIV)
    }

//    @BindingAdapter("visible")
//    @JvmStatic
//    fun View.setVisible(show: Boolean) {
//        visibility = if (show) VISIBLE else GONE
//    }
}
