package com.picpay.desafio.android.contacts

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.picpay.desafio.android.core.commons.isCollapsed
import com.picpay.desafio.android.core.commons.isExpanded
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers


//Todo: Mover para core

fun readFileAsText(context: Context, name: String): String =
    context.classLoader.getResource(name).readText()

inline fun <reified T> readFile(context: Context, name: String): T =
    Gson().fromJson(readFileAsText(context, name), T::class.java)

val errorResponse get() = MockResponse().setResponseCode(404)

fun checkViewVisibility(@IdRes id: Int, isVisible: Boolean) {
    val match =
        if (isVisible) ViewMatchers.isDisplayed() else Matchers.not(ViewMatchers.isDisplayed())
    Espresso.onView(ViewMatchers.withId(id))
        .check(ViewAssertions.matches(match))
}

fun collapseAppBarLayout(): ViewAction = object : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return isAssignableFrom(AppBarLayout::class.java)
    }

    override fun getDescription(): String {
        return "Collapse App Bar Layout"
    }

    override fun perform(uiController: UiController, view: View) {
        val appBarLayout = view as AppBarLayout
        appBarLayout.setExpanded(false)
        uiController.loopMainThreadUntilIdle()
    }
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