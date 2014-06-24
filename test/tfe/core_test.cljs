(ns tfe.core-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)]
                   [cljs.core.match.macros :refer [match]])
  (:require [cemerick.cljs.test :as t]
            [cljs.core.match]
            [tfe.core :refer [next-game]]))

(defn empty-row []
  [nil nil nil nil])

(defn game [board]
  {:state :playing :board board})

(deftest next-game-test
  (testing "collapsing"
    (is (match [(next-game :left (game [(empty-row)
                                        (empty-row)
                                        (empty-row)
                                        [2 2 nil nil]]))]
               [{:board [_ _ _ [4 _ _ _]]}] true))
    (is (match [(next-game :left (game [(empty-row)
                                        (empty-row)
                                        (empty-row)
                                        [2 2 2 2]]))]
               [{:board [_ _ _ [4 4 _ _]]}] true))
    (is (match [(next-game :left (game [(empty-row)
                                        (empty-row)
                                        (empty-row)
                                        [4 2 2 2]]))]
               [{:board [_ _ _ [4 4 2 _]]}] true)))
  (testing "losing"
    (is (match [(next-game :right {:state :playing :board [[ 4  8 16 nil]
                                                           [16  8  4   2]
                                                           [ 2  4  8  16]
                                                           [16  8  4   2]]})]
               [{:state :lost :board [[ 2 4 8 16]
                                      [16 8 4  2]
                                      [ 2 4 8 16]
                                      [16 8 4  2]]}] true)))
  (testing "winning"
    (is (match [(next-game :down {:state :playing :board [[nil nil nil 1024]
                                                          [nil nil nil 1024]
                                                          [nil nil nil  nil]
                                                          [nil nil nil  nil]]})]
               [{:state :won :board [_ _ _ [_ _ _ 2048]]}] true))))

