(defproject tfe "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :java-target "1.7"
  :main tfe.main
  :aot :all
  :dependencies [[http-kit "2.1.18"]
                 [ring/ring-core "1.2.2"]
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [om "0.5.3"]
                 [org.clojure/core.async "0.1.278.0-76b25b-alpha"]]
  :plugins [[lein-cljsbuild "1.0.3"] [lein-haml-sass "0.2.7-SNAPSHOT"] [com.cemerick/clojurescript.test "0.2.3"]]
  :min-lein-version "2.0.0"
  :hooks [leiningen.cljsbuild leiningen.scss]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:output-to "resources/public/app/main/main.js"
                                   :output-dir "resources/public/app"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "test"
                        :source-paths ["src" "test"]
                        :notify-command ["phantomjs" :cljs.test/runner "target/unit-test.js"]
                        :compiler {:output-to "target/unit-test.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :scss {:src "resources/scss"
         :output-directory "resources/public/css"
         :output-extension "css"}
  :aliases {"test" ["do" "clean," "cljsbuild" "once" "test"]}
  :uberjar-name "tfe-standalone.jar")
