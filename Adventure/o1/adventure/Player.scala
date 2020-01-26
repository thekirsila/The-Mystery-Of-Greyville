package o1.adventure

import scala.collection.mutable.Map

import scala.math.min
import scala.collection.mutable.Buffer
import scala.util.Random
import scala.io.StdIn._
import o1._
//import o1.gui.Pic
//import o1.show


/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area, adventure: Adventure) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  var items = Buffer[Item]()
  private var quests = Buffer[Quest]()
  var currentHealth = Constants.PlayerHealth
  var damage = Constants.BaseDamage
  var locationBuffer = Vector[Area]()
  
  
 
 private val map1 = Pic("map2.svg.png")
 private val map2 = Pic("map1.svg.png")
 private val map3 = Pic("map3.svg.png")
 private val map4 = Pic("map4.svg.png")
  //Tästä alkaa omatekoista tekstiseikkailupeliä varten tehdyt metodit
 
  def help: String = {
    println("You are an adventurer, who needs to seek out the mystery of a silent village...")
    println("Move around and explore the area with the help of maps you might stumble across")
    println("New commands:")
    Vector("drink      - Drink a health potion, if you have one [drink potion]",
           "look       - Look at a map [look map to the gate]",
           "lebensraum - A secret command (might want to try it by the painting of Hitler) [lebensraum]",
           "help       - You quite seem to have discovered this one [help]",
           "quests     - Lists your active quests [quests]").foreach(println(_))
    println("Old commands:")
    Vector("go    	   - Go towards a given exit [go east]",
           "inventory  - Shows what items you carry with you [inventory]",
           "examine    - Look at a certain item and wonder about the meaning of it's existence [examine potion]",
           "get        - Grab an item that is simply lying around [get sword]",
           "drop       - Throw an item away [drop scarf]",
           "rest       - Take your time, stay and ponder [rest]",
           "quit       - In case you'd decide to stop playing [quit]").foreach(println(_))
    println("\nThere are also two types of combat systems in the game - both quite self-explanatory, as long as you read the instructions")
    println("Our test group didn't care to read and complained about complexity")
    println("\nWhen talking to other characters, there are some default responses, but a good way to go forward is by asking about quests")
    ""
  }
  
  def isDead: Boolean = health <= 0
  
  def health = currentHealth
  
  def healthBar: String = {
    //Shows a healthbar of the form [######    ]
    val percent = math.ceil(currentHealth.toDouble / Constants.PlayerHealth.toDouble * Constants.HealthBarResolution).toInt
    var rtrn = "["
    
    var i = 0
    while (i < percent) {
      rtrn += "#"
      i = i + 1
    }
    
    i = 0
    while (i < Constants.HealthBarResolution - percent) {
      rtrn += " "
      i += 1
    }
    rtrn + "] " + currentHealth + "/" + Constants.PlayerHealth
  }
  
  //Vähentää elämiä
  def takeDamage(amount: Int) = currentHealth -= amount
  
  //Lisää elämiä
  def heal(amount: Int) = currentHealth += amount
  
  def talk: String = {
    if (this.currentLocation.human.getOrElse(None) != None) {
      val human = this.currentLocation.human.get
      println(human.name + ": " + human.responses("hello"))
      var command = ""
      do {
        command = readLine("You: ")
        if (command.toLowerCase.contains("quest") && quests.exists(_ == human.quest.getOrElse(None))) {
           if (this.has(human.quest.get.requirement.name)) {
             println(human.name + ": " + "Oh, that is my daughters necklace, so she's alive! Thank you so much. Here's a little something for your troubles")
             items.append(human.quest.get.reward)
             quests.remove(quests.indexOf(human.quest.get))
           } else {
             println(human.name + ": " + "Oh dear... You didn't get me what I wanted. Well I guess you still have time")
           }
        } else if (command.toLowerCase.contains("quest")){
          human.quest match {
            case None        => println(human.name + ": " + "I'm sorry, I don't have a quest for you...")
            case Some(thing) => getQuest(thing)
          }
        } else if (human.responses.contains(command)) {
          println(human.name + ": " + human.responses(command))
        } else {
          println(human.name + ": " + human.noUnderstand)
        }
      } while (command != "bye")
        
      def getQuest(quest: Quest) = {
        println(human.name + ": " + quest.description)
        quests.append(quest)
      }
      
      if (human.name == "Girl in Peril") {
        items.append(new Item("necklace", "An ancient necklace the girl gave to you. Take it to his father"))
        println("Just as you're about to leave, the girl runs after you and gives you her necklace.")
        println("Girl in Peril: Tell my father, I'll be safe here")
      }
    } else println("There is no human here..")
    ""
  }
  
  def lebensraum: String = {
    if (currentLocation.name.toLowerCase.contains("hitler")) {
      currentLocation.setNeighbor("through the painting", adventure.hiddenRoom)
      "The magic words seem to have an effect... The painting moves aside"
    } else {
      "Hmm.. Interesting choice of words... Maybe they'll be useful someday..."
    }
  }
  
  def questList: String = {
    "Your active quests are: " + quests.map(_.description).mkString(", ")
  }
  
  //Ja tähän ne omatekoiset metodit päättyy
  
  private def getItemFromName(itemName: String): Item = {
    items.filter(_.name == itemName)(0)
  }
  
  
  def inventory: String = {
    if (items.size > 0) {
      "You are carrying\n" + items.mkString("\n")
    } else {
      "You are empty-handed."
    }
  }
  
  def has(itemName: String) = items.exists(_.name == itemName)
  
  def get(itemName: String): String = {
    var rtrn = ""
    
    def successful(item: Item): String = {
      items += item
      "You pick up the " + item.name + "."
    }
    
    if (itemName == "gatekey") Constants.SpawnRate = 7
    
    if ((this.inventory.contains("map to the gate") || this.inventory.contains("map to the townhall") || this.inventory.contains("map to the cabin") || this.inventory.contains("map of the school")) && itemName.contains("map")) {
      println("You already have a map!")
      val answer = readLine("Do you wish to grab the new map that will lead you to greatness? (y)es or (n)o: ")
      if (answer == "y" || answer == "yes") {
        rtrn = "Uuuu! A new map!"
        currentLocation.removeItem(itemName) match {
          case Some(theItem)  => rtrn = successful(theItem)
          case None           => rtrn = "There is no " + itemName + " here to pick up."
        }
      } else if (answer == "n" || answer == "no") {
        rtrn = "For some reason you decide to continue following a map that has clearly served its purpose."
      } else {
        rtrn = "After staring blankly at the map unable to make a decision for multiple long seconds you finally snap out of it..."
      }
    } else {
      currentLocation.removeItem(itemName) match {
        case Some(theItem)  => rtrn = successful(theItem)
        case None           => rtrn = "There is no " + itemName + " here to pick up."
      }
    }
    
    rtrn
  }
  
  def drop(itemName: String): String = {
    if (items.exists(_.name == itemName)) {
      val theItem = getItemFromName(itemName)
      items.remove(items.indexOf(theItem))
      currentLocation.addItem(theItem)
      "You drop the " + theItem.name + "."
    } else {
      "You don't have that!"
    }
  }
  
  def examine(itemName: String): String = {
    if (items.exists(_.name == itemName)) {
      val theItem = getItemFromName(itemName)
      "You look closely at the " + theItem.name + ".\n" + theItem.description
    } else {
      "If you want to examine something, you need to pick it up first."
    }
  }


  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven


  /** Returns the current location of the player. */
  def location = this.currentLocation
  
  def drink (itemName: String) = {
    if (this.inventory.contains("potion") && itemName == "potion") {
      var healthRecovered = currentHealth
      items -= getItemFromName(itemName)
      currentHealth = min(100, currentHealth + 50)
      healthRecovered = 100-healthRecovered
      "You drank a potion and your health recovered by " + healthRecovered + " point(s)."
    }
    else {
      "You don't have anything to drink!"
    }
       
  }
  
  def showMap (itemName: String)() = {
    var rtrn = ""
    if (this.inventory.contains("map") && itemName == "map to the gate") {
      show(map1)
      rtrn = "Showing the map of the area"
    }
    else if (this.inventory.contains("map") && itemName == "map to the townhall") {
      show(map2)
      rtrn = "Showing the map of the area"
    }
    else if (this.inventory.contains("map") && itemName == "map to the cabin") {
      show(map3)
      rtrn = "Showing the map of the area"
    }
    else if (this.inventory.contains("map") && itemName == "map of the school") {
      show(map4)
      rtrn = "Showing the map of the area"
    }
    else {
      rtrn = "You don't have the map!"
    }
    rtrn
  }


  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String): String = {
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    locationBuffer = locationBuffer :+ this.currentLocation
    var rtrn = ""
    
    if (destination.isDefined) {
      if (Random.nextInt(101) <= Constants.SpawnRate || destination.get.hasEnemy) {
        val fight = new Fight(this, destination.get)
        rtrn = fight.returnText
      } else rtrn = "You go " + direction + "."
      
      if (destination.get.name == "Spooky Graveyard") {
        Spooky
        rtrn = "\nThe ancient church door opens up leaving a trail of dust and spiderweb in it's wake."
      }
      
      if (destination.get.name == "Gate" && this.has("gatekey")) {
        destination.get.setNeighbor("to the downtown", adventure.VictoriaAvenue1)
        rtrn = "\nThe gate opens up, allowing you to enter a whole new world"
      }
      
      if (destination.get.name == "The door of the room 105" && this.has("The key to room 105")) {
        destination.get.setNeighbor("to the room 105", adventure.room105)
        rtrn = "\nThe door gives way with a silent screech"
      }
      
      if (destination.get.name == "Backdoor of the school" && this.has("corrosive")) {
        destination.get.setNeighbor("to the backyard", adventure.backYard)
        rtrn = "\nThe door gives way with a silent screech"
      }
      
      if (destination.get.name == "Door of the school" && this.quests.length > 0) {
        destination.get.setNeighbor("to the school", adventure.hall)
      }
      
      adventure.bossStart
      
    } else rtrn = "You can't go " + direction + "."
    rtrn
  }


  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() = {
    "You rest for a while. Better get a move on, though."
  }


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() = {
    this.quitCommandGiven = true
    ""
  }


  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name


}