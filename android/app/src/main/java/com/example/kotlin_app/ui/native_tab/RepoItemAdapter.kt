package com.example.kotlin_app.ui.native_tab


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.data.models.RepoItem
import com.example.kotlin_app.databinding.RepoItemBinding
import com.squareup.picasso.Picasso

interface OnItemClickListener{
    fun onItemClick (repo: RepoItem)
}


class RepoItemAdapter( private val listener:OnItemClickListener
) : ListAdapter<RepoItem, RepoItemAdapter.ReposViewHolder>(ReposComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding =
            RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder(binding,
            onItemClick = { position ->
                val repo = getItem(position)
                if (repo != null) {
                    listener.onItemClick(repo)
                }
            })
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class ReposViewHolder(private val binding: RepoItemBinding,
                          private val onItemClick: (Int) -> Unit,
    ) :  RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: RepoItem) {
            binding.apply {
                Picasso.get().load(repo.owner.avatar_url).into(userAvatar)
                texViewOwner.text = repo.owner.login
                texViewTitle.text = repo.full_name
            }
        }

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClick(position)
                    }
                }
            }

        }
    }


    class ReposComparator : DiffUtil.ItemCallback<RepoItem>() {
        override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem) =
            oldItem == newItem
    }
}
