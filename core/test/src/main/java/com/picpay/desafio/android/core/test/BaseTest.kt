package com.picpay.desafio.android.core.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
}