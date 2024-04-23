package com.task.master.domain.usecase

import com.task.master.data.model.Tasks
import com.task.master.domain.repo.HomeRepository
import com.task.master.utils.TaskResult
import com.task.master.utils.resultListCallback

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
class GetTasks(private val homeRepository: HomeRepository) {
    suspend fun execute(resultListCallback: resultListCallback) {
        homeRepository.getTasks(resultListCallback)
    }
}