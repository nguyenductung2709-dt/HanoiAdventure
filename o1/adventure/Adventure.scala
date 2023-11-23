package o1.adventure

/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of “hard-coded” information that pertains to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure:

  /**TITLE*/
  val title = "Hanoi Adventure"

  /**HANOI'S DISTRICTS*/
  private val BacTuLiem      = Area("Bac Tu Liem", "You are in Bac Tu Liem. Bac Tu Liem is a district located in the western part of Hanoi, Vietnam, known for its rapid urban development and modern infrastructure. You can enter Circle K from here.")
  private val TayHo          = Area("Tay Ho", "You are in Tay Ho. Situated around West Lake, Tay Ho offers a more relaxed atmosphere with its scenic lakeside cafes, expatriate communities, and a mix of modern and traditional living.")
  private val NamTuLiem      = Area("Nam Tu Liem", "You are in Nam Tu Liem. Developing as a new urban area, Nam Tu Liem hosts the National Convention Center and a growing number of modern residential and commercial complexes. You can enter Circle K from here.")
  private val CauGiay        = Area("Cau Giay", "You are in Cau Giay.Growing as a business and education hub, Cau Giay is characterized by universities, office buildings, and shopping malls.")
  private val BaDinh         = Area("Ba Dinh", "You are in Ba Dinh. Home to significant historical sites like the Ho Chi Minh Mausoleum and the One Pillar Pagoda, Ba Dinh is a political and cultural center. You can enter Cirle K from here.")
  private val HoanKiem       = Area("Hoan Kiem", "You are in Hoan Kiem. Located in the heart of Hanoi, it's known for the historic Hoan Kiem Lake and the bustling Old Quarter, rich in traditional architecture and street markets.\nYou can go home!")
  private val LongBien       = Area("Long Bien", "You are in Long Bien. Famous for the historic Long Bien Bridge, this district is a blend of traditional and modern living, with a mix of residential and industrial areas. You can enter Circle K from here.")
  private val HaDong         = Area("Ha Dong", "You are in Ha Dong. Ha Dong is a district located in the southwestern part of Hanoi, Vietnam, known for its historical significance, bustling markets, and cultural heritage.")
  private val ThanhXuan      = Area("Thanh Xuan", "You are in Thanh Xuan. A mix of residential and industrial areas, Thanh Xuan is known for its local markets, parks, and vibrant street life.")
  private val DongDa         = Area("Dong Da", "You are in Dong Da. This district combines modern living with historical sites, including the Quoc Tu Giam (Imperial Academy) and the Hanoi Railway Station.\nYou must visit Quoc Tu Giam!!")
  private val HaiBaTrung     = Area("Hai Ba Trung", "You are in Hai Ba Trung. This district is undergoing rapid urbanization, with a blend of residential and commercial spaces, including shopping centers and entertainment venues.")
  private val HoangMai       = Area("Hoang Mai", "You are in Hoang Mai. Hoàng Mai is an urban district in the southeastern part of Hanoi, Vietnam, characterized by its mix of residential areas, commercial centers, and diverse neighborhoods.")
  private val destination    = Home

  BacTuLiem    .setNeighbors(Vector("east" -> TayHo, "south" -> CauGiay))
  TayHo        .setNeighbors(Vector("south" -> BaDinh, "west" -> BacTuLiem))
  NamTuLiem    .setNeighbors(Vector("east" -> CauGiay, "south" -> HaDong))
  CauGiay      .setNeighbors(Vector("north" -> BacTuLiem,"east" -> BaDinh, "south" -> ThanhXuan, "west" -> NamTuLiem))
  BaDinh       .setNeighbors(Vector("north" -> TayHo, "east" -> HoanKiem, "south" -> DongDa, "west" -> CauGiay))
  HoanKiem     .setNeighbors(Vector("east" -> LongBien, "south" -> HaiBaTrung, "west" -> BaDinh))
  LongBien     .setNeighbors(Vector("west" -> HoanKiem))
  HaDong       .setNeighbors(Vector("north" -> NamTuLiem, "east" -> ThanhXuan ))
  ThanhXuan    .setNeighbors(Vector("north" -> CauGiay, "east" -> DongDa, "west" -> HaDong))
  DongDa       .setNeighbors(Vector("north" -> BaDinh, "east" -> HaiBaTrung, "west" -> ThanhXuan))
  HaiBaTrung   .setNeighbors(Vector("north" -> HoanKiem, "south" -> HoangMai, "west" -> DongDa))
  HoangMai     .setNeighbors(Vector("north" -> HaiBaTrung))

  /**ADJUST FLAGS*/
  DongDa.canEnterQuocTuGiam = true
  HoanKiem.canGoHome = true
  LongBien.canEnterCircleK = true
  BaDinh.canEnterCircleK = true
  BacTuLiem.canEnterCircleK = true
  NamTuLiem.canEnterCircleK = true

  /**CUISINE LIST AND ADD CUISINE*/
  val comLangVong  = Cuisine("Vong village's green rice", "Vong village's green rice is a traditional Vietnamese delicacy made from young green rice grains harvested in the Vòng village, known for its fragrant and slightly chewy texture, often enjoyed as a seasonal autumn treat.", 10000)
  val banhTomTayHo = Cuisine("West Lake's fried shrimp cake", "West Lake's fried shrimp cake is a popular Vietnamese street food consisting of crispy shrimp fritters, often served with fresh herbs and a sweet and savory dipping sauce.", 30000)
  val kemTrangTien = Cuisine("Trang Tien ice-cream", "Trang Tien ice-cream is a historic ice cream parlor located in Hanoi, Vietnam, famous for its delectable ice cream and desserts, and it has been serving locals and tourists for decades.", 10000)
  val phoThinLoDuc = Cuisine("Lo Duc's Pho Thin", "Lo Duc's Pho Thin is a renowned Vietnamese restaurant located in Hanoi, celebrated for its delicious and flavorful bowls of phở, a classic Vietnamese noodle soup, often featuring tender slices of beef or chicken in a fragrant broth served with fresh herbs and condiments.", 70000)
  val bunChaSinhTu = Cuisine("Sinh Tu kebab rice noodles", "Sinh Tu kebab rice noodles is a popular Vietnamese dish, particularly associated with Hanoi, consisting of grilled pork patties and slices of pork belly served in a savory broth, typically accompanied by vermicelli noodles, fresh herbs, and dipping sauce.", 45000)
  val oMaiHongLam = Cuisine("Hong Lam salted dried fruit", "Hong Lam salted dried fruit refers to a brand of Vietnamese preserved fruits, often made from a variety of fruits such as plums, apricots, or kumquats, which are candied and flavored with a sweet and sour taste, commonly enjoyed as a snack or souvenir.", 20000)
  val chaoHenHaDong = Cuisine("Ha Dong's clam porridge", "Ha Dong's clam porridge is a specialty dish from Hà Đông, a district in Hanoi, Vietnam. It is a clam porridge that typically includes small clams, rice, and various herbs and spices, resulting in a flavorful and comforting rice congee with a unique seafood twist.", 30000)
  val banhKhucLangHoangMai = Cuisine("Hoang Mai village's cudweed cake", "Hoang Mai village's cudweed cake is a traditional Vietnamese dish, specifically associated with the Hoàng Mai village in Hanoi, consisting of glutinous rice dumplings filled with mung bean and pork, wrapped in banana leaves, and often served with a dipping sauce, creating a delicious and satisfying snack or meal.", 10000)
  val cheKhucBach = Cuisine("Khuc Bach sweet gruel", "Khuc Bach sweet gruel is a popular Vietnamese dessert, known for its sweet and creamy taste, made from glutinous rice balls, usually filled with mung bean paste, served in a sweet, fragrant, and rich coconut milk soup.", 20000)
  val cuisineVector = Vector(comLangVong, banhTomTayHo, kemTrangTien, phoThinLoDuc, bunChaSinhTu, oMaiHongLam, chaoHenHaDong, banhKhucLangHoangMai, cheKhucBach)

  CauGiay.addCuisine(comLangVong)
  TayHo.addCuisine(banhTomTayHo)
  HoanKiem.addCuisine(kemTrangTien)
  HoanKiem.addCuisine(phoThinLoDuc)
  DongDa.addCuisine(bunChaSinhTu)
  ThanhXuan.addCuisine(oMaiHongLam)
  HaDong.addCuisine(chaoHenHaDong)
  HoangMai.addCuisine(banhKhucLangHoangMai)
  HaiBaTrung.addCuisine(cheKhucBach)
  
  /**BASIC GAME INFO*/
  val player = Player(HoanKiem)
  val timeLimit = 400

  /** END GAME SCENARIOS */
  def isComplete =
    this.player.location == this.destination && this.player.hadEaten.size == cuisineVector.size

  def isOutOfMoney =
    QuocTuGiam.QIndex == 19 && this.player.currentMoney < cuisineVector.filter(!_.sold).map(_.price).sum

  def isOutOfStamina =
    this.player.currentStamina == 0 && this.player.checkInventory.size == 0

  def isOver = this.isComplete || this.player.hasQuit || isOutOfMoney || this.isOutOfStamina

  /**MESSAGES*/
  def welcomeMessage = "Welcome to Hanoi Adventure! Embark on a culinary journey through the vibrant streets of Hanoi, savoring local delights that will tantalize your taste buds. Test your knowledge with engaging quizzes about Hanoi's rich history and culture. Get ready for an immersive experience that blends gastronomic delights with intellectual challenges!"

  def goodbyeMessage =
    if this.isComplete then
      "Welcome home! The food was great isn't it. And the city was beautiful as well. After this experience, I hope you would visit Hanoi in the future. I meant Hanoi in real life. It is a wonderful city in a wonderful country!"
    else if isOutOfMoney then "You don't have enough money to enjoy all the cuisine of Hanoi. Better luck nextime."
    else if isOutOfStamina then "You don't have enough stamina to go around anymore. Better luck nexttime."
    else  // game over due to player quitting
      "Quitter!"

  /**PLAY TURN*/
  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    outcomeReport.getOrElse("We don't do that here!")

end Adventure

