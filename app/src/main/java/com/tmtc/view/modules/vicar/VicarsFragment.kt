package com.tmtc.view.modules.vicar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dpoints.dpointsmerchant.utilities.OnItemClickListener
import com.dpoints.dpointsmerchant.view.commons.base.BaseFragment

import com.tmtc.R
import com.tmtc.datasource.model.VicarModel
import com.tmtc.view.adapter.VicarAdapter
import kotlinx.android.synthetic.main.fragment_blank.*

/**
 * A simple [Fragment] subclass.
 */
class VicarsFragment : BaseFragment(),OnItemClickListener {
    override val layout: Int =R.layout.fragment_blank
    override fun init(view: View) {
        rv_vicar.adapter = VicarAdapter(getvicar(),context!!,this)

    }

    private fun getvicar()= listOf(
        VicarModel(R.drawable.allan_achen,"Allan T Samuel","+917021349665"),
        VicarModel(R.drawable.allan_achen,"Allan T Samuel","+917021349665"),
        VicarModel(R.drawable.allan_achen,"Allan T Samuel","+917021349665"),
        VicarModel(R.drawable.allan_achen,"Allan T Samuel","+917021349665"),
        VicarModel(R.drawable.allan_achen,"Allan T Samuel","+917021349665"),
        VicarModel(R.drawable.allan_achen,"Allan T Samuel","+917021349665")






    )

    override fun onItemClick(index: Int, adapter: Int) {

    }


}
