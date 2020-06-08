(ns timely.core
  (:require [io.pedestal.http.route :as route]
            [timely.db.wrapper :as db]))


(def system-map {:database {:server-type :peer-server
                            :access-key "myaccesskey"
                            :secret "mysecret"
                            ;:endpoint "localhost:8998"
                            :validate-hostnames false
                            :db-uri "datomic:mem://hello"}})

(def conn (db/connect system-map))
(db/transact-schemas conn)

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
