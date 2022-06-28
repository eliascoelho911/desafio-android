package com.picpay.desafio.android.core.commons

import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

fun AppBarLayout.isExpanded(): Boolean {
    val behavior = (layoutParams as CoordinatorLayout.LayoutParams).behavior as AppBarLayout.Behavior
    return behavior.topAndBottomOffset == 0
}

fun AppBarLayout.isCollapsed(): Boolean = !isExpanded()