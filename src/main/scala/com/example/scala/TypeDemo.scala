package com.example.scala

/**
  * 高级类型demo
  */
object TypeDemo {

  class Person(name: String, age: Int) {
    def myPrint() = {
      println(name + "," + age)
    }
  }

  def instanceDemo() = {
    //利用类型创建Person
    //得到JavaUniverse用于反射
    val ru = scala.reflect.runtime.universe
    //得到一个JavaMirror，一会用于反射Person.class
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    //得到Person类的Type对象后，得到type的特征值并转为ClassSymbol对象
    val classPerson = ru.typeOf[Person].typeSymbol.asClass
    //得到classMirror对象
    val classMirror = mirror.reflectClass(classPerson)
    //得到构造器Method
    val constructor = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod
    //得到MethodMirror
    val methodMirror = classMirror.reflectConstructor(constructor)
    //实例化该对象
    val p = methodMirror("Mike", 1)
    println(p)

  }


  def accessFieldDemo(): Unit = {
    //获取Environment和universe
    val ru = scala.reflect.runtime.universe
    //获取对应的Mirrors,这里是运行时的
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    //得到Person类的Type对象后，得到type的特征值并转为ClassSymbol对象
    val classPerson = ru.typeOf[Person].typeSymbol.asClass
    //用Mirrors去reflect对应的类,返回一个Mirrors的实例,而该Mirrors装载着对应类的信息
    val classMirror = mirror.reflectClass(classPerson)
    //得到构造器Method
    val constructor = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod
    //得到MethodMirror
    val methodMirror = classMirror.reflectConstructor(constructor)
    //实例化该对象
    val p = methodMirror("Mike", 1)
    println(p)


    //反射方法并调用
    val instanceMirror = mirror.reflect(p)
    //得到Method的Mirror
    val myPrintMethod = ru.typeOf[Person].decl(ru.TermName("myPrint")).asMethod
    //通过Method的Mirror索取方法
    val myPrint = instanceMirror.reflectMethod(myPrintMethod)
    //运行myPrint方法
    myPrint()

    //得到属性Field的Mirror
    val nameField = ru.typeOf[Person].decl(ru.TermName("name")).asTerm
    val name = instanceMirror.reflectField(nameField)
    println(name.get)

  }

  def main(args: Array[String]): Unit = {

    import scala.reflect.runtime.universe._
    //demo　看classOf typeOf区别
    val typeResult = typeOf[Demo]
    val classResult = classOf[Demo]
    println(typeResult) //com.example.scala.Demo
    println(classResult) //class com.example.scala.Demo
    println(typeOf[List[Demo]]) //List[com.example.scala.Demo]
    println(classOf[List[Demo]])
    //class scala.collection.immutable.List
    val list = List(new Demo)
    println(list.getClass) //class scala.collection.immutable.$colon$colon
    println((new Demo).getClass) //class com.example.scala.Demo
    println(classOf[Demo#Sub])
    //类型投影，外部类#内部类  class com.example.scala.Demo$Sub
    type listType = List[Demo#Sub] //类型别名
    println(typeOf[List[Demo#Sub]]) //不知道为啥不能typeOf[listType]　List[com.example.scala.Demo#Sub]
    println(classOf[List[Demo#Sub]]) //class scala.collection.immutable.List

    //结构类型demo
    /*
        结构类型是指一组关于抽象方法、字段和类型的规格说明，
        可以对任何具备append方法的类的实例调用appendLines方法，
        这种方式比定义特质更加灵活，是通过反射进行调用的：

     */
    //声明结构
    type X = {}
    type Y = {def work(): Unit}

    //定义使用结构的方法
    def work(worker: Y) = worker.work()
    //调用使用结构的方法时，新实现结构中定义好的方法。
    work(new { //不可写为new Y{}
      def work() = {
        println("搬砖工开工了")
      }
    })

    work(new {
      def work() = println("白领开工了")
    })

    work(new Structure()) //structure类也具有该方法结构。
    //思考，结构比java中的接口要灵活太多，这些类只需要遵守同样的结构规范，并不需要实现同一接口。

    //复合类型demo
    type Z = Demo with Int
    println(typeOf[Demo with Int]) //com.example.scala.Demo with Int

    //自身类型
    val demo = new Demo
    val sub = new demo.Sub
    sub.printFather //com.example.scala.Demo@63787180

    //运行时反射demo

    /*
        scala运行时类型信息是保存在TypeTag对象中，编译器在编译过程中将类型信息保存到TypeTag中，
        并将其携带到运行期。我们可以通过typeTag方法获取TypeTag类型信息。

     */
    val intListType = typeTag[List[Int]]
    println(intListType)
    //TypeTag[List[Int]]
    val demoListType = typeTag[List[Demo]]
    println(demoListType)
    //TypeTag[List[com.example.scala.Demo]]
    val subListType = typeTag[List[Demo#Sub]]
    println(subListType) //TypeTag[List[com.example.scala.Demo#Sub]]
    //typeTag得到包装type的typeTag对象，typeOf直接得到type。

    //运行时实例化demo
    instanceDemo()
    //运行时访问属性demo
    accessFieldDemo()

  }

}

class Demo {

  //自身类型
  D =>

  class Sub {
    def printFather: Unit = {
      println(D)
    }
  }

}

class Structure {

  def work() = {
    println("瓦匠开工了")
  }
}
