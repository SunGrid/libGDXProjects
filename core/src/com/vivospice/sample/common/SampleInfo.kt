package com.vivospice.sample.common

class SampleInfo(val clazz: Class<out SampleBase>) { //<out SampleBase> accepts any subclass of SampleBase can be used to create sample info
    val name: String = clazz.simpleName

}