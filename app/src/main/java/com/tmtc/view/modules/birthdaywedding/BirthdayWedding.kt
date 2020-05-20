package com.tmtc.view.modules.birthdaywedding

import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.dpoints.dpointsmerchant.view.commons.base.BaseFragment
import com.tmtc.R
import com.tmtc.view.adapter.TabFragmentAdapter
import kotlinx.android.synthetic.main.fragment_birthday_wedding.*


/**
 * A simple [Fragment] subclass.
 */
class BirthdayWedding : BaseFragment() {
    override val layout = R.layout.fragment_birthday_wedding
    private var indicatorWidth = 0

    override fun init(view: View) {
        val adapter = TabFragmentAdapter(fragmentManager)
        adapter.addFragment(BirthDayRecyclerView(), "BirthDay")
        adapter.addFragment(BirthDayRecyclerView(), "Wedding")
        viewPager.adapter = adapter
        tab.setupWithViewPager(viewPager)

        //Determine indicator width at runtime
        tab.post(Runnable {
            indicatorWidth = tab.getWidth() / tab.getTabCount()
            //Assign new width
            val indicatorParams =
               indicator.getLayoutParams() as FrameLayout.LayoutParams
            indicatorParams.width = indicatorWidth
            indicator.setLayoutParams(indicatorParams)
        })

       viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            //To move the indicator as the user scroll, we will need the scroll offset values
//positionOffset is a value from [0..1] which represents how far the page has been scrolled
//see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener
            override fun onPageScrolled(
                i: Int,
                positionOffset: Float,
                positionOffsetPx: Int
            ) {
                val params =
                   indicator.getLayoutParams() as FrameLayout.LayoutParams
                //Multiply positionOffset with indicatorWidth to get translation
                val translationOffset = (positionOffset + i) * indicatorWidth
                params.leftMargin = translationOffset.toInt()
                indicator.setLayoutParams(params)
            }

            override fun onPageSelected(i: Int) {}
            override fun onPageScrollStateChanged(i: Int) {}
        })


    }
}
