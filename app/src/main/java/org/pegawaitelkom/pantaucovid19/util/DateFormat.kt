@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package org.pegawaitelkom.pantaucovid19.util
import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun convertWaktu(systemTime: Long): String {
    return SimpleDateFormat("EEEE, dd MMMM yyyy")
        .format(systemTime).toString()
}

@SuppressLint("SimpleDateFormat")
fun convertWaktuNews(itemWaktu: String): String {
    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date: Date =
        dateFormat.parse(itemWaktu) //You will get date object relative to server/client timezone wherever it is parsed

    val formatter: DateFormat =
        SimpleDateFormat("dd-MM-yyyy") //If you need time just put specific format for time like 'HH:mm:ss'

    return formatter.format(date)
}