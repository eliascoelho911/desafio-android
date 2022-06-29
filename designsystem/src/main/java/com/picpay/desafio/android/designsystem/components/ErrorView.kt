package com.picpay.desafio.android.designsystem.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.picpay.desafio.android.designsystem.R
import com.picpay.desafio.android.designsystem.databinding.ErrorViewBinding

/**
 * Todo: Testar onClickTryAgain
 */
class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayoutCompat(context, attrs, defStyleAttr) {
    private val binding: ErrorViewBinding =
        ErrorViewBinding.inflate(LayoutInflater.from(context), this)

    var onClickTryAgain: () -> Unit = {}

    init {
        fillAttributes(context, attrs)
        setup()
    }

    private fun fillAttributes(context: Context, attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ErrorView, 0, 0).apply {
            runCatching {
                (getDrawable(R.styleable.ErrorView_icon) ?: defaultIcon)?.let(::setIcon)
                (getString(R.styleable.ErrorView_message))?.let(::setMessage)
                getColor(R.styleable.ErrorView_iconTint, defaultIconTint).let(::setIconTint)
                getColor(R.styleable.ErrorView_textColor, defaultTextColor).let(::setMessageColor)
                getColor(R.styleable.ErrorView_buttonTint, defaultButtonTint).let(::setButtonTint)
            }
            recycle()
        }
    }

    fun setIcon(drawable: Drawable) {
        binding.icon.setImageDrawable(drawable)
    }

    fun setMessage(message: String) {
        binding.message.text = message
    }

    fun getMessage() = binding.message.text.toString()

    fun setIconTint(@ColorInt color: Int) {
        binding.icon.imageTintList = ColorStateList.valueOf(color)
    }

    fun setMessageColor(@ColorInt color: Int) {
        binding.message.setTextColor(color)
    }

    fun setButtonTint(@ColorInt color: Int) {
        binding.tryAgainButton.apply {
            strokeColor = ColorStateList.valueOf(color)
            setTextColor(color)
        }
    }

    private fun setup() {
        orientation = VERTICAL
        binding.tryAgainButton.setOnClickListener {
            onClickTryAgain()
        }
    }

    private val defaultIcon get() = ContextCompat.getDrawable(context, R.drawable.ic_error)
    private val defaultIconTint get() = ContextCompat.getColor(context, R.color.errorColor)
    private val defaultTextColor get() = ContextCompat.getColor(context, R.color.onBackgroundColor)
    private val defaultButtonTint get() = ContextCompat.getColor(context, R.color.secondaryColor)
}