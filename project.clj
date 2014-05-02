(defproject tfe "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [om "0.5.3"]
                 [org.clojure/core.async "0.1.278.0-76b25b-alpha"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :min-lein-version "2.0.0"
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:output-to "resources/public/app/main/main.js"
                                   :output-dir "resources/public/app"
                                   :optimizations :none
                                   :source-map true}}]})
