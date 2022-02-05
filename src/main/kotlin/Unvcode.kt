import java.awt.*
import java.awt.image.BufferedImage
import java.awt.image.DataBufferInt
import java.io.File
import java.text.Normalizer
import javax.imageio.ImageIO

object Unvcode {
    private val d = mutableMapOf<Char, MutableList<Char>>()

    init {
        for (i in 0 until 65536) {
            val 字 = i.toChar()
            val 新字 = Normalizer.normalize(字.toString(), Normalizer.Form.NFKC).toCharArray()[0]
            if (字 != 新字) {
                d.putIfAbsent(新字, mutableListOf())
                d[新字]?.add(字)
            }
        }
    }

    private fun 画皮(字: Char): List<Int> {
        var img = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
        val fnt = Font(null, Font.PLAIN, 100)
        var g2d = img.createGraphics()
        g2d.font = fnt
        val fm = g2d.fontMetrics
        val width = fm.stringWidth(字.toString())
        val height = fm.height
        g2d.dispose()
        img = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        g2d = img.createGraphics()
        g2d.color = Color.BLACK
        g2d.fillRect(0, 0, img.width, img.height)
        g2d.color = Color.WHITE
        g2d.font = fnt
        g2d.drawString(字.toString(), 0, fm.ascent)
        g2d.dispose()
        //ImageIO.write(img, "png", File("/Users/lz233/Desktop/1.png"))
        return mutableListOf<Int>().apply {
            (img.raster.dataBuffer as DataBufferInt).data.forEach {
                val color = Color(it)
                add(color.red / 255)
                add(color.green / 255)
                add(color.blue / 255)
            }
        }
    }

    private fun 比较(字1: Char, 字2: Char) = (画皮(字1) minus 画皮(字2)).variance()

    private fun 假面(字: Char, skipAscii: Boolean, mse: Double = 0.1): Pair<Double, Char> {
        if ((字.code < 128) and skipAscii) return (-1.0 to 字)
        val 候选组 = d[字] ?: return (-1.0 to 字)
        val 差异组 = mutableListOf<Double>().apply {
            候选组.forEach { add(比较(字, it)) }
        }
        val 差异 = 差异组.minOrNull()!!
        val 新字 = 候选组.get(差异组.minIndex())
        if (差异 > mse) return (-1.0 to 字) else return (差异 to 新字)
    }

    fun 转换(s: String, skipAscii: Boolean = true, mse: Double = 0.1): Pair<String, List<Double>> {
        val 差异列 = mutableListOf<Double>()
        val 串 = StringBuilder().apply {
            s.toCharArray().forEach {
                val 结果 = 假面(it, skipAscii, mse)
                差异列.add(结果.first)
                append(结果.second)
            }
        }.toString()
        return (串 to 差异列)
    }
}

fun String.unvcode(skipAscii: Boolean = true, mse: Double = 0.1) = Unvcode.转换(this, skipAscii, mse)