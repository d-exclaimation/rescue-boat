//
//  Prompt.kt
//  rescue-boat
//
//  Created by d-exclaimation on 6:45 PM.
//

package rescue.boat.prompts

typealias PromptClause = (String?) -> Boolean

fun String.prompt(): String? {
    print("$this? ")
    return readLine()
}

fun String.promptUntil(clause: PromptClause): String? {
    val resp = prompt()
    return if (clause(resp)) resp else this.promptUntil(clause)
}

fun String.prompWith(clause: PromptClause, actionCallback: List<Pair<PromptClause, () -> Unit>>): String? {
    val resp = prompt()
    if (clause(resp)) {
        return resp
    }
    actionCallback.forEach { (guard, action) ->
        if(guard(resp))
            action()
    }
    return prompWith(clause, actionCallback)
}

fun String.promptManyWith(clause: PromptClause, actionCallback: List<Pair<PromptClause, () -> Unit>>): List<String?> {
    val resp = prompt()
    if (clause(resp)) {
        return listOf()
    }
    val called = actionCallback.filter { (guard, action) ->
        val valid  = guard(resp)
        if(valid) action()
        valid
    }
    return (if(called.isNotEmpty()) listOf() else listOf(resp)) + this.promptManyWith(clause, actionCallback)
}