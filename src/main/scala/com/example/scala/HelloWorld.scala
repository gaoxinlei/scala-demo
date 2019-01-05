package com.example.scala

import scala.util.control.Breaks

object HelloWorld {

  def main(args: Array[String]): Unit = {
    println("Hello Scala!"(4))//等同于"Hello Scala".apply(4)

    val arr = new Array[Int](3)
    arr(1)=2//等同于arr.update(1,2)
    arr.update(2,3)
    println(arr.mkString(","))

    val map = Map("a"->"alice","b"->"tom")//构建map测试optional
    println(map.get("a"))
    println(map.get("c"))

    //if else相当于函数，可以有返回值并赋给变量，也同时可使用变量类型推断，即if与else返回的值可以是不同类型。
    val a1 = 18
    val ifEls1 =
      if (a1>20){
        "大于20"
      }else{
        "小于20"
      }
    println(ifEls1)

    val a2 = 22
    val ifEls2 =
      if(a2>20){
        a1
      }else{
        "小于20"
      }
    println(ifEls2)

    //while 表达式
    val break = new Breaks

    var flag1 = 1;
    break.breakable({
      while(flag1<10){
        flag1+=1;
        if(flag1%5==0){
          break.break()
        }
      }
    })
    println(flag1)

    //for 表达式 带保护式（相当于continue）
    var flag2 = 1;
    for(i<-1 to 3;j<-1 to 3 if j!=2){
      if(i==j){
        print(j)
      }
    }
    println()
    //用yield将遍历的结果赋出来。结果是一个Vector.
    var yie = for(i<-1 to 5) yield i
    println(yie)
  }

}
