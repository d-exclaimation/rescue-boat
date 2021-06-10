//
//  Boat.kt
//  rescue-boat
//
//  Created by d-exclaimation on 10:45 PM.
//  Copyright © 2021 d-exclaimation. All rights reserved.
//
package rescue.boat.rescuer

import rescue.boat.currentDir
import java.io.File
import java.util.*


data class Boat(val id: UUID, val groupName: String, val tubes: List<Tube>) {
    constructor(files: List<File>, groupName: String? = null) :
        this(
            id = UUID.randomUUID(),
            groupName = groupName ?: "Untitled ${Math.random()}",
            tubes = files.map { Tube(fileName = it.relativeTo(currentDir).toString(), file = it) }
        )

    fun writeToPath(path: String): List<Tag> =
        tubes.map { it.copyToPath(path) }

    fun rescueToPath(path: String) = tubes.forEach { it.copyToPath(path) }
}