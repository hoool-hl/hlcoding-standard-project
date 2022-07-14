package com.hlcoding.controller;
import com.hlcoding.api.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
/**
 * 异步controller测试
 */
@RestController
public class AsyncController {
    @Resource
    private ThreadPoolExecutor executor;

    /**
     * 测试异步
     * @param id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping(value = "/detailById",produces = "application/json; charset=UTF-8")
    public String detail(Long id) throws ExecutionException, InterruptedException {

        CompletableFuture<String> infoFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步");
            return "异步" + id;
        }, executor);

        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync((res) -> {
            System.out.println("异步");
        }, executor);

        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            System.out.println(id);
        }, executor);

        //等到所有任务都完成
        CompletableFuture.allOf(saleAttrFuture,infoFuture,imageFuture).get();
        return "detail" + id;
    }

    /**
     * 测试请求
     * @param str 用户名字
     * @return
     */
    @GetMapping(path = "/hello", produces = "application/json; charset=UTF-8")
    public User hello(@RequestParam(value = "str") String str){
        System.out.println(str);
        return new User();
    }
}
