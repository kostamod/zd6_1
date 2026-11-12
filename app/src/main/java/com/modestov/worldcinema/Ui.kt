package com.modestov.worldcinema

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.*

const val BLUE = 0xFF2A79B1.toInt()
fun Context.dp(n: Int) = (n * resources.displayMetrics.density).toInt()
fun LinearLayout.label(value: String, size: Float = 16f, color: Int = Color.WHITE): TextView {
    val t = TextView(context)
    val compact = resources.configuration.screenWidthDp < 300
    t.text = value; t.textSize = if (compact && size == 16f) 12f else size; t.setTextColor(color)
    val inset = context.dp(if (compact) 4 else 9)
    t.setPadding(0, inset, 0, inset)
    addView(t, LinearLayout.LayoutParams(-1, -2))
    return t
}
fun LinearLayout.action(value: String, click: () -> Unit): Button {
    val b = Button(context); b.text = value; b.isAllCaps = false
    val compact = resources.configuration.screenWidthDp < 300
    if (compact) { b.textSize = 11f; b.minWidth = 0; b.setPadding(context.dp(4),0,context.dp(4),0) }
    b.setTextColor(Color.WHITE); b.backgroundTintList = android.content.res.ColorStateList.valueOf(BLUE)
    addView(b, LinearLayout.LayoutParams(-1, context.dp(if (compact) 40 else 52)))
    b.setOnClickListener { click() }; return b
}
fun LinearLayout.input(hint: String): EditText {
    val e = EditText(context); e.hint = hint; e.setTextColor(Color.WHITE); e.setHintTextColor(Color.GRAY)
    val compact = resources.configuration.screenWidthDp < 300
    if (compact) e.textSize = 12f
    e.isSingleLine = true; addView(e, LinearLayout.LayoutParams(-1, context.dp(if (compact) 40 else 52))); return e
}
fun LinearLayout.heading(title: String) = label(title, if (resources.configuration.screenWidthDp < 300) 14f else 22f, BLUE).apply { gravity = Gravity.CENTER }
fun LinearLayout.scroller(): LinearLayout {
    val body = LinearLayout(context); body.orientation = LinearLayout.VERTICAL
    // Общая ScrollView страницы работает и на небольшом экране часов.
    addView(body, LinearLayout.LayoutParams(-1, -2)); return body
}
