(defproject tfe "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
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
                                   :source-map true}}]}
  :scss {:src "resources/scss"
         :output-directory "resources/public/css"
         :output-extension "css"
         ;; Other options (provided are default values)
         ;; :auto-compile-delay 250
         ;; :delete-output-dir true ;; -> when running lein clean it will delete the output directory if it does not contain any file
         ;; :ignore-hooks [:clean :compile :deps] ;; -> if you use the hooks, this option allows you to remove some hooks that you don't want to run
         ;; :gem-version "3.2.1"
         }
  :uberjar-name "tfe-standalone.jar" 
  :profiles {:uberjar {:main tfe.main :aot :all}})
