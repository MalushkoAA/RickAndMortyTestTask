package ru.malushko.rickandmortytesttask.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.malushko.rickandmortytesttask.R
import ru.malushko.rickandmortytesttask.databinding.FragmentCharacterDetailsBinding
import ru.malushko.rickandmortytesttask.ui.viewmodels.MainViewModel


class CharacterDetailsFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding: FragmentCharacterDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterDetailsBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = getCharacterId()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getHero(characterId)

        mainViewModel.hero.observe(viewLifecycleOwner) {
            with(binding) {
                tvDetailsName.text = resources.getString(R.string.name, it.name)
                tvDetailsSpecies.text = resources.getString(R.string.species, it.species)
                tvDetailsGender.text = resources.getString(R.string.gender, it.gender)
                tvDetailsStatus.text = resources.getString(R.string.status, it.status)
                tvDetailsLocation.text =
                    resources.getString(R.string.location, it.location?.name)
                tvDetailsEpisodes.text =
                    resources.getString(R.string.episodes, it.episode?.size.toString())
                Glide.with(ivDetailsCharacter.context)
                    .load(it.image)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivDetailsCharacter)
            }
        }
    }

    private fun getCharacterId(): Int {
        return requireArguments().getInt(EXTRA_NAME, DEF_VALUE)
    }

    companion object {
        private const val EXTRA_NAME = "id"
        private const val DEF_VALUE = 1

        fun newInstance(id: Int): CharacterDetailsFragment {
            return CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_NAME, id)
                }
            }
        }
    }
}