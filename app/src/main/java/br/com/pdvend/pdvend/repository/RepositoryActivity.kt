package br.com.pdvend.pdvend.repository

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import br.com.pdvend.pdvend.R
import br.com.pdvend.pdvend.api.RepositoryData
import br.com.pdvend.pdvend.list.ListActivity
import kotlinx.android.synthetic.main.activity_main.*

class RepositoryActivity : AppCompatActivity(), RepositoryView {

    private val ARG_LOGIN = "arg_login"
    private val ARG_NAME = "arg_name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = RepositoryPresenterImpl(this)

        var repositoryText = searchRepositoryEditText.text.toString()

        searchRepositoryEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                repositoryText = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //no op
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //no op
            }

        })

        searchRepositoryButton.setOnClickListener {
            if (repositoryText.isNotEmpty()) {
                presenter.loadRepository(repositoryText)
            }
        }
    }

    override fun showLoading() {
        searchRepositoryLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        searchRepositoryLoading.visibility = View.GONE
    }

    override fun showRepository(repository: RepositoryData) {
        val login = repository.items[0].owner.login
        val name = repository.items[0].name
        Toast.makeText(this, login + "/" + name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ListActivity::class.java)
        intent.putExtra(ARG_LOGIN, login)
        intent.putExtra(ARG_NAME, name)
        startActivity(intent)
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
