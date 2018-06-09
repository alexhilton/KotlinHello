package net.toughcoder.kotlinhello.calendar

import java.util.*
import java.util.Calendar
import kotlin.collections.ArrayList

class ProgrammerCalendar {
    companion object EventKey {
        const val NAME = "name"
        const val GOOD = "good"
        const val BAD = "bad"
        const val WEEKEND = "weekend"
        const val DATE = "date"
        const val TYPE = "type"
        const val DESCRIPTION = "description"
    }

    private val weeks = listOf( "日", "一", "二", "三", "四", "五", "六")
    private val directions = listOf("北方", "东北方", "东方", "东南方", "南方", "西南方", "西方", "西北方")
    private val activities = listOf(
            mapOf(NAME to "写单元测试", GOOD to "写单元测试将减少出错", BAD to "写单元测试会降低你的开发效率"),
            mapOf(NAME to "洗澡", GOOD to "你几天没洗澡了？", BAD to "会把设计方面的灵感洗掉", WEEKEND to "true"),
            mapOf(NAME to "锻炼一下身体", GOOD to "", BAD to "能量没消耗多少，吃得却更多", WEEKEND to "true"),
            mapOf(NAME to "抽烟", GOOD to "抽烟有利于提神，增加思维敏捷", BAD to "除非你活够了，死得早点没关系", WEEKEND to "true"),
            mapOf(NAME to "白天上线", GOOD to "今天白天上线是安全的", BAD to "可能导致灾难性后果"),
            mapOf(NAME to "重构", GOOD to "代码质量得到提高", BAD to "你很有可能会陷入泥潭"),
            mapOf(NAME to "使用%t", GOOD to "你看起来更有品位", BAD to "别人会觉得你在装逼"),
            mapOf(NAME to "跳槽", GOOD to "该放手时就放手", BAD to "鉴于当前的经济形势，你的下一份工作未必比现在强"),
            mapOf(NAME to "招人", GOOD to "你面前这位有成为牛人的潜质", BAD to "这人会写程序吗？"),
            mapOf(NAME to "面试", GOOD to "面试官今天心情Xiao很好", BAD to "面试官不爽，会拿你出气"),
            mapOf(NAME to "提交辞职申请", GOOD to "公司找到了一个比你更能干更便宜的家伙，巴不得你赶快滚蛋", BAD to "鉴于当前的经济形势，你的下一份工作未必比现在强"),
            mapOf(NAME to "申请加薪", GOOD to "老板今天心情很好", BAD to "公司正在考虑裁员"),
            mapOf(NAME to "晚上加班", GOOD to "晚上是程序员精神最好的时候", BAD to "", WEEKEND to "true"),
            mapOf(NAME to "在妹子面前吹牛", GOOD to "改善你矮穷挫的形象", BAD to "会被识破", WEEKEND to "true"),
            mapOf(NAME to "撸管", GOOD to "避免缓冲区溢出", BAD to "强撸灰飞烟灭", WEEKEND to "true"),
            mapOf(NAME to "浏览成人网站", GOOD to "重拾对生活的信心", BAD to "你会心神不宁", WEEKEND to "true"),
            mapOf(NAME to "命名变量\"%v\"", GOOD to "", BAD to ""),
            mapOf(NAME to "写超过%l行的方法", GOOD to "你的代码组织的很好，长一点没关系", BAD to "你的代码将混乱不堪，你自己都看不懂"),
            mapOf(NAME to "提交代码", GOOD to "遇到冲突的几率是最低的", BAD to "你遇到的一大堆冲突会让你觉得自己是不是时间穿越了"),
            mapOf(NAME to "代码复审", GOOD to "发现重要问题的几率大大增加", BAD to "你什么问题都发现不了，白白浪费时间"),
            mapOf(NAME to "开会", GOOD to "写代码之余放松一下打个盹，有益健康", BAD to "小心被扣屎盆子背黑锅"),
            mapOf(NAME to "打DOTA", GOOD to "你将有如神助", BAD to "你会被虐的很惨", WEEKEND to "true"),
            mapOf(NAME to "晚上上线", GOOD to "晚上是程序员精神最好的时候", BAD to "你白天已经筋疲力尽了"),
            mapOf(NAME to "修复BUG", GOOD to "你今天对BUG的嗅觉大大提高", BAD to "新产生的BUG将比修复的更多"),
            mapOf(NAME to "设计评审", GOOD to "设计评审会议将变成头脑风暴", BAD to "人人筋疲力尽，评审就这么过了"),
            mapOf(NAME to "需求评审", GOOD to "", BAD to ""),
            mapOf(NAME to "上微博", GOOD to "今天发生的事不能错过", BAD to "今天的微博充满负能量", WEEKEND to "true"),
            mapOf(NAME to "上AB站", GOOD to "还需要理由吗？", BAD to "满屏兄贵亮瞎你的眼", WEEKEND to "true"),
            mapOf(NAME to "玩FlappyBird", GOOD to "今天破纪录的几率很高", BAD to "除非你想玩到把手机砸了", WEEKEND to "true")
    )


    private val specials = listOf(
            mapOf(DATE to "20140214", TYPE to "BAD", NAME to "待在男（女）友身边", DESCRIPTION to "脱团火葬场，入团保平安。")
    )

