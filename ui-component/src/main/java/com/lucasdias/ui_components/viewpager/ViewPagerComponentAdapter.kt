package com.lucasdias.ui_components.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.lucasdias.extensions.loadImage
import com.lucasdias.ui_components.R

class ViewPagerComponentAdapter(private val context: Context) : PagerAdapter() {

    private val imageUrlList = mutableListOf<String>()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return imageUrlList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.view_pager_component_item, null)
        val viewPager = container as ViewPager
        val imageView: ImageView = view.findViewById(R.id.picture_image_view_pager_item)

        // TODO: Add an error place holder
        imageView.loadImage(imageUrlList[position])
        viewPager.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

    fun updateImageUrlList(imageUrlList: List<String>) {
        this.imageUrlList.addAll(imageUrlList)
        notifyDataSetChanged()
    }
}
