(ns blog.adapter.auth
  (:require
   [blog.domain.user :refer [find-user]]
   [buddy.hashers :as hashers]))

(defn validate-user [login password]
  (let [hashed-password (hashers/derive password)]
    (:role (find-user login hashed-password))))