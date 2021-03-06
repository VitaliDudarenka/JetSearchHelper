package com.dudarenkavitali.domain.uscases

import com.dudarenkavitali.domain.entity.Feed
import com.dudarenkavitali.domain.executors.PostExecutorThread
import com.dudarenkavitali.domain.repositories.FeedsRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val feedsRepository: FeedsRepository
) : BaseUseCase(postExecutorThread) {

    fun getFeeds(aircraft: String, destination: String, company: String): Single<MutableList<Feed>> {
        return feedsRepository.getFeeds(aircraft, destination, company).observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

}