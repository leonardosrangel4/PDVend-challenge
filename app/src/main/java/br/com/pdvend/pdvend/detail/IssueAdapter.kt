package br.com.pdvend.pdvend.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.pdvend.pdvend.R
import br.com.pdvend.pdvend.api.Issue
import br.com.pdvend.pdvend.extensions.listen
import kotlinx.android.synthetic.main.pr_item.view.*


class IssueAdapter(private val issues: ArrayList<Issue>,
                   private val context: Context,
                   private val listener: OnItemClickedListener): RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pr_item, parent, false)
        return IssueViewHolder(view).listen { position, _ ->
            val issue = issues[position]
            listener.onIssueItemClicked(issue)
        }
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val issue = issues[position]
        holder.title.text = issue.title
        holder.number.text = "#" + issue.number.toString()
    }

    class IssueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.title
        val number: TextView = itemView.number
    }

    interface OnItemClickedListener {
        fun onIssueItemClicked(issue: Issue)
    }
}