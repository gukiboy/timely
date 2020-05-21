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

(defn working-period-started?
  "Checks if a working period is going on for a given user"
  [db user])

(defn start-working-period
  "Creates a started working period for the given user"
  [conn {:keys [user timestamp]}])

(defn end-working-period
  "Ends a given working period for the given user"
  [conn {:keys [user working-period timestamp]}])
