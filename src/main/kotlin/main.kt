fun main() {
    val t1 = System.currentTimeMillis()
    for (i in 0 until 23333) {
        val result = "不许自慰！".unvcode()
        println(result.first)
        println(result.second)
    }
    val t2 = System.currentTimeMillis()
    println(t2 - t1)
}
