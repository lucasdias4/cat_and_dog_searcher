package com.lucasdias.ui_components.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lucasdias.extensions.loadImage
import com.lucasdias.ui_components.R
import com.lucasdias.ui_components.card.model.CardProperties

class CardComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var layout: ConstraintLayout
    private var image: ImageView
    private var title: TextView
    private var informationCard: CardView

    init {
        View.inflate(context, R.layout.card_component, this)
        layout = findViewById(R.id.layout_card_component)
        image = findViewById(R.id.image_card_component)
        title = findViewById(R.id.title_card_component)
        informationCard = findViewById(R.id.information_card_view_card_component)
        setupInformationCard()
    }

    fun onComponentClickListener(onClick: (() -> Unit)?) {
        layout.setOnClickListener {
            onClick?.invoke()
        }
    }

    fun applyProperties(properties: CardProperties) {
        title.text = properties.name
        image.loadImage(
            url = properties.thumbnail?.url,
            errorPlaceHolderId = properties.thumbnail?.placeHolder
        )

        onComponentClickListener(properties.action)
    }

    private fun setupInformationCard() {
        informationCard.setBackgroundColor(context.getColor(R.color.mine_shaft))
    }
}
