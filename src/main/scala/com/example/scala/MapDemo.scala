package com.example.scala

/**
  * map demo
  */
object MapDemo {

  def main(args: Array[String]): Unit = {
    //不可变映射
    val map1 = Map("a"->"b","c"->"d")
    //可变映射
    var map2 = scala.collection.mutable.Map("aa"->"bb")
    //带类型限制
    var map3 = scala.collection.mutable.HashMap[String,Int](("2",1))
    //用元组构建map
    var map4 = Map(("a","b"),("c","d"))
    println(map4)
    println(map1)
    //取值
    println(map4("a"))
    //更新值
    map2("aa")="e"//只有可变的map才可以更改值。
    println(map2("aa"))
    //扩容map
    val map5 = map4+("e"->"f")
    println(map5)
    map2+=("cc"->"dd","ee"->"ff")//+=号后跟一个map
    println(map2)

    //遍历
    for((k,v)<-map2) println(k+" 指向： "+v)
    for(v<-map2.values) println("值集:"+v)
    for(k<-map2.keys) println("键集:"+k)
    map2-=("aa","bb")//-=号后跟元组
    println(map2)
    //用key删除
    map2.remove("cc")
    println(map2)

  }
}
