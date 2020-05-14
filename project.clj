(defproject timely "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [io.pedestal/pedestal.service       "0.5.7"]
                 [io.pedestal/pedestal.jetty         "0.5.7"]
                 [io.pedestal/pedestal.route       "0.5.7"]
                 [io.pedestal/pedestal.log         "0.5.7"]
                 [com.datomic/client-pro "0.9.41"]]
  :repl-options {:init-ns timely.core}
  :user {:plugins [[venantius/ultra "0.6.0"]]})
