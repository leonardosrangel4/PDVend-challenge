package br.com.pdvend.pdvend.list

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.pdvend.pdvend.R
import br.com.pdvend.pdvend.api.PullRequestData
import kotlinx.android.synthetic.main.fragment_pull_request.*

private const val ARG_PR = "arg_pr"


class PullRequestFragment : Fragment(),
        PullRequestAdapter.OnItemClickedListener {

    private var prs: ArrayList<PullRequestData> = ArrayList()
    private var listener: OnPullRequestInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            prs = it.getParcelableArrayList(ARG_PR)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pull_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_requests.layoutManager = LinearLayoutManager(context)
        val adapter = PullRequestAdapter(prs, context!!, this)
        recycler_requests.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPullRequestInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnPullRequestInteractionListener {
        fun onPRInteraction(pr: PullRequestData)
    }

    override fun onPRItemClicked(pr: PullRequestData) {
        listener?.onPRInteraction(pr)
    }

    companion object {
        @JvmStatic
        fun newInstance(prs: ArrayList<PullRequestData>) =
                PullRequestFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_PR, prs)
                    }
                }
    }
}
