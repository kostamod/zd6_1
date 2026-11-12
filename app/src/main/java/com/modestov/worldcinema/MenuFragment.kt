package com.modestov.worldcinema

import android.os.Bundle
import android.view.View
import android.view.Gravity
import android.widget.*
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.fragment.app.Fragment

class MenuFragment : Fragment(R.layout.fragment_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val page = view.findViewById<LinearLayout>(R.id.page)
        val host = requireActivity() as MainActivity
        val compact = resources.configuration.screenWidthDp < 300
        if (!compact) {
            page.heading("WorldCinema")
        }
        val grid = GridLayout(requireContext()); grid.columnCount = 2
        page.addView(grid, LinearLayout.LayoutParams(-1, -2))
        fun item(icon: Int, title: String, next: () -> Unit) {
            val box = LinearLayout(requireContext()); box.orientation = LinearLayout.VERTICAL; box.gravity = Gravity.CENTER
            val b = ImageView(requireContext()); b.setImageResource(icon); b.contentDescription = title
            val inset = requireContext().dp(if (compact) 10 else 20)
            b.setPadding(inset,inset,inset,inset)
            b.background = GradientDrawable().apply { shape = GradientDrawable.OVAL; setColor(BLUE) }
            val size = requireContext().dp(if (compact) 44 else 72)
            box.addView(b, LinearLayout.LayoutParams(size, size))
            box.label(title, if (compact) 10f else 14f).gravity = Gravity.CENTER
            box.setPadding(0, requireContext().dp(if (compact) 4 else 18), 0, requireContext().dp(if (compact) 0 else 12))
            grid.addView(box, GridLayout.LayoutParams().apply { width = 0; columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f) })
            box.setOnClickListener { next() }; b.setOnClickListener { next() }
        }
        item(R.drawable.ic_discuss, "Обсуждения") { host.show(DiscussionFragment()) }
        item(R.drawable.ic_movies, "Фильмы") { host.show(MoviesFragment()) }
        item(R.drawable.ic_heart, "Избранное") { host.show(MoviesFragment.favorites()) }
        page.action("Выйти") { host.show(LoginFragment(), false) }
    }
}
