require_relative 'DamageToUI'

class Damage
  
  attr_getter :nShields
  attr_getter :nWeapons
  attr_getter :weapons
  
  def initialize(nshields, nweapons)
    if
    @nShields = nshields
    @nWeapons = nweapons
  end
  
  def newCopy(wl, s)
    @weapons = wl
    @nshields = s
  end
  
  private
    
  def adjust(w, s)
    #Toma como argumento dos arrays
    
  end
  
  public
  
  def discardWeapon(w)
    #Si borramos pero no está nos devuelve nil
    if defined? @weapons then
      @weapons.delete(w)
    elsif @nWeapons > 0
      @nWeapons -= 1
    end 
  end
  
  def UIVersion
    DamageToUI(self)
  end
  
end
