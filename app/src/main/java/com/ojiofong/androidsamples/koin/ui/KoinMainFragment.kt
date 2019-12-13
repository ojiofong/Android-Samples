package com.ojiofong.androidsamples.koin.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.ojiofong.androidsamples.R
import com.ojiofong.androidsamples.koin.model.MyAppManager
import org.koin.android.ext.android.inject

class KoinMainFragment : Fragment() {

    val myAppManager: MyAppManager by inject()

    @BindView(R.id.text_koin)
    lateinit var textView: TextView

    @OnClick(R.id.button_signin_koin)
    fun onClickFakeSignInButton() {
        KoinSignedInFragment.launch(activity)
    }

    companion object {
        private val TAG = KoinMainFragment::class.simpleName
        fun launch(activity: Activity?) {
            val frag = KoinMainFragment()
            (activity as? AppCompatActivity)?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.activity_main_container, frag, TAG)
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_koin_main, container, false)
        ButterKnife.bind(this, rootView)
        textView.text = myAppManager.data
        return rootView
    }

}