package com.smart.id.cards

import androidx.test.core.app.ActivityScenario.launch
import com.google.common.truth.Truth.assertThat
import org.junit.Test



class EditUserUsernameTest {

    @Test
    fun whenUsernameIsValid_returnsTrue() {
        var username = "xyz"
        var activityScenario = launch (EditUserUsername::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username)).isTrue()
        }
    }

    @Test
    fun whenUsernameIsEmpty_returnsFalse() {
        var username = ""
        var activityScenario = launch (EditUserUsername::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username)).isFalse()
        }
    }
}