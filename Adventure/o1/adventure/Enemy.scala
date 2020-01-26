package o1.adventure

class Enemy (val name: String, maxHealth: Int) {
  val damage = Constants.EnemyDamage
  var health = maxHealth
  
  //Vähentää elämiä
  def takeDamage(amount: Int) = health -= amount
  
  //Lisää elämiä
  def heal(amount: Int) = health += amount
  
  def healthBar: String = {
    //Shows a healthbar of the form [######    ]
    val percent = math.ceil(health.toDouble / maxHealth.toDouble * Constants.HealthBarResolution).toInt
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
    rtrn + "] " + health + "/" + maxHealth
  }
  
}