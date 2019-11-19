package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.http.HttpBinServiceJson
import com.example.myapplication.http.HttpBinServiceString
import com.example.myapplication.model.GetData
import kotlinx.android.synthetic.main.activity_web.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class WebActivity : AppCompatActivity() {

    var webViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {

            Toast.makeText(this@WebActivity, "lol", Toast.LENGTH_SHORT).show()

            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        this.webview_component.loadUrl("https://www.google.fr/")
        this.webview_component.webViewClient = this.webViewClient
        this.webview_component.settings.javaScriptEnabled = true

        val retrofit = Retrofit.Builder()
            .baseUrl("http://httpbin.org")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val service: HttpBinServiceString = retrofit.create(HttpBinServiceString::class.java)

        val call: Call<String> = service.getUserAgent()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("HTTP", "Response: ${response?.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("HTTP", "Response: fail")
            }
        })


        val retrofitJson = Retrofit.Builder()
            .baseUrl("http://httpbin.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val serviceJson: HttpBinServiceJson = retrofitJson.create(HttpBinServiceJson::class.java)

        val callJson: Call<GetData> = serviceJson.getUserInfo()

        callJson.enqueue(object : Callback<GetData> {
            override fun onResponse(call: Call<GetData>, response: Response<GetData>) {
                Log.i("HTTPJSON", "Response : ${response.body()?.url}")
            }

            override fun onFailure(call: Call<GetData>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
