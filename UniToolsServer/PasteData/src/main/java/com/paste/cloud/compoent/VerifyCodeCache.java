package com.paste.cloud.compoent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class VerifyCodeCache implements CommandLineRunner {

    private static LoadingCache<String,String> codeCache ;


    @Override
    public void run(String... args) throws Exception {
        codeCache = CacheBuilder.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, String>() {
                            public String load(String key) { // no checked exception
                                return "";
                            }
                        }
                );
    }

    public void putVerifyCode(String key,String code){
        codeCache.put(key,code);
    }

    public boolean verifyCode(String key ) {
        return codeCache.getIfPresent(key) == null ? false : true;
    }
}
