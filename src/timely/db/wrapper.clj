(ns timely.db.wrapper
  (:require [datomic.api :as d]
            [timely.db.schema :as db-schema]))


(defn connect
  "Connects to database. Returns the connection"
  [{:keys [database]}]
  (let [uri (:db-uri database)]
    (d/create-database uri)
    (d/connect uri)))


(defn transact-schemas
  "Transacts schemas. Requires a connection"
  [conn]
  (d/transact conn db-schema/user-schema)
  (d/transact conn db-schema/work-period-schema))

(defn is-user-created?
  [conn {:keys [name password email]}]
  (let [result (d/q '[:find ?e
                      :in $ ?name ?password ?email
                      :where
                      [?e :user/name ?name]
                      [?e :user/password ?password]
                      [?e :user/email ?email]] (d/db conn) name password email)]
    (not (empty? result))))

(defn upsert-user
  "Inserts a single user in the database"
  [conn {:keys [name password email]}]
  (d/transact conn [{:user/name name
                     :user/password password
                     :user/email email}]))

(defn working-period-started?
  "Checks if a working period is currently started on for a given user
  and returns its uuid if so"
  [conn {:keys [user]}]
  (let [db (d/db conn)]))

(defn start-working-period
  "Creates a started working period for the given user"
  [conn {:keys [user timestamp]}]
  (d/transact conn {:tx-data [{:period/start timestamp
                               :period/user user}]}))

(defn end-working-period
  "Ends a given working period for the given user"
  [conn {:keys [user working-period timestamp]}]
  (d/transact conn {:tx-data [{:period/user user
                               :period/uuid working-period
                               :period/end timestamp}]}))
