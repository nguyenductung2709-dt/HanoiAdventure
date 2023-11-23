package o1.adventure

class Consumable(val name: String, val price: Int):
  val staminaIncreased = price * 1.0 / 1000

  override def toString = s"$name (+$staminaIncreased): ${price}VND"
end Consumable

