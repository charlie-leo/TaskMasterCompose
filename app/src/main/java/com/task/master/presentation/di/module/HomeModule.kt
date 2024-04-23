package com.task.master.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.task.master.data.local.dao.TaskDao
import com.task.master.data.local.database.TaskDatabase
import com.task.master.data.local.repository.TaskRepository
import com.task.master.data.repository.datasource.HomeDataSource
import com.task.master.data.repository.datasourceImpl.HomeDataSourceImpl
import com.task.master.data.repository.repoImpl.HomeRepositoryImpl
import com.task.master.domain.repo.HomeRepository
import com.task.master.domain.usecase.AddTaskUseCase
import com.task.master.domain.usecase.CompleteTaskUseCase
import com.task.master.domain.usecase.GetAllTask
import com.task.master.domain.usecase.GetCompletedTask
import com.task.master.domain.usecase.GetTasks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskDatabase::class.java,
            "TaskDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }


    @Singleton
    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepository(taskDao)
    }




    @Singleton
    @Provides
    fun provideHomeRepository(homeDataSource: HomeDataSource) : HomeRepository{
        return HomeRepositoryImpl(homeDataSource)
    }

    @Singleton
    @Provides
    fun provideHomeDataSource(taskRepository: TaskRepository) : HomeDataSource{
        return HomeDataSourceImpl(taskRepository)
    }

    @Singleton
    @Provides
    fun provideAddTaskUseCase(homeRepository: HomeRepository) : AddTaskUseCase {
        return AddTaskUseCase(homeRepository)
    }


    @Singleton
    @Provides
    fun provideCompleteTaskUseCase(homeRepository: HomeRepository) : CompleteTaskUseCase {
        return CompleteTaskUseCase(homeRepository)
    }

    @Singleton
    @Provides
    fun provideGetCompletedTasks(homeRepository: HomeRepository) : GetCompletedTask {
        return GetCompletedTask(homeRepository)
    }

    @Singleton
    @Provides
    fun provideGetTasks(homeRepository: HomeRepository) : GetTasks {
        return GetTasks(homeRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllTasks(homeRepository: HomeRepository) : GetAllTask {
        return GetAllTask(homeRepository)
    }

}