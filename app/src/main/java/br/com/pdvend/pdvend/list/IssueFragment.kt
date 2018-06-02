package br.com.pdvend.pdvend.list

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.pdvend.pdvend.R
import br.com.pdvend.pdvend.api.Issue
import br.com.pdvend.pdvend.detail.IssueAdapter
import kotlinx.android.synthetic.main.fragment_issue.*

private const val ARG_ISSUE = "arg_issue"

class IssueFragment: Fragment(), IssueAdapter.OnItemClickedListener {

    private var issues: ArrayList<Issue> = ArrayList()
    private var listener: OnIssueInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            issues = it.getParcelableArrayList(ARG_ISSUE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_issue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_issues.layoutManager = LinearLayoutManager(context)
        val adapter = IssueAdapter(issues, context!!, this)
        recycler_issues.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnIssueInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onIssueItemClicked(issue: Issue) {
        listener?.onIssueInteracion(issue)
    }

    interface OnIssueInteractionListener {
        fun onIssueInteracion(issue: Issue)
    }

    companion object {
        @JvmStatic
        fun newInstance(issues: ArrayList<Issue>) =
                IssueFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_ISSUE, issues)
                    }
                }
    }
}
