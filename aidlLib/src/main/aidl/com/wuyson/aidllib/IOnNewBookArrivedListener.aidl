// IOnNewBookArrivedListener.aidl
package com.wuyson.aidllib;

import com.wuyson.aidllib.entity.Book;

//新书提醒
interface IOnNewBookArrivedListener {

    void onNewBookArrived(in Book book);
}