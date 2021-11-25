package com.dudarenkavitali.data.repositories

import com.dudarenkavitali.data.net.RestService
import com.dudarenkavitali.domain.entity.Feed
import com.dudarenkavitali.domain.repositories.FeedsRepository
import io.reactivex.Single

class FeedsRepositoryImpl(private val apiService: RestService) : FeedsRepository {

    override fun getFeeds(): Single<MutableList<Feed>> {
        val feeds: MutableList<Feed> = mutableListOf()
        return apiService.getFeeds(
            1, "72.344,32.623,-15.297,-'=59.321", 1,
            1, 1, 1, 1, 1, 1, 1, 14400, 1, 1
        )
            .map {
                for ((k, v) in it) {
                    if (k == "full_count" || k == "version" || k == "stats")
                        continue
                    val dataList = v as List<Any>
                    val feed = Feed(
                        dataList[1] as Double,
                        dataList[2] as Double,
                        dataList[9] as String,
                        dataList[8] as String,
                        dataList[18] as String,
                        dataList[12] as String,
                        dataList[11] as String
                    )
                    feeds.add(feed)
                }
            }
            .flatMap {
                Single.just(feeds)
            }
    }

}