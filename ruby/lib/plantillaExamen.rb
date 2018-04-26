module Test
  class Examen
    
    def initialize(c)
      @counter = c
    end
    
    def self.newNoParams
      new(0)
    end
    
    def increment
      if @counter < 10
        @counter += 1
      end
    end
    
    def principal
      
    end
    
    
  end
  
  Examen.newNoParam.principal
end