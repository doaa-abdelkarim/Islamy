package com.example.islamy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holyquran.R
import com.example.islamy.adapter.ChapterAdapter
import com.example.islamy.util.CustomDividerItemDecoration
import com.example.islamy.util.KEY_CHAPTER_NUMBER
import kotlinx.android.synthetic.main.chapter_ahadeth_list.*

class QuranChapterView : Fragment() {

    private var chapterNumber: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            chapterNumber = it.getInt(KEY_CHAPTER_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chapter_ahadeth_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quranChapterViewModel = QuranChapter()
        val chapter = quranChapterViewModel
            .fillSelectedChapterSentencesList(context!!, chapterNumber)
        setChapterNameTextView(chapter.name);
        buildChapterRecyclerView(chapter.sentences)
    }

    private fun setChapterNameTextView(chapterName: String) {
        text_view_chapter_name.text = "${getString(R.string.chapter_name)} $chapterName"
    }

    private fun buildChapterRecyclerView(chapterSentences: List<String>) {
        val chapterAdapter = ChapterAdapter(chapterSentences)
        val manager = LinearLayoutManager(context)
        recycler_view_chapter_ahadeth.apply {
            adapter = chapterAdapter
            layoutManager = manager
            setHasFixedSize(true)
            addItemDecoration(CustomDividerItemDecoration(
                context.getDrawable(R.drawable.recycler_divider)!!))
        }
    }

    companion object {
        fun newInstance(chapterNumber: Int) =
            QuranChapterView().apply {
                arguments = Bundle().apply {
                    putInt(KEY_CHAPTER_NUMBER, chapterNumber)
                }
            }
    }
}