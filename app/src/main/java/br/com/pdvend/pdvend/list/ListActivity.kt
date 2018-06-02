package br.com.pdvend.pdvend.list

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.pdvend.pdvend.R
import br.com.pdvend.pdvend.api.Issue
import br.com.pdvend.pdvend.api.PullRequestData
import br.com.pdvend.pdvend.detail.IssueDetailActivity
import br.com.pdvend.pdvend.detail.PRDetailActivity
import kotlinx.android.synthetic.main.activity_detail.*

class ListActivity : AppCompatActivity(), ListView,
        IssueFragment.OnIssueInteractionListener,
        PullRequestFragment.OnPullRequestInteractionListener {

    private val ARG_LOGIN = "arg_login"
    private val ARG_NAME = "arg_name"
    private val ARG_ISSUE = "arg_issue"
    private val ARG_PR = "arg_pr"

    private var issues: List<Issue> = ArrayList()
    private var prs: List<PullRequestData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val presenter = ListPresenterImpl(this)

        if (intent.extras != null) {
            val login = intent.extras.getString(ARG_LOGIN)
            val name = intent.extras.getString(ARG_NAME)
            presenter.loadIssues(login, name)
            presenter.loadPullRequests(login, name)
        }
    }

    override fun showPullRequest(pullRequests: List<PullRequestData>) {
        this.prs = pullRequests.reversed()

        tabs.addTab(tabs.newTab().setText("Issues"))
        tabs.addTab(tabs.newTab().setText("Pull Requests"))

        val detailPageAdapter = DetailPageAdapter(supportFragmentManager, ArrayList(issues), ArrayList(prs))
        viewpager.adapter = detailPageAdapter

        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager.currentItem = tab.position
            }

        })
    }

    override fun showIssue(issues: List<Issue>) {
        this.issues = issues.reversed()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onIssueInteracion(issue: Issue) {
        val intent = Intent(this, IssueDetailActivity::class.java)
        intent.putExtra(ARG_ISSUE, issue)
        startActivity(intent)
    }

    override fun onPRInteraction(pr: PullRequestData) {
        val intent = Intent(this, PRDetailActivity::class.java)
        intent.putExtra(ARG_PR, pr)
        startActivity(intent)
    }
}
