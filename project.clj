(defproject timely "0.1.0-SNAPSHOT"
  :description "Software for work time management"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [io.pedestal/pedestal.service "0.5.7"]
                 [io.pedestal/pedestal.jetty "0.5.7"]
                 [io.pedestal/pedestal.route "0.5.7"]
                 [io.pedestal/pedestal.log "0.5.7"]
                 [com.datomic/client-pro "0.9.41"]
                 [com.datomic/datomic-pro "0.9.6045"]
                 [clojure.java-time "0.3.2"]]
  :repl-options {:init-ns timely.core}
  :user {:plugins [[venantius/ultra "0.6.0"]]})
