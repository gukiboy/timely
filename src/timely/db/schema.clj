(ns timely.db.schema)

(def user-schema [{:db/ident :user/uuid
                   :db/valueType :db.type/uuid
                   :db/cardinality :db.cardinality/one
                   :db/doc "UUID of the user"}

                  {:db/ident :user/name
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "Name of the user"}

                  {:db/ident :user/password
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "Hashed user password"}

                  {:db/ident :user/email
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "Email of the user"}])






