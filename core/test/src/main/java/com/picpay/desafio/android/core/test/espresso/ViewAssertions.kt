package com.picpay.desafio.android.core.test.espresso

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.appbar.AppBarLayout
import com.picpay.desafio.android.core.commons.isCollapsed
import com.picpay.desafio.android.core.commons.isExpanded
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

fun checkViewVisibility(@IdRes id: Int, isVisible: Boolean) {
    val match = if (isVisible) isDisplayed() else not(isDisplayed())
    onView(withId(id)).check(matches(match))
}

fun checkAppBarLayoutIsCollapsed(): BoundedMatcher<View, AppBarLayout> {
    return object : BoundedMatcher<View, AppBarLayout>(AppBarLayout::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("appBarLayout is collapsed")
        }

        override fun matchesSafely(item: AppBarLayout?): Boolean {
            return item?.isCollapsed() ?: false
        }
    }
}

fun checkAppBarLayoutIsExpanded(): BoundedMatcher<View, AppBarLayout> {
    return object : BoundedMatcher<View, AppBarLayout>(AppBarLayout::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("appBarLayout is expanded")
        }

        override fun matchesSafely(item: AppBarLayout?): Boolean {
            return item?.isExpanded() ?: false
        }
    }
}

fun checkRecyclerViewItem(@IdRes id: Int, position: Int, withMatcher: Matcher<View>) {
    onView(withId(id)).check(
        matches(
            atPositionOnRecyclerView(
                position,
                hasDescendant(withMatcher)
            )
        )
    )
}

private fun atPositionOnRecyclerView(
    position: Int,
    itemMatcher: Matcher<View>,
) = object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("has item at position $position: ")
        itemMatcher.describeTo(description)
    }

    override fun matchesSafely(item: RecyclerView?): Boolean {
        val viewHolder = item?.findViewHolderForAdapterPosition(position) ?: return false
        return itemMatcher.matches(viewHolder.itemView)
    }
}