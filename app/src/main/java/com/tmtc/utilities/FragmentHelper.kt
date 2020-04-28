package com.tmtc.utilities

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.tmtc.R

object FragmentHelper {

    fun replaceFragmentSlideRightEnter(
        fragment: Fragment,
        activity: AppCompatActivity,
        @IdRes containerId: Int,
        tag: String
    ) {

        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_right_enter,
                0
            )
            .replace(
                containerId,
                fragment,
                tag
            )
            .commit()


    }


    fun addFragmentSlideRightEnterToExit(
        fragment: Fragment,
        activity: FragmentActivity,
        @IdRes containerId: Int,
        tag: String
    ) {

        activity.supportFragmentManager
            .beginTransaction().setCustomAnimations(
                R.anim.slide_right_enter,
                0, 0, R.anim.slide_right_exit
            ).add(
                containerId,
                fragment,
                tag
            ).addToBackStack(
                null
            ).commit()

    }
}