(ns tfe.core)

(def app-state (atom {:state :playing
                      :board [[nil nil nil nil]
                              [nil nil nil   2]
                              [nil nil nil   2]
                              [nil nil nil nil]]}))

(defn next-board [direction board]
  (vec (apply map vector board)))

(defn next-state [board]
  :playing)
