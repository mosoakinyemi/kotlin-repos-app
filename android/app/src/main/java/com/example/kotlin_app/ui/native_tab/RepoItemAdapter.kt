package com.example.kotlin_app.ui.native_tab


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.data.models.RepoItem
import com.example.kotlin_app.databinding.RepoItemBinding
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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
        @SuppressLint("NewApi")
        fun bind(repo: RepoItem) {
            binding.apply {

                val formatter = DateTimeFormatter.ofPattern("MMM dd yyyy")
                val createdAt = formatter.format(OffsetDateTime.parse(repo.created_at))

                texViewTitle.text = repo.name
                texViewOwner.text = "owner: "+ repo.owner.login
                texViewCreatedAt.text = "Created at: " + createdAt
                texViewStars.text = "Stars: " + repo.stargazers_count
                Picasso.get().load(repo.owner.avatar_url).into(userAvatar)

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
