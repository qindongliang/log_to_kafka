 实时收集应用程序日志，统一发送到kafka中

查看kfaka中数据的命令:

````
kafka-console-consumer --zookeeper 192.168.10.5:2181 --topic temp_log_kafka
````

（1）json信息发送到kafka中的数据
````json
{"method":"main","level":"INFO","line":"16","message":"es数据：1=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:41"}
{"method":"main","level":"INFO","line":"16","message":"es数据：2=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:41"}
{"method":"main","level":"INFO","line":"16","message":"es数据：3=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:41"}
{"method":"main","level":"INFO","line":"16","message":"es数据：4=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:42"}
{"method":"main","level":"INFO","line":"16","message":"es数据：5=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:42"}
{"method":"main","level":"INFO","line":"16","message":"es数据：6=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:42"}
{"method":"main","level":"INFO","line":"16","message":"es数据：7=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:42"}
{"method":"main","level":"INFO","line":"16","message":"es数据：8=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:42"}
{"method":"main","level":"INFO","line":"16","message":"es数据：9=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:42"}
{"method":"main","level":"INFO","line":"16","message":"es数据：10=>100","class":"test.SendTest","timestamp":"2017-02-07 16:52:42"}
````
（2）普通文本的信息发送到kafka中的数据
````json
hadoop数据：350
hadoop数据：351
hadoop数据：352
hadoop数据：353
hadoop数据：354
hadoop数据：355
hadoop数据：356
hadoop数据：357
hadoop数据：358
hadoop数据：359
hadoop数据：360
````

## 博客相关


（5）[iteye（2018.05月之前所有的文章，之后弃用）](<http://qindongliang.iteye.com/>)  






## 我的公众号(woshigcs)

有问题可关注我的公众号留言咨询

![image](https://github.com/qindongliang/answer_sheet_scan/blob/master/imgs/gcs.jpg)
