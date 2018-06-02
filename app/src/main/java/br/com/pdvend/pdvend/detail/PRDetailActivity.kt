package br.com.pdvend.pdvend.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.pdvend.pdvend.R
import br.com.pdvend.pdvend.api.PullRequestData
import kotlinx.android.synthetic.main.activity_prdetail.*

class PRDetailActivity : AppCompatActivity() {

    private val ARG_PR = "arg_pr"
    private lateinit var pr: PullRequestData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prdetail)

        if (intent.extras != null) {
            pr = intent.extras.get(ARG_PR) as PullRequestData
            pr_detail_title.text = pr.title
            pr_detail_number.text = "#" + pr.number.toString()
            pr_detail_body.text = pr.body
        }
    }
}
