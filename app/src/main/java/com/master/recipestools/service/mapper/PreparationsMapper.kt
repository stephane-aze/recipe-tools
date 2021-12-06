package com.master.recipestools.service.mapper

import com.master.recipestools.data.model.Instruction
import com.master.recipestools.data.model.Recipe
import com.master.recipestools.service.dto.InstructionDTO
import com.master.recipestools.service.dto.RecipeDTO

class PreparationsMapper {
    fun map(instructions: List<InstructionDTO>): List<Instruction> = instructions.map{ transform(it)}
    private fun transform(instructionsDTO: InstructionDTO): Instruction {
        return with(instructionsDTO) {
            Instruction(imagePath = "",description = description,step = stepNumber)
        }
    }
}