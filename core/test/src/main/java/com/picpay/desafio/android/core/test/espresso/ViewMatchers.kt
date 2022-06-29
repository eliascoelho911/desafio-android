package com.picpay.desafio.android.core.test.espresso

import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.appbar.AppBarLayout
import com.picpay.desafio.android.core.commons.isCollapsed
import com.picpay.desafio.android.designsystem.components.ErrorView
import org.hamcrest.Description
import org.hamcrest.Matcher

fun checkView(@IdRes withId: Int, matcher: Matcher<View>) {
    onView(withId(withId)).check(matches(matcher))
}

object AppBarLayoutMatchers {
    fun isCollapsed(): BoundedMatcher<View, AppBarLayout> {
        return object : BoundedMatcher<View, AppBarLayout>(AppBarLayout::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("appBarLayout is collapsed")
            }

            override fun matchesSafely(item: AppBarLayout?): Boolean {
                return item?.isCollapsed() ?: false
            }
        }
    }

    fun scrollFlagsOnChild(@IdRes childIdRes: Int, flags: Int): BoundedMatcher<View, AppBarLayout> {
        return object : BoundedMatcher<View, AppBarLayout>(AppBarLayout::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("appBarLayout child with id $childIdRes with flags equals to $flags")
            }

            override fun matchesSafely(item: AppBarLayout?): Boolean {
                val child = item?.children?.first { it.id == childIdRes } ?: return false
                return (child.layoutParams as AppBarLayout.LayoutParams).scrollFlags == flags
            }
        }
    }
}

object RecyclerViewMatchers {
    fun atPosition(
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
}

object ErrorViewMatchers {
    fun withMessage(message: String): BoundedMatcher<View, ErrorView> {
        return object : BoundedMatcher<View, ErrorView>(ErrorView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("errorView with message equal to $message")
            }

            override fun matchesSafely(item: ErrorView?): Boolean {
                return item?.getMessage() == message
            }
        }
    }
}
