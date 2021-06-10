package rescue.boat

import rescue.boat.prompts.prompt
import java.io.File
import java.nio.file.Paths

val currentDir: File =  Paths.get("").toAbsolutePath().toFile()
val quitClause: (String?) -> Boolean = { it.isNullOrBlank() || it.toLowerCase() == "q" }


fun main() {
    val controller = Controller()
    when (val resp = "Boat, Tube, or Rescue (b/t/r)".prompt()?.toLowerCase()) {
        "boat" -> controller.setupBoat()
        "tube" -> controller.setupTube()
        "rescue" -> controller.rescueFiles()
        "b" -> controller.setupBoat()
        "t" -> controller.setupTube()
        "r" -> controller.rescueFiles()
        else -> {
            println("Invalid command $resp")
        }
    }
}


