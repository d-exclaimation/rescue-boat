//
//  FilePath.kt
//  rescue-boat
//
//  Created by d-exclaimation on 4:45 PM.
//  Copyright © 2021 d-exclaimation. All rights reserved.
//
package rescue.boat

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


fun listAllFiles(): List<File> {
    val ignoreList = listOf("../.gitignore", ".gitignore", "../../.gitignore")
        .map { Paths.get(it).toAbsolutePath() }
        .filter { Files.exists(it) }
        .map { it.toFile() }
        .flatMap { file -> file.readLines().map { Pair(file.absolutePath, it) } }
        .filter { (_, line) -> !line.startsWith("#") && line.isNotBlank() }
        .map { (curr, line) -> "${compressPath(curr, offSet = 1)}/$line" }

    val currentDir =  Paths.get("").toAbsolutePath().toFile()

    return currentDir.walkTopDown()
        .filter { file -> ignoreList.none { file.absolutePath.startsWith(it) } && !file.isDirectory }.toList()
}

fun compressPath(filePath: String, offSet: Int = 0): String =
    compression(filePath = filePath.split("/"), res = listOf(), offSet = offSet).joinToString("/")


private fun compression(filePath: List<String>, res: List<String>, offSet: Int = 0): List<String> {
    if (filePath.size <= offSet)
        return res
    return when (filePath.first()) {
        ".." -> compression(filePath.slice(1 until filePath.size), res.slice(0 until res.size - 1), offSet)
        "." -> compression(filePath.slice(1 until filePath.size), res, offSet)
        else -> compression(filePath.slice(1 until filePath.size), res + filePath.first(), offSet)
    }
}