    private val tools = listOf("Eclipse写程序", "MSOffice写文档", "记事本写程序", "Windows8", "Linux", "MacOS", "IE", "Android设备", "iOS设备")

    private val varNames = listOf("jieguo", "huodong", "pay", "expire", "zhangdan", "every", "free", "i1", "a", "virtual", "ad", "spider", "mima", "pass", "ui")

    private val drinks = listOf("水", "茶", "红茶", "绿茶", "咖啡", "奶茶", "可乐", "鲜奶", "豆奶", "果汁", "果味汽水", "苏打水", "运动饮料", "酸奶", "酒")

    private val today = GregorianCalendar()
    private val iday = today.get(Calendar.YEAR) * 10000 + (today.get(Calendar.MONTH) + 1) * 100 + today.get(Calendar.DAY_OF_MONTH)

    private fun random(seed: Int, index: Int): Int {
        var n = seed % 11117
        for (i in 0 until 100+index) {
            n *= n
            n %= 11117   // 11117 是个质数
        }
        return n
    }

    private fun isSomeday(): Boolean {
        return today.get(Calendar.MONTH) == 5 && today.get(Calendar.DAY_OF_MONTH) == 4
    }

    private fun star(num: Int): String {
        var result = ""
        var i = 0
        while (i < num) {
            result += "★"
            i++
        }
        while(i < 5) {
            result += "☆"
            i++
        }
        return result
    }

    private fun isWeekend(): Boolean {
        return today.get(Calendar.DAY_OF_WEEK) == 0 || today.get(Calendar.DAY_OF_WEEK) == 6
    }

    // 从 activities 中随机挑选 size 个
    private fun pickRandomActivity(activities: List<Map<String, String>>, size: Int): List<Map<String, String>> {
        val pickedEvents = activities.pickRandom(size)

        return pickedEvents.map { parse(it) }
    }

    // 从数组中随机挑选 size 个
    private fun <T> List<T>.pickRandom(size: Int): List<T> {
        var result = this.toMutableList()
        for (j in 0 until this.size - size) {
            val index = random(iday, j) % result.size
            result.removeAt(index)
        }

        return result
    }

    // 解析占位符并替换成随机内容
    private fun parse(event: Map<String, String>): Map<String, String> {
        var result = event.toMutableMap()

        if (result[NAME]?.indexOf("%v") != -1) {
            result[NAME] = result[NAME]?.replace("%v", varNames[random(iday, 12) % varNames.size])!!
        }

        if (result[NAME]?.indexOf("%t") != -1) {
            result[NAME] = result[NAME]?.replace("%t", tools[random(iday, 11) % tools.size])!!
        }

        if (result[NAME]?.indexOf("%l") != -1) {
            result[NAME] = result[NAME]?.replace("%l", (random(iday, 12) % 247 + 30).toString())!!
        }

        return result.toMap()
    }

    // 添加预定义事件
    // Should return two lists: GOOD list and BAD list, the item of list is a map(dictionary)
    private fun pickSpecials(goodList: MutableList<Map<String, String>>, badList: MutableList<Map<String, String>>) {
        specials.forEach {
            if (iday.toString() == it[DATE]) {
                if (it[TYPE] == GOOD) {
                    goodList.add(mapOf(NAME to it[NAME]!!, GOOD to it[DESCRIPTION]!!))
                } else {
                    badList.add(mapOf(NAME to it[NAME]!!, BAD to it[DESCRIPTION]!!))
                }
            }
        }
    }

    // 生成今日运势
    // Two part: from specials events and random picked from activities
    fun genTodayLuck(): Pair<List<Map<String, String>>, List<Map<String, String>>> {
        var theActivities = if (isWeekend()) activities.filter { it[WEEKEND] == "true" } else activities

        val goodList = ArrayList<Map<String, String>>()
        val badList = ArrayList<Map<String, String>>()
        pickSpecials(goodList, badList)

        val numGood = random(iday, 98) % 3 + 2
        val numBad = random(iday, 87) % 3 + 2
        val pickedEvents = pickRandomActivity(theActivities, numGood + numBad)

        // Add random picked from activities to GOOD/BAD list
        for (i in 0 until numGood) {
            goodList.add(mapOf(NAME to pickedEvents[i][NAME]!!, DESCRIPTION to pickedEvents[i][GOOD]!!))
        }
        for (i in 0 until numBad) {
            badList.add(mapOf(NAME to pickedEvents[numGood + i][NAME]!!, DESCRIPTION to pickedEvents[numGood + i][BAD]!!))
        }
        return Pair(goodList, badList)
    }

    fun genTodayString(): String {
        return "今天是" + today.get(Calendar.YEAR) +
                "年" + (today.get(Calendar.MONTH) + 1) +
                "月" + today.get(Calendar.DAY_OF_MONTH) +
                "日 星期" + weeks[today.get(Calendar.DAY_OF_WEEK) - 1]
    }

    fun genDirection(): String {
        val index = random(iday, 2) % directions.size
        return directions[index]
    }

    fun genGirlsIndex(): String {
        return star(random(iday, 6) % 5 + 1)
    }

    fun genDrinks(): String {
        return drinks.pickRandom(2).joinToString(", ")
    }
}
