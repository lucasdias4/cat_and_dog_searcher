package com.lucasdias.ui_components.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.lucasdias.ui_components.R

class ViewPagerComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var viewPager: ViewPager
    private var adapter: ViewPagerComponentAdapter

    init {
        View.inflate(context, R.layout.view_pager_component, this)
        viewPager = findViewById(R.id.view_pager_view_pager_component)
        adapter = ViewPagerComponentAdapter(context)
        viewPager.adapter = adapter
    }

    fun updateImageUrls(imageUrls: List<String>) {
        adapter.updateImageUrlList(imageUrls)
    }
}
