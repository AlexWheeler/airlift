(defproject airlift "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.3"]
                 [org.clojure/core.async "0.2.395"]
                 [org.clojure/java.jdbc  "0.6.2-alpha3"]
                 [net.sourceforge.jtds/jtds  "1.2.4"]
                 [clj-ssh  "0.5.14"]
                 ]
  :main airlift.core
  :aot [airlift.core]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
