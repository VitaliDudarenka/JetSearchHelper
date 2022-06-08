package com.dudarenkavitali.data.repositories

import com.dudarenkavitali.data.net.RestService
import com.dudarenkavitali.domain.entity.Feed
import com.dudarenkavitali.domain.repositories.FeedsRepository
import io.reactivex.Single

class FeedsRepositoryImpl(private val apiService: RestService) : FeedsRepository {

    override fun getFeeds(
        aircraft: String,
        destination: String,
        company: String
    ): Single<MutableList<Feed>> {
        val feeds: MutableList<Feed> = mutableListOf()
        return apiService.getFeeds(
            1, "62.553,40.746,8.561,47.452", 1,
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
                val filteredList = mutableListOf<Feed>()

                if (aircraft.isNotEmpty())
                    filteredList.addAll(feeds.filter { feed ->
                        feed.airCraft.uppercase().contains(aircraft.uppercase())
                    })
                if (destination.isNotEmpty())
                    filteredList.addAll(feeds.filter { feed ->
                        feed.to.uppercase().contains(destination.uppercase())
                    })
                if (company.isNotEmpty())
                    filteredList.addAll(feeds.filter { feed ->
                        feed.airCompany.uppercase().contains(company.uppercase())
                    })

                return@flatMap Single.just(filteredList)
            }
    }

}