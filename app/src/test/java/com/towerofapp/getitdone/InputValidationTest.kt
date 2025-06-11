package com.towerofapp.getitdone

import com.towerofapp.getitdone.ui.MainActivity
import com.towerofapp.getitdone.util.InputValidator
import junit.framework.TestCase.assertFalse
import org.junit.Test

class InputValidationTest {

    @Test
    fun inputValidator_returnsFalseWhenEmpty(){
        val result = InputValidator.isInputValid("")
        assertFalse(result)
    }

    @Test
    fun inputValidator_returnsFalseWhenNull(){
        val result = InputValidator.isInputValid(null)
        assertFalse(result)
    }

    @Test
    fun inputValidator_returnsFalseWhenWhiteSpace(){
        val result = InputValidator.isInputValid("     ")
        assertFalse(result)
    }
}