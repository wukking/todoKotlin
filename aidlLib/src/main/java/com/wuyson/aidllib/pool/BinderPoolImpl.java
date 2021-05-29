package com.wuyson.aidllib.pool;

import android.os.IBinder;
import android.os.RemoteException;

import com.wuyson.aidllib.IBinderPool;
import com.wuyson.aidllib.pool.sub.EncryptSubImpl;
import com.wuyson.aidllib.pool.sub.OperatorSubImpl;


public class BinderPoolImpl extends IBinderPool.Stub {

    public BinderPoolImpl() {
        super();
    }

    public static final int BINDER_CODE_ENCRYPT= 1;
    public static final int BINDER_CODE_ADD_OPERATOR = 2;

    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder binder = null;

        switch (binderCode){
            default:
                break;
            case BINDER_CODE_ENCRYPT:
                binder = new EncryptSubImpl();
                break;
            case BINDER_CODE_ADD_OPERATOR:
                binder = new OperatorSubImpl();
                break;
        }
        return binder;
    }
}
