package com.picpay.desafio.android.contacts.ui.contactsList

import android.content.Context
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.di.ContactsModule
import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.contacts.domain.usecase.GetAllContacts
import com.picpay.desafio.android.core.di.CoreModule
import com.picpay.desafio.android.core.test.BaseTest
import com.picpay.desafio.android.core.test.espresso.AppBarLayoutMatchers
import com.picpay.desafio.android.core.test.espresso.ErrorViewMatchers
import com.picpay.desafio.android.core.test.espresso.RecyclerViewMatchers
import com.picpay.desafio.android.core.test.espresso.checkView
import com.picpay.desafio.android.core.test.espresso.collapseAppBarLayout
import com.picpay.desafio.android.core.test.espresso.performActionOnView
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.spyk
import kotlinx.coroutines.flow.MutableStateFlow
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class ContactsListFragmentTest : BaseTest() {

    private val context: Context by lazy { ApplicationProvider.getApplicationContext() }
    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true) {
        every { this@mockk.get<List<ContactItemUiState>>(any()) } returns null
    }
    private val getAllContacts: GetAllContacts = mockk()
    private val contactsListViewModel =
        spyk(ContactsListViewModel(savedStateHandle, getAllContacts))
    private val themeResId = com.picpay.desafio.android.designsystem.R.style.PicPayAppTheme

    @Before
    fun setup() {
        setupKoin()
    }

    private fun setupKoin() {
        loadKoinModules(listOf(CoreModule, ContactsModule))
        val mockModule = module {
            single { savedStateHandle }
            single { getAllContacts }
            viewModel { contactsListViewModel }
        }
        loadKoinModules(mockModule)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun givenSuccessOnGetContacts_shouldShowItems() {
        success {
            checkView(withId = R.id.loading_view, matcher = not(isDisplayed()))
            checkView(withId = R.id.error_view, matcher = not(isDisplayed()))
            checkView(withId = R.id.contacts_list, matcher = isDisplayed())
            checkFirstItemOnContactList(matcher = allOf(withText("fullName"), isDisplayed()))
            checkFirstItemOnContactList(matcher = allOf(withText("@username"), isDisplayed()))
        }
    }

    @Test
    fun givenSuccessOnGetContacts_whenFragmentIsRecreated_shouldSaveAppBarState() {
        success {
            performActionOnView(withId = R.id.app_bar_layout, viewAction = collapseAppBarLayout())
            recreate()
            checkView(withId = R.id.app_bar_layout, AppBarLayoutMatchers.isCollapsed())
        }
    }

    @Test
    fun givenSuccessOnGetContacts_shouldEnableScroll() {
        success {
            checkView(
                withId = R.id.app_bar_layout,
                matcher = AppBarLayoutMatchers.scrollFlagsOnChild(
                    childIdRes = R.id.collapsing_toolbar_layout,
                    flags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                )
            )
        }
    }

    @Test
    fun givenErrorOnGetContacts_shouldShowError() {
        error {
            checkView(withId = R.id.loading_view, matcher = not(isDisplayed()))
            checkView(withId = R.id.error_view, matcher = isDisplayed())
            checkView(withId = R.id.contacts_list, matcher = not(isDisplayed()))
            checkView(
                withId = R.id.error_view,
                matcher = ErrorViewMatchers.withMessage(context.getString(R.string.error))
            )
        }
    }

    @Test
    fun givenErrorOnGetContacts_shouldDisableScroll() {
        error {
            checkView(
                withId = R.id.app_bar_layout,
                matcher = AppBarLayoutMatchers.scrollFlagsOnChild(
                    childIdRes = R.id.collapsing_toolbar_layout,
                    flags = SCROLL_FLAG_NO_SCROLL
                )
            )
        }
    }

    @Test
    fun givenLoadingOnGetContacts_shouldShowLoading() {
        loading {
            checkView(withId = R.id.loading_view, matcher = isDisplayed())
            checkView(withId = R.id.error_view, matcher = not(isDisplayed()))
            checkView(withId = R.id.contacts_list, matcher = not(isDisplayed()))
        }
    }

    @Test
    fun givenLoadingOnGetContacts_shouldDisableScroll() {
        loading {
            checkView(
                withId = R.id.app_bar_layout,
                matcher = AppBarLayoutMatchers.scrollFlagsOnChild(
                    childIdRes = R.id.collapsing_toolbar_layout,
                    flags = SCROLL_FLAG_NO_SCROLL
                )
            )
        }
    }

    private inline fun success(
        block: FragmentScenario<ContactsListFragment>.() -> Unit,
    ) {
        coEvery { getAllContacts() } returns Result.success(ContactsMock)
        val scenario = launchFragmentInContainer<ContactsListFragment>(themeResId = themeResId)
        scenario.block()
    }

    private inline fun error(
        block: FragmentScenario<ContactsListFragment>.() -> Unit,
    ) {
        coEvery { getAllContacts() } returns Result.failure(RuntimeException())
        val scenario = launchFragmentInContainer<ContactsListFragment>(themeResId = themeResId)
        scenario.block()
    }

    private inline fun loading(
        block: FragmentScenario<ContactsListFragment>.() -> Unit,
    ) {
        coEvery { contactsListViewModel.uiState } returns MutableStateFlow(
            ContactsListUiState(isLoading = true))
        coEvery { contactsListViewModel.refreshContacts() } just runs
        val scenario = launchFragmentInContainer<ContactsListFragment>(themeResId = themeResId)
        scenario.block()
    }

    private fun checkFirstItemOnContactList(matcher: Matcher<View>) {
        checkView(
            withId = R.id.contacts_list,
            matcher = RecyclerViewMatchers.atPosition(
                position = 0,
                itemMatcher = hasDescendant(matcher)
            )
        )
    }
}

private val ContactsMock = listOf(Contact(id = 0, "imgUrl", "fullName", "username"))