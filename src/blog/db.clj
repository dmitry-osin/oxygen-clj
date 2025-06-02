(ns blog.db
  (:require [next.jdbc :as jdbc])
  (:require [clojure.java.io :as io]))

(def db {:dbtype "postgresql"
         :host   "localhost" :port "5432" :dbname "oxygen"
         :user   "oxygen" :password "oxygen"})
(def ds (jdbc/get-datasource db))

(defn- execute-sql-script [name]
  (->> (io/resource name)
       (slurp)
       (vector)
       (jdbc/execute-one! ds))
  )

(defn initialize []
  (try
    (execute-sql-script "db.sql")
    (execute-sql-script "init-data.sql")
    (catch Exception e
      (println "Error initializing database:" (.getMessage e)))
    ))

(comment
  (initialize)
  )