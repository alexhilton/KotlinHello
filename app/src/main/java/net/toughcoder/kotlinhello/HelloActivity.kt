package net.toughcoder.kotlinhello

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import net.toughcoder.kotlinhello.calendar.CalendarActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val colorTable = listOf("#ff0000", "#00ff00", "#0000ff", "#ffff00", "#00ffff", "#ff00ff")

        verticalLayout {
            padding = dip(20)
            val name = editText()
            name.lparams(width = matchParent) {
                topMargin = dip(20)
                bottomMargin = dip(30)
            }
            button("Say Hello") {
                onClick {
                    val randomIndex = (Math.random() * colorTable.size).toInt()
                    setBackgroundColor(Color.parseColor(colorTable[randomIndex]))
                    toast("Hello, ${name.text}! with color ${colorTable[randomIndex]}")

                    startActivity<CalendarActivity>()
                }
            }
        }
    }
}
