package com.suki.thewheel.presentation.wheel

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.suki.spinning_wheel.SpinningWheelView
import com.suki.thewheel.R
import com.suki.thewheel.databinding.FragmentWheelBinding


class WheelFragment : Fragment() {

    private val maAngle = 50f
    private val duration = 4000L
    private val timeForRotation = 50L

    lateinit var binding: FragmentWheelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentWheelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWheel()
        binding.buttonSpin.setOnClickListener {
            binding.wheel.rotate(maAngle, duration, timeForRotation)
        }
    }

    private fun setUpWheel(){
        binding.wheel.apply {
            isEnabled = false
            items = arrayListOf("sads", "asdas")
            onRotationListener = (object : SpinningWheelView.OnRotationListener<String?> {

                override fun onRotation() {
                    binding.textViewWinner.visibility = View.INVISIBLE
                }

                override fun onStopRotation(item: String?) {
                    binding.textViewWinner.visibility = View.VISIBLE
                    binding.textViewWinner.text = item
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_wheel, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_manage -> {
                findNavController().navigate(R.id.action_wheelFragment_to_manageEntriesFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}