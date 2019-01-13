package com.example.scala

object StreamDemo {

  def main(args: Array[String]): Unit = {
    def nextNumber(n:BigInt) :Stream[BigInt]= n #:: nextNumber(n+1)
    val nm1 = nextNumber(10)
    println(nm1)//Stream(10,?)
    //tail 向下求值
    println(nm1.tail)//Stream(11,?)
    //原值不变
    println(nm1)//Stream(10,11,?)
    //接新
    val nm2 = nm1.tail
    println(nm2)//依旧是Stream(11,?)
    println(nm1)//依旧(10,11,?)
    //映射
    println(nm2.tail.tail.map(n=>n*n/2))//Stream(84,?)
    println(nm1)//Stream(10,11,12,13,?)

    println(nm1.map(x=>x*x))
    //视图类型产生一个方法总是被懒执行的集合，但它从不缓存数据。
    //例：从一到十万中找到逆转和原数相等的数。
    val result = (1 to 100000).view.filter(x=>x.toString.reverse==x.toString)
    println(result)//SeqViewF(...)
//    result.foreach(r=>println(r))
  }
}
