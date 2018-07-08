package com.ojiofong.androidsamples.koin.ui

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.ojiofong.androidsamples.R
import com.ojiofong.androidsamples.koin.model.MyUserManager
import org.koin.android.ext.android.inject

class KoinSignedInFragment : Fragment() {

    val myUserManager: MyUserManager by inject()

    @BindView(R.id.text_koin)
    lateinit var textView: TextView

    companion object {
        private val TAG = KoinSignedInFragment::class.simpleName
        fun launch(activity: Activity?) {
            val frag = KoinSignedInFragment()
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
        hideUnwantedViews(rootView)
        textView.apply {
            text = ""
            append(myUserManager.data)
            append("\n")
            append(myUserManager.appManager.data)
            append("\n")
            append(myUserManager.displayStuff)
        }
        return rootView
    }

    private fun hideUnwantedViews(rootView: View?) {
        rootView?.findViewById<View>(R.id.edit_text_password_koin)?.visibility = View.GONE
        rootView?.findViewById<View>(R.id.edit_text_username_koin)?.visibility = View.GONE
        rootView?.findViewById<View>(R.id.button_signin_koin)?.visibility = View.GONE
    }

}