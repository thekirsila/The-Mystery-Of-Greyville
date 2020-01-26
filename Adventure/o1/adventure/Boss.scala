package o1.adventure

import scala.util.Random
import scala.math.min
import scala.io.StdIn._

class Boss(player: Player) {
  var victory = false
  
  var health = Constants.BossHealth
  var potions = 0
  
  def potionCounter: Unit = {
    for (i<-this.player.items) {
      if (i.name == "potion") {
        potions += 1
      }
      else {
        potions = 0
      }
    }
  }
  
  def healthBar(currentHealth: Int, maxHealth: Int): String = {
    //Shows a healthbar of the form [######    ]
    val percent = math.ceil(currentHealth.toDouble / maxHealth.toDouble * Constants.HealthBarResolution).toInt
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
    rtrn + "] " + currentHealth + "/" + maxHealth
  }
  
  def fight = {
    println("You have stumbled across the mightiest and most feared Dragon from here to Alpha Centauri")
    println("This isn't just your average dragon - it happens to be the greatest keyboard-wielder to ever trot the face of this planet")
    println("Or this is at least up to this day....")
    println("You are here to claim victory over this mighty dragon, once and for all....")
    println("\nThe way this game will work is as follows:")
    println("You will be prompted a word to write - do this as quickly as possible and hit <ENTER>")
    println("The Dragon will attempt the same and if so happens that you indeed are faster, you score damage")
    println("May the fastest keyboard wizard win!")
    while(health > 0 && player.health > 0) {
      //Setting up damage values
      var playerDmg = player.damage
      if (player.has("sword")) playerDmg = Constants.SwordStr
      if (player.has("scarf")) playerDmg += Constants.ScarfStr
      playerDmg = playerDmg + Constants.Factor/2 - Random.nextInt(Constants.Factor)
      val enemyDmg = Constants.BossStrength + Constants.Factor/2 - Random.nextInt(Constants.Factor)
      
      //The actual fight
      val theWord = Constants.Words(Random.nextInt(Constants.Words.length))
      println("\nYour health:         " + healthBar(player.health, Constants.PlayerHealth))
      println("The Dragon's health: " + healthBar(health, Constants.BossHealth))
      readLine("Press <ENTER> to when ready to continue.")
      println("\nThe word is: " + theWord)
      println("Start typing in...")
      Thread.sleep(1000)
      println("3")
      Thread.sleep(1000)
      println("2")
      Thread.sleep(1000)
      println("1")
      Thread.sleep(1000)
      val time1 = System.currentTimeMillis()
      val typedWord = readLine("TYPE: ")
      val timePerStrike = (System.currentTimeMillis() - time1)/theWord.length
      if (timePerStrike < Constants.BossDifficulty && typedWord == theWord && timePerStrike >= 100) {
        val choice = readLine("Do you wish to (h)eal or (a)ttack?")
        if (choice == "h" || choice == "heal") {
          if (potions > 0) {
            potionCounter
            var healthRecovered = this.player.health
            potions -= 1 
            this.player.currentHealth = min(100, this.player.currentHealth + 50)
            healthRecovered = 100-healthRecovered
            println("You drank a potion and your health recovered by " + healthRecovered + " point(s).")
          }
          else {
            println("You don't have any potions left! (The Dragon might've stolen them)")
          }
          
        } else if (choice == "a" || choice == "attack") {
          println("With your astonishing time per strike of " + timePerStrike + " ms, you beat the Dragon, inflicting " + playerDmg + " damage")
          health = health - playerDmg
        } else println("I didn't recognize that command! So obviously you do nothing.")
      } else if (typedWord != theWord) {
        println("You wrote " + typedWord + " when you were supposed to write " + theWord + "! You loose " + enemyDmg + " health")
        player.takeDamage(enemyDmg)
      } else if (timePerStrike < 100) {
        println("Yer a fokin cheater, ye arrrr.... (Nobody can write with a speed of " + timePerStrike + " ms)")
        player.takeDamage(enemyDmg)
      } else {
        println("Your time per strike of " + timePerStrike + " ms is no match to the Dragon. You loose " + enemyDmg + " health")
        player.takeDamage(enemyDmg)
      }
    }
    if (player.isDead) println("Oh no! You made it this far, but it was wrong of you to challenge the mighty keyboard-wielding Dragon. He beats you and will feast upon your soul tonight!")
    else {
      println("Damn. You actually won using your good wits and above-average cunning. Great Job!")
      victory = true
    }
  }
  
}