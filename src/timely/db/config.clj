(ns timely.db.config
  (:require [datomic.client.api :as d]))

(defn connect
  [config]
  (d/connect (d/client config) {:db-name (:db-name config)}))