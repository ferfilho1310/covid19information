package com.example.covid19status.Activity

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.covid19status.R
import com.example.covid19status.Util.ChartUtil
import com.example.covid19status.ViewModels.ViewModelCovid
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val mainActivityViewModel: ViewModelCovid by viewModel()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.hide()

        ChartUtil.configuracaoGrafico(piechart, "Mortes de COVID-19 por Estado*")
        ChartUtil.configuracaoLegenda(piechart)
        ChartUtil.configuracaoGrafico(barchartDadosMundo, "Mortes de COVID-19 por País*")
        ChartUtil.configuracaoLegenda(barchartDadosMundo)
        ChartUtil.configuracaoGrafico(piechartDeathCovidDate, "Mortes por COVID-19 por Data")
        ChartUtil.configuracaoLegenda(piechartDeathCovidDate)

        setObservers()

        data.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.data -> datePicker()
        }
    }

    fun setObservers() {
        mainActivityViewModel.covidBr.observe(this, {
            ChartUtil.morteCovidPorEstado(it, piechart)
        })

        mainActivityViewModel.covidWorld.observe(this, {
            ChartUtil.morteCovidPorPais(it, barchartDadosMundo)
        })

        mainActivityViewModel.covidMortePorData.observe(this, {
            txt_data.visibility = View.VISIBLE
            ChartUtil.morteCovidPorBrazilPorData(it, piechartDeathCovidDate)
        })

        mainActivityViewModel.error.observe(this, {
            Toast.makeText(this, "Error ao buscar os dados $it", Toast.LENGTH_SHORT).show()
        })
    }

    fun datePicker() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->

            val mes = if (monthOfYear.toString().length == 1) {
                String.format("0$monthOfYear")
            } else {
                monthOfYear.toString()
            }

            mainActivityViewModel.covidBrazilPorData(
                String.format(year.toString() + mes + dayOfMonth.toString())
            )

            txt_data.text = String.format("$year/$mes/$dayOfMonth")

        }, year, month, day)

        dpd.show()
    }

}