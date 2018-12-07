(ns adventofcode2018.day6)

(defn parse-point
  [s]
  (let [[x y] (map #(Integer/parseInt %)
                   (clojure.string/split s #", "))]
    [x y]))

(def input (map parse-point
                (-> (clojure.java.io/resource "day6.txt")
                    (slurp)
                    (clojure.string/split-lines))))

(defn find-min-max
  "Points that lie closest to or on the outer edge of the grid will
  have an infinite area"
  [coords]
  (reduce (fn [p1 p2]
            (let [[p-min p-max] p1 
                  [x-min y-min] p-min
                  [x-max y-max] p-max
                  [x2 y2] p2]
              [[(min x-min x2) (min y-min y2)]
               [(max x-max x2) (max y-max y2)]]))
          [[500 500] [0 0]] ; defaults that will be replaced
          coords))

(defn manhattan-distance
  [m p]
  (let [[mx my] m
        [px py] p]
    (+ (. Math abs (- mx px))
       (. Math abs (- my py)))))

(defn closest-points
  [w h points]
  (let [indexed-points (map-indexed vector points)]
    (for [x (range w)
          y (range h)]
      (apply min-key #(manhattan-distance [x y] (second %))
             indexed-points))))

(defn challenge-a
  [coords]
  (let [result (closest-points 400 400 coords)
        [[x-min y-min] [x-max y-max]] (find-min-max coords)]
        
    (->> (frequencies result)
         (filter (fn [[p _]]
                   (let [[_ [p-x p-y]] p]
                     (and (> x-max p-x x-min)
                          (> y-max p-y y-min)))))
         (apply max-key second))))

(defn challenge-b [])
