package com.smart.id.cards


import android.net.Uri
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.android.synthetic.main.activity_add_user.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddUserTest {

    @Test
    fun whenAllFieldsAreProperlyFilled_returnsTrue() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = "xyz"
        var filePath:String = "filepath"
        val activityScenario = launch(AddUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,firstName,lastName,emiratesID,password,filePath)).isEqualTo(true)
        }
    }

    @Test
    fun whenUsernameIsEmpty_returnsFalse() {
        var username:String = ""
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = "xyz"
        var filePath:String = "filepath"
        val activityScenario = launch(AddUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,firstName,lastName,emiratesID,password,filePath)).isEqualTo(false)
        }
    }

    @Test
    fun whenFirstNameIsEmpty_returnsFalse() {
        var username:String = "xys"
        var firstName:String = ""
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = "xyz"
        var filePath:String = "filepath"
        val activityScenario = launch(AddUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,firstName,lastName,emiratesID,password,filePath)).isEqualTo(false)
        }
    }

    @Test
    fun whenLastNameIsEmpty_returnsFalse() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = ""
        var emiratesID:String = "xyz"
        var password:String = "xyz"
        var filePath:String = "filepath"
        val activityScenario = launch(AddUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,firstName,lastName,emiratesID,password,filePath)).isEqualTo(false)
        }
    }

    @Test
    fun whenEmiratesIDIsEmpty_returnsFalse() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = ""
        var password:String = "xyz"
        var filePath:String = "filepath"
        val activityScenario = launch(AddUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,firstName,lastName,emiratesID,password,filePath)).isEqualTo(false)
        }
    }

    @Test
    fun whenPasswordIsEmpty_returnsFalse() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = ""
        var filePath:String = "filepath"
        val activityScenario = launch(AddUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,firstName,lastName,emiratesID,password,filePath)).isEqualTo(false)
        }
    }

    @Test
    fun whenFilePathIsEmpty_returnsFalse() {
        var username:String = "xyz"
        var firstName:String = "xyz"
        var lastName:String = "xyz"
        var emiratesID:String = "xyz"
        var password:String = "xyz"
        var filePath:String = ""
        val activityScenario = launch(AddUser::class.java)
        activityScenario.onActivity {
            assertThat(it.isAllFieldsValid(username,firstName,lastName,emiratesID,password,filePath)).isEqualTo(false)
        }
    }
}