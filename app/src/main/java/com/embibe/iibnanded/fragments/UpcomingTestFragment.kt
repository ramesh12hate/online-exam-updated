package com.embibe.iibnanded.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.embibe.iibnanded.R

class UpcomingTestFragment : Fragment() {
    private lateinit var mMainView:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mMainView = inflater.inflate(R.layout.fragment_upcoming_test , container , false)
        return mMainView
    }
}