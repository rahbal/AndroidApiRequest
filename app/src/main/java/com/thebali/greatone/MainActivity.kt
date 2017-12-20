package com.thebali.greatone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thebali.greatone.R.string.av_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    var disposable: Disposable? = null

    val urla = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_INTRADAY&symbol=BTC&market=CNY&apikey=05QJXK46QPAXOU2O"

    val test1 = "05QJXK46QPAXOU2O"

    val AlphaApiServe by lazy {
        AlphaApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // this will run always
        beginSearch()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun beginSearch() {
        disposable = AlphaApiServe.hitCountCheck("DIGITAL_CURRENCY_INTRADAY", "BTC", "CNY", "05QJXK46QPAXOU2O")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> responseView.text = "${result}" },
                        { error -> responseView.text = error.message }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
