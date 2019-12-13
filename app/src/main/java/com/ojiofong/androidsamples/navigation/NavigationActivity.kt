package com.ojiofong.androidsamples.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ojiofong.androidsamples.R

class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}

class NavDestinationFragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_navigation_component, container, false)
        val textView = rootView.findViewById<TextView>(R.id.text1)
        textView.text = "NavDestinationFragment1"
        rootView.findViewById<Button>(R.id.button1).setOnClickListener {

            val data = "Awesome!!! Arg passed to BlankFragment"
            val action = NavDestinationFragment1Directions
                    .actionNavDestinationFragment1ToBlankFragment(data)

            findNavController().navigate(action)
        }
        return rootView
    }
}

class BlankFragment : Fragment() {

    private val args by navArgs<BlankFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)

        val displayText = args.displayText

        rootView.findViewById<TextView>(R.id.text_view_blank).apply { text = displayText }

        return rootView
    }
}
