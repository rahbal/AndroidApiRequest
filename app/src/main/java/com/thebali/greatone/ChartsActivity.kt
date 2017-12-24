package com.thebali.greatone

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
    }

//    val datap = DataPoint[](
//        DataPoint(0, 1),
//        DataPoint(5, 1),
//        DataPoint(6, 1),
//        DataPoint(5, 6),
//        DataPoint(2, 8)
//    )

//        val series = LineGraphSeries<DataPoint>()
//        graph1.addSeries(series)

}