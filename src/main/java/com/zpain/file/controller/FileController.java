package com.zpain.file.controller;

import com.alibaba.fastjson.JSON;
import com.zpain.file.BusinessException;
import com.zpain.file.User2;
import com.zpain.file.User3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class FileController {

    public static BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(100);
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, blockingQueue);

    @PostMapping("/file")
    public void file(MultipartFile file) throws Exception {
        file.transferTo(new File(""));
    }

    @GetMapping("/t")
    public void t() throws Exception {
        System.out.println(Thread.currentThread().getName() + "主线程开始");

        CompletableFuture.runAsync(() -> {
            int i = 1;
            while (true) {
                i++;
                System.out.println(Thread.currentThread().getName() + "子线程开始");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (i == 8) {
                    break;
                }
            }
        }, threadPoolExecutor);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "主线程结束");
    }

    @GetMapping("/a")
    public void a(User2 user2) {

        log.info("list:{}", JSON.toJSONString(user2));
    }

    @GetMapping("/b")
    public void b(User2 user2) {

        throw new BusinessException("嗡嗡嗡嗡嗡嗡");
    }
}
