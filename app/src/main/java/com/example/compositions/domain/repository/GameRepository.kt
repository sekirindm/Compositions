package com.example.compositions.domain.repository

import com.example.compositions.domain.entity.GameSettings
import com.example.compositions.domain.entity.Level
import com.example.compositions.domain.entity.Question
import com.example.compositions.domain.usecases.GetGameSettingsUseCase

interface GameRepository {

    fun getGameSettings(level: Level): GameSettings

    fun generateQuestions(
        maxSumValue: Int,
        countOfOptions: Int,
    ): Question
}