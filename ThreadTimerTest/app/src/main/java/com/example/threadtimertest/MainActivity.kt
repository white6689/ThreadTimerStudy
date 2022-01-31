package com.example.threadtimertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.threadtimertest.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    //Main thread(UI thread)의 Looper를 불러온다
    private val handler= Handler(Looper.getMainLooper())
    lateinit var timer:Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timer=Timer()
        timer.start()//run이 아니라 start!
    }
    //1초마다 현재 시각을 불러와서 화면을 바꿔주는(main thread) 클래스
    inner class Timer:Thread(){
        override fun run() {
            try{
                while(true){
                    sleep(1000)
                    //handler를 통해 runnable객체를 보냄
                    handler.post{
                        binding.mainClock.text=Calendar.getInstance().time.toString()
                    }
                    Log.d("ThreadTimer", Calendar.getInstance().time.toString())
                }
            }catch (e:InterruptedException){
                Log.d("Interrupt", "Thread종료")
            }
        }

    }
}