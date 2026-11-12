package com.modestov.worldcinema

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment(R.layout.fragment_page) {
    companion object {
        fun create(id: String) = DetailsFragment().apply {
            arguments = Bundle().apply { putString("movieId", id) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val page = view.findViewById<LinearLayout>(R.id.page)
        val host = requireActivity() as MainActivity
        val movie = movies.firstOrNull { it.id == arguments?.getString("movieId") } ?: return
        page.heading(movie.title)
        page.action("← Назад") { parentFragmentManager.popBackStack() }
        val poster = ImageView(requireContext()).apply {
            contentDescription = movie.title
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        page.addView(poster, LinearLayout.LayoutParams(-1, requireContext().dp(100)))
        loadPoster(movie.poster, poster)
        page.label(movie.year)
        page.label(movie.description)
        fun favoriteLabel() =
            if (movie.id in host.favorites) "♥ Убрать из избранного" else "♡ В избранное"
        val button = page.action(favoriteLabel()) {}
        button.setOnClickListener {
            if (!host.favorites.add(movie.id)) host.favorites.remove(movie.id)
            button.text = favoriteLabel()
        }
        page.action("Обсуждение") { host.show(ChatFragment.create(movie.title)) }
    }
}
