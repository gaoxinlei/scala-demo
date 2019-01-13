package com.example.scala

/**
  * 类型匹配demo
  */
object TypeMatchDemo {

  def main(args: Array[String]): Unit = {
    val a = 8
    val obj = if(a == 1) 1
    else if(a == 2) "2"
    else if(a == 3) BigInt(3)
    else if(a == 4) Map("aa" -> 1)
    else if(a == 5) Map(1 -> "aa")
    else if(a == 6) Array(1, 2, 3)
    else if(a == 7) Array("aa", 1)
    else if(a == 8) Array("aa")

    //obj match {case x: type}  这种表达式来判断类型。
    val r1 = obj match {
      case x: Int => x
      case s: String => s.toInt
      case BigInt => -1 //不能这么匹配
      case _: BigInt => Int.MaxValue
      case m: Map[String, Int] => "Map[String, Int]类型的Map集合"
      case m: Map[_, _] => "Map集合" //map会擦除泛型，无法精确判断里面的元素类型
      case a: Array[Int] => "It's an Array[Int]"
      case a: Array[String] => "It's an Array[String]"
      case a: Array[_] => "It's an array of something other than Int"
      case _ => 0
    }
    println(r1 + ", " + r1.getClass.getName)

    //匹配数组
    for(arr<-Array(Array(0),Array(1,0),Array(1,2,3),Array(4,2,3,4))){
      val result = arr match {
        case Array(0) =>"0"//匹配只有一个元素且为0的数组。
        case Array(x,y) => x+","+y//匹配有两个元素的数组，并把x y分别赋值。
        case Array(x,y,z) =>x+","+y+","+z
        case Array(4,_*) => 4
        case _ =>"未知事物"
      }
      println(result)
    }




  }
}
