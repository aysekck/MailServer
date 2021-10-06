package com.academy.mailserver.blockChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericBlockChain {

    public static void main(String[] args) {

        List<GenericBlock<Transaction>> genericBlockChainList = new ArrayList<>();

        GenericBlock<Transaction> genesis = new GenericBlock("GenericBlockChain", 0);
        genericBlockChainList.add(genesis);
        GenericBlock<Transaction> helloGenericBlock = new GenericBlock("Hello", genericBlockChainList.get(genericBlockChainList.size() - 1).getHash());
        genericBlockChainList.add(helloGenericBlock);
        GenericBlock<Transaction> worldGenericBlock = new GenericBlock("World", genericBlockChainList.get(genericBlockChainList.size() - 1).getHash());
        genericBlockChainList.add(worldGenericBlock);
        GenericBlock<Transaction> dZoneGenericBlock = new GenericBlock("DZone", genericBlockChainList.get(genericBlockChainList.size() - 1).getHash());
        genericBlockChainList.add(dZoneGenericBlock);
        System.out.println("---------------------");
        System.out.println("- GenericBlockChain -");
        System.out.println("---------------------");
        genericBlockChainList.forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println("Is valid?: " + validate(genericBlockChainList));
        System.out.println("---------------------");
        // corrupt genericBlock chain by modifying one of the genericBlock
        GenericBlock<Transaction> hiGenericBlock = new GenericBlock("Hi", genesis.getHash());
        int index = genericBlockChainList.indexOf(helloGenericBlock);
        genericBlockChainList.remove(index);
        genericBlockChainList.add(index, hiGenericBlock);
        System.out.println("Corrupted genericBlock chain by replacing 'Hello' genericBlock with 'Hi' GenericBlock");
        System.out.println("---------------------");
        System.out.println("- GenericBlockChain -");
        System.out.println("---------------------");
        genericBlockChainList.forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println("Is valid?: " + validate(genericBlockChainList));
        System.out.println("---------------------");
    }

    private static boolean validate(List<GenericBlock<Transaction>> genericBlockChain) {
        boolean result = true;
        GenericBlock<Transaction> lastGenericBlock = null;
        for (int i = genericBlockChain.size() - 1; i >= 0; i--) {
            if (lastGenericBlock == null) {
                lastGenericBlock = genericBlockChain.get(i);
            } else {
                GenericBlock<Transaction> current = genericBlockChain.get(i);
                if (lastGenericBlock.getPreviousHash() != current.getHash()) {
                    result = false;
                    break;
                }
                lastGenericBlock = current;
            }
        }

        return result;
    }
}