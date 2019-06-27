package com.thread.demos.readwrite;

/**
 * 共享数据
 *
 * @author: xch
 * @create: 2019-06-27 10:13
 **/
public class SharedData {

    private char[] buffer;

    private ReadWriteLock readWriteLock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() {
        try {
            readWriteLock.readLock();
            return this.doRead();
        } finally {
            readWriteLock.unReadLock();
        }

    }

    private char[] doRead() {
        char[] readBuffer = new char[buffer.length];
        System.arraycopy(buffer, 0, readBuffer, 0, buffer.length);
        return readBuffer;
    }

    public void write(char c) {
        try {
            readWriteLock.writeLock();
            this.doWrite(c);
        } finally {
            readWriteLock.unWriteLock();
        }

    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
        }
    }
}
