require_relative 'ShieldToUI'

module Deepspace
  class ShieldBooster

    attr_reader :name  #String
    attr_reader :boost #Float
    attr_reader :uses  #Int

    def initialize(name, boost, uses)
      @name = name
      @boost = boost
      @uses = uses
    end

    def self.newCopy(s)
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
    
    def to_s
      "name: #{@name} - boost: #{@boots} - uses: #{@uses}"
    end
    
    def getUIversion
      ShieldToUI.new(self)
    end

  end
end
