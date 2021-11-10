package com.smart.id.cards

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AdminLoginTest {

    @Test
    fun whenUsernameAndPasswordAreValid() {
        var username = "xyz"
        var password = "123"
        val activityScenario = launch(AdminLogin::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,password)).isEqualTo(true)
        }

    }
    @Test
    fun whenUsernameAndPasswordAreEmpty() {
        var username = ""
        var password = ""
        val activityScenario = launch(AdminLogin::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,password)).isEqualTo(false)
        }

    }
    @Test
    fun whenUsernameEmpty() {
        var username = ""
        var password = "123"
        val activityScenario = launch(AdminLogin::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,password)).isEqualTo(false)
        }

    }
    @Test
    fun whenPasswordEmpty() {
        var username = "xyz"
        var password = ""
        val activityScenario = launch(AdminLogin::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,password)).isEqualTo(false)
        }

    }
}