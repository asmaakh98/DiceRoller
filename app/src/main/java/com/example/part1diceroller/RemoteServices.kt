package com.example.part1diceroller

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object RemoteServices {
    fun get(uri:String, handler: RemoteDataHandler?=null) : String {
        val url = URL(uri)
        val cnx = url.openConnection() as HttpsURLConnection
        cnx.requestMethod = "GET"
        cnx.doInput = true
        val t = RemotAsyncTask(cnx, handler)
        t.excute()

    }
    fun post(uri: String, data:Map<String, String> , handler: RemoteDataHandler?=null) {
        val url = URL(uri)
        val cnx = url.openConnection() as HttpsURLConnection
        cnx.requestMethod = "POST"
        cnx.doInput = true
        val t = RemotAsyncTask(cnx, handler)
        cnx.doInput = true
        cnx.doOutput = true

        val t = RemotAsyncTask(cnx, handler, data)
        t.excute()
    }
}


class RemotAsyncTask(cnx: HttpURLConnection, h: RemoteData Handler?=null, d :Map<String, String>?=null) : AsyncTask<String, String, String>() {
    private val cnx = c
    private val handler = h
    private val data = d
    override fun doInBackground(vararg p0: String?): String {
        cnx.connect()
        if( cnx.doOutput  && data != null) {
            val os = ufferedOutputStream(cnx.outputStream)
            this.writeStream(os, data!!)
            os.close()
        }
        try {
            val streamIn = BufferedInputStream(cnx.inputStream)
            val respone: String = readStream(inputStream = streamIn)
            handler?.onFinishLoadingData(response)
        } finally {
            cnx.disconnect()
        }
            return ""

    }
    fun readStream(inputStream: BufferedInputStream, data: Map<string, string>){
        val bufferedReader = BufferedReader(InputStreamReader(streamIn))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

    fun writeStream(outputStream: BufferedOutputStream, data: Map<string, string>)
    {
        val bufferedWriter = BufferedWriter(OutputstreamWriter(outputStream))
        var p : List<String> = listOf()
        for (d in data) {
            p += d.key+"="+d.value
        }
        val sp = p.joinToString("&")
        bufferedWriter.write(sp)
        bufferedWriter.flush()
        bufferWriter.close()
    }
}
interface RemoteDataHandler {
    fun onFinishLoadingData(response: String)
}