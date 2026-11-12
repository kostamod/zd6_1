package com.modestov.worldcinema

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val host = requireActivity() as MainActivity
        view.findViewById<Button>(R.id.login).setOnClickListener {
            host.show(MenuFragment(), false)
        }
    }
}
