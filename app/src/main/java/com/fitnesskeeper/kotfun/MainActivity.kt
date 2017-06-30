package com.fitnesskeeper.kotfun

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    @BindView(R.id.textView) lateinit var textView: TextView
    @BindView(R.id.textView2) lateinit var textView2: TextView
    @BindView(R.id.textView3) lateinit var textView3: TextView

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @SuppressLint("SetTextI18n")
    @OnClick(R.id.doSomething)
    fun doSomething(@Suppress("UNUSED_PARAMETER") button: Button)
    {
        val money = Money(counter.toDouble(), "$")
//        val money = MoneyJava(counter.toDouble(), "$")
        textView?.text = "Hello world ${money}! / rawAmt=${money.amount}"
        textView2?.text = "type=${money.javaClass.simpleName}"
        textView3?.text = "10 percent of = ${10.0 percentOf money}"
        ++counter
    }
}
