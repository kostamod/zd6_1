package com.modestov.worldcinema

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold

class MoviesFragment : Fragment() {
    companion object {
        fun favorites() = MoviesFragment().apply {
            arguments = Bundle().apply { putBoolean("favorites", true) }
        }
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val host = requireActivity() as MainActivity
        val favorite = arguments?.getBoolean("favorites") == true
        val items = if (favorite) movies.filter { it.id in host.favorites } else movies

        return ComposeView(requireContext()).apply {
            id = R.id.movie_catalog
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val listState = rememberScalingLazyListState(initialCenterItemIndex = 0)
                MaterialTheme {
                    Scaffold(positionIndicator = {
                        PositionIndicator(scalingLazyListState = listState)
                    }) {
                        ScalingLazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = listState,
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            item {
                                AndroidView(
                                    modifier = Modifier.fillMaxWidth(),
                                    factory = { context ->
                                        LinearLayout(context).apply {
                                            orientation = LinearLayout.VERTICAL
                                            heading(if (favorite) "Избранное" else "Новое")
                                            action("← Меню") { host.show(MenuFragment(), false) }
                                            if (items.isEmpty()) label("Добавь фильм из каталога.")
                                        }
                                    }
                                )
                            }
                            items(items.size, key = { items[it].id }) { index ->
                                val movie = items[index]
                                AndroidView(
                                    modifier = Modifier.fillMaxWidth(),
                                    factory = { context ->
                                        LinearLayout(context).apply {
                                            orientation = LinearLayout.VERTICAL
                                            movieCard(movie) { host.show(DetailsFragment.create(movie.id)) }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
