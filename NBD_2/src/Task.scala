package TaskSolution

object Task {
  def main(args: Array[String]): Unit = {
    Task_1()
    Task_2()
    Task_3()
    Task_4()
    Task_5()
  }

  def Task_1(): Unit = {
    println("\nZadanie 1")
    println("Poniedziałek - " + MatchString("Poniedziałek"))
    println("Sobota - " + MatchString("Sobota"))
    println("Jakisnapis - " + MatchString("Jakisnapis"))
  }

  def MatchString(x: String): String = x match {
    case "Poniedziałek" => "Praca"
    case "Wtorek" => "Praca"
    case "Środa" => "Praca"
    case "Czwartek" => "Praca"
    case "Piątek" => "Praca"
    case "Sobota" => "Weekend"
    case "Niedziela" => "Weekend"
    case _ => "Nie ma takiego dnia"
  }

  def Task_2(): Unit = {
    println("\nZadanie 2")
    val MyBankAccount = new BankAccount(100)
    println("Stan konta przed wpłatą: " + MyBankAccount.GetAccountBalance())
    MyBankAccount.PayIn(100)
    println("Stan konta po wpłacie: " + MyBankAccount.GetAccountBalance())
  }

  class BankAccount(var setAccountBalance: Double = 0, var initAccountBalance: Double = 0) {
    private var AccountBalance: Double = setAccountBalance

    def GetAccountBalance(): Double = {
      AccountBalance
    }

    def PayIn(amount: Double): Unit = {
      AccountBalance += amount
    }

    def PayOut(amount: Double): Unit = {
      if (amount <= AccountBalance) AccountBalance -= amount
      else println("Za mało środków na koncie.")
    }
  }

  def Task_3(): Unit = {
    println("\nZadanie 3")
    val firstPerson = new Person("Jan", "Kowalski")
    val secondPerson = new Person("Adam", "Nowak")
    val thirdPerson = new Person("Damian", "Jeznach")
    val fourthPerson = new Person("Gal", "Anonim")
    println(MatchGreeting(firstPerson))
    println(MatchGreeting(secondPerson))
    println(MatchGreeting(thirdPerson))
    println(MatchGreeting(fourthPerson))
  }

  class Person(var name: String, var surname: String) {
    var Name = name
    var Surname = surname
  }

  def MatchGreeting(person: Person): Unit = (person.Name, person.Surname) match {
    case ("Jan", "Kowalski") => println("Cześć")
    case ("Adam", "Nowak") => println("Dzień dobry")
    case ("Damian", "Jeznach") => println("Hej")
    case _ => println("Cześć nieznajomy")
  }

  def Task_4(): Unit = {
    println("\nZadanie 4")
    Increment(5, DoIncrement)
  }

  def Increment(number: Int, callback: (Int) => Int): Unit = {
    var i = 0
    var result = number
    while (i < 3) {
      result = callback(result)
      i += 1
    }
    println(result)
  }

  def DoIncrement(number: Int): Int = {
    val n = number + 1
    n
  }

  def Task_5(): Unit = {
    println("Zadanie 5")
    val student_pracownik = new Osoba("Jan", "Kowalski") with Student with Pracownik
    val pracownik_student = new Osoba("Adam", "Nowak") with Pracownik with Student
    val nauczyciel = new Osoba("Jacek", "Myszkowski") with Nauczyciel
    val pracownik = new Osoba("Klara", "Nowak") with Pracownik
    val student = new Osoba("Klara", "Nowak") with Student

    println(student_pracownik.Podatek)
    println(pracownik_student.Podatek)

    println(nauczyciel.Podatek)
    println(pracownik.Podatek)
    println(student.Podatek)
  }

  abstract class Osoba(var imie: String, var nazwisko: String) {
    private var Imie: String = imie
    private var Nazwisko: String = nazwisko

    def Podatek: Double
  }

  trait Pracownik extends Osoba {
    var Pensja: Double = 1000

    override def Podatek: Double = this.Pensja * 0.2

    def SetPensja(pensja: Double): Unit = {
      Pensja = pensja
    }

    def GetPensja(pensja: Double): Double = {
      Pensja
    }
  }

  trait Nauczyciel extends Pracownik {
    override def Podatek: Double = this.Pensja * 0.1
  }

  trait Student extends Osoba {
    override def Podatek: Double = 0
  }

}
