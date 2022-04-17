package ru.malushko.rickandmortytesttask.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.malushko.rickandmortytesttask.R
import ru.malushko.rickandmortytesttask.databinding.CharacterItemBinding
import ru.malushko.rickandmortytesttask.domain.Character


class CharactersAdapter(
    private val context: Context?
) : PagingDataAdapter<Character, CharactersAdapter.Holder>(CharactersDiffCallback()) {

    var onCharacterClickListener: OnCharacterClickListener? = null


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val resources = context?.resources
        val character = getItem(position) ?: return
        with(holder.binding) {
            tvItemName.text = resources?.getString(
                R.string.name, character.name
            )
            tvItemSpecies.text = resources?.getString(
                R.string.species, character.species
            )
            tvItemGender.text = resources?.getString(
                R.string.gender, character.gender
            )
            Glide.with(ivItemAva.context)
                .load(character.image)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(ivItemAva)
            root.setOnClickListener {
                onCharacterClickListener?.onCharacterClick(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    class Holder(
        val binding: CharacterItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    interface OnCharacterClickListener {
        fun onCharacterClick(details: Character)
    }
}

// ---

class CharactersDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}