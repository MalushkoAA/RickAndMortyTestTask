package ru.malushko.rickandmortytesttask.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.malushko.rickandmortytesttask.R
import ru.malushko.rickandmortytesttask.databinding.FragmentCharactersBinding
import ru.malushko.rickandmortytesttask.domain.Character
import ru.malushko.rickandmortytesttask.ui.CharactersAdapter
import ru.malushko.rickandmortytesttask.ui.adapters.DefaultLoadStateAdapter
import ru.malushko.rickandmortytesttask.ui.adapters.TryAgainAction
import ru.malushko.rickandmortytesttask.ui.viewmodels.MainViewModel


class CharactersFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding ?: throw RuntimeException("FragmentCharactersBinding is null")

    private lateinit var mainLoadStateHolder: DefaultLoadStateAdapter.Holder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupCharactersList()

    }

    private fun setupCharactersList() {

        val adapter = CharactersAdapter(context)
        adapter.onCharacterClickListener = object : CharactersAdapter.OnCharacterClickListener {
            override fun onCharacterClick(details: Character) {
                launchDetailsFragment(details.id!!)
            }
        }

        val tryAgainAction: TryAgainAction = { adapter.retry() }
        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)
        binding.rvCharacters.adapter = adapterWithLoadState

        mainLoadStateHolder = DefaultLoadStateAdapter.Holder(
            binding.viewLoadState,
            tryAgainAction
        )

        observeCharacters(adapter)
        observeLoadState(adapter)

    }

    private fun observeCharacters(adapter: CharactersAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.charactersFlow.collectLatest(adapter::submitData)
        }
    }

    private fun observeLoadState(adapter: CharactersAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
                mainLoadStateHolder.bind(state.refresh)
            }
        }
    }

    private fun launchDetailsFragment(id: Int) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, CharacterDetailsFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

}