package main

import helloworld.HelloWorldApp

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = HelloWorldApp()
            app.launchLaunch(args)
        }
    }
}