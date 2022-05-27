package com.suki.thewheel.presentation.entries

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.suki.thewheel.R
import com.suki.thewheel.databinding.FragmentEntryListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryListFragment : Fragment() {
    private lateinit var binding: FragmentEntryListBinding
    private val viewModel: EntryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentEntryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservables()

        binding.addButton.setOnClickListener {
            val entry = binding.editTextTextPersonName.text.toString()
            if (entry.isNotEmpty()) {
                viewModel.insertWheelEntry(entry)
                binding.editTextTextPersonName.text.clear()
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_lets_go).isEnabled = viewModel.isEntryListValid()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_entries, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_lets_go -> {
                findNavController().navigate(R.id.action_manageEntriesFragment_to_wheelFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeToObservables() {
        viewModel.entries.observe(viewLifecycleOwner) {
            activity?.invalidateOptionsMenu()
        }
    }
}