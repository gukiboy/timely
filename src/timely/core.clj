(ns timely.core
  (:require [io.pedestal.http.route :as route]))

(defn answer-hello [request]
  {:status 200
   :body "Hello World"})

(defn working! [request])

(defn not-working! [request])

(defn working? [request])

(def routes
  (route/expand-routes
    #{["/greet" :get answer-hello :route-name :greet]}))
