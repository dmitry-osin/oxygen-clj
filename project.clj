(defproject blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.3"]
                 [ring/ring-core "1.15.2"]
                 [ring/ring-jetty-adapter "1.15.2"]
                 [ring/ring-devel "1.15.2"]
                 [compojure "1.7.2"]
                 [hiccup "2.0.0"]
                 [garden "1.3.10"]
                 [com.github.seancorfield/next.jdbc "1.3.1070"]
                 [com.taoensso/carmine "3.4.1"]
                 [org.postgresql/postgresql "42.7.8"]
                 [buddy/buddy-hashers "2.0.167"]]
  :ring {:handler blog.core/app
         :auto-reload? true
         :auto-refresh? true}
  :main ^:skip-aot blog.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repl-options {:init-ns blog.core})
