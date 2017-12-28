package com.thebali.greatone

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.thebali.greatone.model.api.AlphaApiClass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.grid_item.*

class MainActivity : AppCompatActivity() {
    val urla = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=INR&apikey=05QJXK46QPAXOU2O"

    val test1 = "05QJXK46QPAXOU2O"
    ////////////////////////////////////////////////////////////////////////////

    lateinit var listView: ListView
    lateinit var gridAdapter1: ArrayAdapter<String>
    lateinit var spinAdapter1: ArrayAdapter<String>
    lateinit var spinAdapter2: ArrayAdapter<String>


    var listmap: ArrayList<String> = ArrayList()  // this is initialisation

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


        // put data in the hashmap    ----- this data gets into the gridview
        listmap.add("exchangeRate")
        listmap.add("currency name")
        listmap.add("currency name")
        listmap.add("currency name")


        //grid view
        gridAdapter1 = ArrayAdapter(this, R.layout.grid_item, listmap)
        gridview1.adapter = gridAdapter1
        // try this thing... val mutableList : MutableList<Kolory> = ArrayList()


        // class object declaration
        val api = AlphaApiClass()


        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                b = spinner1.selectedItem.toString()
                api.exchange(a, b, c)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ item1 ->
                            Toast.makeText(this@MainActivity, "my message", Toast.LENGTH_LONG).show()

                            // this data is not getting written to the gridview
                            listmap[0] = item1.exchangeRate
                            listmap[1] = item1.toCurrencyName
                            listmap[2] = "bali is getting through"
                            listmap[3] = item1.lastRefreshed
                            textview1.text = item1.fromCurrencyName
                        }, { e ->
                            e.printStackTrace()
                        }, {
                            gridview1.adapter = gridAdapter1
                        })


                //Toast.makeText(this@MainActivity, "You have Selected " + spinner1.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }



        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                c = spinner2.selectedItem.toString()
                api.exchange(a, b, c)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ item1 ->
                            listmap[0] = item1.exchangeRate
                            listmap[1] = item1.toCurrencyName
                            listmap[2] = "bali is getting through"
                            listmap[3] = item1.lastRefreshed
                        }, { e ->
                            e.printStackTrace()
                        }, {
                            gridview1.adapter = gridAdapter1
                        })
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

}
