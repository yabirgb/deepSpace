#encoding:utf-8

module Deepspace

# 2.3.17 - Translation from Java
# @author Profesor
    
class HangarToUI 
  attr_reader :maxElements, :weapons, :shieldBoosters
    
  def initialize(h) 
    @weapons=h.weapons
    @shieldBoosters=h.shieldBoosters
    @maxElements=h.maxElements
  end
  
  def to_s
    return "Max Elements: #{@maxElements}, Weapons: [#{@weapons.join(", ")}], Shields: [#{@shieldBoosters.join(", ")}]"
  end

end # class

end # module
