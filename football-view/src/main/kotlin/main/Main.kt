package main

import football.FootballApp

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = FootballApp()
            app.mainStart(args)
        }
    }
}