package net.toughcoder.kotlinhello.calendar

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "程序猿老黄历"

        val calendar = ProgrammerCalendar()
        scrollView {
            verticalLayout {
                val dateString = buildSpanned {
                    append(calendar.genTodayString(), Bold)
                }
                val dateLabel = textView(dateString) {
                    id = View.generateViewId()
                    textColor = Color.BLACK
                    textSize = sp(6.4f).toFloat()
                    singleLine = true
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(10)
                    bottomMargin = dip(10)
                }

                val (goodList, badList) = calendar.genTodayLuck()
                generateLuck("宜", Color.parseColor("#ffee44"), Color.parseColor("#dddddd"), goodList)

                generateLuck("忌", Color.parseColor("#ff4444"), Color.parseColor("#aaaaaa"), badList)

                // Direction
                val directionDetail = buildSpanned {
                    append("座位朝向：", Bold)
                    append("面向")
                    append(calendar.genDirection(), foregroundColor(Color.GREEN))
                    append("写程序，BUG最少。")
                }
                val direction = extraLabel(directionDetail)

                // Drink
                val drinkDetail = buildSpanned {
                    append("今日饮品：", Bold)
                    append(calendar.genDrinks())
                }
                val drink = extraLabel(drinkDetail)

                val girlDetail = buildSpanned {
                    append("女神亲近指数：", Bold)
                    append(calendar.genGirlsIndex())
                }
                val girl = extraLabel(girlDetail)
            }
        }
    }

    private fun @AnkoViewDslMarker _LinearLayout.extraLabel(detail: CharSequence): TextView {
        return textView(detail) {
            textSize = sp(5).toFloat()
            horizontalPadding = dip(10)
            verticalPadding = dip(6)
        }
    }

    private fun @AnkoViewDslMarker _LinearLayout.generateLuck(type: String,
                                                              typeColor: Int,
                                                              detailColor: Int,
                                                              eventList: List<Map<String, String>>) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL

            val good = textView(type) {
                id = View.generateViewId()
                textColor = Color.WHITE
                textSize = sp(14).toFloat()
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                gravity = Gravity.CENTER
                backgroundColor = typeColor
            }.lparams(width = dip(100), height = matchParent)

            val goodDetail = verticalLayout {
                id = View.generateViewId()
                eventList.forEach {
                    val caption = textView(it[ProgrammerCalendar.NAME]) {
                        textColor = Color.BLACK
                        textSize = sp(6).toFloat()
                    }
                    val detail = textView(it[ProgrammerCalendar.DESCRIPTION]) {
                        textColor = Color.GRAY
                        textSize = sp(5).toFloat()
                        horizontalPadding = dip(2)
                    }
                }
                horizontalPadding = dip(15)
                backgroundColor = detailColor
                verticalPadding = dip(10)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}
