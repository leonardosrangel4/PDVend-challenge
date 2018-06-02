package br.com.pdvend.pdvend.repository

import br.com.pdvend.pdvend.api.GithubImpl
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class RepositoryPresenterImpl(private val repositoryView: RepositoryView): RepositoryPresenter {

    override fun onError(message: String?) {
        repositoryView.showError(message)
    }

    override fun loadRepository(name: String) {
        repositoryView.showLoading()
        val api = GithubImpl()

        api.loadRepository(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repository ->
                    repositoryView.hideLoading()
                    repositoryView.showRepository(repository)
                }, { e ->
                    repositoryView.hideLoading()
                    onError(e.message)
                })
    }
}