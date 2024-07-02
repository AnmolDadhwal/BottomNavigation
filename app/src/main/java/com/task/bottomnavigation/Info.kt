package com.task.bottomnavigation

data class Info(
    var name: String?="",
    var number:Int?=0,
    ){
    override fun toString(): String {
        return "$name $number"
    }
}
