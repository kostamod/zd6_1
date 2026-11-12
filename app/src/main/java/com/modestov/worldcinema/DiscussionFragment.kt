package com.modestov.worldcinema

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class DiscussionFragment : Fragment(R.layout.fragment_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val page = view.findViewById<LinearLayout>(R.id.page)
        val host = requireActivity() as MainActivity
        page.heading("Обсуждения")
        page.action("← Главное меню") { host.show(MenuFragment(), false) }
        page.label("Учебные локальные обсуждения на этом устройстве",12f,android.graphics.Color.GRAY)
        listOf("Lucifer","The Witcher","LOST","The Magicians").forEach { title ->
            val row = page.label(title, 20f); row.setPadding(10,requireContext().dp(22),10,requireContext().dp(22))
            row.setOnClickListener { host.show(ChatFragment.create(title)) }
            page.addView(View(requireContext()).apply { setBackgroundColor(BLUE) },LinearLayout.LayoutParams(-1,requireContext().dp(1)))
        }
    }
}
