package test;


import java.util.*;

public class LFU implements CacheReplacementPolicy {
    private int minFrequency = 0;
    private final Map<String, Entry> cache = new HashMap<String, Entry>();

    private static class Entry {
        String word;
        int frequency;
        long lastTimeUsed;

        Entry(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
            this.lastTimeUsed =  System.currentTimeMillis();
        
    }
    }
    public void add(String word) {
        if (cache.containsKey(word)) {
            updateFrequency(word);
        } else {
            addToCache(word);
        }
    }

    public String remove() {
        String wordToRemove = null;
        long oldestTime = Long.MAX_VALUE;
        int lowestFrequency = Integer.MAX_VALUE;

        for (Map.Entry<String, Entry> entry : cache.entrySet()) {
            Entry currentEntry = entry.getValue();
            if (currentEntry.frequency < lowestFrequency || 
                (currentEntry.frequency == lowestFrequency && currentEntry.lastTimeUsed < oldestTime)) {
                wordToRemove = entry.getKey();
                lowestFrequency = currentEntry.frequency;
                oldestTime = currentEntry.lastTimeUsed;
            }
        }
    
        if (wordToRemove != null) {
            cache.remove(wordToRemove);
            return wordToRemove;
        }
    
        return null; // If cache is empty
    }


    private void updateFrequency(String word) {
        Entry entry = cache.get(word);
        int frequency = entry.frequency;
        entry.frequency++;
        entry.lastTimeUsed = System.currentTimeMillis();

        if (frequency == minFrequency && entry.frequency > minFrequency) {
            updateMinFrequency();
        }
    }

    private void addToCache(String word) {
        cache.put(word, new Entry(word, 1));
        minFrequency = 1;
    }

    private void updateMinFrequency() {
        minFrequency++;
    }
}
