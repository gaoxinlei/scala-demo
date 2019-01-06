package com.example.scala

/**
  * 演示异常处理。
  * case顺序从小到大范围捕捉异常。
  */
object ExceptionDemo {

  def divide(x: Int, y: Int): Int = {
    if(y==0){
      throw new Exception("除数是0")
    }
    x/y
  }

  def main(args: Array[String]): Unit = {
    try{
      val result = divide(10,0)
      println(result)
    }catch {
      case e: Exception => println(e.getMessage)
    }finally {}

  }
}
