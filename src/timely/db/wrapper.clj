(ns timely.db.wrapper
  (:require [datomic.client.api :as d]))

(defn connect
  [config]
  (d/connect (d/client config) {:db-name (:db-name config)}))

(defn upsert-user
  "Inserts a single user in the database"
  [conn {:keys [name password email]}]
  (d/transact conn {:tx-data [{:user/name name
                               :user/password password
                               :user/email email}]}))
