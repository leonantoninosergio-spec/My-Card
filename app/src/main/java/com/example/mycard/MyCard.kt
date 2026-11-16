package com.example.mycard

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.mycard.databinding.ViewMyCardBinding
import com.google.android.material.card.MaterialCardView

class MyCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    // Acceso a todos los componentes internos mediante ViewBinding
    private val binding: ViewMyCardBinding =
        ViewMyCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        radius = 12f * resources.displayMetrics.density
        cardElevation = 2f * resources.displayMetrics.density
        strokeWidth = (1f * resources.displayMetrics.density).toInt()
        strokeColor = 0xFFE5E7EB.toInt()

        // Lectura de atributos definidos en XML
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.MyCard, 0, 0)
            try {
                typedArray.getString(R.styleable.MyCard_cardTitle)?.let { setTitle(it) }
                typedArray.getString(R.styleable.MyCard_cardSubtitle)?.let { setSubtitle(it) }
                typedArray.getString(R.styleable.MyCard_cardDescription)?.let { setDescription(it) }

                val imgRes = typedArray.getResourceId(R.styleable.MyCard_cardImage, -1)
                if (imgRes != -1) {
                    setImageResource(imgRes)
                }

                typedArray.getString(R.styleable.MyCard_primaryButtonText)?.let {
                    binding.btnCardPrimary.text = it
                }
                typedArray.getString(R.styleable.MyCard_secondaryButtonText)?.let {
                    binding.btnCardSecondary.text = it
                }
            } finally {
                typedArray.recycle()
            }
        }
    }

    // Métodos flexibles para configurar textos programáticamente
    fun setTitle(title: String) {
        binding.tvCardTitle.text = title
    }

    fun setSubtitle(subtitle: String) {
        binding.tvCardSubtitle.text = subtitle
    }

    fun setDescription(description: String) {
        binding.tvCardDescription.text = description
    }

    // Métodos programáticos para añadir imagen (Drawable, Resource o Bitmap)
    fun setImageResource(@DrawableRes resId: Int, contentDesc: String? = null) {
        binding.ivCardMedia.imageTintList = null
        binding.ivCardMedia.scaleType = ImageView.ScaleType.CENTER_CROP
        binding.ivCardMedia.setImageResource(resId)
        contentDesc?.let { binding.ivCardMedia.contentDescription = it }
    }

    fun setImageDrawable(drawable: Drawable?, contentDesc: String? = null) {
        binding.ivCardMedia.imageTintList = null
        binding.ivCardMedia.scaleType = ImageView.ScaleType.CENTER_CROP
        binding.ivCardMedia.setImageDrawable(drawable)
        contentDesc?.let { binding.ivCardMedia.contentDescription = it }
    }

    fun setImageBitmap(bitmap: Bitmap?, contentDesc: String? = null) {
        binding.ivCardMedia.imageTintList = null
        binding.ivCardMedia.scaleType = ImageView.ScaleType.CENTER_CROP
        binding.ivCardMedia.setImageBitmap(bitmap)
        contentDesc?.let { binding.ivCardMedia.contentDescription = it }
    }

    // Listeners para acciones de los botones y menú
    fun setOnPrimaryActionClickListener(listener: () -> Unit) {
        binding.btnCardPrimary.setOnClickListener { listener() }
    }

    fun setOnSecondaryActionClickListener(listener: () -> Unit) {
        binding.btnCardSecondary.setOnClickListener { listener() }
    }

    fun setOnMoreClickListener(listener: () -> Unit) {
        binding.btnCardMore.setOnClickListener { listener() }
    }
}