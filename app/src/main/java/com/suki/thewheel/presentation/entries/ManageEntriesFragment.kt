package com.suki.thewheel.presentation.entries

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.suki.thewheel.R
import com.suki.thewheel.databinding.FragmentManageEntriesBinding

class ManageEntriesFragment : Fragment() {
    lateinit var binding: FragmentManageEntriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentManageEntriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}