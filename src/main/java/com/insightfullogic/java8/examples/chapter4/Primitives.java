package com.insightfullogic.java8.examples.chapter4;

import com.insightfullogic.java8.examples.chapter1.Album;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;

public class Primitives {

// BEGIN printTrackLengthStatistics
public static void printTrackLengthStatistics(Album album) {
    IntSummaryStatistics trackLengthStats
            = album.getTracks()
                   .mapToInt(track -> track.getLength())
                   .summaryStatistics();
     LongSummaryStatistics longStats = album.getTracks()
    		 .mapToLong(track -> track.getLength())
    		 .summaryStatistics();
     DoubleSummaryStatistics doubleStats = album.getTracks()
    		 .mapToDouble(track -> track.getLength())
    		 .summaryStatistics();
    
    System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
    		trackLengthStats.getMax(),
    		trackLengthStats.getMin(),
    		trackLengthStats.getAverage(),
    		trackLengthStats.getSum());
}
// END printTrackLengthStatistics

}
