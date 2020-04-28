package com.tmtc.view.modules.dashboard

import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.dpoints.dpointsmerchant.utilities.OnItemClickListener
import com.dpoints.dpointsmerchant.view.commons.base.BaseFragment

import com.tmtc.R
import com.tmtc.datasource.model.Item
import com.tmtc.datasource.model.ItemDashboardImage
import com.tmtc.utilities.FragmentHelper
import com.tmtc.view.adapter.DashboardContentAdapter
import com.tmtc.view.adapter.ImageAdapter
import com.tmtc.view.modules.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(),OnItemClickListener {
    override val layout: Int =R.layout.fragment_home
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var linearLayout: LinearLayout
    override fun init(view: View) {
        rv_image_dashboard.adapter= ImageAdapter(getImage(),context!!,this)
        rv_content.adapter= DashboardContentAdapter(getContent(),this)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_image_dashboard)


        login_btn.setOnClickListener {
    FragmentHelper.addFragmentSlideRightEnterToExit(
        LoginFragment(),
        activity!!,
        R.id.container,
        "Login"
    )
}


    }
    private fun getContent() = listOf(
        Item("Presedential Info/ \nOld Vicars",R.drawable.ic_priest),
        Item("BirthDays/ \n Wedding ",R.drawable.ic_cake),
        Item("Edavka Deepam",R.drawable.ic_prayer),
        Item("Parish directory",R.drawable.ic_directory)


    )

    private fun getImage()= listOf(
        ItemDashboardImage("https://cdn.pixabay.com/photo/2020/02/10/18/52/sunset-4837406_960_720.jpg"),
        ItemDashboardImage("https://cdn.pixabay.com/photo/2020/03/30/16/26/helsinki-4984737_960_720.jpg")


    )

    private fun getDrawer()= listOf(
        Item("Setting",R.drawable.ic_support),
        Item("About Us",R.drawable.ic_about_us),
        Item("How to use App",R.drawable.ic_question),
        Item("Login",R.drawable.ic_login)


    )


    override fun onItemClick(index: Int, adapter: Int) {

    }


}
