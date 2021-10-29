package com.smart.id.cards.data

import com.google.gson.annotations.SerializedName

data class Attendance(
    @SerializedName("in") var studentIn:String,
    @SerializedName("out") var studentOut:String,
    @SerializedName("username") var studentUsername:String,
    @SerializedName("student_name") var studentName:String
){
    constructor():this("","","","")
}