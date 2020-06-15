(ns timely.core-test
  (:require [clojure.test :refer :all]
            [timely.core :refer :all]
            [io.pedestal.http.route :as route]
            [datomic.api :as d]
            [timely.db.wrapper :as db]))


(defn create-database
  "Creates a new database every time it is called and returns the connection"
  []
  (let [database-uri (doto (str "datomic:mem://" (d/squuid))
                       d/create-database)
        conn (d/connect database-uri)]
    (db/transact-schemas conn)
    conn))

(deftest test-routing
  (testing
    (prn (route/try-routing-for timely.core/routes :prefix-tree "/greet" :get))
    (is (not (nil? (route/try-routing-for timely.core/routes
                                          :prefix-tree "/greet" :get))))))

(deftest test-hello-world
  (testing
    (is (= {:body "Hello World"
            :code 200}) (answer-hello {}))))


