package com.picpay.desafio.android.designsystem.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import com.picpay.desafio.android.designsystem.R
import com.picpay.desafio.android.designsystem.databinding.LoadingViewBinding

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        LoadingViewBinding.inflate(LayoutInflater.from(context), this)
        setupAccessibility(context)
    }

    private fun setupAccessibility(context: Context) {
        contentDescription = context.getString(R.string.loading_accessibility)
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
    }
}