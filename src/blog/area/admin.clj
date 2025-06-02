(ns blog.area.admin
  (:require
   [hiccup2.core :refer [html]]))

(defn index [request] (str (html [:html
                                  [:head [:title "Administration"]]
                                  [:body [:h1 "Administration"]]])))