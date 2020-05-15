(ns timely.core
  (:require [io.pedestal.http.route :as route]
            [timely.db.config :as db]
            [datomic.client.api :as d]
            [timely.db.schema :as db-schema]))


(def system-map {:database {:server-type :peer-server
                            :access-key "myaccesskey"
                            :secret "mysecret"
                            :endpoint "localhost:8998"
                            :validate-hostnames false
                            :db-name "hello"}})

(def db-conn (db/connect (:database system-map)))
(d/transact db-conn {:tx-data db-schema/user-schema})

(defn answer-hello [request]
  {:status 200
   :body "Hello World"})

(defn working!
  "Changes user to working state"
  [request])
(defn not-working!
  "Changes user to not working state"
  [request])
(defn working?
  "Checks if user is working"
  [request])

(def routes
  (route/expand-routes
    #{["/greet" :get answer-hello :route-name :greet]}))
