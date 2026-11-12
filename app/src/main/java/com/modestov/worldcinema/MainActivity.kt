package com.modestov.worldcinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    val favorites = mutableSetOf<String>()
    val messages = mutableMapOf<String, MutableList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) show(LoginFragment(), false)
    }

    fun show(fragment: Fragment, back: Boolean = true) {
        if (!back) supportFragmentManager.popBackStackImmediate(
            null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        val transaction = supportFragmentManager.beginTransaction().replace(R.id.content, fragment)
        if (back) transaction.addToBackStack(null)
        transaction.commit()
    }
}
