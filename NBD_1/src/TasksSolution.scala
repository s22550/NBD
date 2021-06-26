import scala.collection.mutable.ListBuffer

object TasksSolution {
  val Days: List[String] = List("poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela")
  val Shop = Map[String, Double]("milk" -> 5, "sugar" -> 10, "water" -> 15)

  def main(args: Array[String]) {
    Task_1a()
    Task_1b()
    Task_1c()
    Task_2a()
    Task_2b()
    Task_3()
    Task_4a()
    Task_4b()
    Task_4c()
    Task_5()
    Task_6()
    Task_7()
    Task_8()
    Task_9()
    Task_10()
  }

  def Task_1a(): Unit = {
    var dayString: String = Days.head
    for (i <- 1 until Days.length) dayString = dayString + ", " + Days(i)
    println("Zadanie 1a:\n" + dayString)
  }

  def Task_1b(): Unit = {
    var daysWithP = List[String]()
    var daysWithPString: String = ""
    for (i <- Days.indices) if (Days(i).toLowerCase().head == 'p') daysWithP = daysWithP :+ Days(i)
    for (i <- daysWithP.indices) {
      if (i == 0) daysWithPString = daysWithP(i)
      else daysWithPString = daysWithPString + ", " + daysWithP(i)
    }
    println("Zadanie 1b:\n" + daysWithPString)
  }

  def Task_1c(): Unit = {
    var dayString: String = ""
    var i = 0
    while (i < Days.length) {
      if (i == 0) dayString = Days(i)
      else dayString = dayString + ", " + Days(i)
      i += 1
    }
    println("Zadanie 1c:\n" + dayString)
  }

  var DayString: String = ""
  var iterator = 0

  def Task_2a(): Unit = {
    if (iterator == 0) {
      DayString = Days(iterator)
      iterator += 1
      Task_2a()
    }
    else if (iterator <= Days.length - 1) {
      DayString = DayString + ", " + Days(iterator)
      iterator += 1
      Task_2a()
    }
    else println("Zadanie 2a:\n" + DayString)
    DayString = ""
    iterator = 0
  }

  var iterator2b: Int = Days.length - 1

  def Task_2b(): Unit = {
    if (iterator2b == Days.length - 1) {
      DayString = Days(iterator2b)
      iterator2b -= 1
      Task_2b()
    }
    else if (iterator2b >= 0) {
      DayString = DayString + ", " + Days(iterator2b)
      iterator2b -= 1
      Task_2b()
    }
    else println("Zadanie 2b:\n" + DayString)
    DayString = ""
    iterator2b = 0
  }

  def Task_3(): Unit = {
    if (iterator == 0) {
      DayString = Days(iterator)
      iterator += 1
      Task_3()
    }
    else if (iterator <= Days.length - 1) {
      DayString = DayString + ", " + Days(iterator)
      iterator += 1
      Task_3()
    }
    else println("Zadanie 3:\n" + DayString)
    DayString = ""
    iterator = 0
  }

  def Task_4a(): Unit = {
    var dayString = Days.foldLeft("") { (a, b) => {
      if (a == "") a + b
      else a + ", " + b
    }
    }
    println("Zadanie 4a:\n" + dayString)
  }

  def Task_4b(): Unit = {
    var dayString = Days.foldRight("") { (b, a) => {
      if (a == "") a + b
      else a + ", " + b
    }
    }
    println("Zadanie 4b:\n" + dayString)
  }

  def Task_4c(): Unit = {
    var dayString = Days.foldLeft("") { (a, b) => {
      if (b.toLowerCase().head == 'p') {
        if (a == "") a + b
        else a + ", " + b
      }
      else a + ""
    }
    }
    println("Zadanie 4c:\n" + dayString)
  }

  def Task_5(): Unit = {
    println("Zadanie 5:")
    println("Przed podwyżką: " + Shop)
    var newMap = Map[String, Double]()
    Shop.map(item => newMap += (item._1 -> item._2 * 0.9))
    println("Po podwyżce: " + newMap)
  }

  def Task_6(): Unit = {
    var result = ("first", 1, true)
    println("Zadanie 6:\n" + result)
  }

  def Task_7(): Unit = {
    println("Zadanie 7:")
    var ShopOptional = Map[String, Double]("milk" -> 5, "sugar" -> 10, "water" -> 15);
    println("cena mleka: " + PrintPrice(ShopOptional.get("milk")));
    println("cena soku: " + PrintPrice(ShopOptional.get("juice")));
  }

  def PrintPrice(a: Option[Double]) = a match {
    case Some(s) => s
    case None => "?"
  }

  def Task_8(): Unit = {
    val nums: ListBuffer[Int] = ListBuffer(1, 0, 1, 1, 1, 0, 0, 0)
    println("Zadanie 8:")
    println("Przed: " + nums)
    DeleteWhenZero(nums)
    println("Po: " + nums)
  }

  var i = 0;

  def DeleteWhenZero(list: ListBuffer[Int]): Unit = {
    if (i < list.length) {
      if (list(i) == 0)
        list.remove(i)
      else i += 1
      DeleteWhenZero(list)
    }
  }

  def Task_9(): Unit = {
    println("Zadanie 9:")
    val nums: List[Int] = List(1, 1, 2, 2, 3, 3, 4, 4)
    val result = nums.map(x => x + 1)
    println("Przed: " + nums)
    println("Po: " + result)
  }

  def Task_10(): Unit = {
    println("Zadanie 10:")
    val nums: List[Int] = List(-6, -5, 12, 13)
    var result = nums.filter(x => x >= -5 && x <= 12)
    result = result.map(x => x.abs)
    println("Przed: " + nums)
    println("Po: " + result)
  }
}