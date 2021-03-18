package com.example.gradleplugintestapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun test(){
    contract {
        returnsNotNull()
    }
}

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}