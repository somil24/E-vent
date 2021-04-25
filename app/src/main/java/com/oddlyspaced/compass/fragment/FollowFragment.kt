package com.oddlyspaced.compass.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.oddlyspaced.compass.Global
import com.oddlyspaced.compass.R
import com.oddlyspaced.compass.custom.adapter.EventAdapter
import com.oddlyspaced.compass.databinding.FragmentFollowBinding

class FollowFragment: Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private val sampleTags = arrayListOf("tag1", "tag2")

    companion object {
        @JvmStatic
        fun newInstance(): FollowFragment {
            return FollowFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFollowBinding.bind(LayoutInflater.from(context).inflate(R.layout.fragment_follow, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateChips(sampleTags)

        binding.rvFollowEvents.layoutManager = LinearLayoutManager(context)
        binding.rvFollowEvents.adapter = EventAdapter(ArrayList(Global.getMockData().filter { item ->
            item.tags.containsAll(sampleTags)
        }), activity)
    }

    private fun populateChips(chipTitles: ArrayList<String>) {
        chipTitles.forEachIndexed { index, str ->
            val chip = LayoutInflater.from(context).inflate(R.layout.item_chip, null, false) as Chip
            chip.text = str
            chip.setOnCloseIconClickListener {
                binding.chipGroup.removeViewAt(index)
                sampleTags.removeAt(index)
                binding.chipGroup.removeAllViews()
                populateChips(sampleTags)
            }
            binding.chipGroup.addView(chip)
        }
    }

}