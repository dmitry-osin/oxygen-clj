(ns blog.middleware
  (:require
   [blog.adapter.auth :as auth]))

(defn wrap-auth [handler]
  (fn [request]
    (let [login (get-in request [:params :login])
          password (get-in request [:params :password])]
      (if (and login password)
        (let [role (auth/validate-user login password)]
          (if role
            (handler request)
            {:status 403 :body "Forbidden"}))
        {:status 401 :body "Unauthorized"}))))