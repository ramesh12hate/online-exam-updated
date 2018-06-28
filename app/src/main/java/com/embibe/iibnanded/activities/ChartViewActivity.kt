package com.embibe.iibnanded.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.embibe.iibnanded.R
import com.github.mikephil.charting.animation.EasingFunction
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_chart_view.*
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import java.util.ArrayList


class ChartViewActivity : AppCompatActivity(), OnChartValueSelectedListener {

    private var mParties = arrayOf("Correct", "Incorrect", "Reviewed", "Not Reviewed", "Attempted", "Not Attempted")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_view)

        toolbar.title = "Graphical View"
        setSupportActionBar(toolbar)

        initData()
    }

    private fun initData() {
        pie_chart.setUsePercentValues(true)
        pie_chart.description.isEnabled = false
        pie_chart.setExtraOffsets(5f, 10f, 5f, 5f)

        pie_chart.dragDecelerationFrictionCoef = 0.95f


        pie_chart.isDrawHoleEnabled = false
        pie_chart.setHoleColor(Color.WHITE)

        pie_chart.setTransparentCircleColor(Color.WHITE)
        pie_chart.setTransparentCircleAlpha(110)

        pie_chart.holeRadius = 58f
        pie_chart.transparentCircleRadius = 61f

        pie_chart.setDrawCenterText(true)

        pie_chart.rotationAngle = 0f
        // enable rotation of the chart by touch
        pie_chart.isRotationEnabled = true
        pie_chart.isHighlightPerTapEnabled = true

        // pie_chart.setUnit(" €");
        // pie_chart.setDrawUnitsInChart(true);

        // add a selection listener
        pie_chart.setOnChartValueSelectedListener(this)

        setData(4, 100f)

        pie_chart.animateY(1400, EaseInOutQuad)

        val l = pie_chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling
        pie_chart.setEntryLabelColor(Color.WHITE)
        pie_chart.setEntryLabelTextSize(12f)
    }


    private fun setData(count: Int, range: Float) {

        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(PieEntry((Math.random() * range + range / 5).toFloat(),
                    mParties[i % mParties.size],
                    resources.getDrawable(R.drawable.app_icon)))
        }

        val dataSet = PieDataSet(entries, "Test Result")

        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors

        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors

        //dataSet.setSelectionShift(0f);

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        pie_chart.data = data

        // undo all highlights
        pie_chart.highlightValues(null)

        pie_chart.invalidate()
    }


    override fun onNothingSelected() {

    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    val EaseInOutQuad: EasingFunction = EasingFunction { input ->
        var input = input
        input *= 2f

        if (input < 1f) {
            0.5f * input * input
        } else -0.5f * (--input * (input - 2f) - 1f)
    }

}