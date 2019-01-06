package com.example.scala

/**
  * list demo
  */
object ListDemo {

  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3)
    println(list1)
    println(list1(2))

    //::是追加，但1::(2,3)代表的是(2,3).::(1),将1加到前面。下面的几个语句可理解为从右向左执行。
    val list2 = 1::2::3::list1::Nil//Nil拼接的list1被当成当独元素。结果List(1, 2, 3, List(1, 2, 3))
    val list3 = 1::2::3::list1//每个元素都是结果list的一个元素，结果List(1, 2, 3, 1, 2, 3)
    val list4 = 1::2::3::Nil//3被当作结果list的一个元素，再向左拼上2和1.结果List(1, 2, 3)
    println(list2)
    println(list3)
    println(list4)
  }
}
