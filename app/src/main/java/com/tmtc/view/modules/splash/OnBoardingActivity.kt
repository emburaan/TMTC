package com.tmtc.view.modules.splash

import android.content.Intent
import android.os.Handler
import android.view.View
import com.dpoints.dpointsmerchant.view.commons.base.BaseActivity
import com.tmtc.R
import com.tmtc.view.modules.dashboard.Dashboard

class OnBoardingActivity : BaseActivity() {
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    override val layout: Int = R.layout.activity_on_boarding
    override fun init() {
        handler = Handler()
        runnable = Runnable {
            val intent = Intent(this@OnBoardingActivity, Dashboard::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        handler?.postDelayed(runnable,1000)
    }
}







