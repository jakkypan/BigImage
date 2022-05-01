package com.bigimage.client

import android.content.Context
import android.os.MemoryFile
import android.os.ParcelFileDescriptor
import java.io.FileDescriptor

/**
 *
 * @author hongchao.pan
 * created at 2022/4/23 9:27 下午
 */
object MemoryFileHelper {
    fun buildMemoryFile(context: Context, fileName: String): ParcelFileDescriptor {
        val inputStream = context.assets.open(fileName)
        val byteArray =inputStream.readBytes()
        val memoryFile = MemoryFile("client_image", byteArray.size)
        memoryFile.writeBytes(byteArray, 0, 0, byteArray.size)
        val fd = getFD(memoryFile)
        return ParcelFileDescriptor.dup(fd)
    }

    fun getFD(memoryFile: MemoryFile): FileDescriptor {
        val c = Class.forName("android.os.MemoryFile")
        val method = c.getDeclaredMethod("getFileDescriptor")
        method.isAccessible = true
        return method.invoke(memoryFile) as FileDescriptor
    }
}