package com.tmtc.view.modules.birthdaywedding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dpoints.dpointsmerchant.view.commons.base.BaseFragment

import com.tmtc.R
import com.tmtc.datasource.model.BirthDayWeddingModel
import com.tmtc.view.adapter.BirthdayWeddingAdapter
import kotlinx.android.synthetic.main.fragment_birth_day_recycler_view.*

/**
 * A simple [Fragment] subclass.
 */
class BirthDayRecyclerView : BaseFragment() {
    override val layout=R.layout.fragment_birth_day_recycler_view
    override fun init(view: View) {
        rv_birthday.adapter=BirthdayWeddingAdapter(getbirthday(),context!!)


    }

    private fun getbirthday()= listOf(
        BirthDayWeddingModel("January","Sumit Sunny Cheriyan","27/1/1996"),
        BirthDayWeddingModel("","Lissy Sunny","29/1/1965"),
        BirthDayWeddingModel("","Sunny Cheriyan","30/1/1964 "),
        BirthDayWeddingModel("February","Sumit Sunny Cheriyan","27/1/1996"),
        BirthDayWeddingModel("","Lissy Sunny","29/1/1965"),
        BirthDayWeddingModel("","Sunny Cheriyan","30/1/1964 "),
        BirthDayWeddingModel("","Lissy Sunny","29/1/1965"),
        BirthDayWeddingModel("","Sunny Cheriyan","30/1/1964 "),
        BirthDayWeddingModel("March","Sumit Sunny Cheriyan","27/1/1996"),
        BirthDayWeddingModel("","Lissy Sunny","29/1/1965"),
        BirthDayWeddingModel("","Sunny Cheriyan","30/1/1964 "),
        BirthDayWeddingModel("April","Sumit Sunny Cheriyan","27/1/1996"),
        BirthDayWeddingModel("","Lissy Sunny","29/1/1965"),
        BirthDayWeddingModel("","Sunny Cheriyan","30/1/1964 "),
        BirthDayWeddingModel("","Lissy Sunny","29/1/1965"),
        BirthDayWeddingModel("","Sunny Cheriyan","30/1/1964 ")




    )


}
