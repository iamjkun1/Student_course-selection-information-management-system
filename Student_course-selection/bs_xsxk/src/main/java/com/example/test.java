package com.example;

///chat-gpt  service 层
///创建 一个 controller 层调用service
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import com.unfbx.chatgpt.interceptor.OpenAiResponseInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class test {
            public static void main(String[] args) {
                //国内访问需要做代理，国外服务器不需要
                //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
                //！！！！千万别再生产或者测试环境打开BODY级别日志！！！！
                //！！！生产或者测试环境建议设置为这三种级别：NONE,BASIC,HEADERS,！！！
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                OkHttpClient okHttpClient = new OkHttpClient
                        .Builder()
                        //.proxy(proxy)//自定义代理
                        .addInterceptor(httpLoggingInterceptor)//自定义日志输出
                        .addInterceptor(new OpenAiResponseInterceptor())//自定义返回值拦截
                        .connectTimeout(10, TimeUnit.SECONDS)//自定义超时时间
                        .writeTimeout(30, TimeUnit.SECONDS)//自定义超时时间
                        .readTimeout(30, TimeUnit.SECONDS)//自定义超时时间
                        .build();
                //构建客户端
                OpenAiClient openAiClient = OpenAiClient.builder()
                        .apiKey(Arrays.asList("sk-f2AB9hFLmgNc29kvzyfTAnxliEloK9OlY7sXKuKmTa7UkRSM"))
                        //自定义key的获取策略：默认KeyRandomStrategy
                        .keyStrategy(new KeyRandomStrategy())
                        //.keyStrategy(new FirstKeyStrategy())
                        .okHttpClient(okHttpClient)
                        //自己做了代理就传代理地址，没有可不不传
               .apiHost("https://api.f2gpt.com")
                        .build();
                //聊天模型：gpt-3.5
                Message message = Message.builder().role(Message.Role.USER).content("你好啊我的伙伴！").build();
                ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
                ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
                chatCompletionResponse.getChoices().forEach(e -> {
                    System.out.println(e.getMessage());
                });
            }
        }

