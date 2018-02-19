package com.hlee.scratch.corejava.collection;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class EnumSetEx {

    public static void main(String[] args) {
        
        testEnumSet();
    }
    
    private static void testEnumSet() {
        
        Set<DistributionChannel> channels = new HashSet<>();
        channels.add(DistributionChannel.TEST);
        channels.add(DistributionChannel.DIGITAL);
        channels.add(DistributionChannel.PHYSICAL);
        channels.add(DistributionChannel.STREAMING);
        channels.add(DistributionChannel.ABC);
        System.out.println("channels = " + channels);

        String result = channels.stream().map(DistributionChannel::toString).collect(Collectors.joining(","));
        System.out.println("result from hash set = " + result);

        Set<DistributionChannel> sortedSet = new TreeSet<>(channels);
        result = sortedSet.stream().map(DistributionChannel::toString).collect(Collectors.joining(","));
        System.out.println("result from tree set = " + result);

        Set<DistributionChannel> enumSet = EnumSet.copyOf(channels);
        System.out.println("enumSet of channels = " + enumSet);

        result = enumSet.stream().map(DistributionChannel::toString).collect(Collectors.joining(","));
        System.out.println("result from Enum set = " + result);
    }
    
}

enum DistributionChannel {

    ABC,
    DIGITAL,
    PHYSICAL,
    STREAMING,
    TEST,
}

