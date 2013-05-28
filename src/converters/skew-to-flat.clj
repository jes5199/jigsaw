
; this will be the math used to take a photograph taken with a standard lens at an angle
; and transpose that data onto a flat surface

(defn transpose [{:x x :y y} {:tilt tilt :tilt-direction tilt-direction :altitude altitude :depth-of-field depth-of-field }]
  (let
    [
      {:dist dist :angle angle} (cartesian-to-polar {:x x :y y})
      effective-tilt (effective-angle tilt angle)
      proj-dist (projected-distance {:distance distance :tilt effective-tilt :altitude altitude :depth-of-field depth-of-field})
      result (polar-to-cartesian {:dist proj-dist :angle angle})
    ]
    result
  )
)

(defn untranspose [{:x x :y y} {:tilt tilt :tilt-direction tilt-direction :altitude altitude :depth-of-field depth-of-field }]
  (let
    [
      {:dist ground-dist :angle angle} (cartesian-to-polar {:x x :y y})
      effective-tilt (effective-angle tilt angle)
      unproj-dist (unprojected-distance {:distance ground-dist :tilt effective-tilt :altitude altitude :depth-of-field depth-of-field})
    ]

  )
)

(defn square [x]
  (* x x)
)

(defn pythag [a b]
  (sqrt (square a) (square b))
)

(defn cartesian-to-polar [{:x x :y y}]
  {
    :angle (atan2 y x)
    :dist  (pythag x y))
  }
)

(defn polar-to-cartesian [{:dist dist :angle angle}]
  {
    :x (* dist (cos angle))
    :y (* dist (sin angle))
  }
)

(defn effective-angle [tilt rotation]
  (let
    [
      x (* (cos tilt) (sin rotation))
      y (* (sin tilt) (sin rotation))
      z (cos rotation)
      w (pythag x z)
      angle (atan2 y w)
    ]
    angle
  )
)

(defn projected-distance [{:distance distance :tilt tilt :altitude altitude :depth-of-field depth-of-field}]
  (let
    [
      q (atan2 distance depth-of-field)
      p (- tilt q)

      a (* altitude (tan tilt))
      b (* altitude (tan p))

      d1 (- a b)
    ]
    d1
  )
)

(defn unprojected-distance [{:distance distance :tilt tilt :altitude altitude :depth-of-field depth-of-field}]
  (let
    [
      a (* altitude (tan tilt))
      b (- a distance)

      p (atan2 b altitude)
      q (- tilt p)

      d0 (* depth-of-field (tan q))
    ]
    d0
  )
)
