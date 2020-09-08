package com.rahulografy.axmecomm.ui.base

interface BaseMapper<in I, out O> {

    fun map(input: I): O
}