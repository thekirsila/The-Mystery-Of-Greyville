package o1.adventure
import scala.collection.mutable.Buffer
import o1._


/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure {
 
    

  /** The title of the adventure game. */
  val title = "Kingwoods' mystery"
 
  //Home
  private val bedroom          = new Area("Bedroom", "Your bedroom during the stay in this weird old town. \nThere is hardly any furniture, just an ancient bed and a chest. \nThere is also a small window that opens to the town´s main road.")
  private val livingroom       = new Area("Livingroom", "In the livingroom there's a sofa and a dresser. There might be something in it")
 
  //Robinsons'
  private val robinsons        = new Area("Robinsons'", "This house must have been empty for years. The door is jammed")
 
 
  //Main road: Veronica Avenue
  private val veronicaAvenue0  = new Area("Veronica Avenue", "The town´s main road. There is a bridge in front of you but it has collapsed, probably during the night. \nWeird that you didn't hear it.")
  private val veronicaAvenue1  = new Area("Veronica Avenue", "The town´s main road. There is not a living soul in sight. It´s hard to see because it's so misty!\nYour home is here and Robinsons' live opposite to you")
  private val veronicaAvenue2  = new Area("Veronica Avenue", "The town´s main road. There is not a living soul in sight. It´s hard to see because it's so misty!")
  private val veronicaAvenue3  = new Area("Veronica Avenue", "The town´s main road. There is not a living soul in sight. It´s hard to see because it's so misty!")
  private val veronicaAvenue4  = new Area("Veronica Avenue", "The town´s main road. There is a crossroad ahead.")
  private val veronicaAvenue5  = new Area("Veronica Avenue", "The town´s main road. The weather forecast said that this should have been a sunny day.")
  private val veronicaAvenue6  = new Area("Veronica Avenue", "The town´s main road. There is not a living soul in sight. It´s hard to see because it's so misty!")
  private var veronicaAvenue7  = new Area("Veronica Avenue", "The town´s main road. There is a bizarre house in the woods")
  private val veronicaAvenue8  = new Area("Veronica Avenue", "The town´s main road. There is not a living soul in sight. It´s hard to see because it's so misty!")
  private val veronicaAvenue9  = new Area("Veronica Avenue", "The town´s main road. There is a closed gate with a massive lock. \nYou couldn't break it even though you tried. Find the key to open the gate.")
 
  //Crossroad
  private val crossroad       = new Area ("Crossroad", "This is the crossroad of Veronica Avenue and Hermann Road")
 
  //Hermann Road
  private val HermannRoad3 = new Area("Hermann Road", "It's hard to see far because it's so misty!")
  private val HermannRoad2 = new Area("Hermann Road", "It's hard to see far because it's so misty!")
  private val HermannRoad0 = new Area("Hermann Road", "There must be something gruesome waiting for you in the mist. Turn around")
  private val HermannRoad1 = new Area("Hermann Road", "The mist gets thicker all the time. You are not supposed to be here.")
  private val HermannRoad4 = new Area("Hermann Road", "It's hard to see far because it's so misty!")
  private val HermannRoad5 = new Area("Hermann Road", "It's hard to see far because it's so misty!")
  private var HermannRoad6 = new Area("Hermann Road", "Wow! I heard something! That sound didn't come from a living creature. It has to be something worse... \nIt's hard to see far because it's so misty!")
  private val HermannRoad7 = new Area("Hermann Road", "Town hall on the right. Road is blocked ahead. It's hard to see far because it's so misty!")
 
  //Townhall
  private val hallway2 = new Area("Hallway", "You see a narrow corridor in front of you. You wish you had a flashlight to fight the darkness.\nThere must be more of those sanquinary goblins somewhere...")
  private var hallway1 = new Area("Hallway", "You see a narrow corridor in front of you. You wish you had a flashlight because it's so dark.\nYou feel there is somebody or something with you... Move carefully!")
  private var atrium   = new Area("Atrium", "Town hall's atrium. The floor is covered by broken glass. There might be something small and shiny hidden...")
 
  //gate
  private var gate = new Area("Gate", "This is the gate to another area")
 
  //Victoria Avenue  
  var VictoriaAvenue1         = new Area("Victoria Avenue", "This road leads to an elementary school")
  private val Bridge          = new Area("Bridge", "There was a river down there yesterday. Now I can't see a thing!")
  private val VictoriaAvenue2 = new Area("Victoria Avenue", "There is a burgerlord on the right")
  private val VictoriaAvenue3 = new Area("Victoria Avenue", "This road leads to an elementary school")
  private val roundabout      = new Area("Roundabout", "You can go south, west and east")
  private val VictoriaAvenue4 = new Area("Victoria Avenue", "There are some old houses to the right")
  private val VictoriaAvenue5 = new Area("Victoria Avenue", "There is an elementary school to the right")
 
  //Stalin Road
  private val stalinRoad1     = new Area ("Stalin Road", "That small road seems to lead nowhere")
  private val stalinRoad2     = new Area ("Stalin Road", "There is a small cabin in the woods")
 
  //Cabin in the woods
  private val cabInTheWods = new Area("The Cabin", "There is a chest in the corner.")
 
  //Elementary school
  //1. floor
  var schoolDoor              = new Area("Door of the school", "To open this door you have had to recieve a responsible quest from other great adventurer!")
  var hall                    = new Area("Hall", "This is the hall of the elementary school. The power seems to be out")
  private val noticeBoard     = new Area("Notice board", "There is a map of the school on the notice board")
  private val staircase       = new Area("Staircase", "You can go to the second floor or back to the hall")
  private val corridor1       = new Area("A narrow corridor", "There are classrooms on both sides")
  private val room100         = new Area("Some old classroom","Must be used in chemistry")
  private val room101         = new Area("Some old classroom", "Must be used in physics")
  private val corridor2       = new Area("A narrow corridor", "There are classrooms on both sides")
  private val room102         = new Area("Some old classroom", "Must be used in physics as well")
  private val room103         = new Area("Some old classroom", "Must be used in math. There is a huge whiteboard on the wall.\nWish I had it at home!")
  private val corridor3       = new Area("A narrow corridor", "There are classrooms on both sides")
  private val room104         = new Area("Some old classroom", "There is blood on the floor. God knows what has happened here!")
  private val roomdoor        = new Area("The door of the room 105", "You have to find the key first!")
  var room105                 = new Area("Room 105", "There is a crucifix on the table.\nHmm... Are we going to go to the church perhaps?")
  var backDoor                = new Area("Backdoor of the school", "It seems to be rusted. You'll have to find corrosive to melt it")
 
  //2. floor
  private val staircase2      = new Area("Staircase of the 2nd floor", "You can go up or down")
  private val corridor5       = new Area("A narrow corridor", "There is an old wheelchair blocking the corridor on the right.\nOnly way is to go back to the staircase or continue to the left.")
  private val room200         = new Area("English class", "Nobody would enjoy being here.")
  private val room201         = new Area("German class", "There is a picture of the Great Leader on the wall")
  private val corridor6       = new Area("A narrow corridor", "There is a huge hole in the floor. You can't jump over it")
 
  //Secret room
  private val hitler          = new Area("The picture of Adolf Hitler", "Hint: Now what was the German living space called?")
  val hiddenRoom              = new Area("Hidden room", "Wow I´m impressed! I though you would never come to visit me!\nYou won't find answers what is happening to the world here but at least we have some fun!\nThen the ghost disappears into thin air")
 
  //3rd floor
  private val staircase3      = new Area("Staircase of the 3rd floor", "You can go down")
  private val corridor7       = new Area("A narrow corridor", "There is a burning candle at the end of the corridor")
  private val room300         = new Area("The door is jammed","")
  private val corridor8       = new Area("End of the corridor", "There is a weeping girl under the barrel")
 
 
  //Backyard of the school
  var backYard                = new Area("Backyard", "It's getting dark")
  private val gate2           = new Area("Gate of the school", "Old iron gate. It's not locked, weird.")
 
  //Path
  private val path1           = new Area("Path in the woods", "Everything looks like monsters in the dark forest!\nWhat was that noice?")
  private val path2           = new Area("Path in the woods", "I wish I had a map of this area!")
  private val path3           = new Area("Path in the woods", "It looks like you are going to an old graveyard")
 
  //Graveyard
  private val graveyard       = new Area("Graveyard", "I think dead people live here.\nIf there was an undead SS-officer, it would definitely live here...\nWell wasn't that oblivious...")
  private val graveyard2      = new Area("Spooky Graveyard", "")
 
  //BurgerLord  
  private val BurgerKing       = new Area("BurgerLord", "There is usually Big Mac on the table but this time you might find something else")
 
  //Church
  private val church          = new Area("Church", "We... Are... Doomed...")
 
 
 
 
 
  //All the neighbors for each "square", please scroll way downwards. There is nothing to see here. Go, please. If you mind.
 
 
       bedroom.setNeighbors(Vector(   "to livingroom"   -> livingroom                                                                                              ))
  livingroom.setNeighbors(Vector(     "to bedroom"      -> bedroom,         "outside"        -> veronicaAvenue1                                                    ))
 
  robinsons.setNeighbors(Vector(                                          "back to the road"   -> veronicaAvenue1                                                   ))
 
  veronicaAvenue1.setNeighbors(Vector("west"         -> veronicaAvenue0, "east"            -> veronicaAvenue2, "home" -> livingroom, "to the robinsons" -> robinsons   ))
  veronicaAvenue0.setNeighbors(Vector(                                   "east"            -> veronicaAvenue1                                                   ))
  veronicaAvenue2.setNeighbors(Vector("west"         -> veronicaAvenue1 ,"east"            -> veronicaAvenue3                                                   ))
  veronicaAvenue3.setNeighbors(Vector("west"         -> veronicaAvenue2, "east"            -> veronicaAvenue4                                                   ))
  veronicaAvenue4.setNeighbors(Vector("west"         -> veronicaAvenue3, "east"            -> crossroad                                                         ))
  veronicaAvenue5.setNeighbors(Vector("west"         -> crossroad,       "east"            -> veronicaAvenue6                                                   ))
  veronicaAvenue6.setNeighbors(Vector("west"         -> veronicaAvenue5 ,"east"            -> veronicaAvenue7                                                   ))
  veronicaAvenue7.setNeighbors(Vector("west"         -> veronicaAvenue6 ,"east"            -> veronicaAvenue8                                                   ))
  veronicaAvenue8.setNeighbors(Vector("west"         -> veronicaAvenue7 ,"east"            -> veronicaAvenue9                                                   ))
  veronicaAvenue9.setNeighbors(Vector("west"         -> veronicaAvenue8, "east"            -> gate                                                              ))
 
  crossroad.setNeighbors(Vector(      "west"         -> veronicaAvenue4, "east"            -> veronicaAvenue5,"north" -> HermannRoad3, "south"   -> HermannRoad4))
 
  HermannRoad3.setNeighbors(Vector(                                                                           "north" -> HermannRoad2,   "south" -> crossroad   ))
  HermannRoad2.setNeighbors(Vector(                                                                           "north" -> HermannRoad1,   "south" -> HermannRoad3))
  HermannRoad1.setNeighbors(Vector(                                                                           "north" -> HermannRoad0,   "south" -> HermannRoad2))
  HermannRoad0.setNeighbors(Vector(                                                                                                      "south" -> HermannRoad1))
  HermannRoad4.setNeighbors(Vector(                                                                           "north" -> crossroad,      "south" -> HermannRoad5))
  HermannRoad5.setNeighbors(Vector(                                                                           "north" -> HermannRoad4,   "south" -> HermannRoad6))
  HermannRoad6.setNeighbors(Vector(                                                                           "north" -> HermannRoad5,   "south" -> HermannRoad7))
  HermannRoad7.setNeighbors(Vector(                                     "to the town hall" -> hallway1,       "north" -> HermannRoad6                           ))
    
  hallway1.setNeighbors(Vector(   "back to the road" -> HermannRoad7 ,"to the creepy corridor" -> hallway2                                                          ))
  hallway2.setNeighbors(Vector(   "towards the door" -> hallway1,          "to the atrium" -> atrium                                                            ))
  atrium.setNeighbors(Vector( "back to the corridor" -> hallway2                                                                                                ))
 
  gate.setNeighbors(Vector("to the town center"          -> veronicaAvenue9                                                                                     ))                                                                                    
 
  VictoriaAvenue1.setNeighbors(Vector("west"         -> gate,            "east"            -> VictoriaAvenue2                                                   ))
  VictoriaAvenue2.setNeighbors(Vector("west"         -> VictoriaAvenue1, "east"            -> VictoriaAvenue3,                "to the burgerlord"-> BurgerKing   ))
  BurgerKing.setNeighbors(Vector("outside"            -> VictoriaAvenue2                                                                                         ))
  VictoriaAvenue3.setNeighbors(Vector("west"         -> VictoriaAvenue2, "east"            -> roundabout                                                        ))
  VictoriaAvenue4.setNeighbors(Vector("west"         -> roundabout,      "east"            -> VictoriaAvenue5    ))
  VictoriaAvenue5.setNeighbors(Vector("west"         -> VictoriaAvenue4, "to the school"   -> schoolDoor))
  schoolDoor.setNeighbors(Vector("back to the road"   -> VictoriaAvenue5))
 
  roundabout.setNeighbors(Vector("west"              -> VictoriaAvenue3,  "east"           -> VictoriaAvenue4,                           "south" -> stalinRoad1 ))
  stalinRoad1.setNeighbors(Vector(                                                                             "north" -> roundabout,    "south" -> stalinRoad2 ))
  stalinRoad2.setNeighbors(Vector(                                                                             "north" -> stalinRoad1,   "to the cabin" -> cabInTheWods))
  cabInTheWods.setNeighbors(Vector(                                                                            "outside" -> stalinRoad2                           ))
    
  hall.setNeighbors(Vector( "to the notice board"    -> noticeBoard,     "to the corridor" -> corridor1, "to the staircase" -> staircase, "outside" -> schoolDoor))
  noticeBoard.setNeighbors(Vector(                                 "back to the hall"       -> hall                                                              ))
  corridor1.setNeighbors(Vector("to the classroom 100" -> room100,  "back to the hall"      -> hall, "to the room 101" -> room101, "to the corridor" -> corridor2))
  room100.setNeighbors(Vector("back to the corridor" -> corridor1                                                                                                                                                                                                 ))
  room101.setNeighbors(Vector("back to the corridor" -> corridor1                                                                                                                                                                                                 ))
  corridor2.setNeighbors(Vector("to the classroom 102" -> room102,  "back towards the door" -> corridor1,"to the room 103" -> room103, "to the corridor" -> corridor3))
  room102.setNeighbors(Vector("back to the corridor" -> corridor2                                                                                                                                                                                                 ))
  room103.setNeighbors(Vector("back to the corridor" -> corridor2                                                                                                                                                                                                 ))
  corridor3.setNeighbors(Vector("to the classroom 104" -> room104,  "back towards the door" -> corridor2,"to the door of the room 105" -> roomdoor, "to the backdoor" -> backDoor))
  roomdoor.setNeighbors(Vector("back to the corridor" -> corridor3                                                                                                 ))
  room104.setNeighbors(Vector("back to the corridor" -> corridor3                                                                                                                                                                                                 ))
  room105.setNeighbors(Vector("back to the corridor" -> roomdoor                                                                                                 ))                                                                                                                                                                                             
  backDoor.setNeighbors(Vector("back to the corridor" -> corridor3                                                                                               ))
  staircase.setNeighbors(Vector("upstairs"         -> staircase2,  "back to the hall" -> hall                                                                 ))
 
  staircase2.setNeighbors(Vector("upstairs"         -> staircase3,  "downstairs"    -> staircase,"to the second floor" -> corridor5                         ))
  corridor5.setNeighbors(Vector("to the classroom 200" -> room200,  "back towards the staircase" -> staircase2,"to the corridor" -> corridor6,"to the room 201" -> room201))
  room200.setNeighbors(Vector("back to the corridor" -> corridor5                                                                                                                                                                                                 ))
  room201.setNeighbors(Vector("back to the corridor" -> corridor5,    "to examine the picture" -> hitler     ))
  hitler.setNeighbors(Vector("back to the classroom" -> room201 ))
  hiddenRoom.setNeighbors(Vector("through the painting" -> hitler))
  corridor6.setNeighbors(Vector("back towards the staircase" -> corridor5                                                                                        ))
 
  staircase3.setNeighbors(Vector("downstairs"    -> staircase2,"to the third floor" -> corridor7                                                              ))
  corridor7.setNeighbors(Vector("back towards the staircase" -> staircase3, "to room 300" -> room300, "to the corridor" -> corridor8                              ))
  room300.setNeighbors(Vector("back to the corridor" -> corridor7                                                                                                ))
  corridor8.setNeighbors(Vector("back towards the staircase" -> corridor7                                                             ))
 
  backYard.setNeighbors(Vector("back to the school" -> backDoor, "to the gate" -> gate2                                                                          ))
  gate2.setNeighbors(Vector("back to the backyard" -> backYard, "to the path" -> path1                                                                           ))
  path1.setNeighbors(Vector("back to the gate" -> gate2,        "to the path" -> path2                                                                           ))
  path2.setNeighbors(Vector("back to the school" -> path1,      "to the path" -> path3                                                                           ))
  path3.setNeighbors(Vector("back to the school" -> path2,      "to the graveyard" -> graveyard                                                                  ))
  graveyard.setNeighbors(Vector("back to the safe path" -> path3, "towards the church" -> graveyard2                                                              ))
  graveyard2.setNeighbors(Vector("back towards the safe path" -> graveyard, "towards the opened door where something cruel is waiting for me" -> church       ))                                                            
 
  private val gatekey = new Item("gatekey", "A massive, old key. A key this size would fit only the bulkiest of locks!")
  private val potion = new Item("potion", "This will recover your health points by 50")
  private val scarf = new Item("scarf", "Scarf of the Guild of Physics. While carrying this, you will make more damage to the enemies")
  private val map1 = new Item("map to the gate", "This is an old map of the city center.\nYou can use it by writing 'look' and the name of the map")
  private val map2 = new Item("map to the townhall", "This is updated version of earlier map")
  private val map3 = new Item("map to the cabin", "This map will lead you to glouroius battles")
  private val map4 = new Item("map of the school", "This stained paper used to be the map of the school. Used to be.")
  private val sword = new Item("sword", "A bit rusty, but still a useful piece of goblin-mangling equipment.")
  private val necklace = new Item("necklace", "An ancient necklace the girl gave to you.  it to his father")
  private val schoolkey = new Item("The key to room 105", "With this key you can open the door of the class 105.\nDon't lose it, you may need it later...")
  private val corrosive = new Item("corrosive", "You need this to melt the lock of the backdoor")
  //Source of the picture: https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pngtube.com%2Fviewm%2FmibTJ_old-burned-vintage-paper-texture-old-burned-paper%2F&psig=AOvVaw0N1_EV5EOoDqEOftJEwWcH&ust=1574702928747000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIixutqvg-YCFQAAAAAdAAAAABAN
 
  private val quest = new Quest("Find the warrior's daughter", necklace, schoolkey)
  private val human = new Human("Steve", Some(quest), Some(Map("what do I do" -> "Go find my little girl")))
  private val girl  = new Human("Girl in Peril", None, Some(Map("what happened?" -> "I usually spend nights here playing with my friends.\nLast night dawn descended faster than usually.\nI lost my friends and came here. Can you please tell me what is going on?")))
 
  atrium.addItem(gatekey)
  livingroom.addItem(potion)
  BurgerKing.addItem(potion)
  graveyard.addItem(potion)
  room101.addItem(scarf)
  robinsons.addItem(map1)
  room102.addItem(scarf)
  hiddenRoom.addItem(sword)
  gate.addItem(map2)
  VictoriaAvenue1.addItem(map3)
  noticeBoard.addItem(map4)
  room105.addItem(corrosive)
  
  hallway2.placeEnemy
  hiddenRoom.placeEnemy
  
  cabInTheWods.placeHuman(human)
  corridor8.placeHuman(girl)
 
    /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 1000

  val player = new Player(bedroom, this)
  val boss = new Boss(player)
 
  def bossStart = {
    if (this.player.location == church) {
    boss.fight
  }
    
  }
  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete: Boolean = boss.victory

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.player.isDead

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "You are spending your holiday in an idyllic little town in the middle of nowhere. All inhabitants were yesterday so frienly to you. \nBut something has happened during the night. You feel it in your bones."



  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete)
      "The end..."
    else if (this.turnCount == this.timeLimit)
      "Wandering around in the vast environments given to you, you lose your sense of time and fail to understand what is going on"
    else if (player.isDead)
      "Closing your eyes, you wonder what could have happened, had you won in this mortal combat."
    else  // game over due to player quitting
      "Quitter!"
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String):String = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    
    if (outcomeReport.isDefined) {
      this.turnCount += 1
    }
    
    
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }

 

 
}



