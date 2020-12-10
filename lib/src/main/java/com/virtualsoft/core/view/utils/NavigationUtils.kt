package com.virtualsoft.core.view.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections

object NavigationUtils {

    fun NavController.navigateSafe(sourceDestination: Int, targetDestination: Int) {
        if (this.currentDestination?.id == sourceDestination)
            this.navigate(targetDestination)
    }

    fun NavController.navigateSafe(sourceDestination: Int, action: NavDirections) {
        if (this.currentDestination?.id == sourceDestination)
            this.navigate(action)
    }
}