package com.example.scala

import java.io.File

/**
  * 从scala中调用shell的demo
  *
  * 注意，每一个感叹号后边，有分号结束
  * scala进程还可以提供：
  * p #&& q操作，即p任务执行成功后，则执行q任务。
  * p #|| q操作，即p任务执行不成功，则执行q任务。
  * 既然这么强大，那么crontab + scala + shell，就完全不需要使用oozie了。
  *
  */
object ShellDemo {

  def main(args: Array[String]): Unit = {
    //执行shell命令，process包中有一个隐含的将字符串转换成ProcessBuild，!代表执行这个对象
    import sys.process._
    "ls /gao/repo" !;
    println("======================")
    "ls /gao/repo" #| "grep pom" #>> new File("/gao/repo/a.txt") !
  }
}
