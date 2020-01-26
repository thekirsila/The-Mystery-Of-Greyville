package o1.adventure

object Constants {
  var SpawnRate = 0 //The chance of finding a monster in percent
  
  var EnemyDamage = 10 //ON VALUES LOWER THAN 5, THE ENEMY COULD INFLICT DAMAGE ON ITSELF
  var EnemyAgression = 60 //How often will the enemy attack you (as opposed to how often will it just stay put and hiss)
  
  val Factor = 10 //Random damage factor for fights
  
  //Player-related variables
  val PlayerHealth = 100
  val BaseDamage = 10 //ON VALUES LOWER THAN 5, THE PLAYER COULD INFLICT DAMAGE ON ITSELF
  
  val HealthBarResolution = 20 //How long (and therefore accurate) the healthbar is
  val HealthPotionStrength = 50
  
  val BossHealth = 200
  val BossDifficulty = 270 // 250 on hankala, 200 aika lailla mahdoton, 300 helpohko
  val Words = Vector("distinguished", "fool", "joker", "weapon", "destroy", "enemy", "battle-hardened", "demon", "Alpha Centauri",
      "twat", "adventurer", "university", "shield", "honorable", "goblin", "tweeb", "scala", "newbie", "noob", "arrow", "godlike")
  val BossStrength = 20
  
  //Weapon strengths:
  val SwordStr = 35
  val ScarfStr = 5
}