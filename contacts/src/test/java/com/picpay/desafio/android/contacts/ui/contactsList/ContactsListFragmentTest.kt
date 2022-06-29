package com.picpay.desafio.android.contacts.ui.contactsList

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.di.ContactsModule
import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import com.picpay.desafio.android.core.di.CoreModule
import com.picpay.desafio.android.core.test.espresso.checkAppBarLayoutIsCollapsed
import com.picpay.desafio.android.core.test.espresso.checkRecyclerViewItem
import com.picpay.desafio.android.core.test.espresso.checkViewVisibility
import com.picpay.desafio.android.core.test.espresso.collapseAppBarLayout
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

//Todo: Mover para sharedTest
@RunWith(AndroidJUnit4::class)
class ContactsListFragmentTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun givenSuccessOnGetContacts_shouldShowItems() {
        success {
            checkViewVisibility(id = R.id.loading_view, isVisible = false)
            checkViewVisibility(id = R.id.error_view, isVisible = false)
            checkFirstItemOnContactList(matcher = allOf(withText("fullName"), isDisplayed()))
            checkFirstItemOnContactList(matcher = allOf(withText("@username"), isDisplayed()))
        }
    }

    @Test
    fun givenErrorOnGetContacts_shouldShowError() {
        error {
            checkViewVisibility(id = R.id.loading_view, isVisible = false)
            checkViewVisibility(id = R.id.error_view, isVisible = true)
        }
    }

    @Test
    fun givenSuccessOnGetContacts_whenFragmentIsRecreated_shouldSaveAppBarState() {
        success {
            onView(withId(R.id.app_bar_layout)).perform(collapseAppBarLayout())
            recreate()
            onView(withId(R.id.app_bar_layout)).check(matches(checkAppBarLayoutIsCollapsed()))
        }
    }

    private inline fun success(
        block: FragmentScenario<ContactsListFragment>.() -> Unit,
    ) {
        coEvery { contactsRepository.getAllContacts() } returns Result.success(contacts)
        val scenario = launchFragmentInContainer<ContactsListFragment>(themeResId = themeResId)
        scenario.block()
    }

    private inline fun error(
        block: FragmentScenario<ContactsListFragment>.() -> Unit,
    ) {
        coEvery { contactsRepository.getAllContacts() } returns Result.failure(RuntimeException())
        val scenario = launchFragmentInContainer<ContactsListFragment>(themeResId = themeResId)
        scenario.block()
    }

    private fun checkFirstItemOnContactList(matcher: Matcher<View>) {
        checkRecyclerViewItem(
            id = R.id.contacts_list,
            position = 0,
            withMatcher = matcher
        )
    }

    @Before
    fun setup() {
        setupKoin()
    }

    private fun setupKoin() {
        loadKoinModules(listOf(CoreModule, ContactsModule))
        loadKoinModules(module { single { contactsRepository } })
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    private val contactsRepository = mockk<ContactsRepository>()
    private val contacts = listOf(Contact(id = 0, "imgUrl", "fullName", "username"))
    private val themeResId = com.picpay.desafio.android.designsystem.R.style.PicPayAppTheme
}