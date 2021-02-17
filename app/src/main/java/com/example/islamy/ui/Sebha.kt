package com.example.islamy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.holyquran.R
import com.example.islamy.util.KEY_TASBIHA_COUNT
import com.example.islamy.util.KEY_TOTAL_COUNT
import kotlinx.android.synthetic.main.sebha.*


class Sebha : Fragment() {

    var tasbihaCount = 0
    var totalCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasbihaCount = savedInstanceState?.getInt(KEY_TASBIHA_COUNT) ?: 0
        totalCount = savedInstanceState?.getInt(KEY_TOTAL_COUNT) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sebha, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonTasbihaCount = button_tasbiha_count
        val buttonTotalCount = button_total_count

        buttonTasbihaCount.text = tasbihaCount.toString()
        buttonTotalCount.text = totalCount.toString()

        buttonTasbihaCount.setOnClickListener {
            (it as Button).text = (++tasbihaCount).toString()
            buttonTotalCount.text = (++totalCount).toString()
        }

        val spinnerTasbih = spinner_tasbih
        spinnerTasbih.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, p2: Int, p3: Long) {
                view?.apply {
                    savedInstanceState?.apply {
                        tasbihaCount = getInt(KEY_TASBIHA_COUNT)
                        clear()
                    } ?: run { tasbihaCount = 0 }
                    buttonTasbihaCount.text = tasbihaCount.toString()
                }
            }
        }

        val imageViewReset = image_view_reset
        imageViewReset.setOnClickListener {
            tasbihaCount = 0
            totalCount = 0
            buttonTasbihaCount.text = tasbihaCount.toString()
            buttonTotalCount.text = totalCount.toString()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TASBIHA_COUNT, tasbihaCount)
        outState.putInt(KEY_TOTAL_COUNT, totalCount)
    }

    companion object {
        fun newInstance() = Sebha()
    }

}