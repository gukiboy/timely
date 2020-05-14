(ns timely.core
  (:require [io.pedestal.http.route :as route]
            [timely.db.config :as db]))

(defn answer-hello [request]
  {:status 200
   :body "Hello World"})

(defn working! [request])

(defn not-working! [request])

(defn working? [request])

(def system-map {:database {:server-type :peer-server
                            :access-key "myaccesskey"
                            :secret "mysecret"
                            :endpoint "localhost:8998"
                            :validate-hostnames false
                            :db-name "hello"}})

(def db-conn (db/connect (:database system-map)))

(def routes
  (route/expand-routes
    #{["/greet" :get answer-hello :route-name :greet]}))
