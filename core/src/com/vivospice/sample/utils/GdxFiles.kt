package com.vivospice.sample.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle


//extention function. When writing extention functions must specify type like String
//FileHandle is the return type created by libGdx to handle null possibilities
fun String.toInternalFile() : FileHandle = Gdx.files.internal(this)