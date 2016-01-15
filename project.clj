(defproject tfe "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :java-target "1.7"
  :main tfe.main
  :aot :all
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [http-kit "2.1.18"]
                 [ring/ring-core "1.2.2"]
                 [om "0.6.5"]
                 [org.clojure/core.match "0.2.1"]]
  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-haml-sass "0.2.7-SNAPSHOT"]
            [lein-figwheel "0.5.0-3"]
            [lein-doo "0.1.7-SNAPSHOT"]]
  :min-lein-version "2.0.0"
  :hooks [leiningen.cljsbuild leiningen.scss]

  :doo {:build "test"}
  :cljsbuild {:builds [{:id "dev"
                        :figwheel true
                        :source-paths ["src"]
                        :compiler {:output-to "resources/public/app/main/main.js"
                                   :output-dir "resources/public/app"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "test"
                        :source-paths ["src" "test"]
                        :compiler {:output-to "target/unit-test.js"
                                   :optimizations :simple
                                   :main          tfe.runner

                                   :pretty-print true}}]}
  :scss {:src "resources/scss"
         :output-directory "resources/public/css"
         :output-extension "css"}
  :aliases {"test" ["do" "doo" "phantom" "once"]}
  :uberjar-name "tfe-standalone.jar")
