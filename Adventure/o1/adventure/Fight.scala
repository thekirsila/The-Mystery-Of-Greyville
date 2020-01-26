package o1.adventure

import scala.util.Random
import scala.io.StdIn._

class Fight(player: Player, area: Area) {
  //Tässä määritellään miten taisteleminen etenee
  private var enemy = new Enemy ("goblin", 40)
  if (area.name == "Hidden room") {
    enemy = new Enemy ("Undead SS-Officer", 120)
  }
  var returnText = ""
  
  println("\n\nYou have encountered a rogue " + enemy.name + " ready to hack you into pieces and sell your valuables at the black market")
  if (player.has("sword")) println("In this battle, you wield a mighty sword, resulting in around " + Constants.SwordStr + " damage to your enemy")
  if (player.has("scarf")) println("In this battle, you wield the mighty yellow-black Scarf of the Physician, adding around " + Constants.ScarfStr + " to your damage")
  while (enemy.health > 0 && player.health > 0) {
    
    //Setting up damage values
    var playerDmg = player.damage
    if (player.has("sword")) playerDmg = Constants.SwordStr
    if (player.has("scarf")) playerDmg += Constants.ScarfStr
    playerDmg = playerDmg + Constants.Factor/2 - Random.nextInt(Constants.Factor)
    val enemyDmg = Constants.EnemyDamage + Constants.Factor/2 - Random.nextInt(Constants.Factor)
    
    
    if(Random.nextInt(101) <= Constants.EnemyAgression) {
      
      //The enemy decides to attack you
      println("\nThe enemy makes it's move and decides to swing it's sword. Will you (a)ttack or (d)efend?")
      println("Enemy health --- " + enemy.healthBar)
      println("Your health ---- " + player.healthBar)
      val command = readLine("Command: ")
            
      if (command == "attack" || command == "a") {
        println("You jump around frantically, making yourself a harder target, resulting in " + enemyDmg/2 + " to youself and " + playerDmg/2 + " to the enemy")
        enemy.takeDamage(playerDmg/2)
        player.takeDamage(enemyDmg/2)
              
      } else if (command == "defend" || command == "d"){
        println("You brace for impact, resulting in " + 0 + " to youself and " + 0 + " to the enemy")
      } else {
        println("You seem to stress about fighting so much, that you fumble up writing commands, resulting in " + enemyDmg + " damage to yourself")
        player.takeDamage(enemyDmg)
      }
    } else {
      //The enemy stays put to gather itself
      println("\nFor some reason the " + enemy.name + " seems intimidated by your athletic body and refuses to swing its sword. Now's your time to (a)ttack!")
      println("Enemy health --- " + enemy.healthBar)
      println("Your health ---- " + player.healthBar)
      val command = readLine("Command: ")
      if (command == "attack" || command == "a") {
        println("You make your attack, resulting in " + playerDmg + " to the enemy")
        enemy.takeDamage(playerDmg)
      } else println("You feel mercy and dont do anything...")
    }
  }
        
  if (player.health <= 0) returnText = "You perish at the blade of an enemy far mightier than you, giving the cruel world one last look before being taken by the sands of time..."
  else {
    returnText = "This puny " + enemy.name + " is no match to your swordsmanship as you strike for the last time into its green flesh."
    area.removeEnemy
  }
}