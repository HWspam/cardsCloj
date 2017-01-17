(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))

(defn create-deck []
  (set
    (for [suit suits
          rank ranks]
      {:suit suit
       :rank rank})))

(defn create-hand [deck]
  (set
    (for [a1 deck
          a2 (disj deck a1)
          a3 (disj deck a1 a2)
          a4 (disj deck a1 a2 a3)]
      #{a1 a2 a3 a4})))

;(defn flush? [hand]
 ; (= 1 (count (set (map :suit hand)))))

(defn straightflush? [hand]
 (= 1 (count (set (map and :suit hand :rank deck)))))

(defn straight? [hand]
 (= 1 (count (set (map :rank hand)))))

(defn fourofakind? [hand]
  (= 1 (count (set (map :rank hand)))))

(defn threeofakind? [hand]
  (thing [a b c d] hand) 
  toak [=a b c
        =a c d
        =a b d
        =b c d] thing)

(defn two-pair [hand]
  (thing2 [a b c d] hand)
  tp [=a b
      =a c
      =a d
      =b c
      =b d
      =c d] thing2)

(def test-hand 
  #{{:suit :clubs
     :rank 2}
    {:suit :hearts
     :rank 3} 
    {:suit :hearts
     :rank 4}
    {:suit :hearts
     :rank 10}})

(defn tests []
  (straightflush? test-hand))

(defn -main []
  (let [deck (create-deck)
        hand (create-hand deck)
        x (filter fourofakind? hand)]
    (println (count x))))
