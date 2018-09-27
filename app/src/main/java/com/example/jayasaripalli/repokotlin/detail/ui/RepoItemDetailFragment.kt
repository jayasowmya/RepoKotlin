package com.example.jayasaripalli.repokotlin.detail.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jayasaripalli.repokotlin.R
import com.example.jayasaripalli.repokotlin.repolist.model.Repository
import kotlinx.android.synthetic.main.activity_repoitem_detail.*
import kotlinx.android.synthetic.main.repoitem_detail.*

/**
 * A fragment representing a single RepoItem detail screen.
 * This fragment is either contained in a [RepoItemListActivity]
 * in two-pane mode (on tablets) or a [RepoItemDetailActivity]
 * on handsets.
 */
class RepoItemDetailFragment : Fragment() {


    private var repository: Repository? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                repository = it.getParcelable(ARG_ITEM_ID)

                activity?.toolbar_layout?.title = repository?.name
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.repoitem_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository?.let {
            repo_name.text = it.name
            num_forks.text = getString(R.string.txt_fork_count, it.forks_count)
            created_time.text = getString(R.string.txt_created_at, it.created_at)
            watchers_count.text = getString(R.string.txt_watchers_count, it.watchers_count)
            repo_description.text = getString(R.string.txt_description, it.description)
        }

    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
