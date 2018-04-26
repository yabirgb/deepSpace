# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Deepspace
  module Transformation
    class Type
      def initialize(s)
        @name = s
      end
      
      def to_s
        "#{@name}"
      end
    end

    SPACECITY = Type.new("SPACECITY")
    GETEFFICIENT = Type.new("GETEFFICIENT") 
    NOTRANSFORM = Type.new("NOTRANSFORM")
  end
end
