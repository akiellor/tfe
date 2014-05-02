(ns tfe.core
  (:require-macros [cljs.core.async.macros :refer [go alt!]])
  (:require [goog.events :as events]
            [cljs.core.async :refer [put! <! >! chan timeout]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:board [[nil nil nil nil]
                              [nil nil nil   2]
                              [nil nil nil   2]
                              [nil nil nil nil]]}))

(defn cell [value]
  (dom/span #js {:className "cell"}  value))

(defn board-row [row]
  (apply dom/div #js {:className "row"} (map cell row)))

(defn board [app owner opts]
  (reify
    om/IRender
    (render [_]
      (apply dom/div #js {:className "board"}
        (map board-row (-> app :board))))))

(defn application [app owner]
  (om/build board app))

(om/root application app-state
  {:target (.getElementById js/document "app")})
