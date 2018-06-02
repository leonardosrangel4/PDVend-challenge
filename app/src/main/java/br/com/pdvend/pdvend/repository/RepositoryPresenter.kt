package br.com.pdvend.pdvend.repository


interface RepositoryPresenter {

    fun loadRepository(name: String)

    fun onError(message: String?)
}