# RocketMQ消息队列


> ### RocketMQ
遇到了问题记录一下
1.  pom中的rocket-client版本，一定要保证和你启动的mqbroker是相同的版本
    我刚开始pom中引用的是4.3.0，但是客户端是4.7.0，导致一直提示错误
    ```
    org.apache.rocketmq.client.exception.MQClientException: No route info of this topic
    ```
