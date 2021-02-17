package com.example.islamy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holyquran.R
import com.example.islamy.adapter.HadethAdapter
import com.example.islamy.model.Hadeth
import com.example.islamy.util.CustomDividerItemDecoration
import kotlinx.android.synthetic.main.chapter_ahadeth_list.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset

class Ahadeth : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chapter_ahadeth_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAhadethImageViewResourceId()
        val ahadeth = fillAHadethList()
        buildChapterRecyclerView(ahadeth)
    }

    private fun fillAHadethList(): ArrayList<Hadeth> {
        val ahadeth = ArrayList<Hadeth>()
        BufferedReader(
            InputStreamReader(
                context?.assets?.open("ahadeth.txt"),
                Charset.forName("UTF-8")
            )
        ).use {
            var line: String? = it.readLine()
            while (line != null) {
                val stringBuffer = StringBuffer()
                while (!line.equals("#")) {
                    stringBuffer.append(line).append("\n")
                    line = it.readLine()
                }
                ahadeth.add(Hadeth(stringBuffer.toString()))
                line = it.readLine()
            }
            return ahadeth
        }
    }

    private fun setAhadethImageViewResourceId(){
        image_view_ahadeth.setImageResource(R.drawable.hadith)
    }

    private fun buildChapterRecyclerView(ahadeth: List<Hadeth>) {
        val hadethAdapter = HadethAdapter(ahadeth)
        val manager = LinearLayoutManager(context)
        recycler_view_chapter_ahadeth.apply {
            adapter = hadethAdapter
            layoutManager = manager
            setHasFixedSize(true)
            addItemDecoration(
                CustomDividerItemDecoration(
                context.getDrawable(R.drawable.recycler_divider)!!)
            )
        }
    }

    companion object {
        fun newInstance() = Ahadeth()
    }
}