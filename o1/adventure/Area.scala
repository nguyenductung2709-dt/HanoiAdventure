package o1.adventure

import scala.collection.mutable.*
import scala.collection.mutable.Map

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an “area” can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Area(var name: String, var description: String):
  
  /**FLAGS*/
  var canEnterQuocTuGiam = false
  var canGoHome = false
  var canEnterCircleK = false

  /**COLLECTIONS*/
  private val neighbors = Map[String, Area]()
  val cuisineCollection = Map[String, Cuisine]()

  /**CUISINE METHOD*/
  def addCuisine(cuisine: Cuisine) = this.cuisineCollection += (cuisine.name -> cuisine)
  def contains(cuisineName: String) = this.cuisineCollection.contains(cuisineName)
  def removeCuisine(cuisineName: String): Option[Cuisine] = this.cuisineCollection.remove(cuisineName)

  /**NEIGHBOR*/
  def neighbor(direction: String) = this.neighbors.get(direction)
  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor
  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits

  /**DESCRIPTION*/
  def fullDescription =
    var cuisineList = "\n\nPopular local cuisine: " + "\n" + this.cuisineCollection.values.mkString("\n")
    val exitList = "\n\nNeighboring district(s) at: " + this.neighbors.keys.mkString(" ")
    var result = ""
    if cuisineCollection.nonEmpty then
      result = this.description + cuisineList + exitList
    else if this == QuocTuGiam then
      result = this.description
    else result = this.description + exitList
    result

  override def toString = this.name + ": " + this.description.replaceAll("\n", " ").take(150)

end Area

