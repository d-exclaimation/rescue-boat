//
//  Tag.kt
//  rescue-boat
//
//  Created by d-exclaimation on 9:15 PM.
//  Copyright © 2021 d-exclaimation. All rights reserved.
//
package rescue.boat.rescuer

data class Tag(val path: String) {
    override fun toString(): String = path
}