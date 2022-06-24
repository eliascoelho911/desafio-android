package com.picpay.desafio.android.designsystem.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.picpay.desafio.android.designsystem.databinding.LoadingViewBinding

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        LoadingViewBinding.inflate(LayoutInflater.from(context), this)
    }
}