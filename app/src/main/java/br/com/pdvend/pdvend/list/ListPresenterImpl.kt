package br.com.pdvend.pdvend.list

import br.com.pdvend.pdvend.api.GithubImpl
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ListPresenterImpl(private val listView: ListView): ListPresenter {

    val api = GithubImpl()

    override fun loadPullRequests(login: String, name: String) {
        api.loadPullRequests(login, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ pullRequest ->
                    listView.showPullRequest(pullRequest)
                }, { e->
                    listView.onError(e.message)
                    e.printStackTrace()
                })
    }

    override fun loadIssues(login: String, name: String) {
        api.loadIssues(login, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ issue ->
                    listView.showIssue(issue)
                }, { e->
                    listView.onError(e.message)
                    e.printStackTrace()
                })
    }
}