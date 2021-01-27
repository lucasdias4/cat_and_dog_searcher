package com.lucasdias.extensions

import android.R
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

fun Spinner.setup(context: Context, options: List<String>, onItemSelectedAction: (String?) -> Unit) {
    val adapter = ArrayAdapter<String>(
        context,
        R.layout.simple_spinner_dropdown_item,
        options
    )
    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    this.adapter = adapter
    this.onItemSelectedListener = this.createListener(onItemSelectedAction)
}

private fun Spinner.createListener(onItemSelectedAction: (String?) -> Unit): AdapterView.OnItemSelectedListener {
    return object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            onItemSelectedAction(parent?.getItemAtPosition(position) as String?)
        }
    }
}
