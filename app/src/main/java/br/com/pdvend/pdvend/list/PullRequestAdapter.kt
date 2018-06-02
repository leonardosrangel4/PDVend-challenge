package br.com.pdvend.pdvend.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.pdvend.pdvend.R
import br.com.pdvend.pdvend.api.PullRequestData
import br.com.pdvend.pdvend.extensions.listen
import kotlinx.android.synthetic.main.pr_item.view.*


class PullRequestAdapter(private val pullRequests: List<PullRequestData>,
                         private val context: Context,
                         private val listener: OnItemClickedListener): RecyclerView.Adapter<PullRequestAdapter.RequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pr_item, parent, false)
        return RequestViewHolder(view).listen { position, _ ->
            val pr = pullRequests[position]
            listener.onPRItemClicked(pr)
        }
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val pr = pullRequests[position]
        holder.title.text = pr.title
        holder.number.text = "#" + pr.number.toString()
    }

    class RequestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.title
        val number: TextView = itemView.number

    }

    interface OnItemClickedListener {
        fun onPRItemClicked(pr: PullRequestData)
    }
}