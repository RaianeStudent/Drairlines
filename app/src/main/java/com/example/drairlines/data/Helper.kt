package com.example.drairlines.data

class Helper {

    companion object{

            @Volatile
            var INSTANCE : Helper? = null


            lateinit var data : String

            fun getInstance(): Helper {
                return INSTANCE?: synchronized(this){
                    val instance = Helper()
                    INSTANCE = instance
                    instance
                }
            }

        }


}