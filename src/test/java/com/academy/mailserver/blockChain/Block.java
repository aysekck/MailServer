package com.academy.mailserver.blockChain;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
    @ToString
    public class Block {
        private int previousHash;
        private String data;
        private int hash;

        public Block(String data, int previousHash) {
            this.data = data;
            this.previousHash = previousHash;

            // Mix the content of this block with previous hash to create the hash of this new block
            // and that's what makes it block chain
            this.hash  = Arrays.hashCode(new Integer[]{data.hashCode(), previousHash});
        }

    public Block() {
    }

    public int getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(int previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
            return "Block " + hash + "---" +data ;
        }
    }

