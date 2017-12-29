package com.thebali.bitroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.thebali.bitroid.model.api.AlphaApiClass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val urla = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=INR&apikey=05QJXK46QPAXOU2O"

    val test1 = "05QJXK46QPAXOU2O"
    ////////////////////////////////////////////////////////////////////////////

    lateinit var gridAdapter1: ArrayAdapter<String>
    lateinit var spinAdapter1: ArrayAdapter<String>
    lateinit var spinAdapter2: ArrayAdapter<String>


//    lateinit var listmap: ArrayList<String>  // this is initialisation

    var a = "CURRENCY_EXCHANGE_RATE"
    var b = "USD"
    var c = "INR"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button onclick to the charts activity
        button1.setOnClickListener {
            val tent = Intent(this, ChartsActivity::class.java)
            //sintent.putExtra("key", b)
            this.startActivity(tent)
        }


        //spinner
        spinAdapter1 = ArrayAdapter(this, R.layout.spinner_item, resources.getStringArray(R.array.crypto_array))
        spinner1.adapter = spinAdapter1

        spinAdapter2 = ArrayAdapter(this, R.layout.spinner_item, resources.getStringArray(R.array.crypto_array))
        spinner2.adapter = spinAdapter2

        // class object declaration
        val api = AlphaApiClass()

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                b = spinner1.selectedItem.toString()
                api.exchange(a, b, c)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ item1 ->
                            textView.text = item1.exchangeRate
                            textView3.text = item1.toCurrencyName
                            textView5.text = item1.fromCurrencyName
                            textView7.text = item1.timeZone
                            textView9.text = item1.lastRefreshed
                        }, { e ->
                            e.printStackTrace()
                        }, {
                            Toast.makeText(this@MainActivity, "spinner 1", Toast.LENGTH_LONG).show()
                        })


                //Toast.makeText(this@MainActivity, "You have Selected " + spinner1.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                textView3.text = getString(R.string.sample_text)
            }
        }



        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                c = spinner2.selectedItem.toString()
                api.exchange(a, b, c)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ item1 ->
                            textView.text = item1.exchangeRate
                            textView3.text = item1.toCurrencyName
                            textView5.text = item1.fromCurrencyName
                            textView7.text = item1.timeZone
                            textView9.text = item1.lastRefreshed

                        }, { e ->
                            e.printStackTrace()
                        }, {
                            Toast.makeText(this@MainActivity, "spinner2", Toast.LENGTH_LONG).show()
                        })
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                textView3.text = getString(R.string.sample_text)
            }
        }

    }

}
