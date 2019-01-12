package com.example.scala

/**
  * 不重复集合set demo
  * 若要可变，需要用mutable包下的set
  */
object SetDemo {

  def main(args: Array[String]): Unit = {
    //不可变
    val set1 = Set(1,2,3)
    //可变
    val set2 = scala.collection.mutable.Set(1,2,3)
    println(set2)
    //可变集合扩充
    set2+=4
    set2.add(5)
    println(set2)
    //删除元素
    set2.remove(1)
    println(set2)
    //遍历
    for(i<-set2){
      println("元素："+i)
    }

    //合并两个新集合
    val set3 = set1++set2
    println(set3)

    //丢弃元素，从左向右，直到条件不成立为止。
    val set4 = set3.dropWhile(n=>n%2==1)
    println(set4)
    //过滤偶数成新集。
    val set5 = set3.filter(n=>n%2==0)
    println(set5)
    //reduce right and reduce left
    val rl = set3.reduceLeft(_-_);
    val rr = set3.reduceRight(_-_)
    println("rl:"+rl+",rr:"+rr)

    val fold = set3.fold(0)((x,y)=>x+y)//等效于set3.sum或set3.reduce(_+_)
    println(fold)
    //foldleft foldwrite可以用/: :\ 分别表示，案例：统计一段文本的各字母数量。
    val text = "scala is a very tricky language"
    //结果毫无疑问是个map，键是字母，值是次数。
    val m1 = text.foldLeft(Map[Char,Int]())((m,c)=>m+(c->(m.getOrElse(c,0)+1)))
    println(m1)
    val m2 = (Map[Char,Int]()/: text)((m,c)=>m+(c->(m.getOrElse(c,0)+1)))
    println(m2)
    //扫描
    val vec = (1 to 10).scanLeft(0)(_+_)//结果等效于val s = (1 to 10).scan(0)(_+_)
    println(vec)
    //拉链操作,将两个集合转为一个元组的集合，集合元素数量是少量的一方，且集合内元素依旧是原来的类型。
    val list1 = List("1","2","3")
    val list2 = List(9,10)
    val list3 = list1.zip(list2)
    val list4 = list2.zip(list1)
    println(list3)
    println(list4)
    for((a,b)<-list3){
      println(a+"的长度:"+a.length+",b的值:"+b)
    }

  }
}
