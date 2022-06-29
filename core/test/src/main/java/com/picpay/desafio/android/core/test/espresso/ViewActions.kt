package com.picpay.desafio.android.core.test.espresso

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.appbar.AppBarLayout
import org.hamcrest.Matcher

fun performActionOnView(@IdRes withId: Int, viewAction: ViewAction) {
    onView(withId(withId)).perform(viewAction)
}

fun collapseAppBarLayout(): ViewAction = object : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return isAssignableFrom(AppBarLayout::class.java)
    }

    override fun getDescription(): String {
        return "collapse AppBarLayout"
    }

    override fun perform(uiController: UiController, view: View) {
        val appBarLayout = view as AppBarLayout
        appBarLayout.setExpanded(false)
        uiController.loopMainThreadUntilIdle()
    }
}