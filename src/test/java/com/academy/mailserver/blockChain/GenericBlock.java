package com.academy.mailserver.blockChain;

import java.util.Arrays;

public class GenericBlock <T> {
    private int previousHash;
    private T data;
    private int hash;

    public GenericBlock (T data, int previousHash) {
        this.data = data;
        this.previousHash = previousHash;

        // Mix the content of this block with previous hash to create the hash of this new block
        // and that's what makes it block chain
        this.hash  = Arrays.hashCode(new Integer[]{data.hashCode(), previousHash});
    }


    public int getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(int previousHash) {
        this.previousHash = previousHash;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public String toString(){
        return "";
    }
}


