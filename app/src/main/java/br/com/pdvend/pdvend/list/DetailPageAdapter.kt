package br.com.pdvend.pdvend.list

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.pdvend.pdvend.api.Issue
import br.com.pdvend.pdvend.api.PullRequestData


class DetailPageAdapter(fm: FragmentManager,
                        private val issues: ArrayList<Issue>,
                        private val prs: ArrayList<PullRequestData>): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                IssueFragment.newInstance(issues)
            }
            1 -> PullRequestFragment.newInstance(prs)
            else -> {
                IssueFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}