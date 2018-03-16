## Arrays

Borrar elementos

    irb(main):010:0> a = Array.new(['a', 'b','c'])
    => ["a", "b", "c"]
    irb(main):011:0> a.delete('a')
    => "a"
    irb(main):012:0> a
    => ["b", "c"]
    irb(main):013:0> a.delete('a')
    => nil
    irb(main):014:0> a
    => ["b", "c"]

Comprobar si un elemento está:

    >> ['Cat', 'Dog', 'Bird'].include? 'Dog'
    => true