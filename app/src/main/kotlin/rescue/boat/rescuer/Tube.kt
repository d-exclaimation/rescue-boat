//
//  Tube.kt
//  rescue-boat
//
//  Created by d-exclaimation on 3:46 PM.
//  Copyright © 2021 d-exclaimation. All rights reserved.
//
package rescue.boat.rescuer

import rescue.boat.currentDir
import java.io.File

data class Tube(val fileName: String, val file: File) {
    fun copyToPath(path: String = "."): Tag {
        val tag = Tag(file.relativeTo(currentDir).toString())
        val target = File("$path/$tag")
        target.parentFile.mkdirs()
        target.createNewFile()
        target.writeText(file.readText())
        return tag
    }
}