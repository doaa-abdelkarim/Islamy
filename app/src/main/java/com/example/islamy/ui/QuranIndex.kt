package com.example.islamy.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.holyquran.R
import com.example.islamy.adapter.IndexAdapter
import com.example.islamy.adapter.IndexAdapter.OnItemClickListener
import com.example.islamy.model.Index
import kotlinx.android.synthetic.main.index_list.*


class QuranIndex : Fragment() {

    var listener:OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnFragmentInteractionListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.index_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quran = fillQuranIndexArray()
        buildIndexRecyclerView(quran.index);

    }

    private fun fillQuranIndexArray() = Index(resources.getStringArray(R.array.chapters_names))

    private fun buildIndexRecyclerView(chapters: Array<String>) {
        val indexAdapter = IndexAdapter(chapters)
        indexAdapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener?.onFragmentInteractionListener(position + 1)
            }
        }
        val manager = GridLayoutManager(
            context,
            3,
            GridLayoutManager.HORIZONTAL, true
        )
        recycler_view_index.apply {
            adapter = indexAdapter
            layoutManager = manager
            setHasFixedSize(true)
        }
        LinearSnapHelper().attachToRecyclerView(recycler_view_index)
    }

    companion object {
        fun newInstance() = QuranIndex()
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteractionListener (chapterNumber: Int)
    }
}