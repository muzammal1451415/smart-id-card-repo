package com.smart.id.cards.data

interface AttendanceListener {
    fun onSuccess(list:MutableList<Attendance>)
    fun onFailure(message:String)
    fun onProgress()
}