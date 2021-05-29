package com.wuyson.aidlsample

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.View
import com.wuyson.aidllib.*
import com.wuyson.aidllib.entity.Book
import com.wuyson.aidllib.pool.BinderPool
import com.wuyson.aidllib.pool.BinderPoolImpl

//自定义Permission
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val MESSAGE_NEW_BOOK_ARRIVED = 1
    private var mRemoteManager : IBookManager?= null

    private val mHander = Handler {
       when(it.what){
           MESSAGE_NEW_BOOK_ARRIVED -> {
               (it.obj as Book).let {
                   Log.e(TAG, "new Book : ${ it.bookName}")
               }
               true
           }
           else -> { return@Handler true }
       }
    }

    private val mOnNewBookArrivedListener = object : IOnNewBookArrivedListener.Stub(){
        override fun onNewBookArrived(book: Book?) {
            Log.e(TAG, "onNewBookArrived: "+Thread.currentThread() )
            mHander.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED,book).sendToTarget()
        }
    }

    private val mServiceConnection = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e(TAG, "onServiceConnected: "+Thread.currentThread())
            val bookManager = IBookManager.Stub.asInterface(service)
            mRemoteManager = bookManager
            bookManager.bookList.forEach{
                Log.e(TAG, "onServiceConnected: ${it.bookName}" )
            }

            bookManager.registerListener(mOnNewBookArrivedListener)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bindService()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindService()
    }

    private fun bindService() {
        val intent = Intent(this, BookManagerService::class.java)
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
    }


    fun aidlPool(view: View) {
        startThread()
    }

    private fun startThread(){
        Thread {
            binderPool()
        }.start()
    }

    private var mNumber = 2
    private val mNames = arrayListOf<String>("Jessica","x.yut","myshuyi")
    private fun binderPool() {
        val mBinderPool = BinderPool.getInstance(this)

        val encryptBinder =
            mBinderPool.queryBinder(BinderPoolImpl.BINDER_CODE_ENCRYPT)
        val asInterface = IEncrypt.Stub.asInterface(encryptBinder)

        val addOperatorBinder =
            mBinderPool.queryBinder(BinderPoolImpl.BINDER_CODE_ADD_OPERATOR)
        val asInterface2 = IOperator.Stub.asInterface(addOperatorBinder)

        Log.e(TAG, "加密后文字: ${asInterface.encrypt("hello ${mNames[(Math.random()*3).toInt()]}!")}" )
        Log.e(TAG, "运算后的值: ${asInterface2.addOperator(5,mNumber++)}" )
    }



    override fun onDestroy() {
        super.onDestroy()
        if (mRemoteManager?.asBinder()?.isBinderAlive == true){
            Log.e(TAG, "onDestroy: unRegister" )
            mRemoteManager?.unRegisterListener(mOnNewBookArrivedListener)
        }

        unbindService(mServiceConnection)
    }
}