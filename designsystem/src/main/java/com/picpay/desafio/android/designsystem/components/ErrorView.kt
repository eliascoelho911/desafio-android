package com.picpay.desafio.android.designsystem.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.picpay.desafio.android.designsystem.R
import com.picpay.desafio.android.designsystem.databinding.ErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding: ErrorViewBinding = ErrorViewBinding.inflate(LayoutInflater.from(context), this)

    var icon: Drawable? = null
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

    var message: String? = null
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

    @ColorInt
    var iconTint: Int = 0
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

    @ColorInt
    var textColor: Int = 0
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

    @ColorInt
    var buttonTint: Int = 0
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

    var onClickTryAgain: () -> Unit = {}

    init {
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ErrorView, 0, 0).apply {
            runCatching {
                icon = getDrawable(R.styleable.ErrorView_icon) ?: defaultIcon
                message = getString(R.styleable.ErrorView_message)
                iconTint = getColor(R.styleable.ErrorView_iconTint, defaultIconTint)
                textColor = getColor(R.styleable.ErrorView_textColor, defaultTextColor)
                buttonTint = getColor(R.styleable.ErrorView_buttonTint, defaultButtonTint)
            }
            recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            message.text = this@ErrorView.message
            message.setTextColor(this@ErrorView.textColor)
            icon.setImageDrawable(this@ErrorView.icon)
            icon.imageTintList = ColorStateList.valueOf(this@ErrorView.iconTint)
            tryAgainButton.strokeColor = ColorStateList.valueOf(this@ErrorView.buttonTint)
            tryAgainButton.setTextColor(this@ErrorView.buttonTint)
            tryAgainButton.setOnClickListener {
                onClickTryAgain()
            }
        }
    }

    private val defaultIcon get() = ContextCompat.getDrawable(context, R.drawable.ic_error)
    private val defaultIconTint get() = ContextCompat.getColor(context, R.color.errorColor)
    private val defaultTextColor get() = ContextCompat.getColor(context, R.color.onBackgroundColor)
    private val defaultButtonTint get() = ContextCompat.getColor(context, R.color.secondaryColor)
}