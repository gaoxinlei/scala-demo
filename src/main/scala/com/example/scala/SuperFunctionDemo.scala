package com.example.scala

import java.lang

object SuperFunctionDemo {

  def main(args: Array[String]): Unit = {

    //定义一个加法函数
    def plus (x: Int)=x+3
    println(Array(1,2,3,4).map(plus(_)).mkString(","))

    //匿名函数
    val f = (x:Int) => x*3
    println(Array(1,2,3,4).map(f).mkString(","))

    //高阶函数。
    //定义一个接收某个函数的函数，接收的函数参数和返回值均为int，本函数向他传入参数3
    val highFunction = (f :Int =>Int)=>f(10)
    println(highFunction(plus))

    //闭包
    //定义一个函数，变量x，返回值是另一个函数，变量y，返回值是y-x
    def closePacket (x:Int) = (y:Int) =>y-x
    println(closePacket(5)(10))//5

    def minusxy(x: Int) = (y: Int) => x - y
    val f1 = minusxy(10)
    val f2 = minusxy(10)
    println(f1(3) + f2(3))//14

    //柯里化
    def mul(x: Int, y: Int) = x * y
    println(mul(10, 10))

    def mulCurry(x: Int) = (y: Int) => x * y
    println(mulCurry(10)(9))

    def mulCurry2(x: Int)(y:Int) = x * y
    println(mulCurry2(10)(8))

    //柯里化集合比较案例
    val a = Array("Hello", "World")
    val b = Array("hello", "world")
    println(a.corresponds(b)(_.equalsIgnoreCase(_)))


    //控制抽象，一种参数是函数且参数本身没有返回值和参数的函数。
    //例：多线程,定义一个在另一个线程中运行参数中的函数的方法
    def runInThread(f: =>Unit) :Unit ={
      //里面创建一个线程调用f
      new Thread(){
        override def run() :Unit ={
          f
        }

      }.start()
    }

    //调用runInThread并传入函数
    runInThread {

      println("开工了")
      Thread.sleep(200)
      println("工作做完")

    }

    //while until实现等待功能。
    //定义一个until的高阶函数，参数为一个判断条件的condition函数，它的返回值是另一个函数，负责在condition条件假时
    //block住代码。 闭包
    def until (condition: =>Boolean)(block: =>Unit){//相当于def until (condition: =>Boolean)(block: =>Unit) : Unit{
      if(!condition){
        block
        until(condition)(block)
      }
    }

    //调用until，传入一个条件判断和block方法

    var flag = 10
    until(flag==8){//10 9
      println(flag)
      flag-=1
    }

  }
}
