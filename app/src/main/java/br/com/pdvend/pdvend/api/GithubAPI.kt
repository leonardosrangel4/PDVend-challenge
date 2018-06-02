package br.com.pdvend.pdvend.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface GithubAPI {

    @GET("/search/repositories")
    fun getRepositories(@Query("q") name: String) : Observable<RepositoryData>

    @GET("/repos/{login}/{name}/issues?per_page=100")
    fun getIssues(@Path("login") login: String, @Path("name") name: String) : Observable<List<Issue>>

    @GET("/repos/{login}/{name}/pulls?state=all&per_page=100")
    fun getPullRequests(@Path("login") login: String, @Path("name") name: String) : Observable<List<PullRequestData>>
}