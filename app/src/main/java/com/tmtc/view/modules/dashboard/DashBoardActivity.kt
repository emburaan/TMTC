package com.tmtc.view.modules.dashboard

import android.view.View

import androidx.fragment.app.Fragment

import com.dpoints.dpointsmerchant.view.commons.base.BaseActivity
import com.tmtc.R

import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.toolbar_main.*


class DashBoardActivity :BaseActivity() {
    override val layout=R.layout.activity_dash_board

    override fun init() {
        toolbar_back.setImageResource(R.drawable.ic_hamburger)
        toolbar_back.visibility=View.INVISIBLE
        toolbar.visibility=View.INVISIBLE
        toolbar_title.setText(getString(R.string.dashboard))
        applyChanges(HomeFragment(),"Home")
    }

    fun applyChanges(fr:Fragment,tag:String) {
        supportFragmentManager.beginTransaction().add(R.id.container,fr).commit()


    }


}
