package com.vivospice.sample.utils

import com.badlogic.gdx.Input.Keys.T
import com.badlogic.gdx.utils.Logger

//fun <T : Any> logger(clazz: Class<T>) : Logger = Logger(clazz.simpleName, Logger.DEBUG)
inline fun <reified: Any> logger() : Logger = Logger(T::class.java.simpleName, Logger.DEBUG)
//reified gives us ability to use a type that we specify in <> directly in our method.
//inline needs to preceed the method.