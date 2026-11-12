package com.modestov.worldcinema

import android.graphics.Color
import android.view.Gravity
import android.widget.*
import com.squareup.picasso.Picasso

fun loadPoster(url: String, image: ImageView) {
    if (url.isBlank() || url == "N/A") { Picasso.get().cancelRequest(image); image.setImageResource(R.drawable.bill_up_close); return }
    Picasso.get().load(url).placeholder(R.drawable.bill_up_close).error(R.drawable.bill_up_close)
        .fit().centerCrop().into(image)
}
fun LinearLayout.movieCard(movie: Movie, click: () -> Unit) {
    val compact = resources.configuration.screenWidthDp < 300
    val card = FrameLayout(context)
    card.background = context.getDrawable(R.drawable.rounded_card); card.clipToOutline = true
    val image = ImageView(context); image.scaleType = ImageView.ScaleType.CENTER_CROP; image.contentDescription = movie.title
    card.addView(image, FrameLayout.LayoutParams(-1, -1)); loadPoster(movie.poster, image)
    val shade = TextView(context); shade.setBackgroundColor(0x33000000); card.addView(shade, FrameLayout.LayoutParams(-1,-1))
    val play = TextView(context); play.text = "▶"; play.textSize = if (compact) 24f else 36f; play.setTextColor(0xFFD5323D.toInt()); play.gravity = Gravity.CENTER
    card.addView(play, FrameLayout.LayoutParams(-1,-1))
    val title = TextView(context); title.text = "${movie.title}  ${movie.year}"; title.textSize = if (compact) 12f else 16f; title.setTextColor(Color.WHITE)
    title.setPadding(context.dp(14), context.dp(8),context.dp(14),context.dp(8)); title.setBackgroundColor(0xAA000000.toInt())
    card.addView(title, FrameLayout.LayoutParams(-1,-2,Gravity.BOTTOM))
    addView(card, LinearLayout.LayoutParams(-1,context.dp(if (compact) 100 else 165)).apply { topMargin = context.dp(12) })
    card.setOnClickListener { click() }
}
