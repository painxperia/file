package com.zpain.file;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@SpringBootTest
@Slf4j
class FileApplicationTests {


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {

        }
        stopWatch.stop();

        System.out.printf(String.valueOf(stopWatch.getTotalTimeMillis()));
    }

    @Test
    public void t1() {
        try {
            User user = new User(1L);
            User2 user2 = UserConverter.INSTANCE.toUser(user);
            String s = objectMapper.writeValueAsString(user2);
            log.info("s:{}", s);
        } catch (Exception e) {

        }
    }

    @Test
    public void t2() throws Exception {
        System.out.println("主线程开始");
        CompletableFuture.runAsync(() -> {
            while (true) {
                System.out.println("子线程开始");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        TimeUnit.SECONDS.sleep(5);
    }

//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        StopWatch stopWatch = new StopWatch("1");
//        System.out.println("start:"+LocalDateTime.now());
//        stopWatch.start();
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        List<Future<Integer>> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            int a = i;
//            Future<Integer> submit = executorService.submit(() -> {
//                TimeUnit.SECONDS.sleep(1);
//                return a;
//            });
//            list.add(submit);
//        }
//        list.stream().forEach(  l->{
//            try {
//                Integer s = l.get();
//                System.out.println(Thread.currentThread().getName()+":"+s);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        System.out.println("end:"+LocalDateTime.now());
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//        TimeUnit.SECONDS.sleep(20);
//    }


    public static void main(String[] args) {
        Random random = new Random();
        int asInt = random.ints(1, 10).findFirst().getAsInt();
        System.out.println(asInt);
        int i = random.nextInt(12);
        System.out.println(i);
        double v = random.nextGaussian();
        double v1 = random.nextDouble();
        System.out.println(v);
        System.out.println(v1);

    }

    @Test
    public void test(){
        String data = "{\n" +
                "\t\"id\":123,\n" +
                "\t\"user_name\":\"zpain\"\n" +
                "}";
        User user = JSON.parseObject(data, User.class);
        log.info("user:{}",JSON.toJSONString(user));
    }


    @Autowired
    private User user;

    @Test
    public void a(){
        String userName = user.getUserName();
        System.out.println(userName);
    }
}
