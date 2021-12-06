package com.master.recipestools.memento



class InstructionMemento(
    var stepNumber: Int, var description: String
)

 {

    fun createState(): InstructionMemento {
        return InstructionMemento(
            stepNumber, description
        )
    }

    fun restoreFromState(memento: InstructionMemento) {
        stepNumber = memento.stepNumber
        description = memento.description
    }

}

class History {
    val stack = ArrayDeque<InstructionMemento>()

    fun push(memento: InstructionMemento) {
        stack.add(memento)
    }

    fun pop(): InstructionMemento {
        return stack.removeLast()
    }
}
