(ns tfe.main
  (:require [org.httpkit.server :refer [run-server]]
            [ring.middleware.resource :refer [wrap-resource]])
  (:gen-class :main true))

(defn request-logging [app]
  (fn [request]
    (let [params (select-keys request [:request-method :uri :content-type])
          message (clojure.string/join " " (map #(apply format "%s=%s" %) params))]
      (println message)
      (app request))))

(defn handler [request]
  {:status 200})

(def app
  (-> handler
      (wrap-resource "public")
      (request-logging)))

(defn -main [port]
  (run-server #'app {:port (java.lang.Integer/parseInt port)}))
