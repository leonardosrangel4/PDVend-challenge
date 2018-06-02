package br.com.pdvend.pdvend.list


interface ListPresenter {

    fun loadIssues(login: String, name: String)

    fun loadPullRequests(login: String, name: String)
}