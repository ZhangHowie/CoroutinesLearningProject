package com.howie.learning.coroutineslearningproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        fistCoroutines()

//        secondCoroutines()

//        startWithJavaThread()

    }

    suspend fun aaa() = coroutineScope {
        launch {

        }
    }
    /**
     * Hello Coroutines
     * 基础的协程例子
     */
    private fun fistCoroutines() {
        printThreadName("out coroutines")
        GlobalScope.launch {
            printThreadName("in coroutines")
        }
    }

    /**
     * 第二个协程例子，
     * 切换协程执行线程
     */
    private fun secondCoroutines() {
        GlobalScope.launch(Dispatchers.Main) {
            ioFunctionSuspend() //--->IO线程
            uiFunction0()       //--->Main线程
        }
    }

    /**
     * 使用Java线程实现线程切换
     */
    private fun startWithJavaThread() {
        thread {
            ioFunction0()
            runOnUiThread {
                uiFunction0()
            }
        }
    }

    /**
     * 打印执行线程名
     */
    fun printThreadName(oddWord: String = "hello coroutines") {
        println("$oddWord, thread name is [${Thread.currentThread().name}]")
    }

    /**
     * 非挂起的IO函数
     */
    private fun ioFunction0() {
        Thread.sleep(1000)
        printThreadName("io thread 0")
    }

    /**
     * 挂起的IO函数
     */
    private suspend fun ioFunctionSuspend() {
        //--->协程线程
        withContext(Dispatchers.IO) {
            printThreadName("io thread 0")
            Thread.sleep(1000)
            //--->IO线程
        }
        //--->协程线程
    }

    /**
     * 非挂起的UI函数
     */
    private fun uiFunction0() {
        printThreadName("ui thread 0")
    }

}
