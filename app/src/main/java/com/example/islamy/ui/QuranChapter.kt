package com.example.islamy.ui

import android.content.Context
import com.example.holyquran.R
import com.example.islamy.model.Chapter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset

class QuranChapter {

    fun fillSelectedChapterSentencesList(context: Context, chapterNumber: Int): Chapter {
        val chapterSentences = ArrayList<String>()
        BufferedReader(
            InputStreamReader(
                context?.assets?.open("$chapterNumber.txt"),
                Charset.forName("UTF-8")
            )
        ).use {
            var line: String? = it.readLine()
            while (line != null) {
                chapterSentences.add(line)
                line = it.readLine()
            }
            return Chapter(
                context.resources.getStringArray(R.array.chapters_names)[chapterNumber - 1],
                chapterSentences
            )
        }
    }
}