(ns blog.core
  (:require
   [blog.area.admin :as admin]
   [blog.area.main :refer [about contact login] :as main]
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [hiccup2.core :refer [html]]
   [ring.adapter.jetty :as ring]
   [ring.middleware.reload :refer [wrap-reload]])
  (:gen-class))

(defroutes app
  (GET "/" request (main/index request))
  (GET "/about" request (about request))
  (GET "/contact" request (contact request))
  (GET "/login" request (login request))
  (GET "/admin" request (admin/index request))
  (route/resources "/")
  (route/not-found (str (html [:h1 "Page not found"]))))

(defn- main
  [port-number]
  (ring/run-jetty app
                  {:port port-number :join? false}))

(defn- dev-main
  [port-number]
  (ring/run-jetty (wrap-reload #'app)
                  {:port port-number :join? false}))

(comment
  (def server (dev-main 3000))
  (.start server)
  (.stop server))
