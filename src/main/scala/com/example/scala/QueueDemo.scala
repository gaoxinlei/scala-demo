package com.example.scala

import scala.collection.mutable

/**
  * 队列demo
  */
object QueueDemo {

  def main(args: Array[String]): Unit = {
    //队列创建
    val q =  new mutable.Queue[Int]
    //队列扩充一个元素入队尾。
    q+=1
    q+=0
    //入队
    q.enqueue(2,3,4)
    //扩容一个集合。
    q++=List(5,6,7)
    println(q)
    //头部出队
    q.dequeue()
    println(q)
    //队列首部元素获取。
    println(q.head)
    //队列除了第一个元素外的其他元素,还是一个队列。
    println(q.tail)
    //队列尾部元素。
    println(q.last)
  }
}
