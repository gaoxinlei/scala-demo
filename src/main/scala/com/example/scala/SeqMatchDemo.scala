package com.example.scala

object SeqMatchDemo {

  //seq 匹配
  def unapplySeq(seq: String): Option[Seq[String]] ={
    if(seq.contains(",")) Some(seq.split(","))
    else None
  }
  def main(args: Array[String]): Unit = {
    //seq 匹配模式

    val names = "大龙,老头,双涛"

    names match {
      case SeqMatchDemo(a,b,c)=>println(a+","+b+","+c)
      case _ =>println("未匹配成功")
    }

    val nameValuePairs=Map(("gao",28),("Li",29))
    //for匹配
    for ((k, v) <- nameValuePairs)
      println(k + " -> " + v)

    //偏函数demo,只有匹配了case才执行，否则过滤
    val f: PartialFunction[Char,Int] = {
      case 'a' =>1
      case 'b' =>2
    }

    println(f('a'))//1
    println(f.isDefinedAt('a'))//true
    println(f.isDefinedAt('c'))//false
    println(f.isDefinedAt(0))//false

    //用一个偏函数，向一个list中的所有整数加一。
    val f1  = new PartialFunction[Any,Int] {
      override def isDefinedAt(x: Any): Boolean = {
        if (x.isInstanceOf[Int]) true else false
      }

      override def apply(v1: Any): Int = {
        v1.asInstanceOf[Int] +1
      }
    }

    val r1 = List(1,8,5,6,"abc") collect f1
    println(r1)//List(2,9,6,7) "abc"被过滤
  }
}
