package com.modestov.worldcinema

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class MoviesFragment : Fragment(R.layout.fragment_page) {
    companion object {
        fun favorites() = MoviesFragment().apply {
            arguments = Bundle().apply { putBoolean("favorites", true) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val page = view.findViewById<LinearLayout>(R.id.page)
        val host = requireActivity() as MainActivity
        val favorite = arguments?.getBoolean("favorites") == true
        page.heading(if (favorite) "Избранное" else "Новое")
        page.action("← Меню") { host.show(MenuFragment(), false) }
        val items = if (favorite) movies.filter { it.id in host.favorites } else movies
        if (items.isEmpty()) page.label("Добавь фильм из каталога.")
        items.forEach { movie -> page.movieCard(movie) { host.show(DetailsFragment.create(movie.id)) } }
    }
}
