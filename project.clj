(defproject functional-di "0.1.0"
  :description "An example of using a composition root for dependency injection"
  :url         "https://github.com/kienstra/functional-di"
  :license     {:name "GPL-2.0-or-later"
                :url "https://www.gnu.org/licenses/old-licenses/gpl-2.0.txt"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :repl-options {:init-ns functional-di.core}
  :main ^:skip-aot functional-di.core
  :target-path "target/%s"
  :profiles    {:uberjar {:aot
                          :all
                          :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
                :dev     {:aot
                          :all
                          :dependencies [[criterium "0.4.6"]]
                          :plugins [[lein-cljfmt "0.9.2"]]}})
