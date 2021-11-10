package com.smart.id.cards

import androidx.test.core.app.ActivityScenario.launch
import com.google.common.truth.Truth.assertThat
import org.junit.Test



class DeleteUserTest {

    @Test
    fun isWhenUsernameIsValid_returnsTrue() {
        var username = "xyz"
        var activityScenario = launch (DeleteUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username)).isTrue()
        }
    }

    @Test
    fun isWhenUsernameIsEmpty_returnsFalse() {
        var username = ""
        var activityScenario = launch (DeleteUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username)).isFalse()
        }
    }
}