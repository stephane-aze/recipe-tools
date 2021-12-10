package com.master.recipestools.service.mapper

import com.master.recipestools.data.model.Ingredient
import com.master.recipestools.data.model.Instruction
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.service.dto.*

class PreparationsMapper {
    fun map(instructions: List<InstructionDTO>): List<Instruction> = instructions.map{ transform(it)}
    private fun transform(instructionsDTO: InstructionDTO): Instruction {
        return with(instructionsDTO) {
            Instruction(imagePath = "",description = description,step = stepNumber)
        }
    }
    fun mapReverse(instructions: List<Instruction>): List<InstructionDTO> = instructions.map { reverseTransform(it) }

    private fun reverseTransform(instruction: Instruction): InstructionDTO {
        return with(instruction) {
            InstructionDTO(description = description,stepNumber = step)
        }
    }
}