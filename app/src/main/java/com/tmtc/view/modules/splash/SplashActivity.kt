package com.tmtc.view.modules.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dpoints.dpointsmerchant.view.commons.base.BaseActivity
import com.tmtc.R
import com.tmtc.view.modules.dashboard.DashBoardActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash_logo.animate().scaleY(0.8F).duration = 1800
        splash_logo.animate().scaleX(0.8F).duration = 1800
       Handler().postDelayed({
           startActivity(Intent(this,DashBoardActivity::class.java))

       },1800

       )
    }
}
