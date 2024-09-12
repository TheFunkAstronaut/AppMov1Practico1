package com.example.trabajopractico1.models

import com.example.trabajopractico1.R

data class Notebook(
    var title: String,
    var content: String,
    var color: Int = R.color.default_notebook_color
)
