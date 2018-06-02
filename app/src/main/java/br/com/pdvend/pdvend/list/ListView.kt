package br.com.pdvend.pdvend.list

import br.com.pdvend.pdvend.api.Issue
import br.com.pdvend.pdvend.api.PullRequestData


interface ListView {

    fun showPullRequest(pullRequests: List<PullRequestData>)

    fun showIssue(issues: List<Issue>)

    fun onError(message: String?)
}