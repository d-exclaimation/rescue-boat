//
//  Controller.kt
//  rescue-boat
//
//  Created by d-exclaimation on 9:01 PM.
//  Copyright © 2021 d-exclaimation. All rights reserved.
//
package rescue.boat

import rescue.boat.prompts.PromptClause
import rescue.boat.prompts.prompWith
import rescue.boat.prompts.prompt
import rescue.boat.prompts.promptManyWith
import rescue.boat.rescuer.Boat
import rescue.boat.rescuer.Tag
import rescue.boat.rescuer.Tube
import java.io.File

class Controller {
    private val allFiles = listAllFiles()

    private val listFileClause: Pair<PromptClause, () -> Unit>
        get() = Pair({ (it ?: "") == "L" }, { allFiles.forEachIndexed { i, file -> println("$i: ${file.relativeTo(currentDir)}") } })

    // Mark: Boat Controller
    fun setupBoat() {
        // Get the name and list all files
        val name = "Name the boat".prompt()
        println("Select files by their indices (Send L to list all files, you can use (1, 2, 3, 4)):")

        // ListFileClause for Listing files
        try {
            val selected =
                ">"
                    .promptManyWith(quitClause, listOf(listFileClause))
                    .filterNotNull()
                    .flatMap { if (it.contains(",")) it.split(", ") else listOf(it) }
                    .filter { it.matches("-?\\d+(\\.\\d+)?".toRegex()) }
                    .map { it.toInt() }
                    .filter { it < allFiles.size }

            // Create boat and do the stuff
            val boat = Boat(files = selected.map { allFiles[it] }, groupName = name)
            val tempDir = "Select a place to rescue to".prompt() ?: "../temp"
            print("Create a boat with $name....")
            writeRescueTag(workDir = tempDir, name = boat.groupName, tags = boat.writeToPath(tempDir))
            println("Done")
        } catch (_: Error) {
            println("File does not exist")
        }
    }

    fun setupTube() {
        // Get tag name
        val name = "Give a tag name".prompt()
        println("Select a file by its index (Send L to list all files):")
        try {
            val resp =
                ">"
                    .prompWith({ it?.matches("-?\\d+(\\.\\d+)?".toRegex()) ?: false }, listOf(listFileClause)) ?: return
            val file = allFiles[resp.toInt()]
            val tube = Tube(fileName = file.relativeTo(currentDir).toString(), file = file)
            val tempDir = "Select a place to rescue to".prompt() ?: "../temp"
            print("Create a tube....")
            writeRescueTag(workDir = tempDir, name = name ?: "Untitled ${Math.random()}", tags = listOf(tube.copyToPath(tempDir)))
            println("Done")
        } catch (_: Error) {
           println("File does not exist")
        }
    }

    fun rescueFiles() {
        val tagname = "Select the .rescuetag file".prompt() ?: return

        if (!tagname.endsWith(".rescuetag"))
            return println("Not a .rescuetag file")

        try {
            val tag = File(tagname)
            val lines = tag.readLines().filterNot { it.isBlank() }
            val name = lines[0]
            val dropPath = "Select a place to drop to".prompt() ?: "../dropped"
            print("Rescuing $name....")
            lines
                .slice(1 until lines.size)
                .asSequence()
                .map { "${tag.parentFile.absolutePath}/$it" }
                .map(::compressPath)
                .map(::File)
                .mapIndexed{ i, it -> Pair(i + 1, it) }
                .filter { (_, it) -> it.exists() }
                .toList()
                .forEach { (i, it) ->
                    val file = File("$dropPath/${lines[i]}")
                    file.parentFile.mkdirs()
                    file.createNewFile()
                    file.writeText(it.readText())
                }
            println("Done")
            tag.parentFile.deleteRecursively()
        } catch (_: Error) {
            println("Cannot find rescuetag")
        }
    }

    private fun writeRescueTag(workDir: String, name: String = "", tags: List<Tag>) {
        val tag = File("$workDir/.rescuetag")
        tag.createNewFile()
        tag.writeText("$name\n\n${tags.joinToString("\n"){ it.toString() }}")
    }

}