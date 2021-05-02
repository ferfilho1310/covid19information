package com.example.covid19status.Activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.covid19status.R
import com.example.covid19status.Util.ChartUtil
import com.example.covid19status.ViewModels.ViewModelCovid
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module



class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: ViewModelCovid by viewModel()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.hide()

        ChartUtil.configuracaoGrafico(piechart, "Mortes de COVID-19 por Estado*")
        ChartUtil.configuracaoLegenda(piechart)
        ChartUtil.configuracaoGrafico(barchartDadosMundo, "Mortes de COVID-19 por País*")
        ChartUtil.configuracaoLegenda(barchartDadosMundo)

        mainActivityViewModel.covidBr.observe(this, {
            ChartUtil.morteCovidPorEstado(it, piechart)
        })

        mainActivityViewModel.covidWorld.observe(this, {
            ChartUtil.morteCovidPorPais(it, barchartDadosMundo)
        })

        mainActivityViewModel.error.observe(this, {
            Toast.makeText(this,"Error ao buscar os dados $it",Toast.LENGTH_SHORT).show()
        })
    }
}