package br.com.pdvend.pdvend.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable


class GithubImpl {
    private val service: GithubAPI

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<GithubAPI>(GithubAPI::class.java)
    }

    fun loadRepository(name: String): Observable<RepositoryData> {
        return service.getRepositories(name)
    }

    fun loadIssues(login: String, name: String) : Observable<List<Issue>> {
        return service.getIssues(login, name)
    }

    fun loadPullRequests(login: String, name: String) : Observable<List<PullRequestData>> {
        return service.getPullRequests(login, name)
    }
}