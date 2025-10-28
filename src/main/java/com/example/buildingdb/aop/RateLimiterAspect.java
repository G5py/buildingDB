package com.example.buildingdb.aop;

import com.example.buildingdb.exception.BucketException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component
public class RateLimiterAspect {

    private final Bucket readBucket;
    private final Bucket writeBucket;

    public RateLimiterAspect(@Value("${rate-limiter.read.read-per-second}") int readBucketSize,
                             @Value("${rate-limiter.read.generate-per-second}") int readBucketGen,
                             @Value("${rate-limiter.write.write-per-second}") int writeBucketSize,
                             @Value("${rate-limiter.write.generate-per-second}") int writeBucketGen) {

        Refill readRefill = Refill.intervally(readBucketGen, Duration.ofSeconds(1));
        Bandwidth readBandwidth = Bandwidth.classic(readBucketSize, readRefill);
        this.readBucket = Bucket.builder().addLimit(readBandwidth).build();

        Refill writeRefill = Refill.intervally(writeBucketGen, Duration.ofSeconds(1));
        Bandwidth writeBandwidth = Bandwidth.classic(writeBucketSize, writeRefill);
        this.writeBucket = Bucket.builder().addLimit(writeBandwidth).build();
    }


    @Pointcut("execution(* com.example.buildingdb.controller.*.get*(..))")
    public void readPointcut() {}

    @Pointcut("execution(* com.example.buildingdb.controller.*.post*(..))")
    public void postPointcut() {}

    @Pointcut("execution(* com.example.buildingdb.controller.*.delete*(..))")
    public void deletePointcut() {}

    @Pointcut("execution(* com.example.buildingdb.controller.*.put*(..))")
    public void putPointcut() {}

    @Pointcut("postPointcut() || deletePointcut() || putPointcut()")
    public void writePointcut() {}


    @Before("readPointcut()")
    public void rateLimitRead() {
        if(!readBucket.tryConsume(1)) {
            throw new BucketException("Read-bucket is empty.");
        }
    }

    @Before("writePointcut()")
    public void rateLimitWrite() {
        if(!writeBucket.tryConsume(1)) {
            throw new BucketException("Write-bucket is empty.");
        }
    }

}
