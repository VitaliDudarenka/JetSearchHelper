package com.dudarenkavitali.jetsearchhelper.di

import android.content.Context
import com.dudarenkavitali.data.net.RestService
import com.dudarenkavitali.data.repositories.FeedsRepositoryImpl
import com.dudarenkavitali.domain.executors.PostExecutorThread
import com.dudarenkavitali.domain.repositories.FeedsRepository
import com.dudarenkavitali.jetsearchhelper.executors.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Named

const val URL_INJECT_NAME = "url_name"

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideFeedsRepository(restService: RestService): FeedsRepository =
        FeedsRepositoryImpl(restService)

    @Provides
    fun provideRestService(@Named(URL_INJECT_NAME) serverUrl: String): RestService =
        RestService(serverUrl)

    @Provides
    fun providePostExecutorThread(): PostExecutorThread = UIThread()

    @Provides
    @Named(URL_INJECT_NAME)
    fun provideServerUrlDebug(): String = "https://data-live.flightradar24.com/zones/fcgi/"


}