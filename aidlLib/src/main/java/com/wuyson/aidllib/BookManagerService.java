package com.wuyson.aidllib;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.wuyson.aidllib.entity.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {

    private static final String TAG = "BookManagerService";
    private final AtomicBoolean mServiceIsDestroyed = new AtomicBoolean(false);

    private final CopyOnWriteArrayList<Book> mBooks = new CopyOnWriteArrayList<>();

    private final RemoteCallbackList<IOnNewBookArrivedListener> mCallbackListener
            = new RemoteCallbackList<>();

    private final Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBooks.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mCallbackListener.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mCallbackListener.unregister(listener);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBooks.add(new Book(1, "牧神记"));
        mBooks.add(new Book(2, "斗破苍穹"));

        new Thread(new CreateNewBook()).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mServiceIsDestroyed.set(true);
    }

    private class CreateNewBook implements Runnable {

        @Override
        public void run() {
            while (!mServiceIsDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Book book = new Book();
                int bookId = mBooks.size() + 1;
                book.bookId = bookId;
                book.bookName = "new Book" + bookId;

                try {
                    onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBooks.add(book);
        int size = mCallbackListener.beginBroadcast();
        for (int i = 0; i < size; i++) {
            IOnNewBookArrivedListener item = mCallbackListener.getBroadcastItem(i);
            if (item != null) {
                item.onNewBookArrived(book);
            }
        }
        mCallbackListener.finishBroadcast();
    }
}