// IBookManager.aidl
package com.wuyson.aidllib;

import com.wuyson.aidllib.entity.Book;
import com.wuyson.aidllib.IOnNewBookArrivedListener;

//图书管理
interface IBookManager {


     List<Book> getBookList();

     void addBook(in Book book);

     void registerListener(IOnNewBookArrivedListener listener);

     void unRegisterListener(IOnNewBookArrivedListener listener);
}