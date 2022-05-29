package com.suki.thewheel.presentation.entries

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.suki.thewheel.R
import com.suki.thewheel.databinding.FragmentEntryListBinding
import com.suki.thewheel.domain.model.WheelEntry
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryListFragment : Fragment() {
    private lateinit var binding: FragmentEntryListBinding
    private val viewModel: EntryListViewModel by viewModels()
    private lateinit var entryAdapter : EntryAdapter
    private var isMenuEnabled = true

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

        entryAdapter = EntryAdapter { entry ->
            adapterOnClick(entry)
        }

        binding.entryRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                activity
            )
            adapter = entryAdapter
        }

        binding.entryRecyclerView.adapter = entryAdapter

        subscribeToObservables()

        binding.addButton.setOnClickListener {
            val entry = binding.entryInputLayout.editText?.text.toString()
            if (entry.isNotBlank()) {
                viewModel.insertWheelEntry(entry)
                binding.entryInputLayout.editText?.text?.clear()
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_lets_go).isEnabled = isMenuEnabled
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

    private fun adapterOnClick(entry: WheelEntry) {
        viewModel.deleteEntry(entry)
    }

    private fun subscribeToObservables() {
        viewModel.entries.observe(viewLifecycleOwner) {

            isMenuEnabled = viewModel.isEntryListValid(it.size)
            activity?.invalidateOptionsMenu()

            entryAdapter.submitList(it as MutableList<WheelEntry>)

            if(it.isEmpty()){
                binding.emptyMessage.visibility = View.VISIBLE
                binding.entryRecyclerView.visibility = View.INVISIBLE
            }else {
                binding.emptyMessage.visibility = View.INVISIBLE
                binding.entryRecyclerView.visibility = View.VISIBLE
            }

            if (viewModel.hasReachedLimit(it.size)) {
                binding.apply {
                    addButton.isEnabled = false
                    entryInputLayout.isEnabled = false
                    entryInputLayout.error = getString(R.string.has_reached_limit)
                }
            }else {
                binding.apply {
                    addButton.isEnabled = true
                    entryInputLayout.isEnabled = true
                    entryInputLayout.isErrorEnabled = false
                }
            }
        }
    }
}