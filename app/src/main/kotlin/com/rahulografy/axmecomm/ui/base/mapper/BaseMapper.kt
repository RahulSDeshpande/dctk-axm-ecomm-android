package com.rahulografy.axmecomm.ui.base.mapper

interface BaseMapper<in I, out O> {

    fun map(input: I?): O?
}