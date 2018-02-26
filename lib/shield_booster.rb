# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class ShieldBooster
  
  attr_reader :boost
  attr_reader :uses
  
  def initialize(name, boost, uses)
    @name = name
    @boost = boost
    @uses = uses
  end
  
  def newCopy(s)
    return ShieldBooster.new(s.name, s.boost, s.uses);
  end
  
  def useIt()
    if uses > 0
      @uses -= 1
      @boost
    else
      1.0
    end
  end
  
  
  
end
