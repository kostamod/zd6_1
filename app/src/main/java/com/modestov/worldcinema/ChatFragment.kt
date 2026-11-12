package com.modestov.worldcinema

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class ChatFragment : Fragment(R.layout.fragment_page) {
    companion object {
        fun create(room: String) = ChatFragment().apply {
            arguments = Bundle().apply { putString("room", room) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val page = view.findViewById<LinearLayout>(R.id.page)
        val host = requireActivity() as MainActivity
        val room = arguments?.getString("room").orEmpty()
        val messages = host.messages.getOrPut(room.lowercase()) { mutableListOf() }
        page.heading(room)
        page.action("← Назад") { parentFragmentManager.popBackStack() }
        val body = page.scroller()
        val input = page.input("Сообщение")
        fun render() {
            body.removeAllViews()
            if (messages.isEmpty()) body.label("Сообщений пока нет.")
            messages.forEach { body.label(it) }
        }
        page.action("Добавить") {
            val message = input.text.toString().trim()
            if (message.isNotEmpty()) {
                messages.add(message)
                input.text.clear()
                render()
            }
        }
        render()
    }
}
