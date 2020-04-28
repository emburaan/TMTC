package com.tmtc.view.modules.dashboard

import android.view.View

import androidx.fragment.app.Fragment

import com.dpoints.dpointsmerchant.view.commons.base.BaseActivity
import com.tmtc.R
import com.tmtc.utilities.FragmentHelper

import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.toolbar_main.*


class DashBoardActivity :BaseActivity() {
    override val layout=R.layout.activity_dash_board

    override fun init() {
FragmentHelper.replaceFragmentSlideRightEnter(HomeFragment(),this,R.id.container,"Home")
    }




}
