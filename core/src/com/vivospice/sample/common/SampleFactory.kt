package com.vivospice.sample.common

import com.badlogic.gdx.utils.reflect.ClassReflection

object SampleFactory {

    fun newSample(name: String) : SampleBase {
        val info = SampleInfos.find(name)
        return ClassReflection.newInstance(info?.clazz)
    }
}