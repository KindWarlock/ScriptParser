import java.io.File

fun main(args: Array<String>) {
    var reading = false // mode
    var lineCnt = 1 // счет реплик
    val script = mutableMapOf<String, MutableList<String>>()

    File("src/main/resources/script.txt").forEachLine {
        when {
            it == "textLines:" -> reading = true
            reading -> {
                val splitIdx = it.indexOf(':')
                val role = it.substring(0, splitIdx)
                val line = "${lineCnt++}) ${it.substring(splitIdx + 1)}"
                if (role in script.keys) {
                    script[role]?.add(line)
                } else {
                    script[role] = mutableListOf(line)
                }
            }
            else -> {}
        }
    }
    for (entry in script) {
        println("${entry.key}:")
        entry.value.forEach {
            println(it)
        }
        println()
    }
}