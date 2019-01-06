package com.example.scala

import scala.util.control.Breaks

object HelloWorld {

  /**
    * 求和函数。
    * 函数的定义格式为：
    * def 函数名(参数名: 参数类型): 返回值 = {方法体}
    * 本例中的写法等同于声明了一个result接收了if else的答案并return，可省略。
    * 无返回值，即显性或隐性返回值类型为Unit的函数，也称为过程。
    * @param stepNumber
    * @return
    */
  def getSumAll(stepNumber: Int): Int = {
      if (stepNumber>0){
        stepNumber*(stepNumber+1)/2
      }else{
        0
      }
  }

  /**
    * 变长参数。
    * @param numbers
    * @return
    */
  def sum(numbers: Int*): Int = {
    if(null==numbers||numbers.isEmpty){
      0
    }else{
      numbers.sum
    }
  }

  /**
    * 带有默认参数的函数。
    * @param source
    * @param factor
    * @return
    */
  def multy2(source: Int,factor: Int=2): Int = {
    source*factor
  }

  def initLazy() :String= {
    "init执行了"
  }

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

    var stepNumber = 100;
    println(getSumAll(stepNumber))
    println(sum(1,3,5,7))
    println(multy2(2))
    //懒执行验证。initLazy方法中的打印语句直到print laz时才输出，说明懒加载。
    lazy val laz = initLazy()
    println("init是否已执行？")
    println(laz)
  }

}
