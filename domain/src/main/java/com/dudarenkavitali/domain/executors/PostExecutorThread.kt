package com.dudarenkavitali.domain.executors

import io.reactivex.Scheduler

interface PostExecutorThread {
    fun getScheduler(): Scheduler
}