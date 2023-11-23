package o1.adventure

object QuocTuGiam extends Area("Quoc Tu Giam", "You are in Quoc Tu Giam, the first university of Vietnam. Let's go answer some quizzes about the city of Hanoi!"):
  
  /**QUESTION LIST*/
  val Q1 = "What is the capital city of Vietnam?\nA. Ho Chi Minh City\nB. Hanoi\nC. Da Nang\nD. Hue"
  val Q2 = "Which river flows through Hanoi?\nA. Mekong River\nB. Red River\nC. Perfume River\nD. Han River"
  val Q3 = "What is the traditional headwear worn by many people in Hanoi?\nA. Baseball cap\nB. Conical hat (nón lá)\nC. Top hat\nD. Beret"
  val Q4 = "Which famous lake is often considered the 'heart' of Hanoi?\nA. West Lake (Hồ Tây)\nB. Hoan Kiem Lake\nC. Ba Be Lake\nD. Dong Mo Lake"
  val Q5 = "Which historical leader's mausoleum can be visited in Hanoi?\nA. Ho Chi Minh\nB. Emperor Ly Thai To\nC. General Vo Nguyen Giap\nD. President Tran Dai Quang"
  val Q6 = "What is the name of Hanoi's ancient quarter known for its narrow streets and old buildings?\nA. Old Quarter\nB. Old Town\nC. Ancient Quarter\nD. French Concession"
  val Q7 = "What traditional Vietnamese dish is commonly associated with Hanoi?\nA. Pho\nB. Sushi\nC. Dim Sum\nD. Pizza"
  val Q8 = "Hanoi's Temple of Literature is dedicated to what?\nA. Confucius\nB. Buddhism\nC. Taoism\nD. Christianity"
  val Q9 = "What is the name of the iconic bridge in Hanoi known for its red color and dragon-shaped design?\nA. Long Bien Bridge\nB. Thang Long Bridge\nC. Nhat Tan Bridge\nD. The Huc Bridge"
  val Q10 = "What is the approximate population of Hanoi?\nA. 100 million\nB. 30 million\nC. 8 million\nD. 1 million"
  val Q11 = "Which event was the historical One Pillar Pagoda constructed to commemorate?\nA. The founding of Hanoi\nB. The city's first lunar new year\nC. The birth of a king's son\nD. The dream of a king about a goddess"
  val Q12 = "What is the first university of Vietnam called?\nA. Oxford\nB. HUST\nC. Aalto University\nD. Quoc Tu Giam"
  val Q13 = "When was Hanoi founded?\nA. 2020\nB. 40 BC\nC. 4000\nD. 1010"
  val Q14 = "In which month is the traditional Vietnamese New Year, Tet, celebrated in Hanoi?\nA. January, some times December\nB. March, sometimes February\nC. February, sometimes January\nD. April"
  val Q15 = "Who founded Hanoi\nA. Ly Cong Uan (Ly Thai To)\nB. Napoleon Bonaparte\nC. Genghis Khan\nD. Abraham Lincoln"
  val Q16 = "Which district in Hanoi is famous for its pottery and ceramics?\nA. Dong Da\nB. Hoan Kiem\nC. Bat Trang\nD. Tay Ho"
  val Q17 = "What is the traditional Vietnamese hat that's often seen at Hanoi's markets?\nA. Cowboy hat\nB. Fedora\nC. Conical hat (nón lá)\nD. Beanie"
  val Q18 = "Hanoi is known for its vibrant street food culture. Which dish is commonly sold by street vendors in the city?\nA. Sushi rolls\nB. Banh Mi\nC. Tacos\nD. Hamburger"
  val Q19 = "What is the symbol of Hanoi?\nA. Mr. Beast\nB. Mount Everest\nC. Dragon\nD. Khue Van Cac"
  val Q20 = "What is the traditional Vietnamese style of painting called?\nA. Dong Ho painting\nB. Watercolor painting\nC. Lacquer painting\nD. Crayon painting"
  val lastSentence = "Hooray! You have answered all the questions. Go enjoy some cuisine and go home!"
  val QVector = Vector(Q1,  Q2,  Q3,  Q4,  Q5,  Q6,  Q7,  Q8,  Q9,  Q10, Q11, Q12, Q13, Q14, Q15, Q16, Q17, Q18, Q19, Q20, lastSentence)
  val AVector = Vector("B", "B", "B", "B", "A", "A", "A", "A", "D", "C", "D", "D", "D", "C", "A", "C", "C", "B", "D", "A","whatever")

  /**FORM A QUESTION -> ANSWER MAP*/
  def questionToAnswer =
    var result: Vector[Map[String, String]] = Vector()
    for number <- 1 to 21 do
      result = result ++ Vector(Map(QVector(number-1) -> AVector(number-1)))
    result

  /**FETCHING NEXT QUESTION*/
  var QIndex = 0
  def currentQuestion = QVector(QIndex)
  def nextQuestion() =
    if QIndex < 20 then
      QIndex += 1
