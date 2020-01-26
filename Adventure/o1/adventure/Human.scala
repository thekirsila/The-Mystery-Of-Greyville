package o1.adventure

class Human(val name: String, val quest: Option[Quest], customResponses: Option[Map[String, String]]) {
  
  private val rspns = Map("hello"                -> "Well hello there!",
                  "bye"                  -> "Goodbye!",
                  "how are you doing"    -> "I'm almost terrified about the goblin apocalypse!",
                  "who are you"          -> ("My name is " + name + " and I'm a proud peasent of Grayville"),
                  "what happened to you" -> "I used to be an adventurer like you, but then I took an arrow to the knee.").toBuffer
                      
  customResponses match {
    case None =>
    case Some(thing) => thing.toVector.foreach(responsePair => rspns.append(responsePair))
  }
  val responses = rspns.toMap
                      
  val noUnderstand = "I'm sorry, I didn't quite catch that..."
}