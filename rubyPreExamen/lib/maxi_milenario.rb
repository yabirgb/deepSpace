# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Deepspace
  class MaxiMilenario < SpaceCity
    
    
    def fire
      f = super
      @collaborators.delete_at(0)
      return f
    end
    
  end
end
