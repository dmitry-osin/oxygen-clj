(ns blog.domain.user
  (:require [next.jdbc :as jdbc]
            [blog.adapter.db :refer [ds]])
  (:import [java.time OffsetDateTime]
           [java.util UUID]))

(defn find-user [login password]
  (jdbc/execute! ds ["SELECT * FROM users WHERE login = ? AND password = ?" login password]))

(defn find-user-by-login [login]
  (jdbc/execute! ds ["SELECT * FROM users WHERE login = ?" login]))

(defn create-user [user]
  (jdbc/with-transaction [tx ds]
                         (jdbc/execute! tx ["INSERT INTO users (login, name, email, password, bio, created_at, active)
                                            VALUES (?, ?, ?, ?, ?, ?, ?)
                                            RETURNING (id)"
                                            (:login user)
                                            (:name user)
                                            (:email user)
                                            (:password user)
                                            (:bio user)
                                            (OffsetDateTime/now)
                                            (:active user)])))

(defn update-user [user]
  (jdbc/with-transaction [tx ds]
                         (jdbc/execute! tx ["UPDATE users SET (name, email, password, bio, updated_at, active)
                                            = (?, ?, ?, ?, ?, ?) WHERE id = ?"
                                            (:name user)
                                            (:email user)
                                            (:password user)
                                            (:bio user)
                                            (OffsetDateTime/now)
                                            (:active user)
                                            (:id user)])))

(defn delete-user [^String id]
  (jdbc/with-transaction [tx ds]
                         (jdbc/execute! tx ["DELETE FROM users WHERE id = ? RETURNING *"
                                            (UUID/fromString id)])))