package com.dpoints.dpointsmerchant.utilities

fun <T> isEmptyList(list: List<T>?) : Boolean {
    return list == null || list.isEmpty()
}

fun <T> isNotEmptyList(list: List<T>?) : Boolean {
    return list != null && !list.isEmpty()
}