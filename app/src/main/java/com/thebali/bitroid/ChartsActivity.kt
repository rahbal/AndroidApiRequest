package com.thebali.bitroid

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_charts.*

/**
 * Created by thebali on 24/12/17.
 */
class ChartsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_charts)

        val datap = arrayOf(DataPoint(0.0, 1.0), DataPoint(5.0, 1.0))
        val seri = LineGraphSeries<DataPoint>(datap)
        graphView1.addSeries(seri)

    }
}
