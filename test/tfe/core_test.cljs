(ns tfe.core-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]
            [tfe.core :refer [next-game place]]))

(deftest next-game-test
  (binding [place identity]
    (testing "collapse left"
      (is (= (next-game :left {:state :playing :board [[ 8   8 2 2]
                                                       [16   8 2 2]
                                                       [32  64 2 2]
                                                       [ 2 nil 2 2]]})
             {:state :playing :board [[16  4 nil nil]
                                      [16  8   4 nil]
                                      [32 64   4 nil]
                                      [ 4  2 nil nil]]})))
    (testing "losing"
      (is (= (next-game :left {:state :playing :board [[ 2 4 8 16]
                                                       [16 8 4  2]
                                                       [ 2 4 8 16]
                                                       [16 8 4  2]]})
             {:state :lost :board [[ 2 4 8 16]
                                   [16 8 4  2]
                                   [ 2 4 8 16]
                                   [16 8 4  2]]})))
    (testing "winning"
      (is (= (next-game :down {:state :playing :board [[nil nil nil 1024]
                                                       [nil nil nil 1024]
                                                       [nil nil nil  nil]
                                                       [nil nil nil  nil]]})
             {:state :won :board [[nil nil nil  nil]
                                  [nil nil nil  nil]
                                  [nil nil nil  nil]
                                  [nil nil nil 2048]]})))))
