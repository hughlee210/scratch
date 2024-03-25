package com.hlee.scratch.corejava;

import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final long capacity;
    private final long refillTokensPerPeriod;
    private final long refillPeriodMillis;
    private long tokens;
    private long lastRefillTime;

    public RateLimiter(long capacity, long refillTokensPerPeriod, long refillPeriod, TimeUnit timeUnit) {
        this.capacity = capacity;
        this.refillTokensPerPeriod = refillTokensPerPeriod;
        this.refillPeriodMillis = timeUnit.toMillis(refillPeriod);
        this.tokens = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        refillTokens();
        if (tokens > 0) {
            tokens--;
            return true;
        } else {
            return false;
        }
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTime;
        long tokensToAdd = (elapsedTime * refillTokensPerPeriod) / refillPeriodMillis;
        tokens = Math.min(capacity, tokens + tokensToAdd);
        lastRefillTime = now;
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(5, 1, 1, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            if (rateLimiter.tryAcquire()) {
                System.out.println("Request " + i + " allowed.");
            } else {
                System.out.println("Request " + i + " blocked.");
            }
            Thread.sleep(200); // Simulating some processing time between requests
        }
    }
}