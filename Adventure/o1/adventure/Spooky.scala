package o1.adventure

object Spooky {
  Thread.sleep(2500)
  print("The silence is almost unfathomable, saint-like, unbreakable. Something that would be torn apart by a whisper")
  var i = 0
  while (i < 13) {
    Thread.sleep(900)
    print(".")
    i += 1
  }
  
  i = 0
  while (i < 600) {
    print("X " * 10)
    Thread.sleep(1)
    i += 1
    if (i%10 == 0) print("\n")
  }
  
  Thread.sleep(5000)
  i = 0
  while (i < 100) {
    println("")
    i += 1
  }
  
  for (char <- "...oh no.......") {
    print(char)
    Thread.sleep(700)
  }
  
  i = 0
  while (i < 100) {
    println("")
    i += 1
  }
  
  for (char <- "Bedroom") {
    print(char)
    Thread.sleep(100)
  }
  println("\n-------")
  
  for (char <- "Your bedroom during the stay in this weird old town.") {
    print(char)
    Thread.sleep(100)
  }
  print("\n")
  
  for (char <- "There is hardly any furniture, just an ancient bed and a chest.") {
    print(char)
    Thread.sleep(100)
  }
  print("\n")
  
  for (char <- "There is also a small window that opens to the town´s main road.") {
    print(char)
    Thread.sleep(100)
  }
  Thread.sleep(5000)
  
  i = 0
  while (i < 100) {
    println("")
    i += 1
  }
}