package com.dudarenkavitali.domain.repositories

import com.dudarenkavitali.domain.entity.Feed
import io.reactivex.Single

interface FeedsRepository : BaseRepository {

    fun getFeeds(): Single<MutableList<Feed>>

}