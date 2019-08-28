package com.vivospice.sample.common

import com.vivospice.sample.samples.*


object SampleInfos {

    val allSamples = arrayListOf(
            SampleInfo(ApplicationListenerSample::class.java),
            SampleInfo(GdxGeneratedSample::class.java),
            SampleInfo(InputListeningSample::class.java),
            SampleInfo(InputPollingSample::class.java),
            SampleInfo(ModuleInfoSample::class.java),
            SampleInfo(MultiplexerSample::class.java),
            SampleInfo(ReflectionSample::class.java)
    )

    /*fun getSampleNames() : Array<String> {
        val names = arrayListOf<String>()

        allSamples.forEach { names.add(it.name) }

        names.sort()

        return names.toTypedArray()  // convert it to an array of strings, return type as strings.
        // if just toArray then it would be just anarray of objects
    }*/
//=== other way to do the function getSampleNames()
    //.associateBy creates a Map. It will be as if doing val map : Map<String, Class<out SampleInfo>>
    //String is the key and class<out SampleInfo> is the value
    fun getSampleNames() = allSamples.associateBy {
        it.name  // it.name lambda function without a name. .name is the  returned for keys
    }.keys.toList().sorted().toTypedArray()

    fun find(name: String) = allSamples.find { it.name == name }
}