package com.example.compositions.data

import com.example.compositions.domain.entity.GameSettings
import com.example.compositions.domain.entity.Level
import com.example.compositions.domain.entity.Question
import com.example.compositions.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

private const val MIN_SUM_VALUE = 2
private const val MIN_ANSWER_VALUE = 1

object GameRepositoryImpl : GameRepository {
    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    6
                )
            }
            Level.EASY -> {
                GameSettings(
                    20,
                    8,
                    70,
                    60
                )
            }
            Level.NORMAL -> {
                GameSettings(
                    35,
                    10,
                    80,
                    60
                )
            }
            Level.HARD -> {
                GameSettings(
                    50,
                    20,
                    90,
                    60
                )
            }
        }
    }

    override fun generateQuestions(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue - 1, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }
}