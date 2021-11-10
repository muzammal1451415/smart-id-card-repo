package com.smart.id.cards

import androidx.test.core.app.ActivityScenario
import com.google.common.truth.Truth
import org.junit.Test


class UpdateUserTest {


    @Test
    fun whenAllFieldsAreProperlyFilled_returnsTrue() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = "xyz"
        val activityScenario = ActivityScenario.launch(UpdateUser::class.java)
        activityScenario.onActivity {
            Truth.assertThat(it.isAllFieldsValid(username, firstName, lastName, emiratesID, password)).isEqualTo(true)
        }
    }

    @Test
    fun whenUsernameIsEmpty_returnsFalse() {
        var username:String = ""
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = "xyz"
        val activityScenario = ActivityScenario.launch(UpdateUser::class.java)
        activityScenario.onActivity {
            Truth.assertThat(it.isAllFieldsValid(username, firstName, lastName, emiratesID, password)).isEqualTo(false)
        }
    }

    @Test
    fun whenFirstNameIsEmpty_returnsFalse() {
        var username:String = "xys"
        var firstName:String = ""
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = "xyz"

        val activityScenario = ActivityScenario.launch(UpdateUser::class.java)
        activityScenario.onActivity {
            Truth.assertThat(it.isAllFieldsValid(username, firstName, lastName, emiratesID, password)).isEqualTo(false)
        }
    }

    @Test
    fun whenLastNameIsEmpty_returnsFalse() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = ""
        var emiratesID:String = "xyz"
        var password:String = "xyz"

        val activityScenario = ActivityScenario.launch(UpdateUser::class.java)
        activityScenario.onActivity {
            Truth.assertThat(it.isAllFieldsValid(username, firstName, lastName, emiratesID, password)).isEqualTo(false)
        }
    }

    @Test
    fun whenEmiratesIDIsEmpty_returnsFalse() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = ""
        var password:String = "xyz"
        val activityScenario = ActivityScenario.launch(UpdateUser::class.java)
        activityScenario.onActivity {
            Truth.assertThat(it.isAllFieldsValid(username, firstName, lastName, emiratesID, password)).isEqualTo(false)
        }
    }

    @Test
    fun whenPasswordIsEmpty_returnsFalse() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = ""
        val activityScenario = ActivityScenario.launch(UpdateUser::class.java)
        activityScenario.onActivity {
            Truth.assertThat(it.isAllFieldsValid(username, firstName, lastName, emiratesID, password)).isEqualTo(false)
        }
    }


}