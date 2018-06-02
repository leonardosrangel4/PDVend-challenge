package br.com.pdvend.pdvend.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.pdvend.pdvend.R
import br.com.pdvend.pdvend.api.Issue
import kotlinx.android.synthetic.main.activity_issue_detail.*

class IssueDetailActivity : AppCompatActivity() {

    private val ARG_ISSUE = "arg_issue"
    private lateinit var issue: Issue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_detail)

        if (intent.extras != null) {
            issue = intent.extras.get(ARG_ISSUE) as Issue
            issue_detail_title.text = issue.title
            issue_detail_number.text = "#" + issue.number.toString()
            issue_detail_body.text = issue.body
        }
    }
}
