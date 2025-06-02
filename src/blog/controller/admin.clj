(ns blog.controller.admin
  "Controller for the administration area."
  (:require
   [hiccup2.core :refer [html]]))

(defn index [request] (str (html [:html
                                  [:head [:title "Administration"]]
                                  [:body [:h1 "Administration"]]])))