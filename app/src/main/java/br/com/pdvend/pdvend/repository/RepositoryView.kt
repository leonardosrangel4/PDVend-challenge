package br.com.pdvend.pdvend.repository

import br.com.pdvend.pdvend.api.RepositoryData


interface RepositoryView {

    fun showLoading()

    fun hideLoading()

    fun showRepository(repository: RepositoryData)

    fun showError(message: String?)
}